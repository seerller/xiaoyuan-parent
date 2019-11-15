package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpCarOrder;

public interface IErpCarOrderService {

    int insert(ErpCarOrder erpCarOrder);

    int delete(Integer id);

    int update(ErpCarOrder erpCarOrder);

    List<ErpCarOrder> lists(Integer id, String sn);

    ErpCarOrder get(Integer clUser);

    /**
     * 车单列表
     * <p>
     * 20190325 司机所属车单筛选，
     *
     * @param id
     * @param car_order_pickdate
     * @return 2019年3月1日上午9:32:41
     * @author : YZH
     */
    List<ErpCarOrder> lists_order(Integer id, String car_order_pickdate,Integer is_car_order);

    int updateOrder(ErpCarOrder erpCarOrder);

    List<ErpCarOrder> queueList();

    ErpCarOrder getCarOrder(ErpCarOrder carOrder);

    Integer getMaxQueue_sn();

    List<ErpCarOrder> lists_order_ingoods(Integer userId, String car_order_pickdate);
    /**
     * 修改操作
     * @param carOrder
     * @return
     */
    int updateByPrimaryKeySelective(ErpCarOrder carOrder);

    /**
     * 插入数据库操作。
     * @param carOrder
     * @return
     */
    int insertService(ErpCarOrder carOrder);

    ErpCarOrder selectByPickgoodsId(Integer pickgoodsId);
}
