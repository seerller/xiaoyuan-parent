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

import com.xiaoyuan.model.ErpCar;
import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.service.IErpCarService;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 车辆类别控制类
 *
 * @author YZH
 * @version 2019年3月8日 下午5:20:54
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpCarCate")
public class ErpCarCateController {
    @Autowired
    private IErpCarService carService;
    @Autowired
    private IErpDriverService driverService;

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam("id") Integer id) {
        int delete = carService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "车辆分类删除成功", delete);
        }
        return new MessageBean(10000, "车辆分类删除失败", null);

    }

    @RequestMapping("/add")
    public MessageBean add(ErpCar erpCar, @RequestParam(value = "id", required = false) Integer id, String car_sn,
                           String car_cate) {
        if (id != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            ErpCar car2 = carService.get(id);
            ErpDriver driver = new ErpDriver();
            driver.setId(car2.getDriver_id());
            driver.setCar_sn(car_sn);
            driver.setCar_cate(car_cate);
            driver.setUpdatetime(sdf.format(new Date()));
            driverService.update(driver);
            ErpCar car = new ErpCar();
            car.setId(id);
            if (erpCar.getCar_owner() != null && erpCar.getCar_owner() != "") {
                car.setCar_owner(erpCar.getCar_owner());
            }
            if (erpCar.getCar_contact_phone() != null && erpCar.getCar_contact_phone() != "") {
                car.setCar_contact_phone(erpCar.getCar_contact_phone());
            }
            // ErpCard card = new ErpCard();
        }
        int update = carService.update(erpCar);
        if (update > 0) {
            return new MessageBean(10001, "车辆分类修改成功", update);
        }
        return new MessageBean(10000, "车辆分类修改失败", null);
    }

    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpCar> list = carService.lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpCar> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", list);
    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam("id") Integer id) {
        ErpCar car = carService.get(id);
        if (car != null) {
            return new MessageBean(10001, "", car);
        }
        return new MessageBean(10000, "没有找到此纪录", car);
    }

    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id, ErpCar car) {
        if (id != null) {
            car.setId(id);
            car.setStatus_(0);
            int result = carService.update(car);
            if (result > 0) {
                return new MessageBean(10001, "车辆分类已禁止", null);
            }
        }
        return new MessageBean(10000, "车辆分类禁止异常", null);
    }

    /*
     * public MessageBean bind(@req){ return null;
     *
     * }
     */
}
