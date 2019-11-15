package com.xiaoyuan.model.search;
import lombok.Data;

/**
 * 进货单条件搜索类
 * @author  zhengweicheng
 * @time 2019-06-21
 */
@Data
public class ErpPickGoodsSearch extends BaseSearch {
    /**
     * 排序
     */
    private String orderBy = "PICKGOODS.pickgoods_date";
    /**
     * 是否降序
     */
    private Integer isDesc = 1;
    /**
     * 前端传入
     * 具体是查询具体订单的订单id
     */
    private Integer orderId;
    /**
     * 订单类型
     * 1是销售，-1则是采购，
     * 1是销货，-1是进货
     */
    private Integer orderCate = 1;
    /**
     * 开始时间
     * 前端传入
     */
    private String startTime;
    /**
     * 结束时间
     * 前端传入
     */
    private String endTime;
    /**
     * 订单状态
     * 前端传入
     */
    private Integer status;
    /**
     * 设备手机号
     * 后端根据用户角色性质传入
     */
    private String phone;
    /**
     * 用户主键
     * 后端根据用户角色性质传入
     */
    private Integer userId;
    /**
     *  终端客户id
     */
    private Integer customerId;
    /**
     * 订单号
     * 用来查询是否存在相同的订单号。
     */
    private String orderSn;
    /**
     * 根据该参数可查询是否存在正在排队中的数据
     */
    private Integer isConfirmQueue;
    /**
     * 司机主键
     */
    private Integer driverId;
    /**
     * 当前时间
     */
    private String nowTime;
    /**
     * 订单取消过期时间
     */
    private String expireTime;
    /**
     * 入库单异常查询字段
     *  0则查询正常入库
     *  1则查询异常入库。
     */
    private Integer orderErr = 0;
    /**
     * 根据商品名称进行查询数据
     */
    private String goodsName;

    public ErpPickGoodsSearch setId(Integer id){
        this.orderId = id;
        return this;
    }

    public ErpPickGoodsSearch setDriverIdResult(Integer id){
        this.orderId = id;
        return this;
    }

}
