package com.xiaoyuan.model.search;
import lombok.Data;
import lombok.ToString;

/**
 * gps告警条件搜索持久类
 */
@Data
@ToString(callSuper = true)
public class ErpGpsAlarmSearch extends BaseSearch  {
    /**
     * gps定位id
     */
    private Integer gpsId;
    /**
     *  设备主键
     */
    private String gps_sn;
    /**
     * gps告警状态
     * 默认等于0
     */
    private Integer status = 0;
    /**
     * 根据车牌号查询
     */
    private String carSn;
    /**
     * 条件筛选开始时间
     */
    private String startTime;
    /**
     * 条件筛选终止时间
     */
    private String endTime;
    /**
     * 当前时间
     */
    private String nowTime;
    /**
     * 提货单主键，根据该数据查询该订单下的信息
     */
    private Integer orderId;



    public ErpGpsAlarmSearch(){}

    /**
     * 自定义构造函数
     * @param gpsId gps告警主键
     */
    public ErpGpsAlarmSearch(Integer gpsId){
        this.gpsId = gpsId;
    }
    /**
     * 自定义构造函数
     */
    public ErpGpsAlarmSearch(String nowTime,Integer orderId){
        this.setNowTime(nowTime).setOrderId(orderId);
    }
    public ErpGpsAlarmSearch  setNowTime(String nowTime){
        this.nowTime = nowTime;
        return this;
    }
    public ErpGpsAlarmSearch  setOrderId(Integer orderId){
        this.orderId = orderId;
        return this;
    }


}
