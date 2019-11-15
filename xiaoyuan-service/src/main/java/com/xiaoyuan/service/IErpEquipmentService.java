package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpEquipment;

public interface IErpEquipmentService {
	
	int insert(ErpEquipment tlErpEquipment);
	
	int delete (Integer id);
	
	int update(ErpEquipment tlErpEquipment);
	
	List<ErpEquipment> lists();
	
	ErpEquipment get(Integer eId);
	
	ErpEquipment getErpEquipment(ErpEquipment equipment);
	
	List<ErpEquipment> getMsgByIP(String equipment_ip);
	
	List<ErpEquipment> getMsg();
}
