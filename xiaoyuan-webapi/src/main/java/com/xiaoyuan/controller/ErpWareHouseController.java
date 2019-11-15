package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.model.ErpWareHouse;
import com.xiaoyuan.service.IErpCarOrderService;
import com.xiaoyuan.service.IErpWareHouseService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 这个？
 *
 * @author YZH
 * @version 2019年3月8日 下午4:13:58
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpWareHouse")
public class ErpWareHouseController {

    @Autowired
    IErpWareHouseService wareHouseService;

    @Autowired
    IErpCarOrderService carOrderService;

    // 列表
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpWareHouse> list = wareHouseService.lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpWareHouse> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    @RequestMapping("/out_lists")
    public MessageBean out_lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpWareHouse> list = wareHouseService.out_lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpWareHouse> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    /**
     * @param erpCarOrder
     * @return 2019年3月1日下午5:49:18
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(ErpCarOrder erpCarOrder) {
        ErpCarOrder carOrder = new ErpCarOrder();

        carOrder.setCar_order_address(carOrder.getCar_order_address());
        int result = carOrderService.update(carOrder);
        if (result > 0) {
            return new MessageBean(10001, "修改成功", null);
        }
        return new MessageBean(10000, "修改失败", null);
    }

    /**
     * 数据库中没有这个方法
     *
     * @return 2019年3月7日上午9:55:00
     * @author : YZH
     * <p>
     * api表中没有这个方法？
     * <p>
     * 2019年3月8日下午4:14:21
     * @author : YZH
     */
    @RequestMapping("/wareHouse")
    public MessageBean wareHouse(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpWareHouse> list = wareHouseService.wareHouse();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpWareHouse> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        // return null;
        return new MessageBean(10000, "列表数据为空", list);// YZH 改

    }
}
