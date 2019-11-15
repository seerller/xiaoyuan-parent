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
 * 设备管理的控制类
 *
 * @author YZH
 * @version 2019年3月8日 下午4:31:02
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpEquipment")
public class ErpEquipmentController {
    @Autowired
    private IErpEquipmentService equipmentService;

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

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = equipmentService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "设备删除成功", null);
            }
        }
        return new MessageBean(10000, "设备删除失败", null);
    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpEquipment erpEquipment = equipmentService.get(id);
            if (erpEquipment != null) {
                return new MessageBean(10001, "", erpEquipment);
            }
        }
        return new MessageBean(10000, "没有找到此纪录", null);
    }

    /**
     * status 1.禁止 0。正常
     *
     * @param id
     * @return
     */
    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id) {
        ErpEquipment equipment = new ErpEquipment();
        if (id != null) {
            equipment.setId(id);
            equipment.setStatus_(0);
            int result = equipmentService.update(equipment);
            if (result > 0) {
                return new MessageBean(10001, "设备已禁止", null);
            }
        }
        return new MessageBean(10000, "设备禁止异常", null);

    }

    @RequestMapping("/add")
    public MessageBean add(
            @RequestParam(value = "id", required = false) Integer id,
            ErpEquipment erpEquipment) {
        ErpEquipment equipment = new ErpEquipment();
        if (id != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            equipment.setId(id);
            equipment.setEquipment_name(erpEquipment.getEquipment_name());
            equipment.setEquipment_area(erpEquipment.getEquipment_area());
            equipment.setEquipment_ip(erpEquipment.getEquipment_ip());
            equipment.setEquipment_enter(erpEquipment.getEquipment_enter());
            equipment.setEquipment_sn(erpEquipment.getEquipment_sn());
            equipment.setEquipment_state(erpEquipment.getEquipment_state());
            //20190408
//            equipment.setEquipment_date(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(erpEquipment.getEquipment_date()));
            equipment.setEquipment_reason(erpEquipment.getEquipment_reason());
            equipment.setEquipment_meddle(erpEquipment.getEquipment_meddle());
            equipment.setUpdatetime(sdf.format(new Date()));
            int result = equipmentService.update(equipment);
            if (result > 0) {
                return new MessageBean(10001, "设备修改成功", null);
            }
        }
        return new MessageBean(10000, "id为空", null);
    }
}
