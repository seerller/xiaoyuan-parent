package com.xiaoyuan.model.setManageEntity;

import lombok.Data;

import java.util.List;

@Data
public class GpsSetEntity {
    /**
     * 该类的关键字，通过该关键字进行获取数据
     */
    public static final String SET_VALUE_KEY = "gpsSet";
    /**
     * gps定位精度
     */
    private double gpsSpace;
    /**
     * 选中通知人员的用户主键
     */
    private List<Integer> checkedIds;
}
