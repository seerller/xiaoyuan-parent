package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 单元？单位类？对应单元表，调用视图，单位管理，，，客户管理也用到？？
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:59:46
 *
 */
@Entity
@Table(name = "tl_erp_unit")
public class ErpUnit {
	@Id
	private Integer id;

	private Integer user_id;
	// 部门
	private String unit_dept;
	// 职位
	private String unit_position;

	private String unit_audit;

	private String user_name;

	private Integer status_;

	private String createtime;

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

	public String getUnit_dept() {
		return unit_dept;
	}

	public void setUnit_dept(String unit_dept) {
		this.unit_dept = unit_dept;
	}

	public String getUnit_position() {
		return unit_position;
	}

	public void setUnit_position(String unit_position) {
		this.unit_position = unit_position;
	}

	public String getUnit_audit() {
		return unit_audit;
	}

	public void setUnit_audit(String unit_audit) {
		this.unit_audit = unit_audit;
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

	public String getUser_name() {
		return user_name;
	}

	@Override
	public String toString() {
		return "ErpUnit [id=" + id + ", user_id=" + user_id + ", unit_dept=" + unit_dept + ", unit_position="
				+ unit_position + ", unit_audit=" + unit_audit + ", user_name=" + user_name + ", status_=" + status_
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}

}
