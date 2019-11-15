package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpWareHouse;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpWareHouseMapper extends MapperConfig<ErpWareHouse> {
	
	List<ErpWareHouse> lists();
	
	List<ErpWareHouse> out_lists();
	
	int insert (ErpWareHouse wareHouse);
	
	List<ErpWareHouse> wareHouse();
}
