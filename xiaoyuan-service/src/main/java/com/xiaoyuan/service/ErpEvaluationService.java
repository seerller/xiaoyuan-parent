package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpEvaluationMapper;
import com.xiaoyuan.model.ErpEvaluation;
@Service
public class ErpEvaluationService implements IErpEvaluationService {
	@Autowired
	ErpEvaluationMapper evaluationMapper;

	@Override
	public int insert(ErpEvaluation tlErpEvaluation) {
		int insert = evaluationMapper.insert(tlErpEvaluation);
		if(insert > 0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete =evaluationMapper.deleteByPrimaryKey(id);
		if (delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpEvaluation tlErpEvaluation) {
	  int update = evaluationMapper.updateByPrimaryKeySelective(tlErpEvaluation);
	  if(update>0){
		  return update;
	  }
		return 0;
	}

	@Override
	public List<ErpEvaluation> lists() {
		List<ErpEvaluation> list = evaluationMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpEvaluation get(Integer id) {
		ErpEvaluation get = evaluationMapper.selectByPrimaryKey(id);
		if (get != null){
			return get;
		}
		return null;
	}

}
