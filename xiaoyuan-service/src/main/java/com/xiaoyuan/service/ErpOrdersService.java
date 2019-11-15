package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpOrdersMapper;
import com.xiaoyuan.model.ErpOrders;
@Service
public class ErpOrdersService implements IErpOrdersService{
	@Autowired
	private ErpOrdersMapper ordersMapper;
	@Override
	public int insert(ErpOrders orders) {
		// TODO Auto-generated method stub
		return ordersMapper.insert(orders);
	}

	@Override
	public int update(ErpOrders orders) {
		// TODO Auto-generated method stub
		return ordersMapper.updateByPrimaryKeySelective(orders);
	}

	@Override
	public ErpOrders get(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ErpOrders> lists() {
		// TODO Auto-generated method stub
		return ordersMapper.selectAll();
	}

	@Override
	public List<ErpOrders> orderslist(ErpOrders orders) {
		// TODO Auto-generated method stub
		return ordersMapper.orderslist(orders);
	}

	@Override
	public List<ErpOrders> whitelist() {
		// TODO Auto-generated method stub
		return ordersMapper.whitelist();
	}
	
}
