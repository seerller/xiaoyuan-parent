package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpUnit;

public interface IErpUnitService {
	
	int insert (ErpUnit tlErpUnit);
	
	int delete (Integer id);
	
	int update (ErpUnit tlErpUnit);
	
	List<ErpUnit> lists();
	
	ErpUnit get (Integer id);
	
	int updateUnitByUserid(ErpUnit unit);

}
