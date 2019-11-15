package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 又一个订单类，加个s？多订单？打开调用在司机控制类在选择单号方法和选择方法
 * 
 * 还有在EDCStdcall类有用到？
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:24:38
 *
 */
@Entity
@Table(name = "tl_erp_orders")
public class ErpOrders {
	@Id
	private Integer id;
	private String car_order_sn;
	private Long order_sn;
	private Integer status;
	private String createtime;
	private String updatetime;
	private String endtime;

	// read_only
	private String car_order_address;
	private String company_name;
	private String car_sn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCar_order_sn() {
		return car_order_sn;
	}

	public void setCar_order_sn(String car_order_sn) {
		this.car_order_sn = car_order_sn;
	}

	public Long getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(Long order_sn) {
		this.order_sn = order_sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getCar_order_address() {
		return car_order_address;
	}

	public void setCar_order_address(String car_order_address) {
		this.car_order_address = car_order_address;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCar_sn() {
		return car_sn;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}
