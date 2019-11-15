package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpCar;
import com.xiaoyuan.service.IErpCarService;
import com.xiaoyuan.tools.MessageBean;

@CrossOrigin
@RestController
@RequestMapping("/ErpCarStat")
public class ErpCarStateController {
    @Autowired
    private IErpCarService carService;

    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpCar> lists = carService.lists_car_state();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpCar> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);

    }

    /**
     * 车辆提货列表
     *
     * @return
     */
    @RequestMapping("/lists_car_pick")
    public MessageBean lists_car_pick(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpCar> lists = carService.lists_car_pick();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpCar> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);

    }
}
