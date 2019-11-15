package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpRebateMapper;
import com.xiaoyuan.model.ErpRebate;
@Service
public class ErpRebateService implements IErpRebateService {
	@Autowired
	ErpRebateMapper rebateMapper;

	@Override
	public int insert(ErpRebate tlErpRebate) {
		int insert = rebateMapper.insert(tlErpRebate);
		if(insert>0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = rebateMapper.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpRebate tlErpRebate) {
		int update = rebateMapper.updateByPrimaryKeySelective(tlErpRebate);
		if (update>0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpRebate> listtlErpRebate() {
		List<ErpRebate> list = rebateMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpRebate get(Integer id) {
		ErpRebate get = rebateMapper.get(id);
		if(get !=null){
			return get;
		}
		return null;
	}

	@Override
	public int updateByUserid(ErpRebate rebate) {
		return rebateMapper.updateByUserid(rebate);
	}

	@Override
	public List<ErpRebate> lists() {
		List<ErpRebate> list = rebateMapper.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

}
