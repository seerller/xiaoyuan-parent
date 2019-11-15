package com.xiaoyuan.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhengweicheng
 * createTime 2019-01-11
 * 重写authc过滤器
 */
@Slf4j
public class LoginAuthenticationFilter extends AuthenticationFilter {
    /**
     * 判断是否是预提交字段
     */
    public final static String OPTIONS  = "OPTIONS";
    /**
     * 判断
     */
    public final static long OUT_TIME = 1* 60 * 1000;
    /**
     * 令牌名称
     */
    private final String TOKEN_NAME = "X-Token";



    /**
     * Shiro拦截方法。
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //获取Httprequest对象
        HttpServletRequest res= WebUtils.toHttp(request);
        //判断是否是预检请求，是则通过请求。
        if(OPTIONS.equals(res.getMethod())){
            return true;
        }
        if(StringUtils.isBlank(res.getHeader(TOKEN_NAME))){
            return false;
            //判断是否存在用户信息，不存则则拦截。
        }else if(null==SecurityUtils.getSubject().getSession().getAttribute("user")){
            return false;
        }
        /**
         * TODO:需要加入redis集群环境，从redis中取出token令牌判断token的有效期
         */
        return super.isAccessAllowed( request,  response,  mappedValue);
    }

    /**
     * 登录失败后的错误状态回调
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //app请求验证登录不通过
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        //获取Httprequest对象
        HttpServletRequest res= WebUtils.toHttp(request);
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
        if (OPTIONS.equalsIgnoreCase(res.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            //设置请求状态为401未验证授权异常状态
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return false;
    }
}
