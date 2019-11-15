package com.xiaoyuan.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;
import com.xiaoyuan.model.setManageEntity.GpsSetEntity;
import com.xiaoyuan.service.*;
import com.xiaoyuan.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MultithreadScheduleTask {

    public static String BAIDU_JS_PAI_URL = "http://api.map.baidu.com/geoconv/v1/?coords=%s&from=1&to=5&ak=3mn83YpfI9VdPSZg1DfuK7lg0UqRljwt";

    @Autowired
    IErpPickgoodsService pickgoodsService;
    @Autowired
    IErpSetManageService setManageService;
    @Autowired
    IErpAccountService accountService;
    @Autowired
    IErpGpsAlarmService gpsAlarmService;
    @Autowired
    IErpGpsService gpsService;
    @Autowired
    IErpUserService userService;
    @Autowired
    MessageService messageService;

    /**
     * 定时计划，每隔5分钟检查订单状态是否超时并取消订单
     * 暂时每隔一分钟查询数据库判断订单是否存在超时现象
     * 正式环境时则每隔5分钟查询
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 5 * 60 * 1000 )  //间隔5分钟
    public void checkedPickGoods() {
        try {
            CommonSetEntity setEntity = setManageService.getCommonEntitySet();
            ErpPickGoodsSearch search = new ErpPickGoodsSearch();
            search.setOrderCate(1);
            search.setStatus(0);
            List<ErpPickgoodsDto> list = pickgoodsService.selectOrderBySearch(search);
            if (list.size() <= 0) {
                log.info("数据库中再无满足条件的订单，因此终止操作.日志时间为[%s].".replace("%s", TimeUtils.resultCurrentDate(new Date())));
                return;
            }
            //将所有过期订单进行迭代并修改状态
            List<Integer> ids = new ArrayList<>();
            for (ErpPickgoodsDto order : list) {
                //根据发货时间获取发货时间当天最后一刻
                Date endTime = TimeUtils.parseCurrentString(TimeUtils.default_format,order.getSendTime().concat(" 23:59:59"));
                endTime = new Date(endTime.getTime() + (long) (setEntity.getOrderTime() * 60 * 60) * 1000 );
                //判断当前时间是否在该范围内，是则放过。
                if(TimeUtils.isEffectiveDate(new Date(),
                        TimeUtils.parseCurrentString(TimeUtils.default_format,
                                order.getPickgoods_date()),endTime)){
                    String msg = "订单号["+order.getCar_order_sn()+
                            "];未超时;发货日期["+order.getSendTime()+"];超过["
                            +TimeUtils.resultCurrentDate(endTime)+"]则取消订单。";
                    log.info(msg);
                    continue;
                }else if(order.isQueueConfirm()||order.getConfirm_queue_device() != 0){
                    log.info("订单号["+order.getCar_order_sn()+"]的司机已进场，无法取消");
                    continue;
                }
                Integer userId = order.getUser_id();
                ErpUserDto user = userService.selectFiistBySearch(new ErpUserSearch().setId(userId));
                ids.add(order.getId());
                //计算需要返还的差价
                BigDecimal total = new BigDecimal(order.getIngoods_totalprice());
                ErpUser update = new ErpUser().setIdResultThis(userId);
                update.setRebate_balance(user.getRebate_balance().add(total).doubleValue());
                ErpAccount account = new ErpAccount(
                        userId,
                         "系统", "取消订单号为[%s]的订单,"
                        .replace("%s", order.getCar_order_sn())
                        .concat("退返货款共计:%s元".replace("%s",
                                total.toPlainString())), total, ErpAccount.ACCOUNT_TYPE_ORDER_QUIT
                );
                if (userService.updateServiceByPrimaryKey(update) <= 0) {
                    throw new LogicException("用户名为[%s]".replace("%s", user.getUser_name()).concat("的用户取消订单后，返回订单退款时发生异常。"));
                } else if (accountService.insertService(account) <= 0) {
                    throw new LogicException("用户名为[%s]".replace("%s", user.getUser_name()).concat("的用户取消订单后，添加退款记录是发生异常。"));
                }
                Map<String, Object> map = new HashMap<>();
                map.put("code", order.getCar_order_sn());
                messageService.Send(new Message("SMS_170555198", order.getDriver_phone(), map));
                messageService.Send(new Message("SMS_170555198", order.getSend_phone(), map));
            }
            if (ids.size() > 0) {
                ErpPickgoods order = new ErpPickgoods();
                order.setOrder_status(-1);
                pickgoodsService.updateByIds(order, ids);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 定时计划，每隔3分钟检查订单在终端客户范围内
     */
    @Async
    @Scheduled(fixedDelay = 3 * 60 * 1000 )  //间隔5分钟
    public void gps() {
        //获取gps管理设置信息。
        ErpSetManage set = setManageService.getCommonSet(GpsSetEntity.SET_VALUE_KEY);

        ErpPickGoodsSearch search = new ErpPickGoodsSearch();
        search.setStatus(1);
        List<ErpPickgoodsDto> list = pickgoodsService.selectOrderBySearch(search);
        Iterator<ErpPickgoodsDto> iterator = list.iterator();
        try {
            GpsSetEntity setEntity = JSON.parseObject(set.getValue(),GpsSetEntity.class);
            while (iterator.hasNext()){
                ErpPickgoodsDto order = iterator.next();
                ErpGPS gps = gpsService.resultGpsSnByDriverId(order.getDriver_id());
                //获取首次进入终端客户坐标范围时间
                Date inCustomerTime = TimeUtils.parseCurrentString(order.getIn_customer_time());
                //出产时间
                Date outTime = TimeUtils.parseCurrentString(order.getSeconddate());
                //先查找该订单今天是否已创建过告警信息，创建过则跳过。
                if(inCustomerTime!= null
                        &&!TimeUtils.isEffectiveDate(inCustomerTime
                        ,new Date(inCustomerTime.getTime() + (30*60)*1000))){
                    continue;
                }else if(gpsAlarmService.selectBySearch(new ErpGpsAlarmSearch(TimeUtils.resultCurrentDate("yyyy-MM-dd",new Date()),order.getId())).size()>0){
                    log.info("订单编号为[%s]的订单今天已创建过告警信息".replace("%s",order.getCar_order_sn()));
                    continue;
                }else if(null == gps||StringUtils.isBlank(gps.getGps_sn())){
                    log.info("订单编号为[%s]的订单送货司机并无GPS设备编号，请检验无误后再进行该操作".replace("%s",order.getCar_order_sn()));
                    continue;
                }else if(StringUtils.isBlank(order.getCoordinate())){
                    log.info("终端客户地址的所属坐标为空,无法操作");
                    continue;
                }
                //终端客户地址
                Point startPoint = new Point(order.getCoordinate().split(",")[0],order.getCoordinate().split(",")[1]);
                //获取司机当前位置
                JSONObject result = HttpRequestUtil.resultGpsVal(gps.getGps_sn());
                if(!"0".equals(result.getString("res_num"))){
                    log.info(result.get("res_desc").toString());
                    continue;
                }
                //拼接获取到的wgs84坐标
                String c = result.getDoubleValue("res_lng") + "," + result.getDoubleValue("res_lat");
                //请求百度地图api进行对坐标系的装换
                result = JSON.parseObject(HttpRequestUtil.doGet(BAIDU_JS_PAI_URL.replace("%s",c)));
                //判断是否请求成功并返回数据
                if(0 != result.getInteger("status")){
                    log.info("请求百度地图api转换坐标异常");
                    continue;
                }
                //获取坐标并保存到result对象中
                result = (JSONObject) result.getJSONArray("result").get(0);
                ErpGpsAlarm gpsAlarm = null;
                Point endPoint = new Point(result.getDoubleValue("x"),result.getDoubleValue("y"));
                //当司机与终端客户两点之间的距离小于100时，且之前并无记录进入该范围，则对订单内的进入终端客户范围的时间插入到订单内
                if(inCustomerTime == null&& BaiduLocationUtils.getDistance(startPoint,endPoint) < setEntity.getGpsSpace()){
                    ErpPickgoods update = new ErpPickgoods();
                    update.setId(order.getId());
                    update.setIn_customer_time(TimeUtils.resultCurrentDate());
                    pickgoodsService.updateByPrimaryKeySelective(update);
                    continue;
                }else
                    //司机未进入范围内并且出产时间超过48小时则创建告警信息并且发送短信告知设置内的园区用户
                    if(inCustomerTime == null&&!TimeUtils.isEffectiveDate(outTime,new Date(outTime.getTime()+((48 * 60 * 60)*1000)))){
                        gpsAlarm = new ErpGpsAlarm(order).setGps_sn(gps.getGps_sn()).setGps_imei(gps.getGps_imei());
                    }else
                        //假若司机曾经进入终端客户所在范围内，并且不到半小时后走出该范围，则创建卸货过短的告警信息
                        if (inCustomerTime != null&&BaiduLocationUtils.getDistance(startPoint,endPoint) > setEntity.getGpsSpace()){
                            gpsAlarm = new ErpGpsAlarm(order).setGps_sn(gps.getGps_sn()).setGps_imei(gps.getGps_imei()).setGps_alarm_cate(1);
                        }
                //短信发送
                if(gpsAlarm != null){
                    gpsAlarmService.insertSelective(gpsAlarm);
                    if(null==setEntity.getCheckedIds()||setEntity.getCheckedIds().size()<=0){
                        continue;
                    }
                    String template = "SMS_170560220";
                    if(null == gpsAlarm.getGps_alarm_cate()){
                        template = "SMS_170555222";
                    }
                    //TODO: 短信发送
                    List<ErpUser> users =  userService.resultUsersByUserIds(setEntity.getCheckedIds());
                    Iterator<ErpUser> userIterator = users.iterator();
                    while (userIterator.hasNext()){
                        ErpUser user = userIterator.next();
                        Map<String,Object> map = new HashMap<>();
                        map.put("A",order.getCar_order_sn());
                        map.put("B",order.getPickgoods_car_sn());
                        messageService.Send(new Message(template,user.getUser_phone(),map));
                    }
                }else if(gpsAlarm != null){
                    log.info("gps告警插入失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
