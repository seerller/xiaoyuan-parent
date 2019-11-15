package com.xiaoyuan.model.tree;

import com.xiaoyuan.model.permission.Permission;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class PermissionTree extends TreeNode{
    /**
     * 权限名称
     */
    private String permission;
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

    /**
     * 角色是否拥有该权限
     * 默认为false
     */
    private boolean check = false;

    private boolean alwaysShow = true;

    private boolean hidden;

    private String path;

    private Map<String,Object> meta;

    public PermissionTree(){}

    public PermissionTree(Permission permission){
        try {
            BeanUtils.copyProperties(this,permission);
            this.meta = new HashMap<>();
            this.meta.put("title",this.title);
            this.hidden = false;
            this.path = "/"+this.url;
        }catch (Exception e){
            log.info("复制对象失败,");
            e.printStackTrace();
        }
    }
}
