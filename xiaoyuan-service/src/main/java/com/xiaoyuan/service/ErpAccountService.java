package com.xiaoyuan.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.dto.ErpAccountCountTotalDto;
import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.search.ErpAccountSearch;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpAccountMapper;
import com.xiaoyuan.model.ErpAccount;


@Service
public class ErpAccountService implements IErpAccountService {
    @Autowired
    ErpAccountMapper accountMapper;

    @Override
    public int update(ErpAccount tlerpaccount) {
        int result = accountMapper.updateByPrimaryKeySelective(tlerpaccount);
        if (result > 0) {
            return result;
        }
        return 0;
    }


    @Override
    public ErpAccount get(Integer aComId) {
        ErpAccount account = accountMapper.get(aComId);
        if (account != null) {
            return account;
        }
        return null;
    }

    @Override
    public int updateAccountByUserid(ErpAccount account) {
        int result = accountMapper.updateAccountByUserid(account);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public List<ErpAccount> lists_recharge(Integer id, Integer audit, String date1, String date2,String user_company_name) {
        List<ErpAccount> list = accountMapper.lists_recharge(id, audit, date1, date2,user_company_name);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<ErpAccount> lists() {
        List<ErpAccount> list = accountMapper.lists();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int insert(ErpAccount account) {
        int result = accountMapper.insert(account);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    /**
     * 插入数据
     * @param account
     * @return
     */
    @Override
    public int insertService(ErpAccount account) {
        if(null == account){
            return -1;
        }
        return accountMapper.insertService(account);
    }
    /**
     * 根据搜索类内的条件进行条件筛选并返回集合
     * @param search        搜索类
     * @return
     */
    @Override
    public List<ErpAccountDto> selectBySearch(ErpAccountSearch search) {
        return accountMapper.selectBySearch(search);
    }
    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    @Override
    public int countBySearch(ErpAccountSearch search) {
        return accountMapper.countBySearch(search);
    }

    /**
     * 根据搜索类内的条件进行条件筛选并返回总充值金额
     * @param search        搜索类
     * @return
     */
    @Override
    public String sumAccountPriceBySearch(ErpAccountSearch search) {
        return accountMapper.sumAccountPriceBySearch(search);
    }
    /**
     * 根据条件搜索类统计用户支出/入账总数
     * @param search
     * @return
     */
    @Override
    public ErpAccountCountTotalDto countInAndOutTotal(ErpAccountSearch search) {
        return accountMapper.countInAndOutTotal(search);
    }

    @Override
    public List<ErpAccountDto> selectBySearchOfPage(ErpAccountSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpAccountDto> list = selectBySearch(search);
        PageBean<ErpAccountDto> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
        pageData.setItems(list);
        return pageData.getItems();
    }

}
