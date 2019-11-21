package com.xiaoyuan.shiro.realm;


import com.xiaoyuan.model.UserInfo;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.PasswrodsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengweicheng
 * createTime 2019-01-08
 * shiro用户认证模块。
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IErpUserService userService;
    /**
     * 角色与权限的赋予
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据令牌获取用户名和密码。
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        ErpUser result = userService.login(username);
        if(null==result){
            log.info(username+"账户不存在");
          throw new UnknownAccountException();
        }
        //判断密码是否正确
        if (!PasswrodsUtil.oldOrNewIsEquals(result.getPassword_(),password)) {
            log.info("密码不正确");
            throw new IncorrectCredentialsException();
        }else if(result.getStop()!=null&&result.getStop() == 1){
            log.info("账户已冻结");
            throw new LockedAccountException();
        }else if(result.getAudit() <= 0){
            log.info("账户已冻结");
            throw new LockedAccountException();
        }
        //将返回的账户密码清除后存入session中
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",userService.selectFiistBySearch(new ErpUserSearch().setId(result.getId())));
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
