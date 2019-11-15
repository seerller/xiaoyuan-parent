package com.xiaoyuan.config;


import com.xiaoyuan.shiro.filter.LoginAuthenticationFilter;
import com.xiaoyuan.shiro.realm.UserRealm;
import com.xiaoyuan.shiro.session.ShiroSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * @author zhengweicheng
 * createTime 2019-01-08
 * shiro配置类。
 * 通过该类进行对shiro相关bean节点进行配置。
 */
@Slf4j
@Configuration
public class ShiroConfig {
    /**
     * 返回UserRealm认证模块
     * @return 用户认证模块
     */
    @Bean("authRealm")
    public UserRealm userRealm(){
        log.info("##创建用户认证类");
        return new UserRealm();
    }
    /**
     * 将自定义的认证模块注入到安全管理器中
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm")UserRealm userRealm ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        //这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
        shiroSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSessionManager;
    }

    /**
     * 设置自定义拦截器与拦截路径
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc",new LoginAuthenticationFilter());
        Map<String,String> map = new LinkedHashMap<>();
        //对所有请求进行拦截
        map.put("/sub/**","authc");
        //对所有请求进行拦截
        map.put("/Menu/**","authc");
        map.put("/app/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setFilters(filterMap);
        //设置自定义的拦截器。
        log.info("##注入安全管理器");
        return shiroFilterFactoryBean;
    }
}
