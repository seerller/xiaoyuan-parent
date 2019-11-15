package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpUnit;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpUnitMapper extends MapperConfig<ErpUnit> {
	List<ErpUnit> lists();
	
	int updateUnitByUserid(ErpUnit unit);
	
	int insert(ErpUnit unit);
	
	ErpUnit get(Integer id);
}