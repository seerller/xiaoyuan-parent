package com.xiaoyuan.model;

import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.tools.TimeUtils;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * gps告警持久类
 */
@Data
@Entity
@Table(name = "tl_erp_gps_alarm")
public class ErpGpsAlarm {
    @Id
    private Integer id;
    /**
     * 用户主键
     */
    private Integer user_id;
    /**
     * gps设备号
     */
    private String gps_sn;
    /**
     * imei号
     */
    private String gps_imei;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 警告处理
     */
    private String gps_handling;
    /**
     * 处理时间
     */
    private String handling_time;
    /**
     * 触发人联系方式
     */
    private String gps_phone;
    /**
     * 排队号
     */
    private String gps_queue;
    /**
     * 警告类型：0是超时未达,1是卸货时间过短 其他默认未知类型
     */
    private Integer gps_alarm_cate;
    /**
     * 订单自增主键
     */
    private Integer order_id;
    /**
     * 运送的货物
     */
    private String gps_send_goods;
    /**
     * 车牌号
     */
    private String car_sn;
    /**
     * 订单号
     */
    private String order_sn;
    /**
     * 终端客户名称
     */
    private String customer_name;
    /**
     * 警告状态
     */
    private Integer status;
    /**
     * 送货地址
     */
    private String send_address;
    /**
     * 处理人
     */
    private String handling_man;
    /**
     * 驾驶员名称
     */
    private String driver_real_name;
    /**
     * 终端用户自增主键
     */
    private Integer customer_id;

    public ErpGpsAlarm setId(Integer id) {
        this.id = id;
        return this;
    }

    public ErpGpsAlarm setUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public ErpGpsAlarm setGps_sn(String gps_sn) {
        this.gps_sn = gps_sn;
        return this;
    }

    public ErpGpsAlarm setGps_imei(String gps_imei) {
        this.gps_imei = gps_imei;
        return this;
    }

    public ErpGpsAlarm setCreate_time(String create_time) {
        this.create_time = create_time;
        return this;
    }

    public ErpGpsAlarm setGps_handling(String gps_handling) {
        this.gps_handling = gps_handling;
        return this;
    }

    public ErpGpsAlarm setHandling_time(String handling_time) {
        this.handling_time = handling_time;
        return this;
    }

    public ErpGpsAlarm setGps_phone(String gps_phone) {
        this.gps_phone = gps_phone;
        return this;
    }

    public ErpGpsAlarm setGps_queue(String gps_queue) {
        this.gps_queue = gps_queue;
        return this;
    }

    public ErpGpsAlarm setGps_alarm_cate(Integer gps_alarm_cate) {
        this.gps_alarm_cate = gps_alarm_cate;
        return this;
    }

    public ErpGpsAlarm setOrder_id(Integer order_id) {
        this.order_id = order_id;
        return this;
    }

    public ErpGpsAlarm setGps_send_goods(String gps_send_goods) {
        this.gps_send_goods = gps_send_goods;
        return this;
    }

    public ErpGpsAlarm setCar_sn(String car_sn) {
        this.car_sn = car_sn;
        return this;
    }

    public ErpGpsAlarm setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
        return this;
    }

    public ErpGpsAlarm setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public ErpGpsAlarm setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ErpGpsAlarm setSend_address(String send_address) {
        this.send_address = send_address;
        return this;
    }

    public ErpGpsAlarm setHandling_man(String handling_man) {
        this.handling_man = handling_man;
        return this;
    }

    public ErpGpsAlarm setDriver_real_name(String driver_real_name) {
        this.driver_real_name = driver_real_name;
        return this;
    }

    public ErpGpsAlarm setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
        return this;
    }


    public ErpGpsAlarm(){}


    /**
     * 自定义构造函数
     * @param order
     */
    public ErpGpsAlarm(ErpPickgoodsDto order){
        this.user_id = order.getUser_id();
        this.create_time = TimeUtils.resultCurrentDate();
        this.gps_phone = order.getDriver_phone();
        this.order_id = order.getId();
        this.gps_send_goods=order.getGoods_name();
        this.car_sn = order.getPickgoods_car_sn();
        this.order_sn = order.getCar_order_sn();
        this.customer_name = order.getCustomer_name();
        this.status = 0;
        this.send_address = order.getUser_address();
        this.driver_real_name = order.getDriver_name();
        this.customer_id = order.getCustomer_id();
    }

}
