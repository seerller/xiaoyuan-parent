package com.xiaoyuan.model.permission;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 权限菜单功能表
 */
@Data
@Table(name = "tl_erp_card")
public class Permission  {
    /**
     * 自增主键
     */
    @Id
    private Integer id;
    /**
     * 权限名称
     */
    private String permission;
    /**
     * 父类id
     */
    private Integer parentId;
    /**
     * 权限类型
     */
    private Integer type;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 修改时间
     */
    private String updatetime;
    /**
     * 创建人id
     */
    private Integer createId;
    /**
     * 修改人Id
     */
    private Integer updateId;
    /**
     * 权限的请求地址 --->改地址是vue项目中的路由地址，而不是请求服务器的url地址
     */
    private String url;
    /**
     * 请求参数  --->该地址是vue项目中的路由地址，而不是请求服务器的url地址
     */
    private String params;
    /**
     * 权限描述
     */
    private String describe;
    /**
     * 权限标题
     */
    private String title;
    /**
     * 是否显示
     */
    private boolean isShow;
    /**
     * 权限描述
     */
    private String description;
}
