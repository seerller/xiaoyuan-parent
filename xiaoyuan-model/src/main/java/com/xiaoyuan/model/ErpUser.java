package com.xiaoyuan.model;

import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.tools.TimeUtils;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户类，对应用户表，好像是调用视图最多的~
 *
 * @author YZH
 * @version 2019年3月8日 下午4:03:54
 */
@Entity
@Table(name = "tl_erp_user")
public class ErpUser {
    @Id
    private Integer id;
    // 用户名
    private String user_name;
    // 密码
    private String password_;
    // 手机号
    private String user_phone;
    // 用户分类
    // 公司名称
    // 省
    private String user_company_name;
    private Integer user_cate;
    private Integer province_id;
    // 市
    private Integer city_id;
    // 区
    private Integer area_id;
    // 街道
    private Integer street_id;
    // 地址
    private String user_address;
    private String sales_area;
    private String user_description;
    //余额
    private Double rebate_balance;
    private Double user_price;
    private String createtime;
    private String updatetime;
    private Integer status_;
    private String user_company_sn;
    private String user_telephone;
    private String user_wechat;
    private String user_contact;
    private String company_contact;
    private String company_cate;
    private String company_address;
    private Integer stop;
    private Integer role_id;
    private String token;
    private Integer audit;
    private Integer user_role_id;
    private String department;//部门和职务，编号，姓名，字符串地址，20190403
    private String job;
    private String real_name;
    private String number;
    private String province_txt;
    private String city_txt;
    private String area_txt;

    /**
     * read-only start
     */
    private String search;
    private String customer_name;// TODO YZH加上这个，，审核的时候要用。。
    private String goods_name;
    private String car_sn;
    private String driver_sn;
    private String driver_use_date;
    private String driver_eff_date;
    private String car_owner;
    private String car_contact_phone;
    // 角色名称 read_only
    private String role_name;
    private String car_cate;
    /**
     * YZH 数据库中user表没有这个s_id，但是在价格管理的时候要根据这个s_id查询
     * <p>
     * 可以查看SQL语句，用了supplier_id当做s_id
     */
    private Integer s_id;
    /**
     * 用户类型文本形式
     */
    private String userCate;
    /**
     * 车辆信息数据
     */
    private ErpCar car;
    /**
     * 每天最大下单量
     */
    private Double limit_max;
    /**
     * 最小下单量
     */
    private Double min_number;

    public ErpUser(){

    }

    public ErpUser(BigDecimal limit_max,BigDecimal min_number){
        this.limit_max = null == limit_max ? null : limit_max.doubleValue();
        this.min_number =  null == min_number ? null : min_number.doubleValue();;
    }
    public ErpUser(Integer id,String token){
        this.id=id;
        this.token=token;
    }

    public ErpUser(String user_name,String token){
        this.user_name=user_name;
        this.token=token;
    }

    /**
     * web端资料修改使用
     * @param dto
     */
    public ErpUser(ErpUserDto dto){
        this.id = dto.getId();
        boolean isCustomerRole = dto.getUser_cate() == 1;
        boolean isAdminRole = dto.getUser_cate() == 3;
        //web端中客户角色的用户用户名不允许修改
        this.user_name=isCustomerRole ? null : dto.getUser_name();
        this.real_name=dto.getReal_name();
        this.user_phone = dto.getUser_phone();
        this.number = dto.getNumber();
        this.user_company_name = dto.getUser_company_name();
        //传入参数是客户角色时则允许设置以下属性
        if(isCustomerRole) {
            this.sales_area = dto.getSales_area();
            this.user_price = null== dto.getUser_price()? null: dto.getUser_price().doubleValue();
            this.province_id = dto.getProvince_id();
            this.city_id = dto.getCity_id();
            this.area_id = dto.getArea_id();
            this.user_address = dto.getUser_address();
            this.limit_max = dto.getLimit_max();
            this.min_number = dto.getMin_number();
        }
        if(null == dto.getId()){
            this.createtime = TimeUtils.resultCurrentDate();
        }else{
            this.updatetime = TimeUtils.resultCurrentDate();
        }
        //园区用户只需要修改通用属性与下面那个属性即可
        this.department = isAdminRole ?  dto.getDepartment() :null;
        this.job = isAdminRole ?  dto.getJob() :null;
    }

    /**
     * read-only end
     */
    @Override
    public String toString() {
        return "ErpUser{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_company_name='" + user_company_name + '\'' +
                ", user_cate=" + user_cate +
                ", province_id=" + province_id +
                ", city_id=" + city_id +
                ", area_id=" + area_id +
                ", street_id=" + street_id +
                ", user_address='" + user_address + '\'' +
                ", sales_area='" + sales_area + '\'' +
                ", user_description='" + user_description + '\'' +
                ", rebate_balance=" + rebate_balance +
                ", user_price=" + user_price +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", status_=" + status_ +
                ", user_company_sn='" + user_company_sn + '\'' +
                ", user_telephone='" + user_telephone + '\'' +
                ", user_wechat='" + user_wechat + '\'' +
                ", user_contact='" + user_contact + '\'' +
                ", company_contact='" + company_contact + '\'' +
                ", company_cate='" + company_cate + '\'' +
                ", company_address='" + company_address + '\'' +
                ", stop=" + stop +
                ", role_id=" + role_id +
                ", token='" + token + '\'' +
                ", audit=" + audit +
                ", user_role_id=" + user_role_id +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", real_name='" + real_name + '\'' +
                ", number='" + number + '\'' +
                ", province_txt='" + province_txt + '\'' +
                ", city_txt='" + city_txt + '\'' +
                ", area_txt='" + area_txt + '\'' +
                ", search='" + search + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", car_sn='" + car_sn + '\'' +
                ", driver_sn='" + driver_sn + '\'' +
                ", driver_use_date='" + driver_use_date + '\'' +
                ", driver_eff_date='" + driver_eff_date + '\'' +
                ", car_owner='" + car_owner + '\'' +
                ", car_contact_phone='" + car_contact_phone + '\'' +
                ", role_name='" + role_name + '\'' +
                ", car_cate='" + car_cate + '\'' +
                ", s_id=" + s_id +
                '}';
    }


