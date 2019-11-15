package com.xiaoyuan.service;

import java.util.List;
import com.xiaoyuan.model.ErpEvaluation;

public interface IErpEvaluationService {
	
	int insert(ErpEvaluation tlErpEvaluation);
	
	int delete(Integer id);
	
	int update(ErpEvaluation tlErpEvaluation);
	
	List<ErpEvaluation> lists();
	
	ErpEvaluation get(Integer id);

}
