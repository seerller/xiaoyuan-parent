package com.xiaoyuan.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpRegion;
import com.xiaoyuan.service.IErpRegionSerivce;
import com.xiaoyuan.tools.MessageBean;

@CrossOrigin
@RestController
@RequestMapping("/Region")
public class ErpRegionController {
    /**
     * YZH 拼错了。。。
     */
    @Autowired
    private IErpRegionSerivce regionSerivce;

    /**
     * 地址对象的属性的类型为数值，不是字符串，所以转换只能放在前端了，
     * <p>
     * 前端发送请求，返回地址对象，属性的数据类型是数值，无法赋值为字符串
     *
     * @param parent_id
     * @param region_type
     * @param show
     * @return 2019年3月4日下午7:53:51
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(value = "parent_id", required = false) Integer parent_id,
            @RequestParam(value = "region_type", required = false) Integer region_type,
            @RequestParam(value = "show", required = false) String show) {
        ErpRegion region = new ErpRegion();
        region.setParent_id(parent_id);
        region.setRegion_type(region_type);
        region.setPinyin(show);
        List<ErpRegion> lists = regionSerivce.lists(region);
        if (lists != null) {
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * 新增的控制器，根据地址的数字返回地址的字符串
     *
     * @param province_id
     * @param city_id
     * @param area_id
     * @return 2019年3月5日上午10:55:34
     * @author : YZH
     */
    @RequestMapping("/string_address")
    public MessageBean string_address(@RequestParam(value = "province_id", required = false) Integer province_id,
                                      @RequestParam(value = "city_id", required = false) Integer city_id,
                                      @RequestParam(value = "area_id", required = false) Integer area_id) {
        // 传id的话直接根据id查数据库行记录，查三次
        // 要返回的内容声明
        String province = null;
        String city = null;
        String area = null;
        // 查询用的参数声明
        ErpRegion region = new ErpRegion();
        // 装返回内容的容器初始化
        Map<String, Object> map = new LinkedHashMap<>();
        // 执行查询，赋值及返回
        if (province_id != null && city_id != null && area_id != null) {
            // province
            region.setId(province_id);
            // 改了这个方法的SQL语句加了if test，需要查看是否会影响其他调用地方，，
            // 打开调用界面，发现只有我新写的调用了？？
            region = this.regionSerivce.get_address(region);// TODO
            province = region.getTitle();
            // city
            region.setId(city_id);
            region = this.regionSerivce.get_address(region);
            city = region.getTitle();
            // area
            region.setId(area_id);
            region = this.regionSerivce.get_address(region);
            area = region.getTitle();
            // 装容器
            map.put("province", province);
            map.put("city", city);
            map.put("area", area);
            // 返回
            return new MessageBean(10001, "", map);
        }
        return new MessageBean(10000, "参数不全", null);
    }

}
