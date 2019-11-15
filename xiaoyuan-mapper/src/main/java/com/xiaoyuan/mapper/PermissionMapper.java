package com.xiaoyuan.mapper;

import com.xiaoyuan.model.permission.Permission;
import com.xiaoyuan.tools.MapperConfig;

import java.util.List;

public interface PermissionMapper extends MapperConfig<Permission> {
    /**
     * 根据数据是否为空进行插入
     * @param permission
     * @return
     */
    int insertService(Permission permission);

    /**
     * 根据主键进行修改
     * @param permission
     * @return
     */
    int updateService(Permission permission);

    /**
     * 查询pid为0的所有权限或菜单
     * @return
     */
    List<Permission> list();

}
