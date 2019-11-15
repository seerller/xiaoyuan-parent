package com.xiaoyuan.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域类，对应区域表，少用，区域管理用
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:13:59
 *
 */
@Entity
@Table(name = "tl_erp_area")
public class ErpArea {
	@Id
	private Integer id;

	private Integer user_id;

	private String area_remark;

	private String area_audit_date;

	private String area_audit_state;
	// 客户名称
	private String user_name;

	private String sales_area;

	private Integer status_;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createtime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatetime;

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

	public String getArea_remark() {
		return area_remark;
	}

	public void setArea_remark(String area_remark) {
		this.area_remark = area_remark;
	}

	public String getArea_audit_date() {
		return area_audit_date;
	}

	public void setArea_audit_date(String area_audit_date) {
		this.area_audit_date = area_audit_date;
	}

	public String getArea_audit_state() {
		return area_audit_state;
	}

	public void setArea_audit_state(String area_audit_state) {
		this.area_audit_state = area_audit_state;
	}

	public Integer getStatus_() {
		return status_;
	}

	public void setStatus_(Integer status) {
		this.status_ = status;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSales_area() {
		return sales_area;
	}

	public void setSales_area(String sales_area) {
		this.sales_area = sales_area;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "ErpArea [id=" + id + ", user_id=" + user_id + ", area_remark=" + area_remark + ", area_audit_date="
				+ area_audit_date + ", area_audit_state=" + area_audit_state + ", user_name=" + user_name
				+ ", sales_area=" + sales_area + ", status_=" + status_ + ", createtime=" + createtime + ", updatetime="
				+ updatetime + "]";
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
