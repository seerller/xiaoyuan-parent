package com.xiaoyuan.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆预约
 *
 * @author MyPC
 */

/**
 * 车辆订单类，对应车辆订单表，进出货，，
 *
 * @author YZH
 * @version 2019年3月8日 下午2:32:38
 */
@Entity
@Table(name = "tl_erp_car_order")
public class ErpCarOrder {
    @Id
    private Integer id;
    private Integer gps_id;
    private Integer driver_id;
    // 订单编号
    private String car_order_sn;
    //
    private String car_order_date;
    // 订单数量
    private Double car_order_num;
    // 提货数量
    private Double car_order_pick_num;
    // 剩余数量
    private Double car_order_remain_num;

    private BigDecimal car_order_money;
    // 预约时间
    private Integer car_order_senddate;
    // 提货时间
    private String car_order_pickdate;
    // 备注
    private String car_order_remark;

    private Integer status_;

    private String createtime;

    private String updatetime;

    private String car_order_nowstate;

    private String car_order_thenstate;

    private String in_time;

    private String out_time;

    private String car_order_address;

    private String car_order_id;

    private double car_order_price;
    // 提货单编号
    private Integer pickgoods_id;

    private String pickgoods_send_company;
    private String pickgoods_date;

    private String car_sn;
    private String car_cate;
    // read-only
    private String user_company_name;
    // read-only
    private double car_load;
    private double pickgoods_num;
    // 选择的订单号
    private Long choose_sn;
    // 排队号
    private Long queue_sn;
    // 生成排队号的时间
    private String time;

    //20190327 read-only pickgoods
    private Integer is_car_order;
    private Integer is_confirm_queue;
    private String confirm_queue_datetime;
    private String province_txt;
    private String city_txt;
    private String area_txt;


   public ErpCarOrder(){

    }
    public ErpCarOrder(Integer pickgoods_id){
        this.pickgoods_id =pickgoods_id;
    }

    /**
     * 自定义构造函数
     * @param gpsId                     gps主键
     * @param driverId                  设备信息主键
     * @param car_order_sn              车辆订单编号
     * @param car_order_num             订单数量
     * @param car_order_pick_num        订单已配送多少数量
     * @param car_order_remain_num      订单剩余多少数量未发送
     * @param car_order_address         订单配送地址
     * @param car_order_money           订单金额
     * @param pickgoods_id              用户订单主键
     */
    public ErpCarOrder(Integer gpsId,Integer driverId,String car_order_sn,BigDecimal car_order_num,
                       BigDecimal car_order_pick_num,BigDecimal car_order_remain_num,String car_order_address,
                       BigDecimal car_order_money,Integer pickgoods_id){
            this.gps_id = gpsId;
            this.driver_id = driverId;
            this.car_order_sn = car_order_sn;
            this.car_order_num = car_order_num.doubleValue();
            this.car_order_pick_num = car_order_pick_num.doubleValue();
            this.car_order_remain_num = car_order_remain_num.doubleValue();
            this.car_order_address = car_order_address;
            this.car_order_money = car_order_money;
            this.pickgoods_id = pickgoods_id;
    }

    public ErpCarOrder(Integer gpsId,Integer driverId){
        this.gps_id = gpsId;
        this.driver_id = driverId;
    }




    //TODO 增加和订单类一样的接单状态？


    @Override
    public String toString() {
        return "ErpCarOrder{" +
                "id=" + id +
                ", gps_id=" + gps_id +
                ", driver_id=" + driver_id +
                ", car_order_sn='" + car_order_sn + '\'' +
                ", car_order_date='" + car_order_date + '\'' +
                ", car_order_num=" + car_order_num +
                ", car_order_pick_num=" + car_order_pick_num +
                ", car_order_remain_num=" + car_order_remain_num +
                ", car_order_money=" + car_order_money +
                ", car_order_senddate=" + car_order_senddate +
                ", car_order_pickdate='" + car_order_pickdate + '\'' +
                ", car_order_remark='" + car_order_remark + '\'' +
                ", status_=" + status_ +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", car_order_nowstate='" + car_order_nowstate + '\'' +
                ", car_order_thenstate='" + car_order_thenstate + '\'' +
                ", in_time='" + in_time + '\'' +
                ", out_time='" + out_time + '\'' +
                ", car_order_address='" + car_order_address + '\'' +
                ", car_order_id='" + car_order_id + '\'' +
                ", car_order_price=" + car_order_price +
                ", pickgoods_id=" + pickgoods_id +
                ", pickgoods_send_company='" + pickgoods_send_company + '\'' +
                ", pickgoods_date='" + pickgoods_date + '\'' +
                ", car_sn='" + car_sn + '\'' +
                ", car_cate='" + car_cate + '\'' +
                ", user_company_name='" + user_company_name + '\'' +
                ", car_load=" + car_load +
                ", pickgoods_num=" + pickgoods_num +
                ", choose_sn=" + choose_sn +
                ", queue_sn=" + queue_sn +
                ", time='" + time + '\'' +
                ", is_car_order=" + is_car_order +
                ", is_confirm_queue=" + is_confirm_queue +
                ", confirm_queue_datetime='" + confirm_queue_datetime + '\'' +
                ", province_txt='" + province_txt + '\'' +
                ", city_txt='" + city_txt + '\'' +
                ", area_txt='" + area_txt + '\'' +
                ", accept_order=" + accept_order +
                '}';
    }

    public String getProvince_txt() {
        return province_txt;
    }

    public void setProvince_txt(String province_txt) {
        this.province_txt = province_txt;
    }

