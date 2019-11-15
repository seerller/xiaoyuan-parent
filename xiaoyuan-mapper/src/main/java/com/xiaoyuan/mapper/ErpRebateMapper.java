package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpRebate;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpRebateMapper extends MapperConfig<ErpRebate> {
	
	int updateByUserid(ErpRebate rebate);
	
	List<ErpRebate> lists();
	
	int insert(ErpRebate rebate);
	
	ErpRebate get(Integer id);
}