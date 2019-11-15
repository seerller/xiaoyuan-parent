package com.xiaoyuan.service.PermissionSevice;

import com.xiaoyuan.model.permission.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 返回所有权限的权限树
     * @return
     */
    List<Permission> list();
}
