package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.tools.MapperConfig;

public interface UserRoleMapper extends MapperConfig<UserRole> {
	/**
	 * 在登录判断时，根据已有代码，即根据一个仅有seller_id的UserRole对象返回一个非空UserRole对象？
	 * 
	 * @param role_name
	 * @return
	 *
	 * 		2019年2月25日下午3:35:28
	 * @author : YZH
	 */
	UserRole role_auth_ids(UserRole role_name);

	UserRole role(Integer role_id);

	/**
	 * 
	 * 根据seller_id返回一个UserRole对象。。。。？。。。。
	 * 
	 * @param seller_id
	 * @return
	 *
	 * 		2019年2月25日下午3:35:03
	 * @author : YZH
	 */
	UserRole role_auth_id(@Param("seller_id") Integer seller_id);

	List<UserRole> userroles();

	/**
	 * 
	 * 有问题，返回 0 检查SQL
	 * 
	 * @param userRole
	 * @return
	 *
	 * 		2019年2月22日下午7:47:09
	 * @author : YZH
	 * 
	 *         有点问题，，需要看看，，， 2019年2月28日下午4:23:07
	 * @author : YZH
	 */
	int update(UserRole userRole);

	int add(UserRole userRole);// 增加，明天再说吧
}
