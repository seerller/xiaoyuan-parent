package com.xiaoyuan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 提单表，采购和销售都在这里，采购就是进货，销售就是提货（从仓库提货）
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午3:38:37
 */
@Entity
@Table(name = "tl_erp_pickgoods")
public class  ErpPickgoods {
    @Id
    private Integer id;
    private Integer customer_id;
    private Integer user_id;
    private Integer supplier_id;
    private String car_order_sn;// 订单编号  车单编号
    private String pickgoods_out;
    private Double pickgoods_num;// 提货数量
    private String pickgoods_man;// 提货人
    private String pickgoods_date;
    private String pickgoods_head;// 部门负责人
    private String pickgoods_car_sn;//连接条件
    private String pickgoods_remark;//备注
    private Integer pickgoods_sendcate;// 配送方式： 1.自提 2.配送  20190402 Tue. 15:20 配送去掉了
    private BigDecimal pickgoods_send_price;// 运费
    private String pickgoods_send_company;// 公司名称
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createtime;
    private String updatetime;
    private Integer state;
    private Integer status_;//提货园区审核
    private String goods_sn;
    private String goods_cate;//20190402 数据库有但是model没有加上了
    private String goods_spec;
    private String goods_name;
    private Integer audit;
    private String audittime;
    private String order_cate; // 订单类型 销售。采购
    private String user_address;// 送货地址
    private String supplier_spec;
    private Double ingoods_price;
    private Double ingoods_totalprice;
    private Integer is_car_order;//司机是否接单
    private Integer is_return_goods;//是否退货
    private Integer is_confirm_queue;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String confirm_queue_datetime;
    private Integer province_id;
    private Integer city_id;
    private Integer area_id;
    private String province_txt;
    private String city_txt;
    private String area_txt;
    private String customer_name;
    private String audit_man;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String audit_datetime;
    private String driver_phone;
    /**
     * 是否完成出库状态。
     */
    private Integer is_end;
    /**
     * 订单状态，1是已完成，0是进行中，-1则是已取消
     */
    private Integer order_status;
    /**
     * 订单状态文本
     */
    private String orderStatusTxt;
    /**
     * 送货时间，用来数据展示使用。
     */
    private String sendTime;
    /**
     * 终端用户的公司名称
     */
    private String customer_company_name;
    /**
     * 订单过磅信息
     */
    private ErpPound pound;
    /**
     * 驾驶员信息主键
     */
    private Integer driver_id;
    /**
     * 上位机请求确认
     */
    private Integer confirm_queue_device;

    /**
     *  创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTimeTxt;
    /**
     * 进入终端客户范围时间
     */
    private String in_customer_time;

    /**
     * 扣款记录主键
     */
    private Integer accountId;
    public void setRegion(ErpCustomerDto customer){
        this.province_id = customer.getProvince_id();
        this.city_id = customer.getCity_id();
        this.area_id = customer.getArea_id();
        this.province_txt = customer.getProvinceTxt();
        this.city_txt = customer.getCityTxt();
        this.area_txt = customer.getAreaTxt();
    };

    public Integer getDriver_id() {
        return driver_id;
    }

    public String getIn_customer_time() {
        return in_customer_time;
    }

