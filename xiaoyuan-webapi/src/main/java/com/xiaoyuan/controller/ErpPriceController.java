package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.model.ErpPrice;
import com.xiaoyuan.model.ErpSupplier;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpPriceService;
import com.xiaoyuan.service.IErpSupplierService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * @author YZH
 * @version 2019年2月27日 下午5:40:50
 *
 *
 *          价格控制类
 * @author YZH
 * @version 2019年3月8日 下午3:44:46
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpPrice")
public class ErpPriceController extends BaseController {
	@Autowired
	private IErpSupplierService supplierService;
	@Autowired
	private IErpPickgoodsService pickgoodsService;
	@Autowired
	private IErpUserService userService;
	@Autowired
	private IErpPriceService priceService;

	/**
	 * @param token
	 * @return
	 *
	 * 		2019年2月27日下午5:42:37
	 * @author : YZH
	 */
	@RequestMapping("/lists")
	public MessageBean lists(
			String token,
			@RequestParam(defaultValue = "1") Integer currentPage,
			@RequestParam(defaultValue = "50") Integer pageSize) {
		ErpUser rs = userService.detail(this.getUserId(token));
		List<ErpPickgoods> list = null;
		if (rs != null) {
			PageHelper.startPage(currentPage, pageSize);
			list = pickgoodsService.lists_price(rs.getS_id());
			if (rs.getRole_id() == 2) {// 供应商 只有供应商才能看到价格管理的列表？
				if (list != null) {
					int countNums = list.size();//总记录数
					PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
					pageData.setItems(list);
					return new MessageBean(10001, "", pageData.getItems());
				}
				return new MessageBean(10000,"数据列表为空",list);
			}
			return new MessageBean(10000,"登录角色不是供应商",list);
		}
		return new MessageBean(10000, "根据token找不到用户对象", list);
	}

	@RequestMapping("/add")
	public MessageBean add(@RequestParam(value = "id", required = false) Integer id, ErpSupplier erpSupplier,
			@RequestParam(value = "ingoods_num", required = false) Double ingoods_num,
			@RequestParam(value = "ingoods_man", required = false) String ingoods_man) {
		ErpSupplier supplier = new ErpSupplier();
		ErpPickgoods ingoods = new ErpPickgoods();
		if (id == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			int value = userService.increment();
			value += 1;
			supplier.setUser_id(value);
			supplier.setGoods_name(erpSupplier.getGoods_name());
			// supplier.setSupplier_spec(erpSupplier.getSupplier_spec());
			supplier.setGoods_unit(erpSupplier.getGoods_unit());
			supplier.setGoods_price(erpSupplier.getGoods_price());
			supplier.setCreatetime(sdf.format(new Date()));
			int result = supplierService.insert(supplier);
			// ingoods.setSupplier_id(supplier.getId());
			ingoods.setPickgoods_num(ingoods_num);
			ingoods.setPickgoods_man(ingoods_man);
			ingoods.setCreatetime(sdf.format(new Date()));
			pickgoodsService.insert(ingoods);
			if (result > 0) {
				return new MessageBean(10001, "价格录入成功", null);
			}
		}
		return new MessageBean(10000, "价格录入异常", null);

	}

	/**
	 * 供应商确认价格，通过按钮
	 * 
	 * @param id
	 * @return
	 *
	 * 		2019年2月28日上午11:44:33
	 * @author : YZH
	 */
	@RequestMapping("/audit")
	public MessageBean audit(@RequestParam Integer id) {
		if (id != null) {
			ErpPickgoods ingoods = pickgoodsService.get(id);
			ingoods.setStatus_(1);// YZH 审核状态
			ingoods.setId(id);// YZH ??
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			ErpPrice erpPrice = new ErpPrice();
			erpPrice.setSupplier_id(ingoods.getSupplier_id());
			erpPrice.setGoods_name(ingoods.getGoods_name());
			ErpPrice price = priceService.selectByPriceMsg(erpPrice);
			if (price == null) {
				erpPrice.setGoods_price(ingoods.getIngoods_price());
				erpPrice.setStatus(0);
				erpPrice.setCreatetime(sdf.format(new Date()));
				priceService.save(erpPrice);
			}
			int result = pickgoodsService.update(ingoods);
			if (result > 0) {
				return new MessageBean(10001, "审核成功", null);
			}
		}
		return new MessageBean(10000, "审核异常", null);

	}

	@RequestMapping("/unaudit")
	public MessageBean unaudit(@RequestParam Integer id, ErpPickgoods ingoods) {
		if (id != null) {
			ingoods.setStatus_(0);
			ingoods.setId(id);
			int result = pickgoodsService.update(ingoods);
			if (result > 0) {
				return new MessageBean(10001, "审核拒绝", null);
			}
		}
		return new MessageBean(10000, "审核异常", null);

	}
}
