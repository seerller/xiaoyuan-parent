package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_order")
public class Order {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "student_ID")
    private Integer studentId;

    private String commodityname;

    private Double cost;

    private String payment;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return student_ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * @return commodityname
     */
    public String getCommodityname() {
        return commodityname;
    }

    /**
     * @param commodityname
     */
    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname == null ? null : commodityname.trim();
    }

    /**
     * @return cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment
     */
    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}