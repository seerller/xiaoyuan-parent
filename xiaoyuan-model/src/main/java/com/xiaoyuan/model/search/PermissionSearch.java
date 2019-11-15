package com.xiaoyuan.model.search;

import lombok.Data;

import java.util.List;

@Data
public class PermissionSearch extends BaseSearch {
    /***
     * 权限自增主键数组
     */
    private List<Integer> ids;

    public PermissionSearch(){}


    public PermissionSearch(List<Integer> ids){
        this.ids = ids;
    }

    public PermissionSearch setIds(List<Integer> ids){
        this.ids = ids;
        return this;
    }
}
