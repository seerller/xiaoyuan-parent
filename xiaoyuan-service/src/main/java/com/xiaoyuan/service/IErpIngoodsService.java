package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpIngoods;

public interface IErpIngoodsService {
	
	int insert(ErpIngoods tlErpIngoods);
	
	int delete (Integer id);
	
	int update (ErpIngoods tlErpIngoods);
	
	List<ErpIngoods> lists(String ingoods_car_sn);
	
	ErpIngoods get(Integer iMaterialId);
	
	List<ErpIngoods> lists_statistical();
	
	ErpIngoods  out_lists( String ingoods_car_sn);
	
	ErpIngoods in_warehouse(String ingoods_sn,String ingoods_car_sn);
	
	ErpIngoods purchase(String ingoods_sn,String ingoods_car_sn);
	
	List<ErpIngoods> lists_price(Integer supplier_id);
	
	List<ErpIngoods> lists_audit();
	
	ErpIngoods getPriceByMessage(ErpIngoods ingoods);
}
