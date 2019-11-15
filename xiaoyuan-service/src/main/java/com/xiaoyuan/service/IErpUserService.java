package com.xiaoyuan.service;

import java.util.List;
;
import com.xiaoyuan.model.dto.ErpUserDto;

import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.model.search.UniqueSearch;

public interface IErpUserService {
	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
	ErpUser selectByUserId(int id);

	int add(ErpUser tlErpUserRegister);

	int delete(Integer id);
	/**
	 * 查看写入数据，
	 * 
	 * @param tlErpUserRegister
	 * @return
	 *
	 * 		2019年3月4日下午2:30:39
	 * @author : YZH
	 */
	int update(ErpUser tlErpUserRegister);
	List<ErpUser> lists();
	/**
	 * 
	 * IErpUserService接口，detail方法，ErpUserService实现类
	 * 
	 * 根据id还要返回join 供应商表 join 司机表 join客户表
	 * 
	 * @param id
	 * @return
	 *
	 * 		2019年2月21日下午2:01:34
	 * @author : YZH
	 * 
	 *         客户，充值记录，用到这个方法，查到了多个，报错，limit 1 或者 不连接重复数据来源表
	 *
	 *         2019年3月5日下午7:42:18
	 * @author : YZH
	 */
	ErpUser detail(Integer id);

	ErpUser login(String name);

	List<ErpUser> list(ErpUser userRegister);

	int forgetPassword(ErpUser userRegister);

	int increment();

	List<ErpUser> lists_user_cate();

	ErpUser existsPhone(String user_phone,Integer id);

	Integer getUserIdByToken(String token);

	ErpUser balance(Integer id);

	List<UserRole> user_role_list();

	int audit(Integer id);
	int unaudit(Integer id);

	/**
	 * 根据传入参数手机与用户id返回该手机号是否在数据库中存在数据
	 * @param userId
	 * @param phone
	 * @return
	 */
	int selectUserPnoneIsOnly(Integer userId,String phone);

	/**
	 * 根据条件搜索类进行查询用户集合
	 * @param search
	 * @return
	 */
	List<ErpUserDto> selectBySearch(ErpUserSearch search);

	/**
	 * 统计search条件查询类查询后的总数据数。
	 * @param search
	 * @return
	 */
	int countBySearch(ErpUserSearch search);

	/**
	 * 根据条件搜索类进行查询用户集合并取出集合第一条数据
	 * @param search
	 * @return
	 */
	ErpUserDto selectFiistBySearch(ErpUserSearch search);


	/**
	 * 根据条件搜索类进行查询用户集合并返回分页数据
	 * @param search
	 * @return
	 */
	List<ErpUserDto> selectBySearchOfPage(ErpUserSearch search);

	/**
	 * 根据用户主键修改用户信息
	 * @param user
	 * @return
	 */
	int updateServiceByPrimaryKey(ErpUser user);
	/**
	 * 根据传入对象插入数据
	 * @param user
	 * @return
	 */
	int insertService(ErpUser user);

	/**
	 * 检验用户名唯一性
	 * @param user_name
	 * @return
	 */
	void userNameUnique(String user_name);

	/**
	 * 编号唯一性查询
	 * @return
	 */
	void uniqueNumber(UniqueSearch uniqueSearch);
	/**
	 * 根据用户id数组查询出用户信息
	 * @param userIds
	 * @return
	 */
	List<ErpUser> resultUsersByUserIds(List<Integer> userIds);
}
