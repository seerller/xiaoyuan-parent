package com.xiaoyuan.service.PermissionSevice.impl;

import com.xiaoyuan.mapper.PermissionMapper;
import com.xiaoyuan.model.permission.Permission;
import com.xiaoyuan.service.PermissionSevice.PermissionService;
import com.xiaoyuan.service.PermissionSevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionMapper mapper;

    /**
     * 返回所有权限的权限树
     * @return
     */
    @Override
    public List<Permission> list() {
        return mapper.list();
    }


}
