package com.xiaoyuan.service.PermissionSevice.impl;

import com.xiaoyuan.mapper.RoleMapper;
import com.xiaoyuan.model.dto.RoleDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.role.Role;
import com.xiaoyuan.model.search.RoleSearch;
import com.xiaoyuan.service.PermissionSevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper mapper;

    @Override
    public List<RoleDto> selectBySearch(RoleSearch roleSearch) {
        return mapper.selectBySearch(roleSearch);
    }

    @Override
    public RoleDto selectFirstBySearch(RoleSearch search) {
        List<RoleDto> list = selectBySearch(search);
        if(list.size()>0){
            return list.get(0);
        }
        throw new LogicException("请联系管理员给你更换有效的角色.");
    }

    @Override
    public int reomvePermission(Integer roleId) {
        return mapper.reomvePermission(roleId);
    }

    @Override
    public int insertRolePermission(Role role) {
        return mapper.insertRolePermission( role);
    }

    @Override
    public int insertService(Role role) {
        return mapper.insertService(role);
    }

    @Override
    public int updateService(Role role) {
        return mapper.updateService(role);
    }

    @Override
    public int deleteById(Integer roleId) {
        return mapper.deleteById(roleId);
    }

}
