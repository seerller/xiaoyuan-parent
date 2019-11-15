package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.ErpPickgoods;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ErpPickgoodsDto extends ErpPickgoods {
    /**
     *  经销商客户编号
     */
    private String customer_company_sn;

    /**
     *  终端编号
     */
    private String customer_sn;

    /**
     * 车辆载重
     */
    private Double car_load;
    private String queue_sn;
    /**
     * 毛重
     */
    private Double grossweight;
    /**
     * 皮重
     */
    private Double tare;
    /**
     * 净重
     */
    private Double netweight;
    /**
     * 第一次称重时间
     */
    private String firstdate;
    /**
     * 第二次称重时间
     */
    private String seconddate;
    /**
     * 中队？司磅员？
     */
    private String squadron;
    /**
     * 备注
     */
    private String remark;
    /**
     *     出库的司机列
     */
    private String driver_name;

    /**
     *  司机姓名
     */
    private String driver_real_name;

    /**
     *  客户姓名
     */
    private String real_name;

    /**
     * 司机单位
     */
    private String driver_company;
    private String user_price;
    private String user_name;
    private String user_company_name;
    private String car_order_date;
    private String goods_price;
    private Double car_order_money;
    private String supplier_name;
    private Double car_order_price;
    private Integer confirm_queue_device;
    private Double price;
    /**
     * 车辆类别 文本格式
     */
    private String carCateTxt;
    /**
     * 挂牌扣款
     */
    private Double deduct_price;
    /**
     * 毛重   --以吨位单位
     */
    private Double grossweightUnit;
    /**
     * 皮重   --以吨位单位
     */
    private Double tareUnit;
    /**
     * 净重   --以吨位单位
     */
    private Double netweightUnit;
    /**
     * 终端客户的坐标
     */
    private String coordinate;
    /**
     * 客户手机号，用来发送短信使用
     */
    private String send_phone;

    public Double getDeduct_Price(){
        return null == this.deduct_price ? 0.00 : this.deduct_price;
    }
}
