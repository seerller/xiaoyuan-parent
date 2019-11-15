package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.search.ErpGoodsSearch;
import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpGoods;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpGoodsMapper extends MapperConfig<ErpGoods> {

    /**
     * 园区修改供应商信息，这里查到的数据有问题，需要查看SQL，
     *
     * @param goods_cate
     * @return 2019年3月2日下午5:30:42
     * @author : YZH
     */
    List<ErpGoods> goodsName(@Param("goods_cate") Integer goods_cate);

    ErpGoods searchGoodsMessageByGoodsName(@Param("goods_name") String goods_name);

    /**
     * 提货物料管理，传参为1
     *
     * @param goods_cate
     * @return 2019年3月7日上午10:08:26
     * @author : YZH
     */
    List<ErpGoods> lists(@Param("goods_cate") Integer goods_cate, @Param("status_") Integer status_);

    int insert(ErpGoods goods);

    ErpGoods get(Integer id);
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

    int insertService(ErpGoods goods);
}
