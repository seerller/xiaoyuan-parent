package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.permission.Permission;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class PermissionDto extends Permission {
    /**
     * 所有id的集合转化为字符串
     */
    public String ids;
    /**
     * 该属性是用来判断角色是否拥有该权限
     * 默认为拥有
     */
    public boolean isExist = true;
    /**
     *  权限下的子权限
     */
    List<PermissionDto> permissions;
}
