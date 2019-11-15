package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpCarOrderMapper extends MapperConfig<ErpCarOrder> {
    /**
     * @param carOrder
     * @return
     */
    @Override
    int insert(ErpCarOrder carOrder);

    List<ErpCarOrder> lists(@Param("id") Integer user_id, @Param("car_order_sn") String car_order_sn);

    /**
     * 车单列表
     *
     * @param id
     * @param car_order_pickdate
     * @return 2019年3月1日上午9:33:49
     * @author : YZH
     */
    List<ErpCarOrder> lists_order(@Param("user_id") Integer id, @Param("car_order_pickdate") String car_order_pickdate,
                                  @Param("is_car_order") Integer is_car_order);

    int updateCarOrder(ErpCarOrder carOrder);

    ErpCarOrder selectByPickgoodsId(@Param("pickgoods_id") Integer pickgoods_id);

    ErpCarOrder get(Integer id);

    List<ErpCarOrder> queueList();

    ErpCarOrder getCarOrder(ErpCarOrder carOrder);

    Integer getMaxQueue_sn();

    List<ErpCarOrder> lists_order_ingoods(@Param("user_id") Integer id,
                                          @Param("car_order_pickdate") String car_order_pickdate);

    int insertBatch(List<ErpCarOrder> list);
}