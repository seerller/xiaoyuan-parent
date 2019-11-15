package com.xiaoyuan.mapper;

import com.xiaoyuan.model.ErpEvaluation;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpEvaluationMapper extends MapperConfig<ErpEvaluation> {
	int insert(ErpEvaluation evaluation);
}