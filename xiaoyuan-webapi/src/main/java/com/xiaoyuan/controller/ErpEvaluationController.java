package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpEvaluation;
import com.xiaoyuan.service.IErpEvaluationService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 星级评定控制类，，，没放出菜单项，，，？？？
 * 
 * @author YZH
 * @version 2019年3月8日 下午4:32:23
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpEvaluation")
public class ErpEvaluationController {
	@Autowired
	private IErpEvaluationService evaluationService;

	@RequestMapping("/add")
	public MessageBean add(ErpEvaluation evaluation, @RequestParam(value = "id", required = false) Integer id) {
		ErpEvaluation erpEvaluation = new ErpEvaluation();
		if (evaluation.getEvaluation_company_name() != null && evaluation.getEvaluation_company_name() != "") {
			erpEvaluation.setEvaluation_company_name(evaluation.getEvaluation_company_name());
		}
		if (evaluation.getEvaluation_company_cate() != null && evaluation.getEvaluation_company_cate() != "") {
			erpEvaluation.setEvaluation_company_cate(evaluation.getEvaluation_company_cate());
		}
		if (evaluation.getEvaluation_star() != null && evaluation.getEvaluation_star() != "") {
			erpEvaluation.setEvaluation_star(evaluation.getEvaluation_star());
		}
		if (evaluation.getEvaluation_enter() != null && evaluation.getEvaluation_enter() != "") {
			erpEvaluation.setEvaluation_enter(evaluation.getEvaluation_enter());
		}
		if (evaluation.getEvaluation_date() != null && evaluation.getEvaluation_date() != "") {

			erpEvaluation.setEvaluation_date(new SimpleDateFormat().format(evaluation.getEvaluation_date()));

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		erpEvaluation.setCreatetime(sdf.format(new Date()));
		int result = evaluationService.insert(erpEvaluation);
		if (result > 0) {
			return new MessageBean(10001, "星级评定录入成功", null);
		}
		return new MessageBean(10000, "星级评定录入失败", null);

	}

	@RequestMapping("/lists")
	public MessageBean lists() {
		List<ErpEvaluation> lists = evaluationService.lists();
		if (lists != null) {
			return new MessageBean(10001, "", lists);
		}
		return new MessageBean(10000, "列表数据为空", lists);

	}
}
