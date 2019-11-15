package com.xiaoyuan.service;

import java.util.List;
import com.xiaoyuan.model.ErpUser;

public interface IErpUsersService {

	int delete(Integer id);

	int update(ErpUser ErpUser);

	int insert(ErpUser ErpUser);

	ErpUser get(Integer userId);

	/**
	 * 客户管理，列表
	 * 
	 * @param userRegister
	 * @return
	 *
	 * 		2019年3月7日下午4:05:05
	 * @author : YZH
	 */
	List<ErpUser> lists(ErpUser userRegister);

}