    public void setIn_customer_time(String in_customer_time) {
        this.in_customer_time = in_customer_time;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public Date getCreateTimeTxt() {
        return createTimeTxt;
    }

    public void setCreateTimeTxt(Date createTimeTxt) {
        this.createTimeTxt = createTimeTxt;
    }

    public ErpPound getPound() {
        return pound;
    }
    public void setPound(ErpPound pound) {
        this.pound = pound;
    }
    public String getSendTime() {
        return sendTime;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getCustomer_company_name() {
        return customer_company_name;
    }

    public void setCustomer_company_name(String customer_company_name) {
        this.customer_company_name = customer_company_name;
    }

    public String getOrderStatusTxt() {
        return orderStatusTxt;
    }

    public void setOrderStatusTxt(String orderStatusTxt) {
        this.orderStatusTxt = orderStatusTxt;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getIs_end() {
        return is_end;
    }

    public void setIs_end(Integer is_end) {
        this.is_end = is_end;
    }

    public ErpPickgoods(){
        super();
    }

    /**
     * 订单内默认销售类型
     * 后期若有其他类型则继续添加
     */
    public static final String ORDER_CATE_SALE = "销售";

    /**
     * 订单类型 ->采购
     */
    public static final String ORDER_CATE_BUY = "采购";

    /**
     * 自定义构造函数
     * @param userId                    用户主键
     * @param car_order_sn              车辆订单号
     * @param pickgoods_num             销货数量
     * @param pickgoods_man             销售人名称
     * @param pickgoods_date            销售时间
     * @param pickgoods_car_sn          送货车辆车牌号码
     * @param pickgoods_send_company    购买人的公司名称
     * @param goods_sn                  商品编号
     * @param goods_spec                商品规格
     * @param goods_name                商品名称
     * @param user_address              配送的地址
     * @param ingoods_price             进货单价
     * @param ingoods_totalprice        进货总价
     * @param customer_name             终端用户名称
     * @param driver_phone              配送人员联系方式
     * @param customer_id               终端用户id
     * @param driver_id                 司机驾驶信息主键
     */
    public ErpPickgoods(Integer userId,String car_order_sn,BigDecimal pickgoods_num,String pickgoods_man,
                        String pickgoods_date,String pickgoods_car_sn,String pickgoods_send_company,
                        String goods_sn,String goods_spec,String goods_name,String user_address,BigDecimal ingoods_price,
                        BigDecimal ingoods_totalprice,String customer_name,String driver_phone,Integer customer_id
                        ,Integer driver_id){
        this.user_id = userId;
        this.car_order_sn = car_order_sn;
        this.pickgoods_num = pickgoods_num.doubleValue();
        this.pickgoods_man = pickgoods_man;
        this.pickgoods_date = pickgoods_date;
        this.pickgoods_car_sn =pickgoods_car_sn;
        this.pickgoods_send_company = pickgoods_send_company;
        this.goods_sn = goods_sn;
        this.goods_spec = goods_spec;
        this.goods_name = goods_name;
        this.user_address = user_address;
        this.ingoods_price = ingoods_price.doubleValue();
        this.ingoods_totalprice = ingoods_totalprice.doubleValue();
        this.customer_name = customer_name;
        this.driver_phone = driver_phone;
        this.customer_id = customer_id;
        //默认销售类型,后期有特殊需求再进行修改
        this.order_cate = ORDER_CATE_SALE;
        this.status_ = 0;
        this.driver_id = driver_id;
    }

    /**
     *
     * @param id
     * @param updatetime
     * @param pickgoods_car_sn
     * @param driver_phone
     * @param customer_id
     * @param user_address
     * @param customer_name
     */
    public ErpPickgoods(Integer id,String updatetime,String pickgoods_car_sn,String driver_phone,
                        String user_company_name,Integer customer_id,String user_address,String customer_name){
        this.id = id;
        this.updatetime = updatetime;
        this.pickgoods_car_sn =pickgoods_car_sn;
        this.driver_phone = driver_phone;
        this.customer_id = customer_id;
        this.user_address = user_address;
        this.customer_name = customer_name;
        this.pickgoods_send_company = user_company_name;
    }

    public ErpPickgoods(Integer id,String updatetime,String pickgoods_car_sn,String driver_phone,
                        String user_company_name,Integer driver_id,Integer customerId,String customer_name,String user_address){
        this.id = id;
        this.updatetime = updatetime;
        this.pickgoods_car_sn =pickgoods_car_sn;
        this.driver_phone = driver_phone;
        this.driver_id = driver_id;
        this.pickgoods_send_company = user_company_name;
        this.customer_name = customer_name;
                this.customer_id = customerId;
        this.user_address =user_address;
    }



    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        this.driver_phone = driver_phone;
    }

    public String getAudit_man() {
        return audit_man;
    }

    public void setAudit_man(String audit_man) {
        this.audit_man = audit_man;
    }

    public String getAudit_datetime() {
        return audit_datetime;
    }

    public void setAudit_datetime(String audit_datetime) {
        this.audit_datetime = audit_datetime;
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

    public String getGoods_cate() {
        return goods_cate;
    }

    public void setGoods_cate(String goods_cate) {
        this.goods_cate = goods_cate;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public Integer getIs_confirm_queue() {
        return is_confirm_queue;
    }

    public void setIs_confirm_queue(Integer is_confirm_queue) {
        this.is_confirm_queue = is_confirm_queue;
    }

    public String getConfirm_queue_datetime() {
        return confirm_queue_datetime;
    }

    public void setConfirm_queue_datetime(String confirm_queue_datetime) {
        this.confirm_queue_datetime = confirm_queue_datetime;
    }

    public Integer getIs_car_order() {
        return is_car_order;
    }

    public void setIs_car_order(Integer is_car_order) {
        this.is_car_order = is_car_order;
    }

    public Integer getIs_return_goods() {
        return is_return_goods;
    }

    public void setIs_return_goods(Integer is_return_goods) {
        this.is_return_goods = is_return_goods;
    }

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

    public String getPickgoods_car_sn() {
        return pickgoods_car_sn;
    }

    public void setPickgoods_car_sn(String pickgoods_car_sn) {
        this.pickgoods_car_sn = pickgoods_car_sn;
    }

    public Double getPickgoods_num() {
        return pickgoods_num;
    }

    public void setPickgoods_num(Double pickgoods_num) {
        this.pickgoods_num = pickgoods_num;
    }

    public String getPickgoods_man() {
        return pickgoods_man;
    }

    public void setPickgoods_man(String pickgoods_man) {
        this.pickgoods_man = pickgoods_man;
    }

    public String getPickgoods_head() {
        return pickgoods_head;
    }

    public void setPickgoods_head(String pickgoods_head) {
        this.pickgoods_head = pickgoods_head;
    }

    public String getPickgoods_date() {
        return pickgoods_date;
    }

    public void setPickgoods_date(String pickgoods_date) {
        this.pickgoods_date = pickgoods_date;
    }

    public Integer getPickgoods_sendcate() {
        return pickgoods_sendcate;
    }

    public void setPickgoods_sendcate(Integer pickgoods_sendcate) {
        this.pickgoods_sendcate = pickgoods_sendcate;
    }

    public String getPickgoods_send_company() {
        return pickgoods_send_company;
    }

    public void setPickgoods_send_company(String pickgoods_send_company) {
        this.pickgoods_send_company = pickgoods_send_company;
    }

    public String getPickgoods_remark() {
        return pickgoods_remark;
    }

    public void setPickgoods_remark(String pickgoods_remark) {
        this.pickgoods_remark = pickgoods_remark;
    }

    public BigDecimal getPickgoods_send_price() {
        return pickgoods_send_price;
    }

    public void setPickgoods_send_price(BigDecimal pickgoods_send_price) {
        this.pickgoods_send_price = pickgoods_send_price;
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

    public String getPickgoods_out() {
        return pickgoods_out;
    }

    public void setPickgoods_out(String pickgoods_out) {
        this.pickgoods_out = pickgoods_out;
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

    public String getSupplier_spec() {
        return supplier_spec;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public void setSupplier_spec(String supplier_spec) {
        this.supplier_spec = supplier_spec;
    }

    public String getCar_order_sn() {
        return car_order_sn;
    }

    public Integer getConfirm_queue_device() {
        return confirm_queue_device;
    }

    public void setConfirm_queue_device(Integer confirm_queue_device) {
        this.confirm_queue_device = confirm_queue_device;
    }

    public void setCar_order_sn(String car_order_sn) {
        this.car_order_sn = car_order_sn;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrder_cate() {
        return order_cate;
    }

    public void setOrder_cate(String order_cate) {
        this.order_cate = order_cate;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public Double getIngoods_price() {
        return ingoods_price;
    }

    public void setIngoods_price(Double ingoods_price) {
        this.ingoods_price = ingoods_price;
    }

    public Double getIngoods_totalprice() {
        return ingoods_totalprice;
    }

    public void setIngoods_totalprice(Double ingoods_totalprice) {
        this.ingoods_totalprice = ingoods_totalprice;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }
    /**
     * 根据isConfirmQueue属性判断订单是否已排队进场.
     * @return
     */
    public boolean isQueueConfirm(){
        if(1 ==this.is_confirm_queue){
            return true;
        }
        return false;
    }



}
