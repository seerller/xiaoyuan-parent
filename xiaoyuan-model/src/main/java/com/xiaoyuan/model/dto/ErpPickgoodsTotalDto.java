package com.xiaoyuan.model.dto;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 微信公众号数据统计返回类
 */
@Data
public class ErpPickgoodsTotalDto {
    /**
     * 完成订单数量
     */
    private Integer overOrderNum;
    /**
     * 提货数量
     */
    private BigDecimal pickGoodsNum;
    /**
     *  消费金额
     */
    private BigDecimal consumeAmountMoney;
    /**
     * 充值金额
     */
    private String accountAmountMoney;
}
