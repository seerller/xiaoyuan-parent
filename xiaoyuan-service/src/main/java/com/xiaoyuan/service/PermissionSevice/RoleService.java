package com.xiaoyuan.service.PermissionSevice;

import com.xiaoyuan.model.dto.RoleDto;
import com.xiaoyuan.model.role.Role;
import com.xiaoyuan.model.search.RoleSearch;

import java.util.List;

public interface RoleService {


    /***
     *  根据条件搜索类查询数据
     * @param roleSearch
     * @return
     */
    List<RoleDto> selectBySearch(RoleSearch roleSearch);

    /**
     *  根据条件搜索类查询数据内第一条数据
     * @param search
     * @return
     */
    RoleDto selectFirstBySearch(RoleSearch search);


    /**
     * 移除中间表内所有权限
     * @param roleId
     * @return
     */
    int reomvePermission(Integer roleId);
    /**
     * 插入中间表权限
     * @return
     */
    int insertRolePermission(Role role);

    int insertService(Role role);

    int updateService(Role role);

    int deleteById(Integer roleId);
}
