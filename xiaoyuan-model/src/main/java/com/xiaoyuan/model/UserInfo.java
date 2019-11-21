package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_user_info")
public class UserInfo {
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
     * 状态
     */
    private String status;

    /**
     * 时间戳
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取时间戳
     *
     * @return create_time - 时间戳
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间戳
     *
     * @param createTime 时间戳
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}