package com.xiaoyuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoyuan.tools.TimeUtils;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户类，对应账户表，账户管理有用
 *
 * @author YZH
 * @version 2019年3月8日 下午2:13:43
 */
@Entity
@Table(name = "tl_erp_account")
@Data
public class ErpAccount {
    @Id
    private Integer id;

    private Integer supplier_id;

    // 累计充值
    private Double account_totalrecharge;
    // 充值
    private Double account_recharge;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String account_time;

    private String account_date;
    // 金额
    private Double account_price;
    // 流水号
    private String account_serial;
    // 银行卡
    private String account_bank_card;
    // 备注
    private String account_remark;

    private String account_detail;

    private Integer audit;

    private Integer user_id;
    // read-only
    private String user_name;
    // read_only
    private String user_company_name;
    // read-only
    private String rebate_balance;
    // read-only
    private String user_company_sn;

    private String account_aduit;

    private String account_information;

    private Integer status_;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatetime;

    private String audit_man;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String audit_datetime;
    private Integer type;
    /**
     * 充值类型
     * 0.银行转账、1.承兑汇票、2.月底返点、3.退货款 4.挂牌扣款
     */
    private Integer accountType;

    /**
     *  挂牌扣款 常量
     */
    public static final Integer ACCOUNT_TYPE_LISTING_DEDUCTION = 4;
    /**
     *  退货款 常量
     */
    public static final Integer ACCOUNT_TYPE_QUIT = 3;
    /**
     *  订单退款 常量
     */
    public static final Integer ACCOUNT_TYPE_ORDER_QUIT = 5;
    /**
     *  创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTimeTxt;

    public ErpAccount(){

    }

    /**
     * 自定义构造函数
     * @param user_id
     * @param audit_man
     * @param account_remark
     * @param number
     * @param type
     */
    public ErpAccount(Integer user_id,String audit_man,String account_remark,BigDecimal number, Integer type){
        this.user_id = user_id;
        this.accountType = type;
        this.audit_man = audit_man;
        this.account_price  = number.doubleValue();
        this.account_time = TimeUtils.resultCurrentDate();
        this.createtime = TimeUtils.resultCurrentDate();
        this.audit_datetime = TimeUtils.resultCurrentDate();
        this.account_remark = account_remark;
    }
}
