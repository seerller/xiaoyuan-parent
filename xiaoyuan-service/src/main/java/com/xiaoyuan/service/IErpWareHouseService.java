package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.model.ErpWareHouse;

public interface IErpWareHouseService {
	
	List<ErpWareHouse> lists();
	
	List<ErpWareHouse> out_lists();
	
	int update (ErpCarOrder carOrder);
	
	List<ErpWareHouse> wareHouse();

}
