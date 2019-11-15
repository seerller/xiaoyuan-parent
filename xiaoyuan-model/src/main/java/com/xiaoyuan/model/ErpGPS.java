package com.xiaoyuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.TimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * GPS设备表
 *
 * @author YZH
 * @author YZH
 * @updateMan zhengweicheng
 * @updateTime  2019-07-19 17:24:00
 * @version 2019年3月8日 下午3:17:59
 */
@Entity
@Table(name = "tl_erp_gps")
public class ErpGPS {
    @Id
    private Integer id;

    private Integer user_id;

    private String gps_sn;

    private Integer car_id;

    private Integer driver_id;
    // 绑定的手机
    private String gps_phone;

    // 排队
    private String gps_queue;
    // 实时车辆
    private String gps_real_car;
    // 送货
    private String gps_send_goods;
    // 实时监控
    private String gps_real_monitoring;
    // 历史轨迹
    private String gps_history_track;
    // 电子围栏
    private String gps_electronic_fence;

    private String gps_imei;

    private String gps_alarm_cate;

    private String gps_handling;

    // read-only
    private String user_name;
    // read-only
    private String user_phone;
    // read-only
    private String car_sn;
    // read_only
    private String car_order_address;

    private Integer status_;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatetime;
    /**
     * 订单主键 ->pickgoodsId
     */
    private Integer order_id;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    /**
     *  创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTimeTxt;

    public Date getCreateTimeTxt() {
        return createTimeTxt;
    }

    public void setCreateTimeTxt(Date createTimeTxt) {
        this.createTimeTxt = createTimeTxt;
    }

    @Override
    public String toString() {
        return "ErpGPS{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", gps_sn='" + gps_sn + '\'' +
                ", car_id=" + car_id +
                ", driver_id=" + driver_id +
                ", gps_phone='" + gps_phone + '\'' +
                ", gps_queue='" + gps_queue + '\'' +
                ", gps_real_car='" + gps_real_car + '\'' +
                ", gps_send_goods='" + gps_send_goods + '\'' +
                ", gps_real_monitoring='" + gps_real_monitoring + '\'' +
                ", gps_history_track='" + gps_history_track + '\'' +
                ", gps_electronic_fence='" + gps_electronic_fence + '\'' +
                ", gps_imei='" + gps_imei + '\'' +
                ", gps_alarm_cate='" + gps_alarm_cate + '\'' +
                ", gps_handling='" + gps_handling + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", car_sn='" + car_sn + '\'' +
                ", car_order_address='" + car_order_address + '\'' +
                ", status_=" + status_ +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setCar_sn(String car_sn) {
        this.car_sn = car_sn;
    }

    public void setCar_order_address(String car_order_address) {
        this.car_order_address = car_order_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGps_sn() {
        return gps_sn;
    }

    public void setGps_sn(String gps_sn) {
        this.gps_sn = gps_sn;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getGps_phone() {
        return gps_phone;
    }

    public void setGps_phone(String gps_phone) {
        this.gps_phone = gps_phone;
    }

    public String getGps_queue() {
        return gps_queue;
    }

    public void setGps_queue(String gps_queue) {
        this.gps_queue = gps_queue;
    }

    public String getGps_real_car() {
        return gps_real_car;
    }

    public void setGps_real_car(String gps_real_car) {
        this.gps_real_car = gps_real_car;
    }

    public String getGps_send_goods() {
        return gps_send_goods;
    }

    public void setGps_send_goods(String gps_send_goods) {
        this.gps_send_goods = gps_send_goods;
    }

    public String getGps_real_monitoring() {
        return gps_real_monitoring;
    }

    public void setGps_real_monitoring(String gps_real_monitoring) {
        this.gps_real_monitoring = gps_real_monitoring;
    }

    public String getGps_history_track() {
        return gps_history_track;
    }

    public void setGps_history_track(String gps_history_track) {
        this.gps_history_track = gps_history_track;
    }

    public String getGps_electronic_fence() {
        return gps_electronic_fence;
    }

    public void setGps_electronic_fence(String gps_electronic_fence) {
        this.gps_electronic_fence = gps_electronic_fence;
    }

    public String getGps_imei() {
        return gps_imei;
    }

    public void setGps_imei(String gps_imei) {
        this.gps_imei = gps_imei;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getCar_sn() {
        return car_sn;
    }

    public String getCar_order_address() {
        return car_order_address;
    }

    public Integer getStatus_() {
        return status_;
    }

    public void setStatus_(Integer status_) {
        this.status_ = status_;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getGps_alarm_cate() {
        return gps_alarm_cate;
    }

    public void setGps_alarm_cate(String gps_alarm_cate) {
        this.gps_alarm_cate = gps_alarm_cate;
    }

    public String getGps_handling() {
        return gps_handling;
    }

    public void setGps_handling(String gps_handling) {
        this.gps_handling = gps_handling;
    }



    public ErpGPS(){}

    public ErpGPS(ErpUserDto dto){
        this.id = dto.getGpsId();
        this.gps_sn = dto.getGps_sn();
        this.gps_imei = dto.getGps_imei();
        this.driver_id =dto.getDriverId();
        String time = TimeUtils.resultCurrentDate();
        if(null!= id){
            this.updatetime = time;
        }else{
            this.createtime = time;
        }
    }


}
