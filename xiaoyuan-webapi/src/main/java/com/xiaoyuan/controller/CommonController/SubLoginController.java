package com.xiaoyuan.controller.CommonController;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpRegionSearch;
import com.xiaoyuan.model.search.RoleSearch;
import com.xiaoyuan.service.*;
import com.xiaoyuan.service.PermissionSevice.RoleService;
import com.xiaoyuan.tools.FormatUtil;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.PasswrodsUtil;
import com.xiaoyuan.tools.TimeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/***
 *  通用接口
 */
@RestController
public class SubLoginController extends BaseController {
    @Autowired
    private IErpUserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private IAPIService apiService;
    @Autowired
    private IErpRegionSerivce regionSerivce;

    @Autowired
    private IErpDriverService driverService;
    /**
     * 公众号用户登录
     * 修改了之前pc端的登录方法，将登录验证授权交由shiro进行管理
     * <p>
     * @return 2019-06-21
     * @author : zhengweicheng
     */
    @RequestMapping("/login")
    public MessageBean login(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        if(StringUtils.isBlank(username)){
            return resultFailed("用户名不可为空");
        }else if(StringUtils.isBlank(password)){
            return resultFailed("密码不可为空");
        }
        //声明存储失败的字符串变量
        String failed;
        //获得安全管理器
        Subject subject = SecurityUtils.getSubject();
        try {
            //并将用户名与密码存到验证存储器中
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
            usernamePasswordToken.setRememberMe(true);
            //请求shiro登陆认证中。
            subject.login(usernamePasswordToken);
            String token = subject.getSession().getId().toString();
            userService.forgetPassword(new ErpUser(resultUser().getUser_name(),token));
            return new MessageBean(SUC,"登录成功！",null,token,resultUser());
        }catch (UnknownAccountException e) {
            failed = "账号不存在";
        }catch (IncorrectCredentialsException e) {
            failed = "密码错误";
        }catch (LockedAccountException e) {
            failed = "帐号已冻结或未审核";
        }catch (Exception e) {
            //输出异常
            e.printStackTrace();
            return new MessageBean(10010,"登录失败",null);
        }
        return resultFailed(failed);
    }
    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public MessageBean logout(){
        SecurityUtils.getSubject().logout();
        return resultSuccess("已成功退出登录");
    }

    /**
     * 忘记密码时的验证码发送
     * 需要传入用户名与输入的手机号后，进行检验，检验该手机号是否是
     * @param userName
     * @param phone
     * @return
     */
    @RequestMapping("/getCode")
    public MessageBean getCode(String userName,String phone,Integer type){
        //type = 1时则是忘记密码 ， type = null 时则是注册
        String key = REGISTER_CODE;
        if(StringUtils.isBlank(userName)){
            return resultFailed("请输入有效的用户名.");
        }else if(!FormatUtil.isPhone(phone)){
            return resultFailed("请输入有效的手机号");
        }else if(type != null){
             key = FORGET_PASSWORD_CODE;
        }
        if(FORGET_PASSWORD_CODE.equals(key)) {
            ErpUser user = userService.login(userName);
            if (null == user) {
                return resultFailed("用户名不存在");
            } else if (!phone.equals(user.getUser_phone())) {
                return resultFailed("输入的手机号与用户的手机号不符");
            }
        }
        sessionFindCode(key);
        Integer code  = sendMsg(phone);
        if(code <=0){
            return resultFailed("验证码发送失败");
        }
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("time",resultCurrentTime());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(key,json);
        //设置定时器进行定时删除
        timer(session,key);
        return new MessageBean(SUC,"验证码发送成功.",null,session.getId().toString(),null);
    }

    /**
     * 忘记密码
     * @param name          用户名
     * @param phone         接收验证码的手机号
     * @param password      密码
     * @param password_     确认密码
     * @param code          验证码
     * @return
     */
    @RequestMapping("/forgetPassword")
    public MessageBean forgetPassword(@RequestParam("user_name") String name,@RequestParam("user_phone") String phone,
                                      @RequestParam("password") String password,@RequestParam("password_") String password_,
                                      @RequestParam("code") String code){
        if(StringUtils.isBlank(name)){
            return resultFailed("请输入有效的用户名.");
        }else if(FormatUtil.notIsPhone(phone)){
            return resultFailed("请输入有效的手机号");
        }else if(StringUtils.isBlank(password)){
            return resultFailed("请输入有效的密码");
        }else if(StringUtils.isBlank(password_)){
            return resultFailed("请输入有效的确认密码");
        }else if(password.length()< 8){
            return resultFailed("输入的密码必须大于等于八位");
        }else if(password_.length()< 8 ){
            return resultFailed("输入的确认密码必须大于等于八位");
        }else if(!password.equals(password_) ){
            return resultFailed("两次输入的密码不一致");
        }else if(StringUtils.isBlank(code)||code.length()<4){
            return resultFailed("请输入有效的验证码");
        }
        ErpUser user = userService.login(name);
        if(null==user){
            return resultFailed("请输入有效的用户名");
        }else if(!user.getUser_phone().equals(phone)){
            return resultFailed("输入的手机号与该用户名不匹配");
        }
        //取出用户主键，方便后期修改数据库。
        Integer userId = user.getId();
        Session session = SecurityUtils.getSubject().getSession();
        checkCode(FORGET_PASSWORD_CODE,code);
        user = new ErpUser();
        user.setId(userId);
        user.setPassword_(PasswrodsUtil.resultNewPassword(password));
        return resultByCount(userService.update(user));
    }

