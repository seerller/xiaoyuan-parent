package com.xiaoyuan.model.setManageEntity;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 管理设置数据持久类
 * @action 该类进行JSON格式化存入数据与转化JSON格式保存数据。
 * @author zhengweicheng
 */
@Data
public class CommonSetEntity {
    /**
     * 该类的关键字，通过该关键字进行获取数据
     */
    public static final String SET_VALUE_KEY = "commonSet";
    /**
     * 订单超时设置值
     */
    private Integer orderTime;
    /**
     * 最小提货值
     */
    private BigDecimal minNum;
    /**
     * 每天限量吨数
     */
    private BigDecimal everyLimitNum;
    /**
     * 限制范围-开始时间
     */
    private String limitStartDay;
    /**
     * 限制范围-结束时间
     */
    private String limitEndDay;
}
