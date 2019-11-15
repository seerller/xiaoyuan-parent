package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpRebate;

public interface IErpRebateService {
	
	int insert (ErpRebate tlErpRebate);
	
	int delete (Integer id);
	
	int update (ErpRebate tlErpRebate);
	
	List<ErpRebate> listtlErpRebate();
	//返点设置
	List<ErpRebate> lists();
	
	ErpRebate get(Integer id);
	
	int updateByUserid(ErpRebate rebate);

}
