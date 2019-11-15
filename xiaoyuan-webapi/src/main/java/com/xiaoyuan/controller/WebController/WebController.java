package com.xiaoyuan.controller.WebController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.dto.RoleDto;
import com.xiaoyuan.model.permission.Permission;
import com.xiaoyuan.model.search.RoleSearch;
import com.xiaoyuan.model.tree.PermissionTree;
import com.xiaoyuan.service.PermissionSevice.PermissionService;
import com.xiaoyuan.service.PermissionSevice.RoleService;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/web")
@Slf4j
@Transactional
public class WebController extends BaseController {

    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    /**
     * 根据角色获取权限
     * @return
     */
    @GetMapping
    public MessageBean resultPermission(){
        Map<String,Object> maps = new HashMap<>();
        List<Permission> permissions = permissionService.list();
        RoleDto roleDto =  roleService.selectFirstBySearch(new RoleSearch(resultUser().getRole_id()));
        //当不属于admin超级管理员时，则用角色关联的权限进行筛选数据
        if(ADMIN_USER_NAME.equals(resultUser().getUser_name())){
            maps.put("permissions",permissions.stream()
                    .map(Permission::getPermission).collect(Collectors.toList()));
            maps.put("tree",TreeUtil
                    .buildByLoop(permissions.stream()
                            .filter(permission -> permission.getType().equals(0))
                            .map(PermissionTree::new).collect(Collectors.toList()),0));
        }else{
            maps.put("permissions",permissions.stream()
                    .filter(permission -> roleDto.resultIds().indexOf(permission.getId()) > -1)
                    .map(Permission::getPermission).collect(Collectors.toList()));
            //过滤该角色不存在的数据并对原有对象进行转化
            List<PermissionTree> trees = permissions.stream()
                    .filter(permission -> roleDto.resultIds().indexOf(permission.getId()) > -1)
                    .filter(permission -> permission.getType().equals(0))
                    .map(PermissionTree::new).collect(Collectors.toList());
            //树状菜单权限
                maps.put("tree",TreeUtil.buildByLoop(trees,0));
        }
        return resultSuccess(maps);
    }

    /**
     * 返回权限树
     * @param roleId
     * @return
     */
    @GetMapping("resultTree")

    public MessageBean resultPermission(Integer roleId){
        RoleDto roleDto =  roleService.selectFirstBySearch(new RoleSearch(roleId));
        List<PermissionTree> trees = permissionService.list()
                .stream()
                .map(PermissionTree::new)
                .collect(Collectors.toList());
        if(null!=roleDto && CollectionUtils.isNotEmpty(roleDto.resultIds()))
        trees.stream().forEach(permissionTree -> {
           if(roleDto.resultIds().indexOf(permissionTree.getId()) > -1){
               permissionTree.setCheck(true);
           }
        });
        return resultSuccess(TreeUtil.buildByLoop(trees,0));
    }

}
