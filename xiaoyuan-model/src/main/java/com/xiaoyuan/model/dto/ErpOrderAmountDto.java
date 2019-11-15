package com.xiaoyuan.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class ErpOrderAmountDto {
    /**
     * 已完成订单数量
     */
    private Integer overNum;
    /**
     * 未完成订单数量
     */
    private Integer actionNum;
    /**
     * 取消订单数量
     */
    private Integer cannelNum;
    /**
     *  出/入库数量
     */
    private BigDecimal totalNum;
    /**
     * 销售金额
     */
    private BigDecimal sellPrice;
}
