package com.xiaoyuan.controller.SubController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.*;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.*;
import com.xiaoyuan.service.*;
import com.xiaoyuan.tools.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户角色的请求接口
 */
@Slf4j
@RestController
@RequestMapping("/sub/user")
public class SubUserController extends BaseController {
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpTerminalCustomerService terminalCustomerService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpGoodsService goodsService;
    @Autowired
    private IErpAccountService accountService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpSetManageService setManageService;



    /**
     * 公众号：数据统计接口
     * 该接口只开放给客户角色
     * @param search
     * @return
     */
    @RequestMapping("/orderCount")
    public MessageBean orderCount(ErpPickGoodsTotalSearch search){
        if(!resultByUserCate(1)){
            return resultFailed("您的无权限操作该功能");
        }
        search.setOrderCate(1);
        search.setUserId(resultUser().getId());
        Map<String,Object> map = new HashMap<>();
        map.put("total",pickgoodsService.countPickgoodsAccountTotal(search));
        map.put("customer",terminalCustomerService.selectBySearch(new ErpTerminalCustomerSearch(resultUserId())));
        return resultSuccess(map);
    }

    /**
     * 通过该接口获取销货商品信息
     * @return
     */
    @RequestMapping("/pickgoodsInit")
    public MessageBean pickgoodsInit(Integer id){
        Map<String,Object> map = new HashMap<>();
        ErpGoods goods = goodsService.findPickGoods();
        if(null == goods){
            return resultFailed("系统繁忙,请稍后重试.");
        }else if(null!= id&&id >0){
            ErpPickgoodsDto order = pickgoodsService.selectFirstOrderBySearch(new ErpPickGoodsSearch().setId(id));
            ErpUserSearch search = new ErpUserSearch();
            ErpUserDto user = userService.selectFiistBySearch(search);
            if(null==user){
                return resultFailed("根据订单找不到司机信息");
            }
            ErpDriverDto driver = driverService.selectFirstBySearch(new ErpDriverSearch().setUserId(user.getId()));
            ErpTerminalCustomerSearch customerSearch = new ErpTerminalCustomerSearch();
            customerSearch.setCustomerId(order.getCustomer_id());
            ErpTerminalCustomer customer = terminalCustomerService.selectFirstBySearch(customerSearch);
            ErpPickOrderDto dto = new ErpPickOrderDto(order,customer,driver);
            map.put("data",dto);
        }
        ErpUserDto user = userService.selectFiistBySearch(new ErpUserSearch().setId(resultUserId()));
        goods.setGoods_price(user.getUser_price().doubleValue());
        map.put("goods",goods);
        return resultSuccess(map);
    }

