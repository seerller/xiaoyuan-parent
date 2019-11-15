package com.xiaoyuan.controller.SubController;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.dto.RoleDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.*;
import com.xiaoyuan.service.*;
import com.xiaoyuan.service.PermissionSevice.PermissionService;
import com.xiaoyuan.service.PermissionSevice.RoleService;
import com.xiaoyuan.tools.FormatUtil;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.PasswrodsUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 所有角色的通用接口
 */
@RestController
@RequestMapping("/sub/common")
public class SubCommonController extends BaseController {
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpTerminalCustomerService customerService;
    @Autowired
    private IErpAccountService accountService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpPoundService poundService;
    @Autowired
    private IErpCarService carService;
    @Autowired
    private IErpGoodsService goodsService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    /**
     * 返回权限树
     * @return
     */
    @RequestMapping("/resultPermission")
    public MessageBean resultPermission(){
        //判断是否是管理员账号,是则返回拥有所有权限。
        return resultSuccess();
    }
    /**
     * 返回权限列表
     * @return
     */
    @RequestMapping("/resultPermissionList")
    public MessageBean resultPermissionList(Integer roleId){
        return resultSuccess();
    }
    /**
     * 修改/新增角色权限
     * @return
     */
    @RequestMapping("/rolePermission")
    public MessageBean rolePermission(RoleDto role){
        List<Integer> list= new ArrayList<>(Arrays.asList(1,3,4));
        if(null==role.getUser_cate() || list.indexOf(role.getUser_cate()) < 0){
            throw new LogicException("请选择有效的角色分类.");
        }if(role.getId()==null&&roleService.insertService(role)<=0){
            return resultFailed();
        }else if(role.getId()!=null&&roleService.updateService(role)<=0){
            return resultFailed();
        }
        roleService.reomvePermission(role.getId());
        roleService.insertRolePermission(role);
        return resultSuccess();
    }
    /**
     * 删除角色
     * @return
     */
    @RequestMapping("/removeRole")
    public MessageBean rolePermission(Integer roleId){
        if(null==roleId || roleId<=0 ){
            return  resultFailed();
        }
        roleService.reomvePermission(roleId);
        roleService.deleteById(roleId);
        return resultSuccess();
    }

    /**
     * 查询物料信息
     * @param search
     * @return
     */
    @RequestMapping("/queryGoods")
    public MessageBean queryGoods(ErpGoodsSearch search){
        return resultSuccess(goodsService.selectBySearch(search));
    }

    /**
     * 公众号接口：订单查询
     * @param search
     * @return
     */
    @RequestMapping("/queryOrder")
    public MessageBean queryOrder(ErpPickGoodsSearch search){
        //从会话管理器中取出登录时存入会话管理器的用户信息
        ErpUserDto user = resultUser();
        //判断用户角色信息并将相应信息存入search搜索类中
        if(resultByUserCate(1)){
            //客户角色
            search.setUserId(user.getId());
        }else if(resultByUserCate(4)){
            ErpDriverSearch driverSearch = new ErpDriverSearch();
            driverSearch.setUserId(resultUserId());
            //司机角色
            search.setDriverId(driverService.selectFirstBySearch(driverSearch).getDriverId());
        }else if(!resultByUserCate(3)){
            //判断不是客户、司机以及园区用户，则返回您无权限操作该功能。
            return resultFailed("您无权限操作该功能");
        }
        Map<String,Object> map  = new HashMap<>();
        map.put("list",pickgoodsService.selectOrderBySearchOfPage(search));
        //返回已完成，未完成与已取消的三个统计数量对象
        map.put("amount",pickgoodsService.countOrderAmount(search));
        //总记录数
        map.put("countNum",pickgoodsService.countBySearch(search));
        return resultSuccess(map);
    }

