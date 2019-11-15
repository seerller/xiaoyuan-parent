package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 星级评定？没怎么用过好像，，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:48:39
 *
 */
@Entity
@Table(name = "tl_erp_evaluation")
public class ErpEvaluation {
	@Id
	private Integer id;

	private Integer supplier_id;

	private String evaluation_star;

	private String evaluation_company_cate;
	// 录入人员
	private String evaluation_enter;
	// 时间
	private String evaluation_date;
	// 审核
	private String evaluation_audit;

	private String evaluation_company_name;

	private Integer status_;

	private String createtime;

	private String updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getEvaluation_star() {
		return evaluation_star;
	}

	public void setEvaluation_star(String evaluation_star) {
		this.evaluation_star = evaluation_star;
	}

	public String getEvaluation_company_cate() {
		return evaluation_company_cate;
	}

	public void setEvaluation_company_cate(String evaluation_company_cate) {
		this.evaluation_company_cate = evaluation_company_cate;
	}

	public String getEvaluation_enter() {
		return evaluation_enter;
	}

	public void setEvaluation_enter(String evaluation_enter) {
		this.evaluation_enter = evaluation_enter;
	}

	public String getEvaluation_date() {
		return evaluation_date;
	}

	public void setEvaluation_date(String evaluation_date) {
		this.evaluation_date = evaluation_date;
	}

	public String getEvaluation_audit() {
		return evaluation_audit;
	}

	public void setEvaluation_audit(String evaluation_audit) {
		this.evaluation_audit = evaluation_audit;
	}

	public String getEvaluation_company_name() {
		return evaluation_company_name;
	}

	public void setEvaluation_company_name(String evaluation_company_name) {
		this.evaluation_company_name = evaluation_company_name;
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
		return "ErpEvaluation [id=" + id + ", supplier_id=" + supplier_id + ", evaluation_star=" + evaluation_star
				+ ", evaluation_company_cate=" + evaluation_company_cate + ", evaluation_enter=" + evaluation_enter
				+ ", evaluation_date=" + evaluation_date + ", evaluation_audit=" + evaluation_audit
				+ ", evaluation_company_name=" + evaluation_company_name + ", status_=" + status_ + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}

}
