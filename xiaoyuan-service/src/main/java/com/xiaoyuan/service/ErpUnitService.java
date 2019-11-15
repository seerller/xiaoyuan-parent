package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpUnitMapper;
import com.xiaoyuan.model.ErpUnit;
@Service
public class ErpUnitService implements IErpUnitService {
	@Autowired
	ErpUnitMapper unitMapper;

	@Override
	public int insert(ErpUnit tlErpUnit) {
		int insert =unitMapper.insert(tlErpUnit);
		if(insert>0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = unitMapper.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpUnit tlErpUnit) {
		int update = unitMapper.updateByPrimaryKeySelective(tlErpUnit);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpUnit> lists() {
		List<ErpUnit> list =unitMapper.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpUnit get(Integer id) {
		ErpUnit get = unitMapper.get(id);
		if (get!=null){
			return get;
		}
		return null;
	}

	@Override
	public int updateUnitByUserid(ErpUnit unit) {
		int result = unitMapper.updateUnitByUserid(unit);
		if(result>0){
			return result;
		}
		return 0;
	}

}
