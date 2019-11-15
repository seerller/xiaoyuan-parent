package com.xiaoyuan.model;

import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.TimeUtils;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规范规范规范。。。。
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午2:41:05
 */
@Entity
@Table(name = "tl_erp_driver")
public class ErpDriver {
    @Id
    private Integer id;
    private Integer user_id;
    private String car_sn;
    private String driver_sn;
    private String driver_use_date;
    private String driver_eff_date;
    private String car_cate;
    private Integer status_;
    private String createtime;
    private String updatetime;
    private String driver_delivery_sn;
    // read-only
    private String user_name;
    // read-only
    private Integer user_cate;
    // 车主
    private String car_owner;
    // 联系方式
    private String car_contact_phone;
    // 净重
    private BigDecimal car_weight;
    // m 车长
    private BigDecimal car_length;
    // 载重
    private BigDecimal car_load;
    // read-only
    private String gps_sn;
    // read-only
    private String gps_imei;
    private String user_wechar;//read-only 20190330 Sat. 15:16
    private String user_company_sn;
    private String user_company_name;//设计，从需求开始，20190322，需求分析文档
    // read-only
    private String user_phone;
    private String car_order_sn;
    private double grossweight;
    private String order_sn;
    // read-only
    private String card_rfid;// TODO ...
    private Integer o_id;
    private String number;
    private String real_name;
    /**
     * 是否是内部车辆
     * 1则是，0则否
     */
    private Integer is_inside;
    /**
     * 伪删除字段
     */
    private Integer stop;

    @Override
    public String toString() {
        return "ErpDriver{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", car_sn='" + car_sn + '\'' +
                ", driver_sn='" + driver_sn + '\'' +
                ", driver_use_date='" + driver_use_date + '\'' +
                ", driver_eff_date='" + driver_eff_date + '\'' +
                ", car_cate='" + car_cate + '\'' +
                ", status_=" + status_ +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", driver_delivery_sn='" + driver_delivery_sn + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_cate=" + user_cate +
                ", car_owner='" + car_owner + '\'' +
                ", car_contact_phone='" + car_contact_phone + '\'' +
                ", car_weight=" + car_weight +
                ", car_length=" + car_length +
                ", car_load=" + car_load +
                ", gps_sn='" + gps_sn + '\'' +
                ", gps_imei='" + gps_imei + '\'' +
                ", user_wechar='" + user_wechar + '\'' +
                ", user_company_sn='" + user_company_sn + '\'' +
                ", user_company_name='" + user_company_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", car_order_sn='" + car_order_sn + '\'' +
                ", grossweight=" + grossweight +
                ", order_sn='" + order_sn + '\'' +
                ", card_rfid='" + card_rfid + '\'' +
                ", o_id=" + o_id +
                ", number='" + number + '\'' +
                ", real_name='" + real_name + '\'' +
                '}';
    }

    public Integer getIs_inside() {
        return is_inside;
    }

    public void setIs_inside(Integer is_inside) {
        this.is_inside = is_inside;
    }

    public String getUser_company_sn() {
        return user_company_sn;
    }

    public void setUser_company_sn(String user_company_sn) {
        this.user_company_sn = user_company_sn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getUser_wechar() {
        return user_wechar;
    }

    public void setUser_wechar(String user_wechar) {
        this.user_wechar = user_wechar;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_cate(Integer user_cate) {
        this.user_cate = user_cate;
    }

    public void setCar_owner(String car_owner) {
        this.car_owner = car_owner;
    }

    public void setCar_contact_phone(String car_contact_phone) {
        this.car_contact_phone = car_contact_phone;
    }

    public void setCar_weight(BigDecimal car_weight) {
        this.car_weight = car_weight;
    }

    public void setCar_length(BigDecimal car_length) {
        this.car_length = car_length;
    }

    public void setCar_load(BigDecimal car_load) {
        this.car_load = car_load;
    }

    public void setGps_sn(String gps_sn) {
        this.gps_sn = gps_sn;
    }

    public void setGps_imei(String gps_imei) {
        this.gps_imei = gps_imei;
    }

    public String getUser_company_name() {
        return user_company_name;
    }

    public void setUser_company_name(String user_company_name) {
        this.user_company_name = user_company_name;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setCar_order_sn(String car_order_sn) {
        this.car_order_sn = car_order_sn;
    }

    public void setGrossweight(double grossweight) {
        this.grossweight = grossweight;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public void setCard_rfid(String card_rfid) {
        this.card_rfid = card_rfid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCar_sn() {
        return car_sn;
    }

    public void setCar_sn(String car_sn) {
        this.car_sn = car_sn;
    }

    public String getDriver_sn() {
        return driver_sn;
    }

    public void setDriver_sn(String driver_sn) {
        this.driver_sn = driver_sn;
    }

    public String getDriver_use_date() {
        return driver_use_date;
    }

    public void setDriver_use_date(String driver_use_date) {
        this.driver_use_date = driver_use_date;
    }

    public String getDriver_eff_date() {
        return driver_eff_date;
    }

    public void setDriver_eff_date(String driver_eff_date) {
        this.driver_eff_date = driver_eff_date;
    }

    public String getCar_cate() {
        return car_cate;
    }

    public void setCar_cate(String car_cate) {
        this.car_cate = car_cate;
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

    public String getDriver_delivery_sn() {
        return driver_delivery_sn;
    }

    public void setDriver_delivery_sn(String driver_delivery_sn) {
        this.driver_delivery_sn = driver_delivery_sn;
    }

    public String getUser_name() {
        return user_name;
    }

    public Integer getUser_cate() {
        return user_cate;
    }

    public String getCar_owner() {
        return car_owner;
    }

    public String getCar_contact_phone() {
        return car_contact_phone;
    }

    public BigDecimal getCar_weight() {
        return car_weight;
    }

    public BigDecimal getCar_length() {
        return car_length;
    }

    public BigDecimal getCar_load() {
        return car_load;
    }

    public String getGps_sn() {
        return gps_sn;
    }

    public String getGps_imei() {
        return gps_imei;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getCard_rfid() {
        return card_rfid;
    }

    public String getCar_order_sn() {
        return car_order_sn;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public double getGrossweight() {
        return grossweight;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    /**
     * 车辆类别主键
     */
    private Integer cate_id;
    /**
     * 物料id
     */
    private Integer goods_id;
    /**
     * 物料名称
     */
    private String goods_name;

    public Integer getCate_id() {
        return cate_id;
    }
    public void setCate_id(Integer cate_id) {
        this.cate_id = cate_id;
    }

    public ErpDriver(){}


    private Integer car_id;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    /**
     * web端司机资料修改/新增对象
     * @param userDto
     */
    public ErpDriver(ErpUserDto userDto){
        this.id = userDto.getDriverId();
        this.user_id = userDto.getId();
        this.car_sn = userDto.getCar_sn();
        this.driver_sn = userDto.getDriver_sn();
        this.cate_id = userDto.getCate_id();
        if(this.id == null){
            this.createtime = TimeUtils.resultCurrentDate();
        }else{
            this.updatetime = TimeUtils.resultCurrentDate();
        }
        this.is_inside = userDto.getIs_inside();
        this.goods_id = userDto.getGoods_id();
        this.goods_name = userDto.getGoods_name();
    }

}
