package com.xiaoyuan.model.search;

import lombok.Data;
import lombok.ToString;


/**
 * GPS设备条件搜索类
 * @author zhengweicheng
 * createTime 2019-06-13
 */
@Data
@ToString(callSuper = true)
public class ErpGpsDeviceSearch extends BaseSearch {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 根据车牌号查询订单
     */
    private String carSn;
    /**
     * gps设备主键
     */
    private Integer id;
}
