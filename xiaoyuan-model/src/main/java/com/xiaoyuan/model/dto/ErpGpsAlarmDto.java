package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.*;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public class ErpGpsAlarmDto extends ErpGpsAlarm {
    /**
     *  警告类型文本存储属性
     */
    public String gpsAlarmCateTxt;
    /**
     * 警报时间
     */
    public String alarmTime;
    /**
     * 驾驶员真实姓名
     */
    public String driverName;
    /**
     * 驾驶员手机号码
     */
    public String driverPhone;
    /**
     * 终端客户名称
     */
    public String customerName;
    /**
     * 车牌号码
     */
    public String carSn;
    /**
     * 订单号
     */
    public String orderSn;
    /**
     * 省
     */
    public String provinceTxt;
    /**
     * 市
     */
    public String cityTxt;
    /**
     * 区
     */
    public String areaTxt;
}
