package com.xiaoyuan.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.dto.ErpPickgoodsTotalDto;
import com.xiaoyuan.model.dto.ErpOrderAmountDto;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpPickGoodsTotalSearch;
import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.tools.MapperConfig;


/**
 * 有空再排序？增删改查。。。。
 * 20190321
 */
public interface ErpPickgoodsMapper extends MapperConfig<ErpPickgoods> {
    @Override
    int insert(ErpPickgoods erpPickgoods);


    int updateById(ErpPickgoods erpPickgoods);

    List<ErpPickgoods> lists(@Param("driver_phone")String driver_phone,@Param("is_car_order") Integer is_car_order, @Param("is_end") Integer is_end, @Param("user_id") Integer user_id, @Param("status_") Integer status_,
                             @Param("date1") String date1, @Param("date2") String date2,
                             @Param("customer_name") String customer_name,
                             @Param("pickgoods_send_company") String pickgoods_send_company,
                             @Param("pickgoods_car_sn") String pickgoods_car_sn);

    /**
     * 提货统计报表
     *
     * @return 2019年2月27日上午11:51:54
     * @author : YZH
     */
    List<ErpPickgoods> lists_statistice();

    ErpPickgoods get(Integer id);


    ErpPickgoods getByCarOrderSn(@Param("car_order_sn") String car_order_sn,
                                 @Param("pickgoods_car_sn") String pickgoods_car_sn);

    ErpPickgoods out_goods(@Param("car_order_sn") String car_order_sn,
                           @Param("pickgoods_car_sn") String pickgoods_car_sn);

    List<ErpPickgoods> list_out_goods();

    ErpPickgoods getPickgoodsByMessage(ErpPickgoods pick);

    ErpPickgoods getPound(ErpPickgoods pick);

    Integer maxOrderSn(@Param("o_id") Integer o_id);

    /**
     * 数据空，前来看看SQL语句
     *
     * @return 2019年2月27日下午4:29:03
     * @author : YZH
     */
    List<ErpPickgoods> lists_audit();

    ErpPickgoods getPriceByMessage(ErpPickgoods ingoods);

    /**
     * 根据s_id查订单表记录
     *
     * @param supplier_id
     * @return 2019年2月28日上午9:36:58
     * @author : YZH
     */
    List<ErpPickgoods> lists_price(@Param("supplier_id") Integer supplier_id);

    /**
     * 园区审核供应商价格确认，车牌号传not null?但是供应商确认价格之后是园区审核确认价格，审核之后供应商 录入车牌号才是not
     * null吧？或者我试一下在进货申请就填车牌号？还是没有，，
     *
     * @param pickgoods_car_sn
     * @return 2019年3月1日下午4:15:51
     * @author : YZH
     */
    List<ErpPickgoods> lists_ingoods(@Param("pickgoods_car_sn") String pickgoods_car_sn);

    List<ErpPickgoods> lists_statistical();

    ErpPickgoods in_warehouse(@Param("car_order_sn") String car_order_sn,
                              @Param("pickgoods_car_sn") String pickgoods_car_sn);

    ErpPickgoods purchase(@Param("car_order_sn") String car_order_sn,
                          @Param("pickgoods_car_sn") String pickgoods_car_sn);

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
     * 根据自定义的条件搜索类进行订单数量统计
     * @param search
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
     * @param pickgoods
     * @return
     */
    int insertByPickgoods(ErpPickgoods pickgoods);
    /**
     * 根据对象内属性数据是否存在修改数据库数据
     * @param pickgoods
     * @return
     */
    int updateByPickGoods(ErpPickgoods pickgoods);

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
    int updateByIds(@Param("order") ErpPickgoods order ,@Param("ids") List<Integer> ids);

    /**
     * 根据用户id统计是否存在已进场，但未出厂的销货单
     * @param userId
     * @return
     */
    int countIsConfirmQueueNum(@Param("userId")Integer userId);
}