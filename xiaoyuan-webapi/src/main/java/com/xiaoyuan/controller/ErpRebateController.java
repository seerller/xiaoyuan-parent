package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpAccount;
import com.xiaoyuan.model.ErpRebate;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpAccountService;
import com.xiaoyuan.service.IErpRebateService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 回扣控制类？少用？
 * 
 * 基本没用，菜单项没放出来，返点管理
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:46:38
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpRebate")
public class ErpRebateController {
	@Autowired
	private IErpRebateService rebateService;
	@Autowired
	private IErpUserService userService;
	@Autowired
	private IErpAccountService accountService;

	@RequestMapping(value = "/lists")
	public MessageBean lists() {
		List<ErpRebate> list = rebateService.lists();
		if (list != null) {
			return new MessageBean(10001, "", list);
		}
		return new MessageBean(10000, "列表数据为空", list);
	}

	@RequestMapping(value = "/delete")
	public MessageBean delete(@RequestParam("id") Integer id) {
		int result = rebateService.delete(id);
		if (result > 0) {
			return new MessageBean(10001, "返点设置删除成功", null);
		}
		return new MessageBean(10000, "返点设置删除失败", null);
	}

	@RequestMapping(value = "/detail")
	public MessageBean detail(@RequestParam("id") Integer id) {
		ErpRebate get = rebateService.get(id);
		if (get != null) {
			return new MessageBean(10001, "", get);
		}
		return new MessageBean(10000, "没有找到此纪录", get);
	}

	@RequestMapping("/add")
	public MessageBean add(@RequestParam(value = "user_name", required = false) String user_name, ErpRebate tlerprebate,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "account_totalrecharge", required = false) Double account_totalrecharge,
			@RequestParam(value = "sales_area", required = false) String sales_area) {
		if (id != null) {
			ErpUser user = new ErpUser();
			user.setId(id);
			/*
			 * if(user_name != null && user_name !=""){
			 * user.setUser_name(user_name); }
			 */
			if (sales_area != null && sales_area != "") {
				user.setSales_area(sales_area);
				userService.update(user);
			}
			ErpAccount account = new ErpAccount();
			if (account_totalrecharge != null) {
				account.setAccount_totalrecharge(account_totalrecharge);
				account.setUser_id(id);
				accountService.updateAccountByUserid(account);
			}
			ErpRebate rebate = new ErpRebate();
			rebate.setUser_id(id);
			if (tlerprebate.getRebate_points() != null) {
				rebate.setRebate_points(tlerprebate.getRebate_points());
			}
			if (tlerprebate.getRebate_rate() != null) {
				rebate.setRebate_rate(tlerprebate.getRebate_rate());
			}
			if (tlerprebate.getRebate_audit() != null && tlerprebate.getRebate_audit() != "") {
				rebate.setRebate_audit(tlerprebate.getRebate_audit());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			rebate.setUpdatetime(sdf.format(new Date()));
			int result = rebateService.updateByUserid(rebate);
			if (result > 0) {
				return new MessageBean(10001, "返点设置修改成功", null);
			}
		}
		return new MessageBean(10000, "返点设置修改失败", null);
	}

	/**
	 * 返点禁止 status --->0
	 * 
	 * @param tlerprebate
	 * @param id
	 * @return
	 */
	@RequestMapping("/stop")
	public MessageBean stop(ErpRebate tlerprebate, @RequestParam Integer id) {
		if (id != null) {
			tlerprebate.setId(id);
			tlerprebate.setStatus_(0);
			int result = rebateService.update(tlerprebate);
			if (result > 0) {
				return new MessageBean(10001, "返点设置已禁止", null);
			}
		}
		return new MessageBean(10000, "返点设置禁止异常", null);
	}
}
