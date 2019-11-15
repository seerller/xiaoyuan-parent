package com.xiaoyuan.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.search.ErpTerminalCustomerSearch;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpTerminalCustomerMapper;
import com.xiaoyuan.model.ErpTerminalCustomer;

/**
 * 来来回回，，，
 *
 * @author YZH
 * @version 2019年3月6日 下午4:35:07
 */
@Service
public class ErpTerminalCustomerService implements IErpTerminalCustomerService {
    @Autowired
    ErpTerminalCustomerMapper terminalCustomerMapper;

    @Override
    public int insert(ErpTerminalCustomer customer) {
        int insert = terminalCustomerMapper.insert(customer);
        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        int delete = terminalCustomerMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public int update(ErpTerminalCustomer customer) {
        int update = terminalCustomerMapper.updateByPrimaryKeySelective(customer);
        if (update > 0) {
            return update;
        }
        return 0;
    }

    @Override
    public List<ErpTerminalCustomer> lists(Integer user_id, Integer status_, String customer_company_name) {
        List<ErpTerminalCustomer> list = terminalCustomerMapper.lists(user_id, status_, customer_company_name);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpTerminalCustomer get(Integer oCId) {
        ErpTerminalCustomer get = terminalCustomerMapper.selectByPrimaryKey(oCId);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public List<String> customer_names(Integer id) {
        List<String> customer_names = terminalCustomerMapper.customer_names(id);
        if (customer_names.size() > 0) {
            return customer_names;
        }
        return null;
    }
    /**
     * 根据条件搜索类查询数据并返回集合
     * @return
     */
    @Override
    public List<ErpCustomerDto> selectBySearch(ErpTerminalCustomerSearch search) {
        return terminalCustomerMapper.selectBySearch(search);
    }

    @Override
    public int countBySearch(ErpTerminalCustomerSearch search) {
        return terminalCustomerMapper.countBySearch(search);
    }

    /**
     * 根据条件搜索类查询数据并返回集合中第一条数据
     * @param search
     * @return
     */
    @Override
    public ErpCustomerDto selectFirstBySearch(ErpTerminalCustomerSearch search) {
        List<ErpCustomerDto> list = terminalCustomerMapper.selectBySearch(search);
        if(list.size()<=0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据search条件搜索类查询并返回分页数据
     * @param search
     * @return
     */
    @Override
    public List<ErpCustomerDto> selectBySearchOfPage(ErpTerminalCustomerSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpCustomerDto> list = selectBySearch(search);
        if(list.size()>0){
            PageBean<ErpCustomerDto> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
            pageData.setItems(list);
            return pageData.getItems();
        }
        return list;
    }

    /**
     * 根据用户主键修改用户信息
     * @param customer
     * @return
     */
    @Override
    public int updateServiceByPrimaryKey(ErpTerminalCustomer customer) {
        return terminalCustomerMapper.updateServiceByPrimaryKey(customer);
    }
    /**
     * 根据传入对象插入数据
     * @param customer
     * @return
     */
    @Override
    public int insertService(ErpTerminalCustomer customer) {
        return terminalCustomerMapper.insertService(customer);
    }


}
