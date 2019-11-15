package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpGoods;
import com.xiaoyuan.model.search.ErpGoodsSearch;

public interface IErpGoodsService {

    int add(ErpGoods goods);

    int update(ErpGoods goods);

    int delete(Integer id);

    /**
     * 提货物料管理，传参为1
     *
     * @param goods_cate
     * @return 2019年3月7日上午10:07:56
     * @author : YZH
     */
    List<ErpGoods> lists(Integer goods_cate, Integer status_);

    ErpGoods get(Integer id);

    /**
     * 园区修改供应商信息，查找goods表，表里有物料编号但查到的数据有点怪，可能是筛选条件的问题？需要查看SQL语句
     *
     * @param id
     * @return 2019年3月2日下午5:29:33
     * @author : YZH
     */
    List<ErpGoods> goodsName(Integer id);

    ErpGoods searchGoodsMessageByGoodsName(String goodsName);

    /**
     * 返回销货商品
     * @return
     */
    ErpGoods findPickGoods();
    /**
     * 返回所有进货商品信息
     * @return
     */
    List<ErpGoods> resultList();


    /**
     * 根据条件搜索类查询相关数据
     * @return
     */
    List<ErpGoods> selectBySearch(ErpGoodsSearch search);
    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    int countBySearch(ErpGoodsSearch search);

    /**
     * 根据条件搜索类查询相关数据
     * @return
     */
    ErpGoods selectFirstBySearch(ErpGoodsSearch search);

    /**
     * 根据条件搜索类查询相关数据
     * @return
     */
    List<ErpGoods> selectBySearchOfPage(ErpGoodsSearch search);

    /**
     * 检验物料编码唯一性
     * @param oldGoodsSn
     * @param goodsSn
     */
     void goodsSnUnique(String oldGoodsSn,String goodsSn);

    /**
     * 检验物料编码唯一性
     * @param goodsSn
     */
    void goodsSnUnique(String goodsSn);

    int insertService(ErpGoods goods);
}
