package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 仓库类，对应仓库表，仓库管理？打开调用视图，没用到？
 * 
 * @author YZH
 * @version 2019年3月8日 下午4:05:16
 *
 */
@Entity
@Table(name = "tl_erp_warehouse")
public class ErpWareHouse {
	@Id
	private Integer id;

	private Integer warehouse_sn;

	private Integer warehouse_real;

	private Integer warehouse_surface;

	private Integer status_;

	private String createtime;

	private String updatetime;

	// only read
	private String goods_sn;

	private String goods_name;

	private String supplier_spec;

	private String goods_unit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWarehouse_sn() {
		return warehouse_sn;
	}

	public void setWarehouse_sn(Integer warehouse_sn) {
		this.warehouse_sn = warehouse_sn;
	}

	public Integer getWarehouse_real() {
		return warehouse_real;
	}

	public void setWarehouse_real(Integer warehouse_real) {
		this.warehouse_real = warehouse_real;
	}

	public Integer getWarehouse_surface() {
		return warehouse_surface;
	}

	public void setWarehouse_surface(Integer warehouse_surface) {
		this.warehouse_surface = warehouse_surface;
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

	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_unit() {
		return goods_unit;
	}

	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}

	public String getSupplier_spec() {
		return supplier_spec;
	}

	public void setSupplier_spec(String supplier_spec) {
		this.supplier_spec = supplier_spec;
	}

	@Override
	public String toString() {
		return "ErpWareHouse [id=" + id + ", warehouse_sn=" + warehouse_sn + ", warehouse_real=" + warehouse_real
				+ ", warehouse_surface=" + warehouse_surface + ", status_=" + status_ + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", goods_sn=" + goods_sn + ", goods_name=" + goods_name
				+ ", supplier_spec=" + supplier_spec + ", goods_unit=" + goods_unit + "]";
	}

}
