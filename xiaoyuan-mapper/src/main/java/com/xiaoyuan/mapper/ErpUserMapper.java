package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.model.UserRole;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.model.search.UniqueSearch;
import com.xiaoyuan.tools.MapperConfig;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Administrator
 */
public interface ErpUserMapper extends MapperConfig<ErpUser>{
    /**
     * 根据用户id查询用户
     * @auth zhengweicheng
     * @param id    用户id
     * @return
     */
    ErpUser selectByUserId(int id);

    /**
     * 根据账号修改密码和token
     *
     * @param userRegister
     * @return
     */
    int forgetPassword(ErpUser userRegister);

    /**
     * 客户管理，列表
     *
     * @param userRegister
     * @return 2019年3月7日下午4:05:27
     * @author : YZH
     */
    List<ErpUser> lists(ErpUser userRegister);

    List<UserRole> user_role_list();

    ErpUser login(String login_name);

    int insert(ErpUser userRegister);

    int increment();

    List<ErpUser> lists_user_cate();

    ErpUser existsPhone(@Param("user_phone") String user_phone, @Param("id") Integer id);

    Integer getUserIdByToken(String token);

    ErpUser balance(Integer id);

    /**
     * 多个表的同一个账号的所有信息
     *
     * @param id
     * @return 2019年2月27日上午10:03:30
     * @author : YZH
     */
    ErpUser get(Integer id);//客户全部信息

    /**
     * 司机审核失败，，没有报错，，，，，查看SQL语句执行
     *
     * @param tlErpUserRegister
     * @return 2019年2月28日下午2:52:37
     * @author : YZH
     * <p>
     * 供应商在u表中的信息修改，查看修改数据，应该使用非空全字段，，if test
     * <p>
     * 2019年3月4日下午2:31:14
     * @author : YZH
     */
    int update(ErpUser tlErpUserRegister);

    int audit(Integer id);

   // int unaudit(Integer id);

    int delete_user(Integer id);

    int delete_driver(Integer id);

    int delete_supplier(Integer id);

    /**
     * 根据条件搜索类进行查询用户
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
     * 根据传入参数手机与用户id返回该手机号是否在数据库中存在数据
     * @param userId    用户自增主键，不传则查询所有用户 传入则除了该用户以外
     * @param phone
     * @return
     */
    int selectUserPnoneIsOnly(@Param("userId")Integer userId,@Param("phone")String phone);

    int updateServiceByPrimaryKey(ErpUser user);

    int insertService(ErpUser user);


    /**
     * 检验用户名唯一性
     * @param user_name
     * @return
     */
    int userNameUnique(String user_name);

    /**
     *  唯一性编号查询
     * @param uniqueSearch
     * @return
     */
    int uniqueNumber(UniqueSearch uniqueSearch);


    /**
     * 根据用户id数组查询出用户信息
     * @param userIds
     * @return
     */
    List<ErpUser> resultUsersByUserIds(@Param("userIds") List<Integer> userIds);
}