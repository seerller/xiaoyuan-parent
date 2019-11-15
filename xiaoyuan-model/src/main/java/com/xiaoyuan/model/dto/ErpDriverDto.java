package com.xiaoyuan.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class ErpDriverDto {
    /**
     * 车辆主键
     */
    private Integer driverId;
    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 车牌号
     */
    private String carSn;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     *  最大载重
     */
    private BigDecimal carLoad;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 该司机所隶属的公司名称
     */
    private String userCompanyName;


}
