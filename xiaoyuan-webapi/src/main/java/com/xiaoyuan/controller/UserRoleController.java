package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpRole;
import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.service.IUserRoleService;
import com.xiaoyuan.tools.MessageBean;

@RestController
@RequestMapping("/UserRole")
@CrossOrigin
public class UserRoleController {
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IErpUserService userService;

    /**
     * 修改权限
     *
     * @param role_id
     * @param role_name
     * @param role_auth_ids
     * @return 2019年2月22日下午5:02:13
     * @author : YZH
     */
    @RequestMapping("/update")
    public MessageBean update(@RequestParam(value = "role_id") Integer role_id,
                              @RequestParam(value = "role_name") String role_name,
                              @RequestParam(value = "role_auth_ids") String role_auth_ids) {

        UserRole userRole = this.userRoleService.role(role_id);
        if (userRole != null) {
            userRole.setRole_auth_ids(role_auth_ids);
            if (this.userRoleService.update(userRole) >= 1) {
                return new MessageBean(10001, "修改权限成功", null);
            }
        }
        return new MessageBean(10000, "修改权限失败", null);
    }

    /**
     * 增加权限种类到权限表
     * <p>
     * 有如果角色名称存在就直接修改的需求：
     * <p>
     * 先判断角色名称是否存在，存在就是修改，不存在就是新增
     *
     * @param userRole
     * @param role_name
     * @param role
     * @return 2019年2月22日下午4:23:26
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "role_id", required = false) Integer role_id,
                           @RequestParam(value = "role_name", required = false) String role_name,
                           @RequestParam(value = "role_auth_ids", required = false) String role_auth_ids) {

        // 表中有角色则修改，无则增加，
        // 呃呃
        int result = this.userRoleService.getId(role_name);
        if (result != 0) {
            UserRole userRole = this.userRoleService.role(result);
            if (userRole != null) {// TODO YZH 看着觉得好恶心。。。代码写的太。。。。？
                userRole.setRole_auth_ids(role_auth_ids);
                userRole.setRole_id(role_id);
                userRole.setRole_name(role_name);
                int update_result = this.userRoleService.update(userRole);
                System.out.println("update_result: " + update_result);
                if (update_result >= 1) {
                    return new MessageBean(10001, "修改权限成功", null);
                }
            }
            return new MessageBean(10000, "修改权限失败", null);
        }

        UserRole userRole = new UserRole();
        userRole.setRole_name(role_name);
        userRole.setRole_auth_ids(role_auth_ids);

        if (this.userRoleService.add(userRole) >= 1) {
            return new MessageBean(10001, "增加权限成功", null);
        }
        return new MessageBean(10000, "增加权限失败", null);

    }

    @RequestMapping("/roleisExists")
    public MessageBean roleisExists(Integer seller_id) {
        UserRole role = userRoleService.role_auth_id(seller_id);
        if (role != null) {
            return new MessageBean(10001, "", null);
        }
        return new MessageBean(10000, "已存在该角色", null);
    }

    @RequestMapping("/trash_add")
    public MessageBean trash_add(UserRole userRole,
                                 @RequestParam(value = "role_name", required = false) String role_name,
                                 @RequestParam(value = "role", required = false) String role) {
        // 根据角色名字去获取id

        // ErpRole erp1=userRoleService.getRole(role);//查询到全部的角色和id
        // System.out.println(role);
        int id = userRoleService.getId(role_name);// 出现类型转换异常
        System.out.println(userRoleService.getId(role_name));
        System.out.println(id);
        ErpRole erpRole = new ErpRole();
        int result;
        // 在根据ID的有无去判断是否为修改还是新增
        if (id > 0) {// 添加名字与前端传来的名字相同就是去修改 有问题得想下
            // 是根据权限id来修改
            userRoleService.update(userRole);// 修改权限
            // 入库操作
            UserRole userrole = new UserRole();// 那张表数据要放到那张表中
            // userRole.getRole_auth_ids();
            userrole.setRole_auth_ids(userRole.getRole_auth_ids());
            // System.out.println(userRole.getRole_auth_ids());
            userrole.setRole_name(role_name);
            // System.out.println(role_name);
            result = userRoleService.add(userrole);// 将数据放入到张表中
            if (result > 0) {
                return new MessageBean(10000, "已修改", null);
            } else {
                return new MessageBean(10001, "未修改", null);
            }
        } else {// 不相同就是新增
            UserRole userrole = new UserRole();// 那张表数据要放到那张表中
            userrole.setRole_auth_ids(userRole.getRole_auth_ids());
            userrole.setRole_name(role_name);
            result = userRoleService.add(userrole);// 入库操作出现问提
            if (result > 0) {
                return new MessageBean(10001, "添加成功", null);
            } else {
                return new MessageBean(10000, "未添加", null);

            }
        }

    }
    /*
     * else{ erpRole.setCreatetime(new
     * SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
     * userRoleService.addRole(erpRole); } UserRole roles = new UserRole();
     * //roles.setRole_name(userRole.getRole_name());
     *
     * roles.setSeller_id(erpRole.getId()); //UserRole role =
     * userRoleService.role_auth_ids(roles); /*if (role != null){ return new
     * MessageBean(20001,"不能重复分配权限",null); }else{
     */
    /*
     * UserRole role_ = userRoleService.role_auth_id(userRole.getSeller_id());
     * if(role_ != null){ roles.setRole_auth_ids(role_.getRole_auth_ids());
     * }else{ roles.setRole_auth_ids(userRole.getRole_auth_ids()); } result =
     * userRoleService.add(roles); }
     */
    /*
     * if(result>0){ return new MessageBean(10001,"添加权限成功",null); } return new
     * MessageBean(10000,"操作权限异常",null); }
     */

