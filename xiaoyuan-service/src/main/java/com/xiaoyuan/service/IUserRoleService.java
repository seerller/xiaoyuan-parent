package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpRole;
import com.xiaoyuan.model.UserRole;

public interface IUserRoleService {
	int add(UserRole userRole);

	/**
	 * 在登录判断时， 要求根据一个仅有seller_id属性的UserRole对象返回一个非空的UserRole对象？
	 * 
	 * 改为根据user的role_id查询角色
	 * 
	 * @param userRole
	 * @return
	 *
	 * 		2019年2月25日下午3:29:41
	 * @author : YZH
	 */
	UserRole role_auth_ids(UserRole userRole);

	/**
	 * 修改权限，，有点问题，，，需要看看
	 * 
	 * @param userRole
	 * @return
	 *
	 * 		2019年2月28日下午4:22:36
	 * @author : YZH
	 */
	int update(UserRole userRole);

	UserRole role(Integer id);

	int delete(Integer id);

	UserRole role_auth_id(Integer seller_id);

	/**
	 * 这里返回类型改为List<UserRole>
	 * 
	 * @return
	 *
	 * 		2019年2月25日下午4:34:11
	 * @author : YZH
	 */
	List<UserRole> userroles();

	int addRole(ErpRole role);

	ErpRole getRole(String role);

	int getId(String role_name);// 根据角色查询id



}