    /**
     * 通过该接口进行下单
     * @return
     */
    @RequestMapping("/orderOperaction")
    @Transactional(rollbackFor = Exception.class)
    public MessageBean orderAdd(ErpPickOrderDto order){
        ErpUserDto user = userService.selectFiistBySearch(new ErpUserSearch().setId(resultUserId()));
        if(!resultByUserCate(1)){
            return resultFailed("本接口只对用户角色开放.");
        }else if(user.getRebate_balance().compareTo(new BigDecimal(0)) == -1 ){
            return resultFailed("余额不足.");
        }
        user.setPassword_(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        setSetManageLimit(user,pickgoodsService.sumCurrentPickGoodsNum(resultUserId()),order.getBuyNum());
        //查询司机车辆信息是否存在
        ErpDriverDto driver = driverService.selectFirstBySearch(new ErpDriverSearch(order.getDriverId()));
        if(null == driver){
            return resultFailed("请选择有效的司机");
        }
        ErpTerminalCustomerSearch customerSearch = new ErpTerminalCustomerSearch();
        customerSearch.setCustomerId(order.getCustomerId());
        customerSearch.setUserId(resultUserId());
        ErpCustomerDto customer = terminalCustomerService.selectFirstBySearch(customerSearch);
        if(null == customer||!resultUserId().equals(customer.getUser_id())){
            return resultFailed("请选择有效的终端用户");
        }
       if(null!=order.getPickgoodsId()&&order.getPickgoodsId()>0){
           //订单修改操作，修改订单时，只修改订单车辆信息与订单终端用户，因此不变动。
           ErpPickgoodsDto oldOrder =pickgoodsService.selectFirstOrderBySearch(new ErpPickGoodsSearch().setId(order.getPickgoodsId()));
           if(null==  oldOrder){
               return resultFailed("找不到该订单信息。");
           }else if(!resultUserId().equals(oldOrder.getUser_id())){
               return resultFailed("请选择有效并正确的订单信息");
           }else if( oldOrder.getConfirm_queue_device() !=0||oldOrder.getIs_confirm_queue() != 0){
               return resultFailed("该订单车辆已入厂,无法进行修改.");
           }
            try {
                //订单修改操作
                ErpPickgoods pickOrder = new ErpPickgoods(order.getPickgoodsId(),time,driver.getCarSn(),driver.getUserPhone(),
                        driver.getUserCompanyName(),driver.getDriverId()
                        ,customer.getId() ,customer.getCustomer_name()
                        ,customer.getTerminal_customer_address());
                if(pickgoodsService.updateByPrimaryKeySelective(pickOrder)<=0){
                    log.info("客户订单修改失败。");
                    throw new LogicException();
                }
                return resultSuccess("操作成功。");
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return resultFailed("操作失败。");
            }
        }
        //订单新增操作
        ErpGoods goods = goodsService.findPickGoods();
        if(null==goods||!goods.isGoods_type()){
            return resultFailed("请输入有效的商品信息");
        }
        //设置挂牌价
        goods.setGoods_price(resultUser().getUser_price().doubleValue());
        double num = Math.ceil(order.getBuyNum().doubleValue() / order.getEveryNum().doubleValue());
         //计算合理性
        if(num != order.getDriverTotal()){
            return resultFailed("请输入的合理运送次数与每次运输的数量");
        }else if(resultUser().getRebate_balance().compareTo(new BigDecimal(0)) == -1){
            return resultFailed("余额不足。");
        }else if(driver.getCarLoad().compareTo(order.getEveryNum()) == -1){
            return resultFailed("每车载送数量大于车辆载重.");
        }
        //设置创建时间
        try {
                BigDecimal buyNum = order.getBuyNum();
                BigDecimal total = new BigDecimal(0.00);
                while (buyNum.doubleValue() > 0){
                    BigDecimal orderNum;
                    if(buyNum.subtract(order.getEveryNum()).doubleValue() >= 0.00){
                        orderNum = order.getEveryNum();
                    }else{
                        orderNum = buyNum;
                    }
                    buyNum = buyNum.subtract(order.getEveryNum());
                    BigDecimal totalPrice = orderNum.multiply(new BigDecimal(goods.getGoods_price()));
                    total = total.add(totalPrice);
                    ErpPickGoodsSearch search = new ErpPickGoodsSearch();
                    String carOrderSn;
                    //确保订单唯一性。
                    do{
                        carOrderSn = order_sn(resultUserId().toString());
                        search.setOrderSn(carOrderSn);
                    }while (pickgoodsService.selectOrderBySearch(search).size()>0);
                    ErpPickgoods pickOrder = new ErpPickgoods(
                            resultUserId(),carOrderSn,orderNum,resultUser().getReal_name(),order.getSendDay(),
                            driver.getCarSn(),driver.getUserCompanyName(),goods.getGoods_sn(),goods.getGoods_spec(),
                            goods.getGoods_name(),customer.getTerminal_customer_address(),new BigDecimal(goods.getGoods_price()),totalPrice,
                            customer.getCustomer_name(),driver.getUserPhone(),customer.getId(),driver.getDriverId());
                    pickOrder.setCar_order_sn(carOrderSn);
                        pickOrder.setCreatetime(time);
                        pickOrder.setDriver_id(driver.getDriverId());
                    pickOrder.setRegion(customer);
                    ErpAccount account = new ErpAccount(
                            resultUserId(),"系统","下单操作扣除%s元"
                            .replace("%s",totalPrice.toPlainString())
                            .concat("，订单单号为[%s]").replace("%s",carOrderSn)
                            ,totalPrice.multiply(new BigDecimal(-1 )),ErpAccount.ACCOUNT_TYPE_LISTING_DEDUCTION
                    );
                    if(!(accountService.insertService(account)>0)){
                        log.info("插入消费数据失败");
                        throw new LogicException();
                    }
                    pickOrder.setAccountId(account.getId());
                    if(pickgoodsService.insertByPickgoods(pickOrder) <0){
                        log.info("订单插入数据库时插入失败，抛出错误并回滚");
                        throw new LogicException();
                    }
                    Map<String,Object> map = new HashMap<>();
                    map.put("code",carOrderSn);
                    sendMsg("SMS_170560180",driver.getUserPhone(),map);
            }
            ErpUser update = new ErpUser().setIdResultThis(resultUserId());
            BigDecimal rebateBalance = user.getRebate_balance().subtract(total);
            update.setRebate_balance(rebateBalance.doubleValue());
            if(!(userService.updateServiceByPrimaryKey(update)> 0)){
                log.info("减免用户余额失败");
                throw new LogicException();
            }else if(rebateBalance.doubleValue() < 20000){
                Map<String,Object> map = new HashMap<>();
                map.put("code",rebateBalance);
                sendMsg("SMS_170560177",user.getUser_phone(),map);
            }
            user.setRebate_balance(rebateBalance);
            setSessionAttr("user",user);
            return resultSuccess("操作成功。");
        }catch (Exception e){
            //异常回滚。
            rollBack();
            throw e;
        }
    }
    /**
     * 取消订单
     * @return
     */
    @RequestMapping("/orderCancel")
    @Transactional(rollbackFor = Exception.class)
    public MessageBean orderCancel(Integer pickgoodsId){
        try {
        ErpUserDto user = resultUser();
        if(!resultByUserCate(1)){
            return resultFailed("您无权限操作该功能");
        }else if(null == pickgoodsId || pickgoodsId <=0){
            return resultFailed("请传入有效的请求参数");
        }
        ErpPickgoodsDto pickgoods = pickgoodsService.selectFirstOrderBySearch(new ErpPickGoodsSearch().setId(pickgoodsId));
        if(!pickgoods.getUser_id().equals(user.getId())){
            return resultFailed("该订单不属于你名下,您无权限操作该订单.");
        }else if(pickgoods.isQueueConfirm()||pickgoods.getConfirm_queue_device() != 0){
            return resultFailed("订单车辆已排队进厂,无法取消订单.");
        }
        pickgoods.setOrder_status(-1);
        int count = pickgoodsService.updateByPrimaryKeySelective(pickgoods);
        if(count <= 0 ){
            throw resultLogicException();
        }
            BigDecimal total = new BigDecimal(pickgoods.getIngoods_totalprice());
            ErpUser update = new ErpUser().setIdResultThis(resultUserId());
            update.setRebate_balance(user.getRebate_balance().add(total).doubleValue());
            ErpAccount account = new ErpAccount(
                    resultUserId(),"系统","取消订单号为[%s]的订单,".replace("%s",pickgoods.getCar_order_sn()).concat("退返货款共计:%s元".replace("%s",total.toPlainString())),total,ErpAccount.ACCOUNT_TYPE_ORDER_QUIT
            );
            if(!(userService.updateServiceByPrimaryKey(update)> 0)){
                throw resultLogicException();
            }else if(!(accountService.insertService(account)>0)){
                throw resultLogicException();
            }
            ErpDriverDto driverDto = driverService.selectFirstBySearch(new ErpDriverSearch(pickgoods.getDriver_id()));
            Map<String,Object> map = new HashMap<>();
            map.put("code",pickgoods.getCar_order_sn());
            sendMsg("SMS_170560183",driverDto.getUserPhone(),map);
            setSessionAttr("user",user);
            return resultSuccess("订单取消成功");
        }catch (Exception e){
            //异常回滚。
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if(!(e instanceof RuntimeException)){
                e.printStackTrace();
            }
            throw e;
        }

    }

}
