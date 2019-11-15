package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.ErpAccount;
import lombok.Data;
import lombok.ToString;


/**
 * 公众号：充值信息持久类
 */
@Data
@ToString(callSuper = true)
public class ErpAccountDto extends ErpAccount {
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 充值类型文本信息
     * 后期充值类型明确后会在sql中直接返回定义文本
     */
    private String accountTypeTxt;
}
