package com.xiaoyuan.controller.WebController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpTerminalCustomerSearch;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.model.search.UniqueSearch;
import com.xiaoyuan.service.*;
import com.xiaoyuan.tools.FormatUtil;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.PasswrodsUtil;
import com.xiaoyuan.tools.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * WEB端后台数据提交
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Transactional
public class WebAdminController extends BaseController {
    @Autowired
    IErpAccountService accountService;
    @Autowired
    IErpUserService userService;
    @Autowired
    IErpCardService cardService;
    @Autowired
    IErpDriverService driverService;
    @Autowired
    IErpGpsService gpsService;
    @Autowired
    IErpCarService carService;
    @Autowired
    IErpTerminalCustomerService customerService;
    @Autowired
    IErpPickgoodsService pickgoodsService;

    /**
     * 后台充值功能
     * @param bean
     * @return
     */
    @RequestMapping("/addAccount")
    public MessageBean addAccount(ErpAccountDto bean) {
        if(!resultByUserCate(3)){
            return resultFailed("您无权限操作该功能.");
        }else if(null == bean||null==bean.getUser_id()||bean.getUser_id()<=0){
            return resultFailed("请选择正确的用户.");
        }else if(ACCOUNT_TYPE_ARRAY.indexOf(bean.getAccountType()) < 0){
            return resultFailed("请选择正确的记录类型.");
        }else if((bean.getAccountType() == 1 ||bean.getAccountType()  == 0||bean.getAccountType()  == 2)&& bean.getAccount_price()<= 0){
            return resultFailed("请输入正确的充值金额.");
        }else if(bean.getAccountType() == 4 && bean.getAccount_price()>= 0){
            return resultFailed("请输入正确的退款金额.");
        }
        try {
            String time = resultCurrentDate();
            bean.setAccount_date(time);
            bean.setCreatetime(time);
            bean.setAccount_time(time);
            bean.setStatus_(1);
            bean.setAudit_man(resultUser().getReal_name());
            if(accountService.insertService(bean)<0){
                throw resultLogicException("充值失败,请稍后重试.");
            }
            ErpUser user = userService.selectByUserId(bean.getUser_id());
            if(userService.update(user.addBalance(bean.getAccount_price().doubleValue())) <=0){
                throw resultLogicException("充值失败,请稍后重试.");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("code",bean.getAccount_price());
            sendMsg("SMS_170560171",user.getUser_phone(),map);
            return resultSuccess("充值成功");
        }catch (LogicException e){
            //操作失败时的异常回滚
            rollBack();
            return resultFailed(e.getMsg());
        }
    }

    /**
     * 删除卡片
     * @return
     */
    @RequestMapping("/cardDelete")
    public MessageBean addAccount(Integer id) {
        return resultByCount(cardService.delete(id));
    }
    /**
     * 新增/修改卡片信息。
     * @return
     */
    @RequestMapping("/cardOperaction")
    public MessageBean addAccount(ErpUserDto dto) {
        if(null==dto.getDriverId()||dto.getDriverId()<=0){
            return resultFailed("请选择有效的车辆.");
        }else if(StringUtils.isBlank(dto.getCard_rfid())){
            return resultFailed("请输入有效的RFID号.");
        }else if(null != driverService.getDriverById(dto.getId())){
            return resultFailed("请选择正确的车辆");
        }
        ErpCard card = new ErpCard(dto);
        int count;
        if(null == card.getId()){
            count=  cardService.insertCardService(card);
        }else{
            count=  cardService.updateCardService(card);
        }
        return resultByCount(count);
    }

    /**
     * gps设备删除功能
     * @return
     */
    @RequestMapping("/gpsDelete")
    public MessageBean gpsDelete(Integer id) {
        return resultByCount(gpsService.delete(id));
    }

    /**
     * gps设备新增/修改
     * @return
     */
    @RequestMapping("/gpsOperaction")
    public MessageBean gpsOperaction(ErpUserDto dto) {
        if(StringUtils.isBlank(dto.getGps_sn())){
            return resultFailed("请输入有效的GPS ID号.");
        }else if(StringUtils.isBlank(dto.getGps_imei())){
            return resultFailed("请输入有效的GPS IMEI号.");
        }
        ErpGPS gps = new ErpGPS(dto);
        try{
            int count;
            if(gps.getId()!=null){
                count=  gpsService.update(gps);
            }else{
                count=  gpsService.insert(gps);
            }
            if(count < 0 ||userService.update(new ErpUser().setIdResultThis(dto.getId()).setRealNameResultThis(dto.getReal_name())) < 0){
                throw new LogicException().setMsgResultThis("系统繁忙,请稍后重试");
            }
            return resultByCount(count);
        }catch (Exception e){
            rollBack();
            if(e instanceof LogicException){
                return resultFailed(((LogicException) e).getMsg());
            }
            return resultFailed();
        }
    }

    /**
     * 资料修改
     * @return
     */
    @RequestMapping("/updateUser")
    public MessageBean updateUser(ErpUserDto user) {
        try {
            if(user.getId()== null){
                return resultFailed("请选择有效用户");
            }
            ErpUserDto result = userService.selectFiistBySearch(new ErpUserSearch().setId(user.getId()));
            if(result== null){
                return resultFailed("请选择有效用户");
            }else if (FormatUtil.notIsPhone(user.getUser_phone())){
                return resultFailed("请输入有效的手机号");
            }
            ErpUser update = new ErpUser(user);
            userService.uniqueNumber(new UniqueSearch(update.getNumber(),update.getId(),null));
            boolean isDriverRole = user.getUser_cate() == 4;
           if(isDriverRole){
               //判断前端传递过来的手机号与数据库的作对比，判断是否修改手机号，是则重置密码并发送短信
                if (FormatUtil.notIsPhone(user.getCar_contact_phone())){
                   return resultFailed("请输入有效的车主手机号");
                }else if(!result.getUser_phone().equals( user.getUser_phone())){
                    update.setPassword_(PasswrodsUtil.resultNewPassword(PasswrodsUtil.charArray()));
                }
                //司机角色的资料修改,首先修改车辆信息
                ErpDriver driver = new ErpDriver(user);
                ErpGPS gps = new ErpGPS(user);
                ErpCar car = new ErpCar(user);
                ErpCard card = new ErpCard(user);
                if(driverService.updateServiceByPrimaryKey(driver) <= 0){
                    throw resultLogicException();
                }else if(null != gps.getId()&&gpsService.updateServiceByPrimaryKey(gps) <= 0){
                    throw resultLogicException();
                }else if(null == gps.getId()&&gpsService.insertService(gps)<=0){
                    throw resultLogicException();
                }else if(null!=car.getId()&&carService.updateServiceByPrimaryKey(car)<=0){
                    throw resultLogicException();
                }else if(null==car.getId()&&carService.insertService(car)<=0){
                    throw resultLogicException();
                }else if(null!=card.getId()&& cardService.updateCardService(card)<=0){
                    throw resultLogicException();
                }else if(null == card.getId() && cardService.insertCardService(card)<=0){
                    throw resultLogicException();
                }
            }

            //用户名不允许修改
            update.setUser_name(null);
            if(FormatUtil.notIsNumber(update.getNumber())){
                throw resultLogicException("请输入正确的编号");
            }else if(userService.updateServiceByPrimaryKey(update)<=0){
                throw resultLogicException();
            }
            return resultSuccess("用户资料修改成功.");
        }catch (Exception e){
            String msg = "用户资料修改失败,请稍后重试.";
            rollBack();
            if(e instanceof LogicException){
                msg = ((LogicException) e).getMsg();
            }
            e.printStackTrace();
            return resultFailed(msg);
        }
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/deleteUser")
    public MessageBean deleteUser(Integer userId) {
        if(userId== null){
            return resultFailed("请选择有效用户");
        }
        ErpUserDto result = userService.selectFiistBySearch(new ErpUserSearch().setId(userId));
        if(result== null){
            return resultFailed("请选择有效用户");
        }
        ErpUser user = new ErpUser().setIdResultThis(userId).userStopIsNow();
        user.setUser_phone("");
        return resultByCount(userService.updateServiceByPrimaryKey(user));
    }

    /**
     * 终端用户资料修改/新增
     * @return
     */
    @RequestMapping("/customerOperaction")
    public MessageBean customerOperaction(ErpCustomerDto customer) {
        try {
            if (customer.getUser_id() == null) {
                return resultFailed("请选择有效用户");
            }
            ErpUserDto result = userService.selectFiistBySearch(new ErpUserSearch().setId(customer.getUser_id()));
            if (result == null) {
                return resultFailed("请选择有效用户");
            }else if(customer.getId()==null){
                customer.setCreatetime(TimeUtils.resultCurrentDate());
            }
            customer.setCustomer_company_name(result.getUser_company_name());
            customer.setCustomer_company_sn(result.getUser_company_sn());
            customer.setCustomer_sales_area(result.getSales_area());
            userService.uniqueNumber(new UniqueSearch(customer.getCustomer_sn(),null,customer.getId()));
            if(customer.getId()!=null &&customerService.updateServiceByPrimaryKey(customer)<=0){
                throw resultLogicException();
            }else if(customer.getId()==null &&customerService.insertService(customer)<=0){
                throw resultLogicException();
            }
            return resultSuccess("操作成功.");
        }catch (Exception e){
            String msg = "操作失败,请稍后重试.";
            rollBack();
            if(e instanceof LogicException){
                msg = ((LogicException) e).getMsg();
            }
            e.printStackTrace();
            return resultFailed(msg);
        }
    }
    /**
     * 删除终端用户
     * @return
     */
    @RequestMapping("/deleteCustomer")
    public MessageBean deleteCustomer(Integer customerId) {
        if(customerId== null){
            return resultFailed("请选择有效的终端用户");
        }
        ErpCustomerDto result = customerService.selectFirstBySearch(new ErpTerminalCustomerSearch(null,customerId));
        if(result== null){
            return resultFailed("请选择有效的终端用户");
        }
        //设置伪删除
        result.setStop(1);
        return resultByCount(customerService.updateServiceByPrimaryKey(result));
    }

    /**
     * 审核用户
     * @return
     */
    @RequestMapping("/auditUsers")
    public MessageBean auditUsers(ErpUserDto params) {
        //检验数据是否合理
        if(!resultByUserCate(3)){
            return resultFailed("您无权限操作该功能");
        }else if(null==params.getId()){
            return resultFailed("请选择正确的用户");
        }else if(new ArrayList<Integer>(Arrays.asList(1,-1)).indexOf(params.getAudit()) < 0){
            return resultFailed("请选择正确审核状态");
        }else if(params.getAudit() == -1){
            //审核失败则删除用户。
            if(userService.delete(params.getId()) <= 0){
                throw resultLogicException();
            }else if(params.getUser_cate() == 4&&driverService.delete(params.getDriverId())<=0){
                throw resultLogicException();
            }
            sendMsg("SMS_162635031",params.getUser_phone(),null);
            return resultSuccess();
        }
        ErpUser update = new ErpUser().setAuditResultThis(params.getAudit())
                .setIdResultThis(params.getId());
        //判断用户是否存在
        ErpUser user = userService.selectByUserId(params.getId());
        if(null==user){
            return resultFailed("请选择有效的用户");
        }else if(user.getAudit()<0){
            return resultFailed("该用户的审核请求已拒绝");
        }else if(user.getStop()!= null){
            return resultFailed("该用户已被冻结.");
        }else if(user.getUser_cate() == 1&&StringUtils.isBlank(params.getSales_area())){
            return resultFailed("客户的销售区域不允许为空.");
        }else if(user.getUser_cate() == 1&&params.getAudit() == 1){
            update.setSales_area(params.getSales_area());
        }
        try{
            if(userService.updateServiceByPrimaryKey(update) <= 0 ){
                throw resultLogicException();
            }
            //TODO:发送审核通过或拒绝的短信
            sendMsg("SMS_162635028",update.getUser_phone(),null);
            return resultSuccess();
        }catch (Exception e){
            String msg = null;
            if(e instanceof LogicException){
                msg = ((LogicException) e).getMsg();
            }
            return resultFailed(msg);
        }
    }

    @RequestMapping("/removeDriver")
    public MessageBean removeDriver(Integer driverId) {
        if(null == driverService.getDriverById(driverId)){
           return resultFailed("找不到该驾驶员信息");
        }else if(CollectionUtils.isNotEmpty(pickgoodsService.selectOrderBySearch(new ErpPickGoodsSearch().setDriverIdResult(driverId)))){
            return resultFailed("该车辆有关联的订单，移除后会影响关联订单,无法移除");
        }
        driverService.removeDriver(driverId);
        return resultSuccess();
    }


}
