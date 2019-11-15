package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpCarOrderMapper;
import com.xiaoyuan.mapper.ErpWareHouseMapper;
import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.model.ErpWareHouse;

@Service
public class ErpWareHouseService implements IErpWareHouseService {
	@Autowired
	ErpWareHouseMapper wareHouseMapper;
	
	@Autowired
	ErpCarOrderMapper carOrderMapper;
	

	@Override
	public List<ErpWareHouse> lists() {
		List<ErpWareHouse> list = wareHouseMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<ErpWareHouse> out_lists() {
		List<ErpWareHouse> list = wareHouseMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int update(ErpCarOrder carOrder) {
		int update =carOrderMapper.updateCarOrder(carOrder);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpWareHouse> wareHouse() {
		List<ErpWareHouse> list = wareHouseMapper.wareHouse();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	
}
