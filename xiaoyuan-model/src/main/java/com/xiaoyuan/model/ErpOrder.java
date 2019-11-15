package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单
 * @author MyPC
 *
 */
/**
 * 订单类，但是没用到呀，，打开调用视图没看到调用，，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:23:13
 *
 */
@Entity
@Table(name = "tl_erp_order")
public class ErpOrder {
	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	private Integer status;

	private String createdate;

	private String updatedate;
}
