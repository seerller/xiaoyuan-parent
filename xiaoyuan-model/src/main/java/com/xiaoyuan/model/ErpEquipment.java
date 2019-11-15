package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 又是设备类？和device有啥不同呢？。。。
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:42:01
 *
 */
@Entity
@Table(name = "tl_erp_equipment")
public class ErpEquipment {
	@Id
	private Integer id;

	private String equipment_name;

	private String equipment_sn;

	private String equipment_area;

	private String equipment_ip;
	// 录入人
	private String equipment_enter;
	// 工作状态
	private String equipment_state;

	private String equipment_date;
	// 原因
	private String equipment_reason;
	// 维护人
	private String equipment_maintainer;
	// 干预人员
	private String equipment_meddle;

	private Integer status_;

	private String createtime;

	private String updatetime;

	private String equipment_cate;
	private String equipment_addr;
	private String equipment_position;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getEquipment_sn() {
		return equipment_sn;
	}

	public void setEquipment_sn(String equipment_sn) {
		this.equipment_sn = equipment_sn;
	}

	public String getEquipment_area() {
		return equipment_area;
	}

	public void setEquipment_area(String equipment_area) {
		this.equipment_area = equipment_area;
	}

	public String getEquipment_ip() {
		return equipment_ip;
	}

	public void setEquipment_ip(String equipment_ip) {
		this.equipment_ip = equipment_ip;
	}

	public String getEquipment_enter() {
		return equipment_enter;
	}

	public void setEquipment_enter(String equipment_enter) {
		this.equipment_enter = equipment_enter;
	}

	public String getEquipment_state() {
		return equipment_state;
	}

	public void setEquipment_state(String equipment_state) {
		this.equipment_state = equipment_state;
	}

	public String getEquipment_date() {
		return equipment_date;
	}

	public void setEquipment_date(String equipment_date) {
		this.equipment_date = equipment_date;
	}

	public String getEquipment_reason() {
		return equipment_reason;
	}

	public void setEquipment_reason(String equipment_reason) {
		this.equipment_reason = equipment_reason;
	}

	public String getEquipment_maintainer() {
		return equipment_maintainer;
	}

	public void setEquipment_maintainer(String equipment_maintainer) {
		this.equipment_maintainer = equipment_maintainer;
	}

	public String getEquipment_meddle() {
		return equipment_meddle;
	}

	public void setEquipment_meddle(String equipment_meddle) {
		this.equipment_meddle = equipment_meddle;
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

	@Override
	public String toString() {
		return "ErpEquipment [id=" + id + ", equipment_name=" + equipment_name + ", equipment_sn=" + equipment_sn
				+ ", equipment_area=" + equipment_area + ", equipment_ip=" + equipment_ip + ", equipment_enter="
				+ equipment_enter + ", equipment_state=" + equipment_state + ", equipment_date=" + equipment_date
				+ ", equipment_reason=" + equipment_reason + ", equipment_maintainer=" + equipment_maintainer
				+ ", equipment_meddle=" + equipment_meddle + ", status_=" + status_ + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}

	public String getEquipment_cate() {
		return equipment_cate;
	}

	public void setEquipment_cate(String equipment_cate) {
		this.equipment_cate = equipment_cate;
	}

	public String getEquipment_addr() {
		return equipment_addr;
	}

	public void setEquipment_addr(String equipment_addr) {
		this.equipment_addr = equipment_addr;
	}

	public String getEquipment_position() {
		return equipment_position;
	}

	public void setEquipment_position(String equipment_position) {
		this.equipment_position = equipment_position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
