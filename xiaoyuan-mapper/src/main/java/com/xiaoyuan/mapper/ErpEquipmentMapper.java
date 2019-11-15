package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpEquipment;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpEquipmentMapper extends MapperConfig<ErpEquipment> {
	int insert(ErpEquipment equipment);
	
	List<ErpEquipment> list();
	
	ErpEquipment getErpEquipment(ErpEquipment erpEquipment);
	
	List<ErpEquipment> getMsgByIP(@Param("equipment_ip")String equipment_ip);
	
	List<ErpEquipment> getMsg();
}