    /**
     * 公众号接口：订单详情查询
     * @param search
     * @return
     */
    @RequestMapping("/queryOrderDetail")
    public MessageBean queryOrderDetail(ErpPickGoodsSearch search){
        if(null==search.getOrderId()){
            return resultFailed("请求失败.");
        }
        //从会话管理器中取出登录时存入会话管理器的用户信息
        ErpUserDto user = resultUser();
        //判断用户角色信息并将相应信息存入search搜索类中
        if(resultByUserCate(1) ){
            //客户角色
            search.setUserId(user.getId());
        }else if(resultByUserCate(4)){
            //司机角色
            search.setPhone(user.getUser_phone());
        }
        ErpPickgoodsDto pickgoods = pickgoodsService.selectFirstOrderBySearch(search);
        //根据订单编号查询过磅信息。
        pickgoods.setPound(poundService.selectByOrderSn(pickgoods.getCar_order_sn()));
        return resultSuccess(pickgoods);
    }

    /**
     * 公众号接口：用户个人资料详情查询
     * @return
     */
    @RequestMapping("/userDetail")
    public MessageBean userDetail(Integer userId){
        if(!resultByUserCate(3)){
            userId = resultUser().getId();
        }else if(null == userId || userId <=0){
            userId = resultUser().getId();
        }
        ErpUserDto user = userService.selectFiistBySearch(new ErpUserSearch().setId(userId));
        user.setCar(carService.getIdByUserId(userId));
        return resultSuccess(user);
    }
    /**
     * 公众号接口：用户个人资料手机号修改，该功能只能修改
     * @return
     */
    @RequestMapping("/userUpdatePhone")
    public MessageBean userUpdate(String code,String phone){
        //从会话管理器中取出登录时存入会话管理器的用户信息
        ErpUserDto user = resultUser();
        if(resultByUserCate(4) ){
            return resultFailed("司机不可修改手机号");
        } else if (FormatUtil.notIsPhone(phone)) {
            return resultFailed("请输入有效的手机号");
            //判断是否是客户角色，是则检验验证码，园区角色修改用户手机号不需要验证码
        }else if(resultByUserCate(1)) {
            checkCode(PHONE_CODE,code);
        }

        if(userService.selectUserPnoneIsOnly(user.getId(),phone) > 0){
            return resultFailed("该手机号码已存在");
        }else if(user.getUser_phone().equals(phone)){
            return resultFailed("用户手机号与现在输入的一致");
        }
        ErpUser update = new ErpUser();
        update.setId(user.getId());
        update.setUser_phone(phone);
        int count = userService.updateServiceByPrimaryKey(update);
        if(count >= 0) {
            //更新用户手机并存入session中
            user.setUser_phone(phone);
            setSessionAttr("user",user);
            return resultSuccess("手机号修改成功");
        }
        return resultFailed("手机号修改失败");
    }

    /**
     * 公众号接口：密码修改接口
     * @return
     */
    @RequestMapping("/userUpdatePassword")
    public MessageBean userUpdatePassword(String code,String password,String confirmPassword){
        //从会话管理器中取出登录时存入会话管理器的用户信息
        if(StringUtils.isBlank(password)){
            return resultFailed("请输入正确的密码");
        }else if(StringUtils.isBlank(confirmPassword)){
            return resultFailed("请输入正确的确认密码");
        }else if(password.length() < 8 ){
            return resultFailed("新密码的长度不可小于八位.");
        }else if(confirmPassword.length() < 8 ){
            return resultFailed("确认密码的长度不可小于八位.");
        }else if(!password.equals(confirmPassword)){
            return resultFailed("两次输入的密码不一致");
        }
        checkCode(PASSWORD_CODE,code);
        //获取旧密码的加密密匙对新密码进行加密
        ErpUser update = new ErpUser();
        update.setId(resultUserId());
        update.setPassword_(PasswrodsUtil.resultNewPassword(password));
        int count = userService.updateServiceByPrimaryKey(update);
        if(count >= 0) {
            SecurityUtils.getSubject().logout();
            return resultSuccess("密码修改成功,请重新登录.");
        }
        return resultFailed("密码修改失败");
    }

