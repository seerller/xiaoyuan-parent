package com.xiaoyuan.model;

import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.TimeUtils;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ggggggggggggggggggggg
 * 
 * @author YZH
 * @version 2019年3月5日 下午4:44:18
 *
 *          车辆类，对应车辆表，车辆管理还有进出货司机接单等等涉及
 * @author YZH
 * @version 2019年3月8日 下午2:16:30
 *
 */
@Entity
@Table(name = "tl_erp_car")
public class ErpCar {
	@Id
	private Integer id;

	private Integer user_id;

	private Integer driver_id;
	// 车主
	private String car_owner;
	// 联系方式
	private String car_contact_phone;
	// 净重
	private Double car_weight;
	// m 车长
	private BigDecimal car_length;
	// 载重
	private Double car_load;
	// 车辆编号 read-only
	private String car_sn;
	// 车辆类型 read-only
	private String car_cate;

	private Integer status_;

	private String createtime;

	private String updatetime;

	// read-only
	private String user_name;
	// read-only
	private String user_phone;
	// read-only
	private String card_rfid;
	// read-only
	private String gps_sn;
	// read-only
	private String gps_imei;

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

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}

	public String getCar_owner() {
		return car_owner;
	}

	public void setCar_owner(String car_owner) {
		this.car_owner = car_owner;
	}

	public Double getCar_weight() {
		return car_weight;
	}

	public void setCar_weight(Double car_weight) {
		this.car_weight = car_weight;
	}

	public BigDecimal getCar_length() {
		return car_length;
	}

	public void setCar_length(BigDecimal car_length) {
		this.car_length = car_length;
	}

	public Double getCar_load() {
		return car_load;
	}

	public void setCar_load(Double car_load) {
		this.car_load = car_load;
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

	public String getCar_sn() {
		return car_sn;
	}

	public String getCar_cate() {
		return car_cate;
	}

	public String getCar_contact_phone() {
		return car_contact_phone;
	}

	public void setCar_contact_phone(String car_contact_phone) {
		this.car_contact_phone = car_contact_phone;
	}

	@Override
	public String toString() {
		return "ErpCar [id=" + id + ", user_id=" + user_id + ", driver_id=" + driver_id + ", car_owner=" + car_owner
				+ ", car_contact_phone=" + car_contact_phone + ", car_weight=" + car_weight + ", car_length="
				+ car_length + ", car_load=" + car_load + ", car_sn=" + car_sn + ", car_cate=" + car_cate + ", status_="
				+ status_ + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public String getCard_rfid() {
		return card_rfid;
	}

	public String getGps_sn() {
		return gps_sn;
	}

	public String getGps_imei() {
		return gps_imei;
	}

	public ErpCar(){};

	public ErpCar(ErpUserDto dto){
		this.id = dto.getCarId();
		this.car_owner = dto.getCar_owner();
		this.user_id = dto.getId();
		this.driver_id = dto.getDriverId();
		this.car_contact_phone = dto.getCar_contact_phone();
		this.car_load = null == dto.getCar_load() ? null : dto.getCar_load().doubleValue();
		this.car_weight =null == dto.getCar_weight() ? null : dto.getCar_weight().doubleValue();
		String time = TimeUtils.resultCurrentDate();
		if(this.id == null){
			this.createtime = time;
		}else{
			this.updatetime = time;
		}
	}



}
