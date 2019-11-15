package com.xiaoyuan.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpGoodsSearch;
import com.xiaoyuan.tools.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpGoodsMapper;
import com.xiaoyuan.model.ErpGoods;

@Service
public class ErpGoodsService implements IErpGoodsService {

    @Autowired
    private ErpGoodsMapper goodsMapper;

    @Override
    public int add(ErpGoods goods) {
        // TODO Auto-generated method stub
        int add = goodsMapper.insert(goods);
        if (add > 0) {
            return add;
        }
        return 0;
    }

    @Override
    public int update(ErpGoods goods) {
        // TODO Auto-generated method stub
        int updateByPrimaryKeySelective = goodsMapper.updateByPrimaryKeySelective(goods);
        if (updateByPrimaryKeySelective > 0) {
            return updateByPrimaryKeySelective;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO Auto-generated method stub
        int delete = goodsMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public List<ErpGoods> lists(Integer goods_cate, Integer status_) {
        // TODO Auto-generated method stub
        List<ErpGoods> list = goodsMapper.lists(goods_cate, status_);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpGoods get(Integer id) {
        // TODO Auto-generated method stub
        ErpGoods goods = goodsMapper.get(id);
        if (goods != null) {
            return goods;
        }
        return null;
    }

    @Override
    public List<ErpGoods> goodsName(Integer id) {
        // TODO Auto-generated method stub
        List<ErpGoods> goodsName = goodsMapper.goodsName(id);
        if (goodsName != null) {
            return goodsName;
        }
        return null;
    }

    @Override
    public ErpGoods searchGoodsMessageByGoodsName(String goodsName) {
        // TODO Auto-generated method stub
        ErpGoods goods = goodsMapper.searchGoodsMessageByGoodsName(goodsName);
        if (goods != null) {
            return goods;
        }
        return null;
    }
    /**
     * 返回销货商品
     * @return
     */
    @Override
    public ErpGoods findPickGoods() {
        return goodsMapper.findPickGoods();
    }
    /**
     * 返回所有进货商品信息
     * @return
     */
    @Override
    public List<ErpGoods> resultList() {
        return goodsMapper.resultList();
    }
    /**
     * 根据条件搜索类查询相关数据
     * @return
     */
    @Override
    public List<ErpGoods> selectBySearch(ErpGoodsSearch search){
        return goodsMapper.selectBySearch(search);
    }
    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    @Override
    public int countBySearch(ErpGoodsSearch search){
        return goodsMapper.countBySearch(search);
    }

    @Override
    public ErpGoods selectFirstBySearch(ErpGoodsSearch search) {
        List<ErpGoods>  list =selectBySearch( search);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据条件搜索类查询相关数据
     * @return
     */
    @Override
    public List<ErpGoods> selectBySearchOfPage(ErpGoodsSearch search){
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpGoods> list = selectBySearch(search);
        PageBean<ErpGoods> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
        pageData.setItems(list);
        return pageData.getItems();
    }
    /**
     * 检验物料编码唯一性
     * @param goodsSn
     */
    @Override
    public void goodsSnUnique(String oldGoodsSn, String goodsSn) throws LogicException {
        if(StringUtils.isBlank(goodsSn)){
            throw new LogicException("物料编码不可为空.");
        }
        ErpGoodsSearch search = new ErpGoodsSearch();
        search.setCateId(0);
        search.setOld_goods_sn(oldGoodsSn);
        search.setGoods_sn(goodsSn);
        if(selectBySearch(search).size() > 0){
            throw new LogicException("物料编号已存在.");
        }
    }
    /**
     * 检验物料编码唯一性
     * @param goodsSn
     */
    @Override
    public void goodsSnUnique(String goodsSn) throws LogicException{
        goodsSnUnique(null,goodsSn);
    }

    @Override
    public int insertService(ErpGoods goods) {
        return goodsMapper.insertService(goods);
    }
}