    public Double getLimit_max() {
        return limit_max;
    }

    public ErpUser setLimit_max(Double limit_max) {
        this.limit_max = limit_max;
        return this;
    }

    public Double getMin_number() {
        return min_number;
    }

    public ErpUser setMin_number(Double min_number) {
        this.min_number = min_number;
        return this;
    }

    public String getCar_cate() {
        return car_cate;
    }

    public String getUserCate() {
        return userCate;
    }

    public void setUserCate(String userCate) {
        this.userCate = userCate;
    }
    public ErpCar getCar() {
        return car;
    }
    public void setCar(ErpCar car) {
        this.car = car;
    }
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setCar_cate(String car_cate) {
        this.car_cate = car_cate;
    }

    public String getCar_owner() {
        return car_owner;
    }

    public void setCar_owner(String car_owner) {
        this.car_owner = car_owner;
    }

    public String getCar_contact_phone() {
        return car_contact_phone;
    }

    public void setCar_contact_phone(String car_contact_phone) {
        this.car_contact_phone = car_contact_phone;
    }

    public String getProvince_txt() {
        return province_txt;
    }

    public void setProvince_txt(String province_txt) {
        this.province_txt = province_txt;
    }

    public String getCity_txt() {
        return city_txt;
    }

    public void setCity_txt(String city_txt) {
        this.city_txt = city_txt;
    }

    public String getArea_txt() {
        return area_txt;
    }

    public void setArea_txt(String area_txt) {
        this.area_txt = area_txt;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReal_name() {
        return real_name;
    }


    public ErpUser setRealName(String real_name) {
        this.real_name = real_name;
        return this;
    }
    public ErpUser setUserId(Integer id) {
        this.id = id;
        return this;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setCar_sn(String car_sn) {
        this.car_sn = car_sn;
    }

    public void setDriver_sn(String driver_sn) {
        this.driver_sn = driver_sn;
    }

    public void setDriver_use_date(String driver_use_date) {
        this.driver_use_date = driver_use_date;
    }

    public void setDriver_eff_date(String driver_eff_date) {
        this.driver_eff_date = driver_eff_date;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Integer getUser_cate() {
        return user_cate;
    }

    public void setUser_cate(Integer user_cate) {
        this.user_cate = user_cate;
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

    public Integer getStreet_id() {
        return street_id;
    }

    public void setStreet_id(Integer street_id) {
        this.street_id = street_id;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getSales_area() {
        return sales_area;
    }

    public void setSales_area(String sales_area) {
        this.sales_area = sales_area;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public Double getUser_price() {
        return user_price;
    }

    public void setUser_price(Double user_price2) {
        this.user_price = user_price2;
    }

    public Double getRebate_balance() {
        return rebate_balance;
    }

    public void setRebate_balance(Double rebate_balance) {
        this.rebate_balance = rebate_balance;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
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

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getUser_company_name() {
        return user_company_name;
    }

    public void setUser_company_name(String user_company_name) {
        this.user_company_name = user_company_name;
    }

    public String getUser_company_sn() {
        return user_company_sn;
    }

    public void setUser_company_sn(String user_company_sn) {
        this.user_company_sn = user_company_sn;
    }

    public String getUser_wechat() {
        return user_wechat;
    }

    public void setUser_wechat(String user_wechat) {
        this.user_wechat = user_wechat;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getCompany_cate() {
        return company_cate;
    }

    public void setCompany_cate(String company_cate) {
        this.company_cate = company_cate;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_telephone() {
        return user_telephone;
    }

    public void setUser_telephone(String user_telephone) {
        this.user_telephone = user_telephone;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getCar_sn() {
        return car_sn;
    }

    public String getDriver_sn() {
        return driver_sn;
    }

    public String getDriver_use_date() {
        return driver_use_date;
    }

    public String getDriver_eff_date() {
        return driver_eff_date;
    }

    public Integer getS_id() {
        return s_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * 增加余额并返回自身对象
     * @param price
     * @return
     */
    public ErpUser addBalance(double price){
        this.rebate_balance += price;
        return this;
    }

    public ErpUser setIdResultThis(Integer id){
        this.id = id;
        return this;
    }
    public ErpUser setRealNameResultThis(String name){
        this.real_name = name;
        return this;
    }

    public ErpUser setAuditResultThis(Integer id){
        this.audit = id;
        return this;
    }
    public ErpUser setSales_areaResultThis(String sales_area){
        this.sales_area = sales_area;
        return this;
    }
    /**
     * 通过该函数设置stop为1作为删除该用户的方法。
     * @return
     */
    public ErpUser userStopIsNow(){
        this.stop = 1;
        this.number = "0000000000";
        return this;
    }


    public String updateParams = "id";

    public String getUpdateParams() {
        return updateParams;
    }
    public ErpUser setUpdateParams(String updateParams) {
        this.updateParams = updateParams;
        return this;
    }
}
