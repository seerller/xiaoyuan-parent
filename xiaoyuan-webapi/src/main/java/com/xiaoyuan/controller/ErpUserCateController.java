package com.xiaoyuan.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;
@CrossOrigin
@RestController
@RequestMapping("/ErpUserCate")
public class ErpUserCateController {
	@Autowired
	private IErpUserService userService; 
	
	/**
	 * 修改用户分类
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	public MessageBean updateUsertype(@RequestParam(value="id",required=false) Integer id,ErpUser user){
		if (id != null){
			ErpUser tlErpUser = new ErpUser(); 
			tlErpUser.setId(id);
			/*if(user.getUser_name() != null && user.getUser_name() != ""){
				tlErpUser.setUser_name(user.getUser_name());
			}*/
			if(user.getUser_cate() != null){
					tlErpUser.setUser_cate(user.getUser_cate());
				
			}
			if(user.getRole_id() != null){
				tlErpUser.setRole_id(user.getRole_id());
			}
			if(user.getUpdatetime() != null && user.getUpdatetime() != ""){
				tlErpUser.setUpdatetime(user.getUpdatetime());
			}
			int result = userService.update(tlErpUser);
			if (result > 0) {
				return new MessageBean(10001, "用户分类修改成功", null);
			}
		}
		return new MessageBean(10000, "用户分类修改失败", null);
	}
	
	/**
	 * 删除用户分类
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/delete")
	public MessageBean delete(@RequestParam("id") Integer id) {
		int result = userService.delete(id);
		if (result > 0) {
			return new MessageBean(10001, "删除成功", null);
		}
		return new MessageBean(10000, "删除失败", null);
	}
	
	/**
	 * stop 3禁止 4.正常  禁止用户分类
	 * 
	 * @param tlErpUserRegister
	 * @param id
	 * @return
	 */
	
	@RequestMapping("/stop")
	public MessageBean stop(ErpUser userRegister, @RequestParam Integer id) {
		if (id != null) {
			userRegister.setId(id);
			userRegister.setStop(0);
			int result = userService.update(userRegister);
			if (result > 0) {
				return new MessageBean(10001, "用户已禁止", null);
			}
		}
		return new MessageBean(10000,"用户禁止异常",null);
	}
	/**
	 * 用户分类列表
	 * @return
	 */
	@RequestMapping("/lists")
	public MessageBean listUsertype(
			@RequestParam(defaultValue = "1") Integer currentPage,
			@RequestParam(defaultValue = "50") Integer pageSize
	){
		PageHelper.startPage(currentPage, pageSize);
		List<ErpUser> list = userService.lists_user_cate();
		int countNums = list.size();//总记录数
		PageBean<ErpUser> pageData = new PageBean<>(currentPage, pageSize, countNums);
		pageData.setItems(list);
		return new MessageBean(10001,"",pageData.getItems());
	}
	
	@RequestMapping(value = "/detail")
	public MessageBean detail(@RequestParam Integer id) {
		ErpUser get = userService.detail(id);
		if (get != null) {
			return new MessageBean(10001,"",get);
		}
		return new MessageBean(10000,"没有找到此信息",null);
	}
}
