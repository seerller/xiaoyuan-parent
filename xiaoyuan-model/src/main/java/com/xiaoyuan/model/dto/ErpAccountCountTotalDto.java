package com.xiaoyuan.model.dto;
import lombok.Data;

/**
 * 公众号：
 *          账户支出/入账总数持久类
 */
@Data
public class ErpAccountCountTotalDto {
    /**
     * 入账总数
     */
    private String inAccountTotal;
    /**
     * 支出总数
     */
    private String outAccountTotal;
}
