package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyuan.mapper.ErpEquipmentMapper;
import com.xiaoyuan.model.ErpEquipment;
@Service
public class ErpEquipmentService implements IErpEquipmentService {
	@Autowired
	ErpEquipmentMapper equipmentMapper;

	@Override
	public int insert(ErpEquipment tlErpEquipment) {
	int insert =equipmentMapper.insert(tlErpEquipment);
	if(insert > 0){
		return insert;
	}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete =  equipmentMapper.deleteByPrimaryKey(id);
		if(delete >0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpEquipment tlErpEquipment) {
		int update =equipmentMapper.updateByPrimaryKeySelective(tlErpEquipment);
		if(update > 0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpEquipment> lists() {
		List<ErpEquipment> list =equipmentMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpEquipment get(Integer eId) {
		ErpEquipment get = equipmentMapper.selectByPrimaryKey(eId);
		if(get !=null){
			return get;
		}
		return null;
	}

	@Override
	public ErpEquipment getErpEquipment(ErpEquipment equipment) {
		ErpEquipment get = equipmentMapper.getErpEquipment(equipment);
		if(get !=null){
			return get;
		}
		return null;
	}

	@Override
	public List<ErpEquipment> getMsgByIP(String equipment_ip) {
		return equipmentMapper.getMsgByIP(equipment_ip);
	}

	@Override
	public List<ErpEquipment> getMsg() {
		// TODO Auto-generated method stub
		return equipmentMapper.getMsg();
	}

}
