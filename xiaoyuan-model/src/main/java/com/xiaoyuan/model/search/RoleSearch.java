package com.xiaoyuan.model.search;

import lombok.Data;

@Data
public class RoleSearch extends BaseSearch {
    /**
     * 角色自增主键
     */
    private Integer roleId;

    public RoleSearch(){}

    public RoleSearch(Integer roleId){
        this.roleId = roleId;
    }

    public RoleSearch setRoleId(Integer roleId){
        this.roleId = roleId;
        return this;
    }

}
