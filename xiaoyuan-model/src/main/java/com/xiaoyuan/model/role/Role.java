package com.xiaoyuan.model.role;

import lombok.Data;

import javax.persistence.Table;

/**
 * 权限菜单功能表
 */
@Data
@Table(name = "tl_role")
public class Role {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 关键字名称
     */
    private String keyName;
    /**
     * 描述
     */
    private String describe;
    /**
     * 标题
     */
    private String title;
    /**
     * 用户分类
     */
    private Integer user_cate;
}
