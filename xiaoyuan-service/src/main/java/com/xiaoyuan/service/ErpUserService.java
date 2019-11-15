package com.xiaoyuan.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.dto.ErpUserDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpUserSearch;
import com.xiaoyuan.model.search.UniqueSearch;
import com.xiaoyuan.tools.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpUserMapper;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.model.UserRole;

@Service
public class ErpUserService implements IErpUserService {

    @Autowired
    private ErpUserMapper userMapper;
    @Autowired
    private ErpTerminalCustomerService customerService;
    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Override
    public ErpUser selectByUserId(int id) {
        return userMapper.selectByUserId(id);
    }

    @Override
    public int add(ErpUser tlErpUserRegister) {
        int insert = userMapper.insert(tlErpUserRegister);
        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        int delete = userMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public int update(ErpUser tlErpUserRegister) {
        int update = userMapper.update(tlErpUserRegister);
        if (update > 0) {
            return update;
        }
        return 0;
    }

    @Override
    public List<ErpUser> lists() {
        List<ErpUser> list = userMapper.selectAll();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.yuanqu.service.IErpUserService#detail(java.lang.Integer)
     *
     * TODO 数据库中有这个ID，但是没有查到，找到执行的SQL语句放到数据库中执行查看
     */
    @Override
    public ErpUser detail(Integer id) {
        ErpUser get = userMapper.get(id);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public ErpUser login(String name) {
        ErpUser get = userMapper.login(name);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public List<ErpUser> list(ErpUser userRegister) {
        List<ErpUser> list = userMapper.lists(userRegister);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int forgetPassword(ErpUser userRegister) {
        int result = userMapper.forgetPassword(userRegister);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public int increment() {
        return userMapper.increment();
    }

    @Override
    public List<ErpUser> lists_user_cate() {
        return userMapper.lists_user_cate();
    }

    @Override
    public ErpUser existsPhone(String user_phone, Integer id) {
        ErpUser get = userMapper.existsPhone(user_phone, id);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public Integer getUserIdByToken(String token) {
        Integer id = userMapper.getUserIdByToken(token);
        if (id != null) {
            return id;
        }
        return null;
    }

    @Override
    public ErpUser balance(Integer id) {
        ErpUser get = userMapper.balance(id);
        if (get != null) {
            return get;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.yuanqu.service.IErpUserService#user_role_list()
     *
     * 有问题
     */
    @Override
    public List<UserRole> user_role_list() {
        // TODO Auto-generated method stub
        return userMapper.user_role_list();
    }

    /*
     * (non-Javadoc)
     *
     * audit方法，审核，
     *
     * @see com.yuanqu.service.IErpUserService#audit(java.lang.Integer)
     */
    @Override
    public int audit(Integer id) {
        int update = userMapper.audit(id);
        if (update > 0) {
            return update;
        }
        return 0;
    }

    @Override
    public int unaudit(Integer id) {
        int update = userMapper.delete_user(id);
        if (update > 0) {
            userMapper.delete_driver(id);
            userMapper.delete_supplier(id);
            return update;
        }
        return 0;
    }
    /**
     * 根据条件搜索类进行查询用户
     * @param search
     * @return
     */
    @Override
    public List<ErpUserDto> selectBySearch(ErpUserSearch search) {
        return userMapper.selectBySearch(search);
    }

    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */

    @Override
    public int countBySearch(ErpUserSearch search) {
        return userMapper.countBySearch(search);
    }

    /**
     * 根据条件搜索类进行查询用户集合并取出集合第一条数据
     * @param search
     * @return
     */
    @Override
    public ErpUserDto selectFiistBySearch(ErpUserSearch search) {
        List<ErpUserDto> list = selectBySearch(search);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 根据传入参数手机与用户id返回该手机号是否在数据库中存在数据
     * @param userId
     * @param phone
     * @return
     */
    @Override
    public int selectUserPnoneIsOnly(Integer userId, String phone) {
        return userMapper.selectUserPnoneIsOnly(userId,phone);
    }
    /**
     * 根据条件搜索类进行查询用户集合并返回分页数据
     * @param search
     * @return
     */
    @Override
    public List<ErpUserDto> selectBySearchOfPage(ErpUserSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpUserDto> list = selectBySearch(search);
        if(list.size()>0){
            PageBean<ErpUserDto> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
            pageData.setItems(list);
            return pageData.getItems();
        }
        return list;
    }
    /**
     * 根据用户主键修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updateServiceByPrimaryKey(ErpUser user) {
        return userMapper.updateServiceByPrimaryKey(user);
    }
    /**
     * 根据传入对象插入数据
     * @param user
     * @return
     */
    @Override
    public int insertService(ErpUser user) {
        return userMapper.insertService(user);
    }
    /**
     * 检验用户名唯一性
     * @param user_name
     * @return
     */
    @Override
    public void userNameUnique(String user_name) throws LogicException {
        if(StringUtils.isBlank(user_name)){
          throw new LogicException("请输入有效的用户名");
        }
        if(userMapper.userNameUnique(user_name)> 0 ){
            throw new LogicException("该用户名已被使用.");
        }
    }



    /**
     * 编号唯一性查询
     * @return
     */
    @Override
    public void uniqueNumber(UniqueSearch uniqueSearch) {
        if(userMapper.uniqueNumber(uniqueSearch) > 0 || userMapper.uniqueNumber(uniqueSearch.setType(false))>0){
            throw new LogicException("唯一性编号重复.");
        }
    }

    @Override
    public List<ErpUser> resultUsersByUserIds(List<Integer> userIds) {
        return userMapper.resultUsersByUserIds(userIds);
    }


}
