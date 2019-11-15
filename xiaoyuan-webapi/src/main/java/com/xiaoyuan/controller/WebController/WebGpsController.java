package com.xiaoyuan.controller.WebController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpGpsAlarmService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * gps通用接口
 */
@RestController
@RequestMapping("/gps")
@Slf4j
public class WebGpsController extends BaseController {

    @Autowired
    IErpGpsAlarmService gpsDeviceService;
    @Autowired
    IErpUserService userService;
    @Autowired
    IErpDriverService driverService;
    /**
     *  gps设备列表
     * @param search
     * @return
     */
    @RequestMapping("/gpsAlarmTable")
    public MessageBean gpsAlarmTable(ErpGpsAlarmSearch search){
        if(!resultByUserCate(3)){
            return resultFailed("该功能只对园区用户开放.");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",gpsDeviceService.selectBySearchOfPage(search));
        map.put("countNum",gpsDeviceService.countBySearch(search));
        return resultSuccess(map);
    }
}
