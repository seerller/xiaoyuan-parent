package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpOrders;

public interface IErpOrdersService {
	int insert(ErpOrders orders);
	
	int update(ErpOrders orders);
	
	ErpOrders get(Integer id);
	
	List<ErpOrders> lists();
	
	List<ErpOrders> orderslist(ErpOrders orders);
	
	List<ErpOrders> whitelist();
}
