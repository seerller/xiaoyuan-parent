package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpUnit;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpUnitService;
import com.xiaoyuan.service.IErpUsersService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 客户列表
 *
 * @author YZH
 * @version 2019年3月6日 下午3:56:27
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpUsers")
public class ErpClientController {
    @Autowired
    IErpUsersService usersService;
    @Autowired
    IErpUnitService unitSerivce;

    /**
     * 审核列表
     * <p>
     * 客户管理，列表，现在显示的东西太多，查看SQL语句，只显示客户
     * <p>
     * 登录者是园区显示全部，客户显示自己？
     * <p>
     * //	 * @param user_role_id
     *
     * @return 2019年3月7日下午3:44:25
     * @author : YZH
     * <p>
     * 审核和客户列表，，，相同内容，，用前端传参区分，audit=0,
     * <p>
     * audit=1,role_id=1
     * <p>
     * 2019年3月7日下午4:14:51
     * @author : YZH
     */
    @RequestMapping("/lists_audit")
    public MessageBean lists_audit(
            @RequestParam(value = "audit", required = false) Integer audit,
            @RequestParam(value = "role_id", required = false) Integer role_id,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {
        // TODO 重写，
        ErpUser user = new ErpUser();
        user.setAudit(audit);
        user.setRole_id(role_id);
        // if (role_id != null) {
        // user.setRole_id(role_id);
        // }
        PageHelper.startPage(currentPage, pageSize);
        List<ErpUser> list = this.usersService.lists(user);
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpUser> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", list);
    }

    /**
     * 客户列表
     *
     * @param audit
     * @param role_id
     * @return 2019年3月7日下午4:24:42
     * @author : YZH
     */
    @RequestMapping("/lists_customers")
    public MessageBean lists_customers(
            @RequestParam(value = "audit", required = false) Integer audit,
            @RequestParam(value = "role_id", required = false) Integer role_id,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            String search) {
        ErpUser user = new ErpUser();
        user.setAudit(audit);
//		if (role_id != null) {
        user.setRole_id(role_id);// 吴经理说改成这个
//		}
        user.setSearch(search);
        PageHelper.startPage(currentPage, pageSize);
        List<ErpUser> list = this.usersService.lists(user);
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpUser> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", list);
    }

    // 列表
    @RequestMapping("/trash_lists")
    public MessageBean trash_lists(
            @RequestParam(value = "user_role_id", required = false) Integer user_role_id) {

        List<ErpUser> list = usersService.lists(null);
        if (user_role_id != null && list != null) {
            ErpUser user = new ErpUser();
            user.setUser_cate(1);// YZH 显示客户
            user.setUser_role_id(1);// YZH 显示客户
            // 没有设置审核，，
            List<ErpUser> lists = usersService.lists(user);
            return new MessageBean(10001, "", lists);
        } else if (list != null) {
            return new MessageBean(10001, "", list);
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    // 删除
    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam("id") Integer id) {
        //TODO 20190409 正在使用不能被删除，去各个表里查看一下？


        int delete = usersService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "用户删除成功", null);
        }
        return new MessageBean(10000, "用户删除失败", null);

    }

    // 查找
    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam("id") Integer id) {
        ErpUser user = usersService.get(id);
        if (user != null) {
            return new MessageBean(10001, "", user);
        }
        return new MessageBean(10000, "没有找到此纪录", user);
    }

    // 禁止
    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id, ErpUser user) {
        if (id != null) {
            user.setId(id);
            user.setStatus_(0);
            int result = usersService.update(user);
            if (result > 0) {
                return new MessageBean(10001, "此用户已禁止", null);
            }
        }
        return new MessageBean(10000, "此用户禁止异常", null);
    }

    @RequestMapping("/addClient")
    public MessageBean add(ErpUser erpUser, @RequestParam(value = "id", required = false) Integer id,
                           @RequestParam(value = "unit_dept", required = false) String unit_dept) {
        ErpUser user = new ErpUser();
        Integer user_role_id = 1;
        if (id == null && user_role_id == 1) {

            user.setUser_company_name(erpUser.getUser_company_name());
            user.setUser_company_sn(erpUser.getUser_company_sn());
            user.setUser_name(erpUser.getUser_name());
            user.setUser_phone(erpUser.getUser_phone());
            user.setUser_telephone(erpUser.getUser_telephone());
            user.setUser_wechat(erpUser.getUser_wechat());
            user.setCompany_address(erpUser.getCompany_address());
            user.setSales_area(erpUser.getSales_area());
            user.setUser_price(erpUser.getUser_price());
            user.setUser_role_id(user_role_id);
            ErpUnit erpUnit = new ErpUnit();
            erpUnit.setUser_id(user.getId());
            erpUnit.setUnit_dept(unit_dept);
            unitSerivce.insert(erpUnit);
            /*
             * user.setUser_contact(erpUser.getUser_contact());
             * user.setUser_address(erpUser.getUser_address());
             * user.setCompany_contact(erpUser.getCompany_contact());
             * user.setCompany_cate(erpUser.getCompany_cate());
             */

            int result = usersService.insert(user);
            if (result > 0) {
                return new MessageBean(10001, "新增成功", null);
            }
            return new MessageBean(10000, "新增异常", null);
        } else {
            user.setId(id);
            user.setUser_company_name(erpUser.getUser_company_name());
            user.setUser_company_sn(erpUser.getUser_company_sn());
            user.setUser_name(erpUser.getUser_name());
            user.setUser_phone(erpUser.getUser_phone());
            user.setUser_telephone(erpUser.getUser_telephone());
            user.setUser_wechat(erpUser.getUser_wechat());
            user.setCompany_address(erpUser.getCompany_address());
            user.setSales_area(erpUser.getSales_area());
            user.setUser_price(erpUser.getUser_price());
            user.setUser_role_id(user_role_id);
            ErpUnit erpUnit = new ErpUnit();
            erpUnit.setUser_id(id);
            erpUnit.setUnit_dept(unit_dept);
            unitSerivce.updateUnitByUserid(erpUnit);
            int result = usersService.update(user);
            if (result > 0) {
                return new MessageBean(10001, "修改成功", null);
            }
            return new MessageBean(10000, "修改异常", null);
        }

    }

}
