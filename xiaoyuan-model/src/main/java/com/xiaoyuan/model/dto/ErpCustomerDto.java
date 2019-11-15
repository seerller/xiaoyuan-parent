package com.xiaoyuan.model.dto;

import lombok.Data;
import com.xiaoyuan.model.ErpTerminalCustomer;

@Data
public class ErpCustomerDto extends ErpTerminalCustomer {
    /**
     * 省    文本格式
     */
    private String provinceTxt;
    /**
     * 市    文本格式
     */
    private String cityTxt;
    /**
     * 区    文本格式
     */
    private String areaTxt;
    /**
     * 结算价  文本格式
     */
    private String priceTxt;

    /**
     * 用户编号
     */
    private String number;
}
