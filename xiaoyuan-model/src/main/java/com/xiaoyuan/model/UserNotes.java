package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_user_notes")
public class UserNotes {
    @Id
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录时间
     */
    private Date logintime;

    /**
     * 登出时间
     */
    private Date logouttime;

    /**
     * 登录IP地址
     */
    @Column(name = "ip-adress")
    private String ipAdress;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_id - 用户名
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户名
     *
     * @param userId 用户名
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取登录时间
     *
     * @return logintime - 登录时间
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * 设置登录时间
     *
     * @param logintime 登录时间
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    /**
     * 获取登出时间
     *
     * @return logouttime - 登出时间
     */
    public Date getLogouttime() {
        return logouttime;
    }

    /**
     * 设置登出时间
     *
     * @param logouttime 登出时间
     */
    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    /**
     * 获取登录IP地址
     *
     * @return ip-adress - 登录IP地址
     */
    public String getIpAdress() {
        return ipAdress;
    }

    /**
     * 设置登录IP地址
     *
     * @param ipAdress 登录IP地址
     */
    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress == null ? null : ipAdress.trim();
    }
}