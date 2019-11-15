package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpSupplier;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpSupplierService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

@CrossOrigin
@RestController
@RequestMapping("/ErpSupplierCate")
public class ErpSupplierCateController {
    @Autowired
    IErpSupplierService supplierService;
    @Autowired
    IErpUserService userService;

    /**
     * 供应商列表
     *
     * @return
     */
    @RequestMapping(value = "/lists")
    public MessageBean list(
            @RequestParam(value = "goods_name", required = false) String goodsName,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {
        List<ErpSupplier> list = null;
        if (goodsName != null && goodsName != "") {
            PageHelper.startPage(currentPage, pageSize);
            list = supplierService.list(goodsName, null);
        } else {
            PageHelper.startPage(currentPage, pageSize);
            list = supplierService.list(null, null);
        }
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpSupplier> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", list);
    }


    @RequestMapping(value = "/delete")
    public MessageBean delete(@RequestParam("id") Integer id) {
        int result = supplierService.delete(id);
        if (result > 0) {
            return new MessageBean(10001, "删除成功", null);
        }
        return new MessageBean(10000, "删除失败", null);
    }


    @RequestMapping(value = "/detail")
    public MessageBean get(@RequestParam("id") Integer id) {
        ErpSupplier detail = supplierService.get(id);
        if (detail != null) {
            return new MessageBean(10001, "", detail);
        }
        return new MessageBean(10000, "没有找到此纪录", null);
    }


    /**
     * status 0。禁止 	1.正常
     * 禁止供应商
     *
     * @param tlerpsupplier
     * @param id
     * @return
     */

    @RequestMapping("/stop")
    public MessageBean stop(ErpSupplier tlerpsupplier, @RequestParam Integer id) {
        if (id != null) {
            ErpSupplier supplier = new ErpSupplier();
            supplier.setId(id);
            supplier.setStatus_(0);
            int result = supplierService.update(supplier);
            if (result > 0) {
                return new MessageBean(10001, "供应商已禁止", null);
            }
        }
        return new MessageBean(10000, "供应商禁止异常", null);
    }


    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "user_phone", required = false) String user_phone, ErpSupplier tlerpsupplier, @RequestParam(value = "id", required = false) Integer id) {
        ErpSupplier supplier = new ErpSupplier();
        if (id != null) {
            ErpUser user = new ErpUser();
            user.setId(id);
            supplier.setUser_id(id);
            if (tlerpsupplier.getSupplier_name() != null && tlerpsupplier.getSupplier_name() != "") {
                supplier.setSupplier_name(tlerpsupplier.getSupplier_name());
            }

            if (user_phone != null && user_phone != "") {
                user.setUser_phone(user_phone);
                userService.update(user);
            }
            if (tlerpsupplier.getSupplier_cate() != null) {
                supplier.setSupplier_cate(tlerpsupplier.getSupplier_cate());
            }

            if (tlerpsupplier.getSupplier_audit() != null && tlerpsupplier.getSupplier_audit() != "") {
                supplier.setSupplier_audit(tlerpsupplier.getSupplier_audit());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            supplier.setUpdatetime(sdf.format(new Date()));
            int result = supplierService.updateByUserid(supplier);
            if (result > 0) {
                return new MessageBean(10001, "供应商分类修改成功", null);
            }
        }
        return new MessageBean(10000, "供应商分类修改失败", null);
    }
}
