package com.xiaoyuan.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 终端客户类，对应终端客户表，好像涉及业务不是很复杂？打开调用视图，两个地方
 * <p>
 * 注册和终端客户管理用到
 *
 * @author YZH
 * @version 2019年3月8日 下午3:51:03
 */
@Data
@Entity
@Table(name = "tl_erp_terminal_customer")
public class ErpTerminalCustomer {
    @Id
    private Integer id;
    /**
     * 终端客户客户名称
     */
    private String customer_name;

    private Integer status_;

    private String createtime;

    private String updatetime;


    private String customer_company_name;

    private String customer_company_sn;

    private String customer_sales_area;

    private String customer_description;

    private String terminal_customer_address;

    private Integer user_id;

    /**
     * 终端客户用户编号
     */
    private String customer_sn;
    /**
     * 省
     */
    private Integer province_id;
    /**
     * 市
     */
    private Integer city_id;
    /**
     * 区
     */
    private Integer area_id;
    /**
     * 结算价
     */
    private BigDecimal price;
    /**
     * 终端用户公司坐标
     */
    private String coordinate;
    /**
     * 挂牌价  PS:也是结算价
     */
    private BigDecimal user_price;
    /**
     * 伪删除字段
     */
    private Integer stop;


    public String getCustomer_sn() {
        return customer_sn;
    }

    public void setCustomer_sn(String customer_sn) {
        this.customer_sn = customer_sn;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public BigDecimal getUser_price() {
        return user_price;
    }

    public void setUser_price(BigDecimal user_price) {
        this.user_price = user_price;
    }


    public String getTerminal_customer_address() {
        return terminal_customer_address;
    }

    public void setTerminal_customer_address(String terminal_customer_address) {
        this.terminal_customer_address = terminal_customer_address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCustomer_company_name() {
        return customer_company_name;
    }

    public void setCustomer_company_name(String customer_company_name) {
        this.customer_company_name = customer_company_name;
    }

    public String getCustomer_company_sn() {
        return customer_company_sn;
    }

    public void setCustomer_company_sn(String customer_company_sn) {
        this.customer_company_sn = customer_company_sn;
    }

    public String getCustomer_sales_area() {
        return customer_sales_area;
    }

    public void setCustomer_sales_area(String customer_sales_area) {
        this.customer_sales_area = customer_sales_area;
    }

    public String getCustomer_description() {
        return customer_description;
    }

    public void setCustomer_description(String customer_description) {
        this.customer_description = customer_description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