    public String getCity_txt() {
        return city_txt;
    }

    public void setCity_txt(String city_txt) {
        this.city_txt = city_txt;
    }

    public String getArea_txt() {
        return area_txt;
    }

    public void setArea_txt(String area_txt) {
        this.area_txt = area_txt;
    }

    public Integer getIs_confirm_queue() {
        return is_confirm_queue;
    }

    public void setIs_confirm_queue(Integer is_confirm_queue) {
        this.is_confirm_queue = is_confirm_queue;
    }

    public String getConfirm_queue_datetime() {
        return confirm_queue_datetime;
    }

    public void setConfirm_queue_datetime(String confirm_queue_datetime) {
        this.confirm_queue_datetime = confirm_queue_datetime;
    }

    public void setUser_company_name(String user_company_name) {
        this.user_company_name = user_company_name;
    }

    public void setCar_load(double car_load) {
        this.car_load = car_load;
    }

    public void setPickgoods_num(double pickgoods_num) {
        this.pickgoods_num = pickgoods_num;
    }

    public Integer getIs_car_order() {
        return is_car_order;
    }

    public void setIs_car_order(Integer is_car_order) {
        this.is_car_order = is_car_order;
    }

    public Integer getAccept_order() {
        return accept_order;
    }

    public void setAccept_order(Integer accept_order) {
        this.accept_order = accept_order;
    }

    public void setPickgoods_send_company(String pickgoods_send_company) {
        this.pickgoods_send_company = pickgoods_send_company;
    }

    public void setPickgoods_date(String pickgoods_date) {
        this.pickgoods_date = pickgoods_date;
    }

    public void setCar_sn(String car_sn) {
        this.car_sn = car_sn;
    }

    public void setCar_cate(String car_cate) {
        this.car_cate = car_cate;
    }

    private Integer accept_order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGps_id() {
        return gps_id;
    }

    public void setGps_id(Integer gps_id) {
        this.gps_id = gps_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getCar_order_sn() {
        return car_order_sn;
    }

    public void setCar_order_sn(String car_order_sn) {
        this.car_order_sn = car_order_sn;
    }

    public String getCar_order_date() {
        return car_order_date;
    }

    public void setCar_order_date(String car_order_date) {
        this.car_order_date = car_order_date;
    }

    public Double getCar_order_num() {
        return car_order_num;
    }

    public void setCar_order_num(Double car_order_num) {
        this.car_order_num = car_order_num;
    }

    public Double getCar_order_pick_num() {
        return car_order_pick_num;
    }

    public void setCar_order_pick_num(Double car_order_pick_num) {
        this.car_order_pick_num = car_order_pick_num;
    }

    public Double getCar_order_remain_num() {
        return car_order_remain_num;
    }

    public void setCar_order_remain_num(Double car_order_remain_num) {
        this.car_order_remain_num = car_order_remain_num;
    }

    public BigDecimal getCar_order_money() {
        return car_order_money;
    }

    public void setCar_order_money(BigDecimal car_order_money) {
        this.car_order_money = car_order_money;
    }

    public Integer getCar_order_senddate() {
        return car_order_senddate;
    }

    public void setCar_order_senddate(Integer car_order_senddate) {
        this.car_order_senddate = car_order_senddate;
    }

    public String getCar_order_pickdate() {
        return car_order_pickdate;
    }

    public void setCar_order_pickdate(String car_order_pickdate) {
        this.car_order_pickdate = car_order_pickdate;
    }

    public String getCar_order_remark() {
        return car_order_remark;
    }

    public void setCar_order_remark(String car_order_remark) {
        this.car_order_remark = car_order_remark;
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

    public String getCar_order_nowstate() {
        return car_order_nowstate;
    }

    public void setCar_order_nowstate(String car_order_nowstate) {
        this.car_order_nowstate = car_order_nowstate;
    }

    public String getCar_order_thenstate() {
        return car_order_thenstate;
    }

    public void setCar_order_thenstate(String car_order_thenstate) {
        this.car_order_thenstate = car_order_thenstate;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getCar_order_address() {
        return car_order_address;
    }

    public void setCar_order_address(String car_order_address) {
        this.car_order_address = car_order_address;
    }

    public String getCar_order_id() {
        return car_order_id;
    }

    public void setCar_order_id(String car_order_id) {
        this.car_order_id = car_order_id;
    }

    public Integer getStatus_() {
        return status_;
    }

    public void setStatus_(Integer status_) {
        this.status_ = status_;
    }

    public double getCar_order_price() {
        return car_order_price;
    }

    public void setCar_order_price(double car_order_price) {
        this.car_order_price = car_order_price;
    }

    public Integer getPickgoods_id() {
        return pickgoods_id;
    }

    public void setPickgoods_id(Integer pickgoods_id) {
        this.pickgoods_id = pickgoods_id;
    }

    public String getPickgoods_send_company() {
        return pickgoods_send_company;
    }

    public String getPickgoods_date() {
        return pickgoods_date;
    }

    public String getCar_sn() {
        return car_sn;
    }

    public String getCar_cate() {
        return car_cate;
    }

    public String getUser_company_name() {
        return user_company_name;
    }

    public double getCar_load() {
        return car_load;
    }

    public double getPickgoods_num() {
        return pickgoods_num;
    }

    public Long getChoose_sn() {
        return choose_sn;
    }

    public void setChoose_sn(Long choose_sn) {
        this.choose_sn = choose_sn;
    }

    public Long getQueue_sn() {
        return queue_sn;
    }

    public void setQueue_sn(Long queue_sn) {
        this.queue_sn = queue_sn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
