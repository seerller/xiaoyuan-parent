package com.xiaoyuan.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 回扣类，对应回扣表？返点？好像没怎么用过，打开调用视图就两个地方有用到
 * 
 * 返点控制类和用户修改返点的方法，但是菜单项返点管理没放出来，所以基本没用？
 * 
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:45:39
 *
 */
@Entity
@Table(name = "tl_erp_rebate")
public class ErpRebate {
	@Id
	private Integer id;

	private Integer user_id;
	// 返还点数
	private BigDecimal rebate_points;
	// 返点率
	private BigDecimal rebate_rate;
	//
	private String rebate_audit;
	// 消费金额
	private BigDecimal rebate_amount;
	// 客户名称 read-only
	private String user_name;
	// 累计充值 read-only
	private BigDecimal totalrecharge;

	private String sales_area;

	private String rebate_month;

	private Integer status_;

	private String createtime;

	private String updatetime;

	public BigDecimal getRebate_amount() {
		return rebate_amount;
	}

	public void setRebate_amount(BigDecimal rebate_amount) {
		this.rebate_amount = rebate_amount;
	}

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

	public BigDecimal getRebate_points() {
		return rebate_points;
	}

	public void setRebate_points(BigDecimal rebate_points) {
		this.rebate_points = rebate_points;
	}

	public BigDecimal getRebate_rate() {
		return rebate_rate;
	}

	public void setRebate_rate(BigDecimal rebate_rate) {
		this.rebate_rate = rebate_rate;
	}

	public String getRebate_audit() {
		return rebate_audit;
	}

	public void setRebate_audit(String rebate_audit) {
		this.rebate_audit = rebate_audit;
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

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public BigDecimal getTotalrecharge() {
		return totalrecharge;
	}

	public String getSales_area() {
		return sales_area;
	}

	public String getRebate_month() {
		return rebate_month;
	}

	public void setRebate_month(String rebate_month) {
		this.rebate_month = rebate_month;
	}

	@Override
	public String toString() {
		return "ErpRebate [id=" + id + ", user_id=" + user_id + ", rebate_points=" + rebate_points + ", rebate_rate="
				+ rebate_rate + ", rebate_audit=" + rebate_audit + ", rebate_amount=" + rebate_amount + ", user_name="
				+ user_name + ", totalrecharge=" + totalrecharge + ", sales_area=" + sales_area + ", rebate_month="
				+ rebate_month + ", status_=" + status_ + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ "]";
	}
}
