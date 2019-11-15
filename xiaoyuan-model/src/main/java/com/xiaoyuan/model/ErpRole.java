package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色类，对应角色表，来的第一天搞权限和审核，这个表舍弃了，
 * 
 * 三表挺好的，分组啥的，打开调用视图，基本没用？
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:48:45
 *
 */
@Entity
@Table(name = "tl_erp_role")
public class ErpRole {
	@Id
	private Integer id;
	private String role_name;
	private Integer status_;
	private String createtime;
	private String updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
		return "ErpRole [id=" + id + ", role_name=" + role_name + ", status_=" + status_ + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}

}
