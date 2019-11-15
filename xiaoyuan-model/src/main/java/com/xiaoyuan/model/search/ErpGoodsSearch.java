package com.xiaoyuan.model.search;

import lombok.Data;
import lombok.ToString;

/**
 * 物料商品条件搜索类
 */
@Data
@ToString(callSuper = true)
public class ErpGoodsSearch extends BaseSearch {
    /**
     * 根据该属性判断是进货物料还是销货物料
     * 当属性值等于1时则是销货物料
     * 当属性值等于2时则是进货物料
     * 当属性值等于0时则查询所有物料
     */
    private Integer cateId = 1;
    private Integer goodsId;
    /**
     * 物料编号，用来检验物料的唯一性
     */
    private String goods_sn;
    /**
     * 原物料编号，用来检验物料的唯一性
     */
    private String old_goods_sn;
}