    /**
     * 返回地址信息
     * @param search
     * @return
     */
    @RequestMapping("/getRegion")
    public MessageBean getRegion(ErpRegionSearch search){
        return resultSuccess(regionSerivce.selectBySearch(search));
    }

    /**
     * 检验用户名唯一性
     * @param user_name
     * @return
     */
    @RequestMapping("/checkUserNameUnique")
    public MessageBean checkUserNameUnique(String user_name){
        userService.userNameUnique(user_name);
        return resultSuccess();
    }
    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public MessageBean register(ErpUserDto user){
        try{
            if(null== user){
                return resultFailed("请完善信息后再进行提交");
            }else if(null == user.getUser_cate() || Arrays.asList(1,3,4).indexOf(user.getUser_cate()) < 0){
                return resultFailed("请选择有效的用户类型");
            }else if(StringUtils.isBlank(user.getUser_phone())){
                return resultFailed("手机号不可为空");
            }else if(FormatUtil.notIsPhone(user.getUser_phone())){
                return resultFailed("请输入有效的手机号");
            }else if(StringUtils.isBlank(user.getPassword_()) || user.getPassword_().length()< 8){
                return resultFailed("输入的密码必须大于等于八位");
            }else if(StringUtils.isBlank(user.getPassword_re())|| user.getPassword_re().length()< 8 ){
                return resultFailed("输入的确认密码必须大于等于八位");
            }else if(!user.getPassword_re().equals(user.getPassword_())){
                return resultFailed("两次输入的密码不一致");
            }else if(user.getUser_cate() != 3 &&(StringUtils.isBlank(user.getUser_company_name()) || user.getUser_company_name().length() <4)){
                return resultFailed("公司名称不可为空且必须长度大于等于4位");
            }else if(user.getUser_cate() == 1 &&StringUtils.isBlank(user.getUser_address())){
                return resultFailed("详细地址不可为空");
            }else if(user.getUser_cate() == 1 &&(user.getProvince_id()==null || user.getCity_id() == null || user.getArea_id() == null)){
                return resultFailed("省市区不可为空.");
            }else if(user.getUser_cate() == 4){
                if(StringUtils.isBlank(user.getCar_sn()) || user.getCar_sn().length() < 5){
                    return resultFailed("车牌号码不可为空且长度必须大于等于5位");
                }else if(StringUtils.isBlank(user.getDriver_sn()) || user.getDriver_sn().length() < 6){
                    return resultFailed("驾驶证证件号不可为空且长度必须大于等于6位");
                }
            }
            //TODO:驾驶证与车牌号唯一检验尚未完成
            if(StringUtils.isBlank(user.getCode()) || user.getCode().length() < 4){
                return resultFailed("验证码不可为空且长度必须等于4位");
            }
            userService.userNameUnique(user.getUser_name());
            checkCode(REGISTER_CODE,user.getCode());
            ErpUser insertUser = new ErpUser(user);
            insertUser.setRole_id(user.getUser_cate());
            insertUser.setUser_name(user.getUser_name());
            insertUser.setAudit(0);
            insertUser.setUser_cate(user.getUser_cate());
            insertUser.setPassword_(PasswrodsUtil.resultNewPassword(user.getPassword_()));
            if(userService.insertService(insertUser) < 0){
                return resultFailed();
            }
            user.setId(insertUser.getId());
            if(user.getUser_cate() == 4){
                ErpDriver driver = new ErpDriver(user);
                driver.setCreatetime(TimeUtils.resultCurrentDate());
                if(driverService.insertService(driver) < 0){
                    return resultFailed();
                }
            }
            return resultSuccess();
        }catch (Exception e){
            rollBack();
            if(e instanceof LogicException){
                throw e;
            }
            e.printStackTrace();
            return resultFailed();
        }
    }

    /**
     * 返回角色列表
     * @return
     */
    @RequestMapping("/resultRoleList")
    public MessageBean resultRoleList(RoleSearch search){
        return resultSuccess(roleService.selectBySearch(search));
    }
}
