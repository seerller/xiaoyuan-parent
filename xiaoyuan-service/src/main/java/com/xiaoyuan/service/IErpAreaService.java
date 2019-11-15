package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpArea;

public interface IErpAreaService {
	int update(ErpArea tlErpArea);
	int delete(Integer id);
	List<ErpArea> list();
	ErpArea detail(Integer id);
	
	int updateByUserid(ErpArea area);
}
