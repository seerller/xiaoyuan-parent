package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpSupplier;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpSupplierMapper extends MapperConfig<ErpSupplier> {
    /**
     * 根据用户id查询供应商信息
     * @param userId
     * @return
     */
    ErpSupplier selectByUserId(int userId);
    /**
     * 园区修改供应商信息s表，应该使用非空全字段，，
     *
     * @param supplier
     * @return 2019年3月4日下午2:36:16
     * @author : YZH
     */
    int updateByUserid(ErpSupplier supplier);

    int insert(ErpSupplier supplier);

    /**
     * 根据货物名称查找供应商列表
     *
     * @param goodsName
     * @return 2019年2月25日上午9:05:25
     * @author : YZH
     * <p>
     * 返回信息不足
     * <p>
     * 2019年3月4日下午2:18:49
     * @author : YZH
     */
    List<ErpSupplier> lists(@Param("goods_name") String goodsName, @Param("search") String search);

    ErpSupplier get(Integer id);

    int deleteByUserId(@Param("user_id") Integer user_id);
}