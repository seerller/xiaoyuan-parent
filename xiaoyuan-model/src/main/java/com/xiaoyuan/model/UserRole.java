package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户子角色，，，本来中间夹了一个角色表，后来那个表没用了，，，
 * 
 * 其实三表挺好的，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午4:11:29
 *
 */
@Entity
@Table(name = "tl_user_child_role")
public class UserRole {
	@Id
	private Integer role_id;
	private String role_name;
	private String role_auth_ids;
	private Integer seller_id;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_auth_ids() {
		return role_auth_ids;
	}

	public void setRole_auth_ids(String role_auth_ids) {
		this.role_auth_ids = role_auth_ids;
	}

	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

}
