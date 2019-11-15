package com.xiaoyuan.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 供应商
 * 
 * @author YZH
 * @version 2019年2月25日 下午8:09:20
 *
 *          model缺少表中一些字段，如supplier_spec等，，
 * @author YZH
 * @version 2019年3月4日 下午3:05:11
 *
 *          供应商类，对应供应商表，打开调用视图，用的挺多的，，
 * @author YZH
 * @version 2019年3月8日 下午3:50:16
 *
 */
@Entity
@Table(name = "tl_erp_supplier")
public class ErpSupplier {
	@Id
	private Integer id;
	@Column(name = "user_id")
	private Integer user_id;

	private BigDecimal goods_price;

	private String supplier_name;
	// 编号
	private String supplier_sn;
	// 联系人
	private String supplier_contact;
	// 座机号
	private String supplier_telephone;
	// 微信
	private String supplier_wechat;
	// 单位
	private String goods_unit;

	private Integer supplier_cate;

	private String supplier_audit;

	private Integer status_;

	private String goods_cate;

	private String goods_name;
	private String goods_sn;

	// read-only
	private String user_phone;
	private String user_company_sn;
	private String user_contact;// 20190304 YZH debug 91 操作u表的SQL语句没有写这个字段
	private String user_telephone;
	private String user_wechat;
	private Integer ingoods_num;

	private String createtime;
	private String updatetime;
	private String state;
	private String company_name;
	private String company_address;

	private String user_company_name;

	private Integer i_id;

	private Integer u_id;

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

	public BigDecimal getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_sn() {
		return supplier_sn;
	}

	public void setSupplier_sn(String supplier_sn) {
		this.supplier_sn = supplier_sn;
	}

	public String getSupplier_contact() {
		return supplier_contact;
	}

	public void setSupplier_contact(String supplier_contact) {
		this.supplier_contact = supplier_contact;
	}

	public String getSupplier_telephone() {
		return supplier_telephone;
	}

	public void setSupplier_telephone(String supplier_telephone) {
		this.supplier_telephone = supplier_telephone;
	}

	public String getSupplier_wechat() {
		return supplier_wechat;
	}

	public void setSupplier_wechat(String supplier_wechat) {
		this.supplier_wechat = supplier_wechat;
	}

	public String getGoods_unit() {
		return goods_unit;
	}

	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
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

	public String getSupplier_audit() {
		return supplier_audit;
	}

	public void setSupplier_audit(String supplier_audit) {
		this.supplier_audit = supplier_audit;
	}

	public String getGoods_cate() {
		return goods_cate;
	}

	public void setGoods_cate(String goods_cate) {
		this.goods_cate = goods_cate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSupplier_cate() {
		return supplier_cate;
	}

	public void setSupplier_cate(Integer supplier_cate) {
		this.supplier_cate = supplier_cate;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_company_sn() {
		return user_company_sn;
	}

	public String getUser_contact() {
		return user_contact;
	}

	public String getUser_telephone() {
		return user_telephone;
	}

	public String getUser_wechat() {
		return user_wechat;
	}

	public Integer getIngoods_num() {
		return ingoods_num;
	}

	public Integer getI_id() {
		return i_id;
	}

	public String getUser_company_name() {
		return user_company_name;
	}

	public Integer getU_id() {
		return u_id;
	}
}
