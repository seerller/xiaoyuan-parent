package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 物品类，对应物品表，goods_cate 1，2，供应商物料，客户提货物品
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午4:33:50
 */
@Entity
@Table(name = "tl_erp_goods")
public class ErpGoods {
    @Id
    private Integer id;
    private String goods_sn;
    private String goods_man;
    private Integer goods_cate;
    private String goods_spec;
    private String goods_name;
    private String unit;//20190330
    private Integer status_;
    private String createtime;
    private String updatetime;
    private Integer user_id;
    /**
     * 物料性质判断，1为销货物料，0为进货物料。\r\n注意：数据库中只允许存在一条销货物料数据。
     */
    private boolean goods_type;
    // read-only
    /**
     * 销货价格。当goods_type为销货物料时，该价格就是销售价格
     * 否则则是进货价格。
     */
    private Double goods_price;//???
    /**
     * 供应商名称
     */
    private  String supplier_name;


    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    @Override
    public String toString() {
        return "ErpGoods{" +
                "id=" + id +
                ", goods_sn='" + goods_sn + '\'' +
                ", goods_man='" + goods_man + '\'' +
                ", goods_cate=" + goods_cate +
                ", goods_spec='" + goods_spec + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", unit='" + unit + '\'' +
                ", status_=" + status_ +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", user_id=" + user_id +
                ", goods_price=" + goods_price +
                '}';
    }

    public boolean isGoods_type() {
        return goods_type;
    }

    public void setGoods_type(boolean goods_type) {
        this.goods_type = goods_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGoods_man() {
        return goods_man;
    }

    public void setGoods_man(String goods_man) {
        this.goods_man = goods_man;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public Integer getGoods_cate() {
        return goods_cate;
    }

    public void setGoods_cate(Integer goods_cate) {
        this.goods_cate = goods_cate;
    }

}
