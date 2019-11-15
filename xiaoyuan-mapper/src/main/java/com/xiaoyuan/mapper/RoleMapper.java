package com.xiaoyuan.mapper;

import com.xiaoyuan.model.dto.RoleDto;
import com.xiaoyuan.model.role.Role;
import com.xiaoyuan.model.search.RoleSearch;
import com.xiaoyuan.tools.MapperConfig;

import java.util.List;

public interface RoleMapper extends MapperConfig<Role> {

    /**
     * 根据数据是否为空进行插入
     * @param role
     * @return
     */
    int insertService(Role role);
    /**
     * 根据主键进行修改
     * @param role
     * @return
     */
    int updateService(Role role);

    List<RoleDto> selectBySearch(RoleSearch search);

    /**
     * 根据角色自增主键删除角色
     * @param roleId
     * @return
     */
    int deleteById(Integer roleId);

    /**
     * 根据角色id与权限id集合插入中间表
     * @param roleId
     * @param pIds
     * @return
     */
    int insertRolePermission(Role role);

    /**
     * 移除角色相关的权限
     * @param roleId
     * @return
     */
    int reomvePermission(Integer roleId);
}
