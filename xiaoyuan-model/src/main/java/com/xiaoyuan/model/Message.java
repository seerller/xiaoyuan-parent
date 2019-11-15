package com.xiaoyuan.model;
import lombok.Data;
import javax.persistence.Entity;
import java.util.Map;
import com.alibaba.fastjson.JSON;
/**
 * 短信类
 */
@Entity
@Data
public class Message {
    private String PhoneNumbers;//手机号码
    private String TemplateCode;//模板代码  标题？
    private String TemplateParam;//模板变量 内容？


    /**
     * 模板常量
     * 注册或找回密码的模板代码。
     */
    public static final String registerTemplate= "SMS_162456282";
    /**
     *  模板常量
     *  限量消息发送的模板代码。
     */
    public static final String LimitMaxTemplate= "SMS_170555214";

    /**
     *  模板常量
     *  超过限量消息发送的模板代码。
     */
    public static final String LimitMaxOutTemplate= "SMS_170555217";
    /**
     *  模板常量
     *  客户取消订单消息发送的模板代码。
     */
    public static final String OrderCancelTemplate= "SMS_170560183";

    /**
     * 后续有需求再继续添加，
     * 因之前的同事写的太繁琐，因此暂时不修改之前代码，
     * 仅在已有的基础上作优化。
     */
    String register = "注册或找回密码";
    String registerAudit = "注册审核通过";
    String registerUnAudit = "注册审核拒绝";
    String pickgoodsAudit = "提货单审核通过";
    String pickgoodsUnAudit = "提货单审核拒绝";
    String isCarOrder = "提货单提醒接单";
    String noCarOrder = "提货单司机不接单";
    String outWareHouse = "完成出库";
    String rechargeAudit = "充值审核通过";
    String rechargeUnAudit = "充值审核拒绝";
    String ingoodsAudit = "进货审核通过";
    String ingoodsUnAudit = "进货审核拒绝";
    String goodsAudit = "物料申请通过";
    String goodsUnAudit = "物料申请拒绝";

    public Message(){}

    /**
     * 传入类型代码，自动设置相应模板
     * TODO:后续有需求再进行添加，现只加入注册或密码即可。
     * @param typeCode
     */
    public Message(String typeCode, String phone, Map<String,Object> params){
        this.TemplateCode = typeCode;
        this.PhoneNumbers = phone;
        this.TemplateParam = null == params ? null : JSON.toJSONString(params);
    }
    /**
     * 传入类型代码，自动设置相应模板
     * TODO:后续有需求再进行添加，现只加入注册或密码即可。
     * @param typeCode
     */
    public Message(String typeCode, String phone, String params){
        this.TemplateCode = typeCode;
        this.PhoneNumbers = phone;
        this.TemplateParam = params;
    }


    public String getPhoneNumbers() {
        return PhoneNumbers;
    }

    public String getTemplateCode() {
        return TemplateCode;
    }

    public String getTemplateParam() {
        return TemplateParam;
    }

    public String getRegister() {
        return register;
    }

    public String getRegisterAudit() {
        return registerAudit;
    }

    public String getRegisterUnAudit() {
        return registerUnAudit;
    }

    public String getPickgoodsAudit() {
        return pickgoodsAudit;
    }

    public String getPickgoodsUnAudit() {
        return pickgoodsUnAudit;
    }

    public String getIsCarOrder() {
        return isCarOrder;
    }

    public String getNoCarOrder() {
        return noCarOrder;
    }

    public String getOutWareHouse() {
        return outWareHouse;
    }

    public String getRechargeAudit() {
        return rechargeAudit;
    }

    public String getRechargeUnAudit() {
        return rechargeUnAudit;
    }

    public String getIngoodsAudit() {
        return ingoodsAudit;
    }

    public String getIngoodsUnAudit() {
        return ingoodsUnAudit;
    }

    public String getGoodsAudit() {
        return goodsAudit;
    }

    public String getGoodsUnAudit() {
        return goodsUnAudit;
    }

    public String get_PhoneNumbers() {
        return PhoneNumbers;
    }

    public void set_PhoneNumbers(String t) {
        this.PhoneNumbers = t;
    }

    public String get_TemplateCode() {
        return TemplateCode;
    }

    public void set_TemplateCode(String t) {
        this.TemplateCode = t;
    }

    public String get_TemplateParam() {
        return TemplateParam;
    }

    public void set_TemplateParam(String t) {
        this.TemplateParam = t;
    }


}