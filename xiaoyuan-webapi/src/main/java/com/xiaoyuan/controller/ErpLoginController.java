package com.xiaoyuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.xiaoyuan.tools.PasswrodsUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.service.IAPIService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.service.IUserRoleService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 登录
 *
 * @author MyPC
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpLogin")
public class ErpLoginController {
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IAPIService apiService;

    /**
     * 供应商登录不了，过来查看，
     * <p>
     * 登录判断：user.role_id对应的child表role_id的auth_ids不为空
     *
     * @param login_name
     * @param password
     * @param request
     * @return 2019年2月25日下午3:12:11
     * @author : YZH
     */
    @RequestMapping("/login")
    public MessageBean login(@RequestParam("login_name") String login_name, @RequestParam String password,
                             HttpServletRequest request) {
        ErpUser result = userService.login(login_name);
        //取出shiro安全管理器中的会话管理器
        Session session = SecurityUtils.getSubject().getSession();
        if (result != null) {
            //根据密码检验工具类检验密码是否匹配
            if (!PasswrodsUtil.oldOrNewIsEquals(result.getPassword_(),password)) {//密码正确
                String data  = null;
                if (result.getAudit() == 1) {// TODO YZH 改为==2，里面的代码看不懂，
                    StringBuffer buffer = new StringBuffer();
                    UserRole role = this.userRoleService.role(result.getRole_id());
                    if (role != null) {
                        buffer.append(" and a.id in (");
                        StringBuffer roleBuffer = new StringBuffer(role.getRole_auth_ids());
                        buffer.append(roleBuffer.append(")"));
                        buffer.append(" or tl_api_cate.id in (").append(roleBuffer).append(")");
                    }
                    //根据账号更新数据库中的token
                    result.setUser_name(login_name);
                    result.setToken(session.getId().toString());
                    userService.forgetPassword(result);
                    //将返回的账户密码清除后存入session中
                    result.setPassword_(null);
                    session.setAttribute("user",result);
                    //admin账号为管理员账号，因此拥有所有权限
                    if(login_name.equals("admin")){
                        data = JSON.toJSONString(apiService.lists(null));
                    }else{
                        data = JSON.toJSONString(apiService.listsbycategroy(null, null, buffer.toString()));
                    }
                    return new MessageBean(10001, "登录成功", JSON.parse(data),
                            result.getToken(), result);
                } else {
                    System.out.println("还没有权限二");
                    return new MessageBean(10005, "还没有通过审核,请联系管理员进行审核", null);
                }
            } else {
                return new MessageBean(10100, "密码不正确", null);
            }
        } else {
            return new MessageBean(10200, "用户名不存在", null);
        }
    }

    /**
     * 忘记密码
     *
     * @param name
     * @param phone
     * @param password
     * @return 2019年2月25日下午3:15:13
     * @author : YZH
     */
    @RequestMapping("/forgetPassword")
    public MessageBean forgetPassword(
            @RequestParam("user_name") String name,
            @RequestParam("user_phone") String phone,
            @RequestParam("password") String password) {
        ErpUser user = userService.login(name);
        if (user != null) {
            if (phone.equals(user.getUser_phone())) {
                ErpUser userRegister = new ErpUser();
                userRegister.setUser_name(name);
                userRegister.setPassword_(PasswrodsUtil.resultNewPassword(password));
                int result = userService.forgetPassword(userRegister);
                if (result == 1) {
                    return new MessageBean(10001, "密码设置成功", null);
                } else {
                    return new MessageBean(10000, "修改密码失败", result);
                }
            } else {
                return new MessageBean(10100, "请使用注册时的手机号", null);
            }
        }
        return new MessageBean(10200, "用户名不存在", null);

    }

    /**
     * 退出登录
     *
     * @param request
     * @return 2019年2月25日下午3:15:40
     * @author : YZH
     */
    @RequestMapping("/logout")
    public MessageBean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        session.invalidate();
        return new MessageBean(10001, "退出成功", null);
    }
}
