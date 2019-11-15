package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpOrders;

public interface ErpOrdersMapper extends com.xiaoyuan.tools.MapperConfig<ErpOrders>{
	List<ErpOrders> orderslist(ErpOrders orders);
	
	int insert(ErpOrders orders);
	
	List<ErpOrders> whitelist();
}
