package com.xiaoyuan.controller.SubController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.*;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.*;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;
import com.xiaoyuan.model.setManageEntity.GpsSetEntity;
import com.xiaoyuan.service.*;
import com.xiaoyuan.tools.HttpRequestUtil;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 园区角色的请求接口
 */
@RestController
@RequestMapping("/sub/admin")
@Slf4j
public class SubAdminController extends BaseController {

    @Autowired
    private IErpSetManageService setManageService;

    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpAccountService accountService;
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpCarOrderService carOrderService;
    @Autowired
    private IErpTerminalCustomerService customerService;
    @Autowired
    private IErpGpsAlarmService gpsService;
    @Autowired
    private IErpDriverService driverService;

    /**
     * 获取首页信息
     * @return
     */
    @RequestMapping("/index")
    public MessageBean index(ErpPickGoodsTotalSearch search) {
        if(!resultByUserCate(3)){
            return resultFailed("您无权限执行该操作.");
        }
        //设置条件
        search.setStatus(1);
        //设置只统计销售单
        search.setOrderCate(1);
        //获取订单统计持
        ErpPickgoodsTotalDto totalDto = pickgoodsService.countPickgoodsAccountTotal(search);
        //创建充值记录条件搜索类
        ErpAccountSearch accountSearch = new ErpAccountSearch(search);
        //设置充值记录搜索条件
        accountSearch.setAccountCate(1);
        //获取总金额。
        totalDto.setAccountAmountMoney(accountService.sumAccountPriceBySearch(accountSearch));
        return resultSuccess(totalDto);
    }

    /**
     * 获取用户列表信息。
     * @param search
     * @return
     */
    @RequestMapping("/userList")
    public MessageBean userList(ErpUserSearch search) {
        if(!resultByUserCate(3)){
            return resultFailed("您无权限执行该操作.");
        }
        search.setUserId(resultUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("countNum",userService.countBySearch(search));
        map.put("data",userService.selectBySearchOfPage(search));
        return resultSuccess(map);
    }
    /**
     * 充值统计
     * @param search
     * @return
     */
    @RequestMapping("/accountCount")
    public MessageBean accountCount(ErpAccountSearch search) {
        if(!resultByUserCate(3)){
            return resultFailed("您无权限执行该操作.");
        }
        Map<String,Object> map  = new HashMap<>();
        //设置充值记录类型为充值类型，即金额大于0
        map.put("data",accountService.selectBySearchOfPage(search));
        map.put("total",accountService.sumAccountPriceBySearch(search));
        map.put("countNum",accountService.countBySearch(search));
        return resultSuccess(map);
    }



    /**
     * 修改销货订单内存储的终端用户
     * @return
     */
    @RequestMapping("/updateOrderCustormer")
    public MessageBean updateOrderCustormer(Integer pickgoodsId,Integer custormerId) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String time = sdf.format(new Date());
            if(!resultByUserCate(3)){
                return resultFailed("该功能只对园区用户开放.");
            }else if(null == pickgoodsId || pickgoodsId<=0){
                return resultFailed("请输入有效的订单");
            }else if(null == custormerId || custormerId<=0){
                return resultFailed("请选择有效的终端用户");
            }
            ErpPickGoodsSearch search = new ErpPickGoodsSearch();
            search.setOrderId(pickgoodsId);
            ErpPickgoodsDto pickgoods = pickgoodsService.selectFirstOrderBySearch(search);
            if(!pickgoods.getOrder_status().equals(0) || pickgoods.getNetweight() == null){
                return resultFailed("只有已完成状态下才能使用.");
            }else if(custormerId.equals(pickgoods.getCustomer_id())){
                return resultFailed("当前订单的终端用户与正在修改的终端用户为同一终端用户");
            }
            //查询是否是该订单下的终端用户。
            ErpTerminalCustomer customer = customerService.selectFirstBySearch(
                    new ErpTerminalCustomerSearch(pickgoods.getUser_id(),custormerId));
            if (null == customer){
                return resultFailed("请选择有效的终端用户.");
            }
            pickgoods.setUpdatetime(time);
            pickgoods.setCustomer_name(customer.getCustomer_name());
            pickgoods.setCustomer_id(custormerId);
            pickgoods.setUser_address(customer.getTerminal_customer_address());
            if(pickgoodsService.updateByPrimaryKeySelective(pickgoods)<=0){
                log.info("订单信息修改终端用户信息失败");
                throw new RuntimeException();
            }
            ErpDriverDto driverDto = driverService.selectFirstBySearch(new ErpDriverSearch(pickgoods.getDriver_id()));
            Map<String,Object> map = new HashMap<>();
            map.put("code",pickgoods.getCar_order_sn());
            sendMsg("SMS_170555200",driverDto.getUserPhone(),map);
            return resultSuccess("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return resultFailed("操作失败");
        }
    }

