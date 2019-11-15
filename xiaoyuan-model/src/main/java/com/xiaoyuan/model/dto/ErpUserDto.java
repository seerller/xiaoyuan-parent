package com.xiaoyuan.model.dto;
import com.xiaoyuan.model.ErpCar;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ErpUserDto {
    /**
     * 用户自增主键
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer role_id;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 用户真实姓名
     */
    private String real_name;
    /**
     * 用户手机号
     */
    private String user_phone;
    /**
     * 用户公司名
     */
    private String user_company_name;
    /**
     * 用户余额
     */
    private BigDecimal rebate_balance;
    /**
     * 用户余额->文本格式
     */
    private String rebateBalanceTxt;
    /**
     * 用户编号
     */
    private String number;
    /**
     * 用户地址
     */
    private String user_address;
    /**
     * 用户销售区域
     */
    private String sales_area;
    /**
     * 用户挂牌价
     */
    private BigDecimal user_price;
    /**
     * 用户挂牌价 ->文本格式
     */
    private String userPriceTxt;
    /**
     * 用户部门
     */
    private String department;
    /**
     * 用户职位
     */
    private String job;
    /**
     * 用户公司编号
     */
    private String user_company_sn;
    /**
     * 驾驶员证件号
     */
    private String driver_sn;
    /**
     * 驾驶员证件号
     */
    private String car_sn;
    /**
     * 驾驶员证件号
     */
    private String car_owner;
    /**
     * 驾驶员证件号
     */
    private String car_contact_phone;
    /**
     * 车辆净重
     */
    private BigDecimal car_weight;
    /**
     * 车辆载重
     */
    private BigDecimal car_load;
    /**
     * 通行卡的识别号
     */
    private String card_rfid;
    /**
     * gps_imei
     */
    private String gps_imei;
    /**
     * GPSid
     */
    private String gps_sn;
    /**
     * 车辆类型 ->文本格式
     */
    private String carCate;
    /**
     * 车辆类型 1是销货，2是进货
     */
    private Integer cate_id;
    /**
     * 关联表主键  驾驶员信息主键
     */
    private Integer driverId;
    /**
     * 关联表主键    gps设备主键
     */
    private Integer gpsId;
    /**
     * 关联表主键    车辆信息自增主键
     */
    private Integer carId;
    /**
     * 关联表主键    通行卡自增主键
     */
    private Integer cardId;
    /**
     * 用户类型    文本格式
     */
    private String userCate;
    /**
     * 挂牌价    文本格式
     */
    private String userPirce;
    /**
     * 装载物料名称
     */
    private String goods_name;
    /**
     * 装载物料主键
     */
    private Integer goods_id;
    /**
     * 卡片状态
     */
    private String card_state;
    /**
     * 开卡时间
     */
    private String card_open_date;
    /**
     * 卡片状态
     * 1等于正常，-1代表停用
     */
    private Integer cardStatus;
    /**
     * 卡片类型
     */
    private String card_cate;
    /**
     * 每天最大下单量
     */
    private Double limit_max;
    /**
     * 最小下单量
     */
    private Double min_number;
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
     * 省        文本格式
     */
    private String province_txt;
    /**
     * 市        文本格式
     */
    private String city_txt;
    /**
     * 区        文本格式
     */
    private String area_txt;
    /**
     * 用户类型
     */
    private Integer user_cate;
    /**
     * 公司地址
     */
    private String company_address;
    /**
     * 审核状态
     */
    private Integer audit;
    /**
     * 之前公众号内有使用该参数，因此先给与保留，后期前端修改后才对该属性进行删除。
     */
    private ErpCar car;

    /**
     *  省市区字符串
     */
    private String str;
    /**
     * 用户密码，该属性专门针对注册使用
     */
    private String password_;
    /**
     * 确认密码，该属性专门针对注册使用
     */
    private String password_re;
    /**
     * 验证码
     */
    private String code;
    /**
     * 是否是内部车辆
     * 1则是，0则否
     */
    private Integer is_inside;

    public BigDecimal getLimitMaxByBigDecimal(){
        return new BigDecimal(this.limit_max);
    }

    public BigDecimal getMinNumberByBigDecimal(){
        return new BigDecimal(this.min_number);
    }


}
