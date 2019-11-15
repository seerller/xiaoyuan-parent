package com.xiaoyuan.model.dto;

import com.xiaoyuan.model.ErpGpsDevice;
import lombok.Data;
import lombok.ToString;

/**
 * 前端数据展示持久类
 */
@Data
@ToString(callSuper = true)
public class ErpGpsDeviceDto extends ErpGpsDevice {
    /**
     * 车牌号
     */
    private String carSn;

    /**
     * 驾驶员真实姓名
     */
    private String driverRealName;
    /**
     * 驾驶员手机号
     */
    private String driverPhone;
}