    /**
     * gps告警列表
     * @return
     */
    @RequestMapping("/gpsList")
    public MessageBean gpsList(ErpGpsAlarmSearch search) {
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }
        Map<String,Object> map = new HashMap<>();
        List<ErpGpsAlarmDto> list = gpsService.selectBySearchOfPage(search);
        map.put("data",list);
        map.put("countNum",gpsService.countBySearch(search));
        return resultSuccess(map);
    }

    /**
     * gps告警详情
     * @return
     */
    @RequestMapping("/gpsDetail")
    public MessageBean gpsDetail(ErpGpsAlarmSearch search) {
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }
        return resultSuccess(gpsService.selectFirstBySearch(search));
    }

    /**
     * gps告警处理
     * @return
     */
    @RequestMapping("/gpsHandling")
    public MessageBean gpsHandling(Integer gpsId,String handling) {
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }else if(StringUtils.isBlank(handling)||handling.length() < 3){
            return resultFailed("请用文本描述人工处理结果,并且不得少于3个字");
        }else if(null == gpsId || gpsId <=0
                || null == gpsService.selectFirstBySearch(new ErpGpsAlarmSearch(gpsId))){
            return resultFailed("请选择有效的gps告警数据。");
        }
        ErpGpsAlarm dto = new ErpGpsAlarm();
        dto.setId(gpsId);
        dto.setStatus(1);
        dto.setGps_handling(handling);
        dto.setHandling_man(resultUser().getReal_name());
        dto.setHandling_time(TimeUtils.resultCurrentDate());
        return resultByCount(gpsService.updateByPrimaryKeySelective(dto));
    }
    /**
     * 从前端接收数据并进行保存数据
     * @return
     */
    @RequestMapping("/setManage")
    public MessageBean setManage(CommonSetEntity entity) {
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }
        //通过getcommonSet获取通用管理设置信息对象。
        ErpSetManage manage = setManageService.getCommonSet();
        //数据存在则是修改操作，不存在则新增操作
        if(null == manage){
            manage = new ErpSetManage(entity,resultUser().getUser_name());
        }else if(null!=manage.getId()){
            CommonSetEntity update =  JSON.parseObject(manage.getValue(),CommonSetEntity.class);
            if(null!=entity.getMinNum()){
                update.setMinNum(entity.getMinNum());
            }else if(null!=entity.getEveryLimitNum()){
                update.setEveryLimitNum(entity.getEveryLimitNum());
            }else if(null!= entity.getOrderTime()){
                update.setOrderTime(entity.getOrderTime());
            }else if(!StringUtils.isBlank(entity.getLimitStartDay())&&!StringUtils.isBlank(entity.getLimitEndDay())){
                update.setLimitStartDay(entity.getLimitStartDay());
                update.setLimitEndDay(entity.getLimitEndDay());
            }else{
                throw resultLogicException("请输入有效的设置值.");
            }
            manage = manage.setValue(update);
        }

        try {
            setManageVoid(manage);
            if((null!=entity.getEveryLimitNum()||null!=entity.getMinNum())
                    &&userService.updateServiceByPrimaryKey(
                    new ErpUser(entity.getEveryLimitNum(),
                            entity.getMinNum()).setUpdateParams(null)) <= 0){
                throw new LogicException();
            }
            //只有设置了限量时间才会发送短信
            ErpUserSearch userSearch = new ErpUserSearch();
            userSearch.setUserCate(1);
            List<ErpUserDto> list = userService.selectBySearch(userSearch);
            Iterator<ErpUserDto> iterator = list.iterator();
            while (iterator.hasNext()) {
                ErpUserDto user = iterator.next();
                Map<String, Object> map = new HashMap<>();
                map.put("code", user.getLimit_max());
                //判断是否是设置每天限量还是设置了限制时间
                if (null != entity.getEveryLimitNum()) {
                    sendMsg("SMS_170555217", user.getUser_phone(), map);
                    continue;
                } else if (!StringUtils.isBlank(entity.getLimitStartDay()) && !StringUtils.isBlank(entity.getLimitEndDay())) {
                    map.put("A", entity.getLimitStartDay());
                    map.put("B", entity.getLimitEndDay());
                    sendMsg("SMS_170555214", user.getUser_phone(), map);
                    continue;
                }
                break;
            }
            return resultSuccess();
        }catch (Exception e){
            rollBack();
            throw e;
        }
    }

    /**
     *  通过service层内的函数getcommonSet获取gps管理设置信息对象。
     * @return
     */
    @RequestMapping("/getManage")
    public MessageBean setManage(){
        if(!resultByUserCate(3)
        ){
            return resultFailed("该功能只对园区用户开放.");
        }
        return resultSuccess(resultJsonObj(CommonSetEntity.SET_VALUE_KEY));
    }

    /**
     *  通过service层内的函数getcommonSet获取通用管理设置信息对象。
     * @return
     */
    @RequestMapping("/getGpsManage")
    public MessageBean getGpsManage(){
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = resultJsonObj(GpsSetEntity.SET_VALUE_KEY);
        map.put("data",jsonObject);
        ErpUserSearch search = new ErpUserSearch();
        search.setUserCate(3);
        map.put("arr",userService.selectBySearch(search));
        return resultSuccess(map);
    }

    /**
     * 从前端接收数据并进行保存数据
     * @return
     */
    @RequestMapping("/setGpsManage")
    public MessageBean setGpsManage(GpsSetEntity entity) {
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }
        //通过getcommonSet获取通用管理设置信息对象。
        ErpSetManage manage = setManageService.getCommonSet(GpsSetEntity.SET_VALUE_KEY);
        //数据存在则是修改操作，不存在则新增操作
        if(null == manage){
            manage = new ErpSetManage(entity,resultUser().getUser_name());
        }else if(null!=manage.getId()){
            manage = manage.setValue(entity);
        }
        setManageVoid(manage);

        return resultSuccess();
    }


    /**
     * 通过关键字查询设置信息
     * @param keyName
     * @return
     */
    public JSONObject resultJsonObj(String keyName){
        ErpSetManage manage = setManageService.getCommonSet(keyName);
        JSONObject entity = null;
        if(null != manage){
            entity = JSON.parseObject(manage.getValue());
        }
        return entity;
    }


    public void setManageVoid(ErpSetManage manage){
        //数据存在则是修改操作，不存在则新增操作
        if(manage == null){
            throw new LogicException();
        } else if(null == manage.getId()&&setManageService.insertService(manage)<=0){
            throw new LogicException();
        }else if(null!= manage.getId()&&setManageService.updateByPrimaryKeySelective(manage)<=0){
            throw new LogicException();
        }
    }

    /**
     * 返回模拟gps历史数据
     * @param lng
     * @param lat
     * @return
     */
    public Map<String,Object> resultData(double lng,double lat){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = sdf.format(new Date());
        Map<String,Object> data = new HashMap<>();
        data.put("time",time);
        data.put("province","省");
        data.put("city","市");
        data.put("district","区");
        data.put("address","地址");
        data.put("speed","速度");
        data.put("lng",lng);
        data.put("lat",lat);
        return data;
    }


    /**
     * gps定位当前gpsid所代表的实时地理位置
     * @return
     */
    @RequestMapping("/gpsNow")
    public MessageBean gpsNow(ErpGpsAlarmSearch search) {
        if(StringUtils.isBlank(search.getGps_sn())){
            return resultFailed("设备主键不可为空");
        }
        return resultSuccess(HttpRequestUtil.resultGpsVal(search.getGps_sn()));
    }

    /**
     * gps定位当前gpsid所代表的实时地理位置
     * @return
     */
    @RequestMapping("/gpsHistory")
    public MessageBean gpsHistory(ErpGpsAlarmSearch search){
        if(StringUtils.isBlank(search.getGps_sn())){
            return resultFailed("设备主键不可为空");
        }else if(StringUtils.isBlank(search.getStartTime())||StringUtils.isBlank(search.getEndTime())){
            return resultFailed("请选择有效的开始时间与结束时间");
        }
        return resultSuccess(HttpRequestUtil.resultGpsVal(HttpRequestUtil.REC_CODE_HISTORY,search.getGps_sn(),search.getStartTime(),search.getEndTime()));
    }







}
