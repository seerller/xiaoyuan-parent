package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpTerminalCustomer;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.search.ErpTerminalCustomerSearch;

public interface IErpTerminalCustomerService {

	int insert(ErpTerminalCustomer erpTerminalCustomer);

	int delete(Integer id);

	int update(ErpTerminalCustomer erpTerminalCustomer);

	/**
	 * 
	 * 客户查看终端客户列表
	 * 
	 * @param user_id
	 * @return
	 *
	 * 		2019年3月4日下午4:10:31
	 * @author : YZH
	 * 
	 *         审核查看客户的终端客户
	 *
	 *         2019年3月6日下午4:34:13
	 * @author : YZH
	 */
	List<ErpTerminalCustomer> lists(Integer user_id,Integer status_,String customer_company_name);

	ErpTerminalCustomer get(Integer id);

	List<String> customer_names(Integer id);

	/**
	 * 根据条件搜索类查询数据并返回集合
	 * @return
	 */
	List<ErpCustomerDto> selectBySearch(ErpTerminalCustomerSearch search);
	int countBySearch(ErpTerminalCustomerSearch search);

	/**
	 * 根据条件搜索类查询数据并返回集合中第一条数据
	 * @param search
	 * @return
	 */
	ErpCustomerDto selectFirstBySearch(ErpTerminalCustomerSearch search);

	/**
	 * 根据search条件搜索类查询并返回分页数据
	 * @param search
	 * @return
	 */
	List<ErpCustomerDto> selectBySearchOfPage(ErpTerminalCustomerSearch search);



	/**
	 * 根据用户主键修改用户信息
	 * @param customer
	 * @return
	 */
	int updateServiceByPrimaryKey(ErpTerminalCustomer customer);
	/**
	 * 根据传入对象插入数据
	 * @param customer
	 * @return
	 */
	int insertService(ErpTerminalCustomer customer);
}
