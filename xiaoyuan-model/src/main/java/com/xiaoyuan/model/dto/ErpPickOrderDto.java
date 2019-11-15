package com.xiaoyuan.model.dto;
import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.model.ErpTerminalCustomer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单新增保存/修改类
 */
@Data
public class ErpPickOrderDto {
    /**
     * 订单主键
     */
    private Integer pickgoodsId;
    /**
     * 车辆信息主键
     */
    private Integer driverId;
    /**
     * 终端用户主键
     */
    private Integer customerId;
    /**
     * 购买数量
     */
    private BigDecimal buyNum;
    /**
     * 每车数量
     */
    private BigDecimal everyNum;
    /**
     * 车辆总数
     */
    private Integer driverTotal;
    /**
     * 送货时间
     */
    private String sendDay;

    //以下三个对象是返回修改订单数据的。
    /**
     * 订单信息对象
     */
    private ErpPickgoods order;
    /**
     *  终端客户对象
     */
    private ErpTerminalCustomer customer;
    /**
     *  车辆信息对象
     */
    private ErpDriverDto driver;

    public ErpPickOrderDto(){

    }
    public ErpPickOrderDto(ErpPickgoods order,ErpTerminalCustomer customer,ErpDriverDto driver){
        this.order = order;
        this.customer = customer;
        this.driver = driver;
    }
}
