package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.role.Role;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString(callSuper = true)
public class RoleDto extends Role {
    /**
     * 该角色拥有权限的自增主键集合
     */
    private List<Integer> ids;

    /**
     * 该角色拥有权限的自增主键集合
     */
    private String pIds;

    public List<Integer> resultIds(){
        return Arrays.asList(pIds.split(","))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