    // 注意新增和修改的sql是否正确
    /*
     * int result = 0; ErpRole erpRole = null;//前端闯过来用户角色对象，角色名字，角色，
     * 通过角色名来查询第二次是否给权限 if (role != null){ erpRole.setRole_name(role); result =
     * userRoleService.update(userRole); if(result>0){ return new
     * MessageBean(10001,"编辑权限成功",null); } return new
     * MessageBean(10000,"操作权限异常",null); }else{ erpRole = new ErpRole();
     * erpRole.setRole_name(role_name); ErpRole role2 =
     * userRoleService.getRole(erpRole); //ErpRole
     * role2=userRoleService.update(role_name); if(role2 !=null){//查询到有改角色
     * return new MessageBean(20001,"角色名不能重复",null);
     *
     * }else{//查询没有该角色 erpRole.setCreatetime(new
     * SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
     * userRoleService.addRole(erpRole); } UserRole roles = new UserRole();
     * roles.setRole_name(userRole.getRole_name());
     * roles.setSeller_id(erpRole.getId()); UserRole role1 =new UserRole();
     * role1 = userRoleService.role_auth_ids(roles); //获得角色的权限id
     *
     * if (role1 != null){// userRoleService.update(role1);//修改权限 return new
     * MessageBean(10000,"修改权限成功",null);
     *
     * }else{//角色无权限的情况下 UserRole role_ =
     * userRoleService.role_auth_id(userRole.getSeller_id());//根据卖家ID去得到权限id
     *
     * /* if(role_ != null){
     * roles.setRole_auth_ids(role_.getRole_auth_ids());//根据权限的id来设置权限id }else{
     * roles.setRole_auth_ids(userRole.getRole_auth_ids()); } result =
     * userRoleService.add(roles);//2 } if(result>0){ return new
     * MessageBean(10001,"添加权限成功",null); } return new
     * MessageBean(10000,"操作权限异常",null); } }
     */
    // 只能线下测，线上不能测。加油。。。。。。

    @RequestMapping("/delete")
    public MessageBean delete(Integer id) {
        int delete = userRoleService.delete(id);
        if (delete > 0) {

            return new MessageBean(10001, "删除成功", null);
        }
        return new MessageBean(10000, "删除失败", null);
    }

    /**
     * 单表查询，select from 查全部
     *
     * @return 2019年2月22日下午8:30:08
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<UserRole> lists = userService.user_role_list();
        if (lists.size() > 0) {
            int countNums = lists.size();//总记录数
            PageBean<UserRole> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "权限列表数据为空", null);
    }

    @RequestMapping("/detail")
    public MessageBean detail(Integer id) {
        UserRole role = userRoleService.role(id);
        if (role != null) {
            return new MessageBean(10001, "", role);
        }
        return new MessageBean(10000, "", null);
    }

    /**
     * 注册的时候选择角色，因为原来的三表改为了两表，从原来的的userrole表改为从child表中
     * <p>
     * 获取角色选项列表，之后可能有需求还需要改回来，到时候改的更多了，
     * <p>
     * 用户组的实现可以group by user.role_id
     * <p>
     * 在需求分析和设计数据库的时候没有考虑这个功能，，导致的更改，，
     *
     * @return 2019年2月25日下午4:17:34
     * @author : YZH
     */
    @RequestMapping("/userrole")
    public MessageBean userrole() {
        List<UserRole> role = userRoleService.userroles();
        // List<UserRole>role=this.service
        if (role != null) {
            return new MessageBean(10001, "", role);
        }
        return new MessageBean(10000, "", null);
    }
}
