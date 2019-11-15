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

import com.xiaoyuan.model.ErpEquipment;
import com.xiaoyuan.service.IErpEquipmentService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 设备分类管理的控制类，
 *
 * @author YZH
 * @version 2019年3月8日 下午4:29:47
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpEquipmentCate")
public class ErpEquipmentCateController {
    @Autowired
    private IErpEquipmentService equipmentService;

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpEquipment equipment = equipmentService.get(id);
            if (!equipment.equals(null)) {
                return new MessageBean(10001, "", equipment);
            }
        }
        return new MessageBean(10000, "没有找到此纪录", null);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = equipmentService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "设备分类删除成功", null);
            }
        }
        return new MessageBean(10000, "设备分类删除失败·", null);
    }

    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id, ErpEquipment equipment) {
        if (id != null) {
            equipment.setId(id);
            equipment.setStatus_(0);
            int result = equipmentService.update(equipment);
            if (result > 0) {
                return new MessageBean(10001, "设备分类已禁止", null);
            }
        }
        return new MessageBean(10000, "设备分类禁止异常", null);
    }

    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpEquipment> list = equipmentService.lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpEquipment> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "id", required = false) Integer id, ErpEquipment equipment) {
        if (id != null) {
            ErpEquipment erpEquipment = new ErpEquipment();
            erpEquipment.setId(id);
            if (equipment.getEquipment_area() != null && equipment.getEquipment_area() != "") {
                erpEquipment.setEquipment_area(equipment.getEquipment_area());
            }
            if (equipment.getEquipment_ip() != null && equipment.getEquipment_ip() != "") {
                erpEquipment.setEquipment_ip(equipment.getEquipment_ip());
            }
            if (equipment.getEquipment_state() != null && equipment.getEquipment_state() != "") {
                erpEquipment.setEquipment_state(equipment.getEquipment_state());
            }
            if (equipment.getEquipment_date() != null && equipment.getEquipment_date() != "") {
                erpEquipment.setEquipment_date(equipment.getEquipment_date());
            }
            if (equipment.getEquipment_maintainer() != null && equipment.getEquipment_maintainer() != "") {
                erpEquipment.setEquipment_maintainer(equipment.getEquipment_maintainer());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            erpEquipment.setUpdatetime(sdf.format(new Date()));
            int result = equipmentService.update(erpEquipment);
            if (result > 0) {
                return new MessageBean(10001, "设备分类修改成功", null);
            }
        }
        return new MessageBean(10000, "设备分类修改失败", null);
    }
}
