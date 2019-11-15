package com.xiaoyuan.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 重量类，对应重量表？好像没怎么用过，打开调用视图，在那个类，硬件？
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:40:57
 *
 */
@Entity
@Table(name = "tl_erp_pound")
public class ErpPound {
	@Id
	private Integer id;
	private String pickgoods_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String firstdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String seconddate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createtime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatetime;
	private Integer status;
	private Double grossweight;
	private Double tare;
	private Double netweight;
	private String car_sn;
	private String card_rfid;
	private Integer equipment_id;
	private String squadron;
	private String remark;
	private String order_sn;
	private String goods_name;
	private String goods_sn;
	private String user_company_name;
	private String supplier_spec;
	private String user_name;
	private Integer o_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPickgoods_id() {
		return pickgoods_id;
	}

	public void setPickgoods_id(String pickgoods_id) {
		this.pickgoods_id = pickgoods_id;
	}

	public String getFirstdate() {
		return firstdate;
	}

	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}

	public String getSeconddate() {
		return seconddate;
	}

	public void setSeconddate(String seconddate) {
		this.seconddate = seconddate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCar_sn() {
		return car_sn;
	}

	public void setCar_sn(String car_sn) {
		this.car_sn = car_sn;
	}

	public String getCard_rfid() {
		return card_rfid;
	}

	public void setCard_rfid(String card_rfid) {
		this.card_rfid = card_rfid;
	}

	public Integer getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(Integer equipment_id) {
		this.equipment_id = equipment_id;
	}

	public Double getGrossweight() {
		return grossweight;
	}

	public void setGrossweight(Double grossweight) {
		this.grossweight = grossweight;
	}

	public Double getTare() {
		return tare;
	}

	public void setTare(Double tare) {
		this.tare = tare;
	}

	public Double getNetweight() {
		return netweight;
	}

	public void setNetweight(Double netweight) {
		this.netweight = netweight;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public String getGoods_sn() {
		return goods_sn;
	}

	public String getUser_company_name() {
		return user_company_name;
	}

	public String getSupplier_spec() {
		return supplier_spec;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getSquadron() {
		return squadron;
	}

	public void setSquadron(String squadron) {
		this.squadron = squadron;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getO_id() {
		return o_id;
	}

}
