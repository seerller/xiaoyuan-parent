package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 进货订单类？对应进货订单表，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:18:47
 *
 */
@Entity
@Table(name = "tl_erp_ingoods")
public class ErpIngoods {
	@Id
	private Integer id;

	private Integer supplier_id;

	private String ingoods_car_sn;
	// 提货数量
	private Integer ingoods_num;
	// 采购员
	private String ingoods_man;
	// 配送方式： 1.自提 2.配送
	private Integer ingoods_cate;
	// 公司名称
	private String ingoods_send_company;
	// 备注
	private String ingoods_remark;
	// 单价
	private Double ingoods_price;

	// 运费
	private Double ingoods_money;

	private String ingoods_sn;

	private String ingoods_incate;

	private Integer status_;

	private String createtime;

	private String updatetime;

	private String goods_name;
	private String goods_sn;
	private String goods_spec;
	private String setgoods_date;

	private Double ingoods_totalprice;

	// read-only
	private String company_name;

	private String user_name;

	private String company_address;

	private String supplier_name;
	private Integer audit;
	private String audittime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIngoods_car_sn() {
		return ingoods_car_sn;
	}

	public void setIngoods_car_sn(String ingoods_car_sn) {
		this.ingoods_car_sn = ingoods_car_sn;
	}

	public Integer getIngoods_num() {
		return ingoods_num;
	}

	public void setIngoods_num(Integer ingoods_num) {
		this.ingoods_num = ingoods_num;
	}

	public String getIngoods_man() {
		return ingoods_man;
	}

	public void setIngoods_man(String ingoods_man) {
		this.ingoods_man = ingoods_man;
	}

	public String getIngoods_send_company() {
		return ingoods_send_company;
	}

	public void setIngoods_send_company(String ingoods_send_company) {
		this.ingoods_send_company = ingoods_send_company;
	}

	public String getIngoods_remark() {
		return ingoods_remark;
	}

	public void setIngoods_remark(String ingoods_remark) {
		this.ingoods_remark = ingoods_remark;
	}

	public Double getIngoods_price() {
		return ingoods_price;
	}

	public void setIngoods_price(Double ingoods_price) {
		this.ingoods_price = ingoods_price;
	}

	public String getIngoods_sn() {
		return ingoods_sn;
	}

	public void setIngoods_sn(String ingoods_sn) {
		this.ingoods_sn = ingoods_sn;
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

	public String getIngoods_incate() {
		return ingoods_incate;
	}

	public void setIngoods_incate(String ingoods_incate) {
		this.ingoods_incate = ingoods_incate;
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

	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	public void setIngoods_totalprice(Double ingoods_totalprice) {
		this.ingoods_totalprice = ingoods_totalprice;
	}

	public Double getIngoods_totalprice() {
		return ingoods_totalprice;
	}

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public Double getIngoods_money() {
		return ingoods_money;
	}

	public void setIngoods_money(Double ingoods_money) {
		this.ingoods_money = ingoods_money;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public Integer getIngoods_cate() {
		return ingoods_cate;
	}

	public void setIngoods_cate(Integer ingoods_cate) {
		this.ingoods_cate = ingoods_cate;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}

}
