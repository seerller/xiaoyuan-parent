package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpSupplier;

public interface IErpSupplierService {

    /**
     * 根据用户id查询供应商信息
     * @param userId
     * @return
     */
    ErpSupplier selectByUserId(int userId);

    int insert(ErpSupplier tlErpSupplier);

    int delete(Integer id);

    int update(ErpSupplier tlErpSupplier);

    /**
     * 根据货物名称查找供应商列表
     *
     * @param name
     * @return
     *
     *        2019年2月25日上午9:02:39
     * @author : YZH
     */
    /**
     * 返回信息不足
     *
     * @param name
     * @return 2019年3月4日下午2:18:08
     * @author : YZH
     */
    List<ErpSupplier> list(String name, String search);

    ErpSupplier get(Integer sId);

    /**
     * 园区修改供应商信息s表，应该使用非空全字段，
     *
     * @param supplier
     * @return 2019年3月4日下午2:35:35
     * @author : YZH
     */
    int updateByUserid(ErpSupplier supplier);

    int deleteByUserId(Integer user_id);
}
