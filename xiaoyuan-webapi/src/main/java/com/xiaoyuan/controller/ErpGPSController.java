package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpGPS;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpGpsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * GPS控制类
 *
 * @author YZH
 * @version 2019年3月8日 下午3:20:35
 */
@CrossOrigin
@RestController
@Transactional
@RequestMapping("/ErpGps")
public class ErpGPSController extends BaseController {
    @Autowired
    private IErpGpsService gpsService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpUserService userService;

    @RequestMapping("/add")
    public MessageBean add( ErpUserDto user) {
        try {
            if(user.getId()==null){
                throw resultLogicException("请选择有效的用户.");
            }else if(StringUtils.isBlank(user.getReal_name())){
                throw resultLogicException("驾驶员姓名不可为空..");
            }
            ErpGPS gps = new ErpGPS(user);
            ErpUser update = new ErpUser().setIdResultThis(user.getId()).setRealNameResultThis(user.getReal_name());
            if(gps.getId()== null &&gpsService.insertService(gps)<=0){
                throw resultLogicException("GPS新增失败,请稍后重试");
            }else if(gps.getId() != null &&gpsService.updateServiceByPrimaryKey(gps)<=0){
                throw resultLogicException("请选择有效的GPS设备");
            }else if(userService.updateServiceByPrimaryKey(update)<= 0){
                throw resultLogicException();
            }
            return resultSuccess("操作成功");
        }catch (Exception e){
            rollBack();
            throw e;
        }
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = gpsService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "GPS删除成功", null);
            }
        }
        return new MessageBean(10000, "GPS删除失败", null);
    }

    /**
     * GPS设备列表
     *
     * @return
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpGPS> lists = gpsService.lists();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpGPS> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpGPS gps = gpsService.get(id);
            if (gps != null) {
                return new MessageBean(10001, "", gps);
            }
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    /**
     * GPS告警列表
     *
     * @return
     */
    @RequestMapping("/lists_alarm")
    public MessageBean lists_alarm(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpGPS> lists = gpsService.lists_alarm();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpGPS> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * GPS设备实时信息
     *
     * @return
     */
    @RequestMapping("/lists_real_information")
    public MessageBean lists_real_information(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpGPS> lists = gpsService.lists_real_information();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpGPS> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * GPS异常报告统计
     *
     * @return
     */
    @RequestMapping("/lists_alarm_statistical")
    public MessageBean lists_alarm_statistical(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpGPS> lists = gpsService.lists_real_information();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpGPS> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }
}
