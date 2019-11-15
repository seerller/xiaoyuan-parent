package com.xiaoyuan.model;

import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.TimeUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ggggggggggggg
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午2:16:54
 */
@Entity
@Table(name = "tl_erp_card")
public class ErpCard {
    @Id
    private Integer id;

    private Integer gps_id;

    private Integer driver_id;

    private String card_cate;

    private String card_open_date;
    // rfid
    private String card_rfid;

    private String card_state;
    // 车牌号 read-only
    //20190408 改为car_sn 不是card_sn
    private String car_sn;

    private Integer status_;

    private String createtime;

    private String updatetime;

    @Override
    public String toString() {
        return "ErpCard{" +
                "id=" + id +
                ", gps_id=" + gps_id +
                ", driver_id=" + driver_id +
                ", card_cate='" + card_cate + '\'' +
                ", card_open_date='" + card_open_date + '\'' +
                ", card_rfid='" + card_rfid + '\'' +
                ", card_state='" + card_state + '\'' +
                ", car_sn='" + car_sn + '\'' +
                ", status_=" + status_ +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    public String getCar_sn() {
        return car_sn;
    }

    public void setCar_sn(String car_sn) {
        this.car_sn = car_sn;
    }

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

    public String getCard_cate() {
        return card_cate;
    }

    public void setCard_cate(String card_cate) {
        this.card_cate = card_cate;
    }

    public String getCard_open_date() {
        return card_open_date;
    }

    public void setCard_open_date(String card_open_date) {
        this.card_open_date = card_open_date;
    }

    public String getCard_rfid() {
        return card_rfid;
    }

    public void setCard_rfid(String car_rfid) {
        this.card_rfid = car_rfid;
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

    public String getCard_state() {
        return card_state;
    }

    public void setCard_state(String card_state) {
        this.card_state = card_state;
    }

    public static final String OPEN = "正常";
    public static final String CLOSE = "停用";


    public ErpCard(){}

    /**
     * 自定义构造函数
     * 主要作用于web传入用户综合对象进行插入/修改操作
     * @param dto
     */
    public ErpCard(ErpUserDto dto){
        this.id = dto.getCardId();
        this.driver_id = dto.getDriverId();
        this.card_rfid =dto.getCard_rfid();
        this.card_open_date=dto.getCard_open_date();
        this.status_ = dto.getCardStatus();
        this.card_cate =dto.getCarCate();
        if(this.status_ == 1){
            this.card_state = OPEN;
        }else if(this.status_ != 1){
            this.card_state = CLOSE;
        }
        String time = TimeUtils.resultCurrentDate();
        if(null!= id){
            this.updatetime = time;
        }else{
            this.createtime = time;
        }
    }

}
