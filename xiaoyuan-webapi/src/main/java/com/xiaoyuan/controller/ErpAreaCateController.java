package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpArea;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpAreaService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 区域管理，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:48:11
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpAreaCate")
public class ErpAreaCateController {
	@Autowired
	private IErpAreaService areaService;
	@Autowired
	private IErpUserService userService;

	/**
	 * 区域删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public MessageBean delete(@RequestParam Integer id) {
		int delete = areaService.delete(id);
		if (delete > 0) {
			return new MessageBean(10001, "区域删除成功", null);
		}
		return new MessageBean(10000, "区域删除失败", null);
	}

	/**
	 * 区域禁止
	 * 
	 * @param area
	 * @param id
	 * @return
	 */
	@RequestMapping("/stop")
	public MessageBean stop(ErpArea area, @RequestParam Integer id) {
		if (id != null) {
			area.setId(id);
			area.setStatus_(0);
			int result = areaService.update(area);
			if (result > 0) {
				return new MessageBean(10001, "区域分类已禁止", null);
			}
		}
		return new MessageBean(10000, "区域分类禁止异常", null);
	}

	/**
	 * 区域详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	public MessageBean detail(@RequestParam Integer id) {
		if (id != null) {
			ErpArea detail = areaService.detail(id);
			if (detail != null) {
				return new MessageBean(10001, "", detail);
			}
		}
		return new MessageBean(10002, "没有找到此记录", null);
	}

	@RequestMapping("/lists")
	public MessageBean lists(
			@RequestParam(defaultValue = "1") Integer currentPage,
			@RequestParam(defaultValue = "50") Integer pageSize
	) {
		PageHelper.startPage(currentPage, pageSize);
		List<ErpArea> list = areaService.list();

		if (list != null) {
			int countNums = list.size();//总记录数
			PageBean<ErpArea> pageData = new PageBean<>(currentPage, pageSize, countNums);
			pageData.setItems(list);
			return new MessageBean(10001, "", pageData.getItems());
		}
		return new MessageBean(10000, "数据列表为空", list);
	}

	@RequestMapping("/add")
	public MessageBean add(@RequestParam(value = "sales_area", required = false) String sales_area,
			@RequestParam(value = "user_name", required = false) String user_name, ErpArea area,
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			ErpUser user = new ErpUser();
			user.setId(id);
			if (sales_area != null && sales_area != "") {
				user.setSales_area(sales_area);
			}
			/*
			 * if(user_name!=null && user_name!=""){
			 * user.setUser_name(user_name); }
			 */
			userService.update(user);
			ErpArea erpArea = new ErpArea();
			erpArea.setUser_id(id);
			if (area.getArea_remark() != null && area.getArea_remark() != "") {
				erpArea.setArea_remark(area.getArea_remark());
			}
			if (area.getArea_audit_state() != null && area.getArea_audit_state() != "") {
				erpArea.setArea_audit_state(area.getArea_audit_state());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			if (area.getArea_audit_date() != null && area.getArea_audit_date() != "") {
				erpArea.setArea_audit_date(sdf.format(area.getArea_audit_date()));
			}
			erpArea.setUpdatetime(sdf.format(new Date() ));
			int result = areaService.updateByUserid(erpArea);
			if (result > 0) {
				return new MessageBean(10001, "区域分类修改成功", null);
			}
			return new MessageBean(10000, "区域分类修改失败", null);
		}
		return null;
	}
}
