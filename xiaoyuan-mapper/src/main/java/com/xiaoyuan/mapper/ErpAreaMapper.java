package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpArea;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpAreaMapper extends MapperConfig<ErpArea> {
	List<ErpArea> lists();
	
	int updateByUserid(ErpArea area);
	
	int insert(ErpArea area);
	
	ErpArea get(Integer id);
}