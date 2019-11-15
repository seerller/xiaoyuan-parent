package com.xiaoyuan.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * gps设备持久类
 */
@Data
@Entity
@Table(name = "tl_erp_gps_device")
public class ErpGpsDevice {
    @Id
    private Integer id;
    /**
     * 用户主键
     */
    private Integer user_id;
    /**
     * 车辆信息主键
     */
    private Integer car_id;
    /**
     * 驾驶员主键
     */
    private Integer driver_id;
    /**
     * 设备id
     */
    private String device_id;
    /**
     * imei号
     */
    private String imei_sn;

    public ErpGpsDevice setId(Integer id) {
        this.id = id;
        return this;
    }

    public ErpGpsDevice setUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public ErpGpsDevice setCar_id(Integer car_id) {
        this.car_id = car_id;
        return this;
    }

    public ErpGpsDevice setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    public ErpGpsDevice setDevice_id(String device_id) {
        this.device_id = device_id;
        return this;
    }
    public ErpGpsDevice setImei_sn(String imei_sn) {
        this.imei_sn = imei_sn;
        return this;
    }
}