    /**
     * 根据类型发送短信验证码
     * @param type
     * @return
     */
    @RequestMapping("/getCode")
    public MessageBean getCode(int type){
        //1是修改密码，2是修改手机号
        try {
            String key;
            if(type == 1){
                key = PASSWORD_CODE;
            }else if(type == 2){
                key = PHONE_CODE;
                if(resultByUserCate(3)){
                    return resultFailed("园区用户手机号时无需验证码");
                }else if(resultByUserCate(4)){
                    return resultFailed("司机用户手机无法修改。");
                }
            }else{
                return resultFailed("参数类型不符要求");
            }
            sessionFindCode(key);
            Integer code  = sendMsg();
            if(code <=0){
                return resultFailed("消息发送失败");
            }
            JSONObject json = new JSONObject();
            json.put("code",code);
            json.put("time",resultCurrentTime());
            resultSession().setAttribute(key, json);
            //设置定时器进行定时删除
            timer(resultSession(),key);
            return resultSuccess();
        }catch (Exception e){
            String msg = null;
            if(e instanceof LogicException){
                msg = ((LogicException) e).getMsg();
            }
            return resultFailed(msg);
        }
    }

    /**
     * 公众号：账户明细
     * @param search
     * @return
     */
    @RequestMapping("/accountDetail")
    public MessageBean accountDetail(ErpAccountSearch search){
        Map<String,Object> map = new HashMap<>();
        Integer userId = null;
        if(resultByUserCate(1)){
            search.setUserId(resultUserId());
            userId = resultUserId();
        }else if(!resultByUserCate(3)){
            return resultFailed("您无权限操作该功能");
        }else if(search.isOnly()){
            if(null==search.getUserId()){
                return resultFailed("请选择正确的客户.");
            }
            userId = search.getUserId();
        }
        //且返回该用户的支出与入账总数
        map.put("total",accountService.countInAndOutTotal(search));
        //判断是否是园区角色操作，是则根据用户主键查询用户并返回剩余余额。
        ErpUserDto user = userService.selectFiistBySearch(new ErpUserSearch().setId(userId));
        map.put("data",accountService.selectBySearchOfPage(search));
        //总记录数
        map.put("countNum",accountService.countBySearch(search));
        //若是用户进入该方法则是查询用户本身的余额，若是园区则查询search内的用户id
        map.put("rebate_balance",user.getRebate_balance());
        return resultSuccess(map);
    }

    /**
     * 公众号接口：返回终端用户
     * 根据登录账户的角色id判断是否有权限获取数据
     * 若登录账户角色为用户则从会话管理器中获取用户主键
     * 若登录账号角色为园区则根据search的中的用户id进行查询并返回数据
     * @param search
     * @return
     */
    @RequestMapping("/queryCustomer")
    public MessageBean queryCustomer(ErpTerminalCustomerSearch search){
        if(resultByUserCate(1)){
            search.setUserId(resultUser().getId());
        }else if(!resultByUserCate(3)) {
            return resultFailed("您无权限操作该功能.");
        }else if(search.isOnly()){
            if(null==search.getUserId()||search.getUserId()<=0){
                return resultFailed("请传入有效的参数.");
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",customerService.selectBySearchOfPage(search));
        map.put("countNum",customerService.countBySearch(search));
        return resultSuccess(map);
    }

    /**
     * 公众号接口：返回车辆信息
     * @param search
     * @return
     */
    @RequestMapping("/queryDriver")
    public MessageBean queryDriver(ErpDriverSearch search){
        if(!resultByUserCate(4)){
            Map<String,Object> map = new HashMap<>();
            map.put("data",driverService.selectBySearchOfPage(search));
            map.put("countNum",driverService.countBySearch(search));
            return resultSuccess(map);
        }else{
            return resultFailed("您无权限操作该功能.");
        }
    }

}
