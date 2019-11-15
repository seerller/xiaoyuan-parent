package com.xiaoyuan.model.search;
import lombok.Data;

/**
 * 进货单条件搜索类
 * @author  zhengweicheng
 * @time 2019-06-21
 */
@Data
public class ErpPickGoodsTotalSearch extends BaseSearch {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     *  终端客户id
     */
    private Integer customerId;
    /**
     * 进货商品名称
     */
    private String goodsName;
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
     * 筛选订单状态
     */
    private Integer status;
    /**
     * 筛选订单类型
     * 1是销售，-1采购 其他不管。
     */
    private Integer orderCate;

}
