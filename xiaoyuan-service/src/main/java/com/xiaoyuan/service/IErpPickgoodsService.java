package com.xiaoyuan.service;

import java.math.BigDecimal;
import java.util.List;

import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.dto.ErpPickgoodsTotalDto;
import com.xiaoyuan.model.dto.ErpOrderAmountDto;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpPickGoodsTotalSearch;

public interface IErpPickgoodsService {


    int updateById(ErpPickgoods erpPickgoods);

    int insert(ErpPickgoods erpPickgoods);

    int delete(Integer id);

    /**
     * 进货申请的提交按钮
     *
     * @param tlErpPickgoods
     * @return 2019年2月25日上午11:00:31
     * @author : YZH
     */
    int update(ErpPickgoods tlErpPickgoods);

    List<ErpPickgoods> lists(String driver_phone,Integer is_car_order,Integer is_end,Integer user_id, Integer status_, String date1, String date2, String customer_name,
                             String pickgoods_send_company,
                             String pickgoods_car_sn);

    ErpPickgoods get(Integer pMaterialId);

    /**
     * 提货统计报表
     * <p>
     * 实现接口的时候，判断非空，然后返回情况有为null，但是controller中没有进行非空判断，导致空指针异常
     *
     * @return 2019年2月27日上午11:51:14
     * @author : YZH
     */
    List<ErpPickgoods> lists_statistice();

    ErpPickgoods getByCarOrderSn(String car_order_sn, String pickgoods_car_sn);

    ErpPickgoods out_goods(String car_order_sn, String pickgoods_car_sn);

    List<ErpPickgoods> list_out_goods();

    ErpPickgoods getPickgoodsByMessage(ErpPickgoods pickgoods);

    ErpPickgoods getPound(ErpPickgoods pick);

    Integer maxOrderSn(Integer o_id);

    // *************************进货******************************

    /**
     * 数据空，前来看看SQL语句
     *
     * @return 2019年2月27日下午4:28:23
     * @author : YZH
     */
    List<ErpPickgoods> lists_audit();

    ErpPickgoods getPriceByMessage(ErpPickgoods ingoods);

    /**
     * 根据所登录用户的供应商ID即s_id列，查询订单表中的记录
     *
     * @param s_id
     * @return 2019年2月28日上午9:34:46
     * @author : YZH
     */
    List<ErpPickgoods> lists_price(Integer s_id);

    /**
     * @param pickgoods_car_sn
     * @return
     *
     *        2019年2月27日下午5:45:35
     * @author : YZH
     */
    /**
     * 园区审核供应商价格确认，没显示数据
     *
     * @param pickgoods_car_sn
     * @return 2019年3月1日下午4:14:28
     * @author : YZH
     */
    List<ErpPickgoods> lists_ingoods(String pickgoods_car_sn);

    List<ErpPickgoods> lists_statistical();

    ErpPickgoods in_warehouse(String car_order_sn, String pickgoods_car_sn);

    ErpPickgoods purchase(String car_order_sn, String pickgoods_car_sn);
    /**
     * 根据自定义的条件搜索类进行查询集合。
     * @param search        条件搜索类
     * @return
     */
    List<ErpPickgoodsDto> selectOrderBySearch(ErpPickGoodsSearch search);

    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    int countBySearch(ErpPickGoodsSearch search);
    /**
     * 根据自定义的条件搜索类进行查询集合返回分页数据
     * @param search        条件搜索类
     * @return
     */
    List<ErpPickgoodsDto> selectOrderBySearchOfPage(ErpPickGoodsSearch search);
    /**
     * 根据自定义的条件搜索类进行查询集合，并取出集合中的第一个元素。
     * @param search        条件搜索类
     * @return
     */
    ErpPickgoodsDto selectFirstOrderBySearch(ErpPickGoodsSearch search);

    /**
     * 根据自定义的条件搜索类进行订单数量统计
     * @return
     */
    ErpOrderAmountDto countOrderAmount(ErpPickGoodsSearch search);

    /**
     * 根据自定义的条件搜索类统计完成订单数量、提货数量与消费金额
     * @return
     */
    ErpPickgoodsTotalDto countPickgoodsAccountTotal(ErpPickGoodsTotalSearch search);

    /**
     * 根据对象内属性数据是否存在生成数据库数据。
     * @param goods
     * @return
     */
    int insertByPickgoods(ErpPickgoods goods);

    /**
     * 根据对象内属性数据是否存在修改数据库数据
     * @param pickgoods
     * @return
     */
    int updateByPrimaryKeySelective(ErpPickgoods pickgoods);

    /**
     * 根据传入参数查询是否存在正在排队中的订单，存在则不允许进行再次排队
     * @param search
     * @return
     */
    int selectIsConfirmQueueByTime(ErpPickGoodsSearch search);

    /**
     * 根据用户id统计今天该用户的商品总数量
     * @param userId
     * @return
     */
    BigDecimal sumCurrentPickGoodsNum(Integer userId);

    /**
     * 根据主键修改数据
     * @param order
     * @param ids
     * @return
     */
    int updateByIds(ErpPickgoods order , List<Integer> ids);

    /**
     * 根据用户id统计是否存在已进场，但未出厂的销货单
     * @param userId
     * @return
     */
    int countIsConfirmQueueNum(Integer userId);
}
