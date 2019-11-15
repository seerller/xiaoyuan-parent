package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpAccount;
import com.xiaoyuan.model.dto.ErpAccountCountTotalDto;
import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.search.ErpAccountSearch;

public interface IErpAccountService {

    int update(ErpAccount tlerpaccount);

    ErpAccount get(Integer aComId);

    int updateAccountByUserid(ErpAccount account);

    /**
     * 园区查看所有充值记录，客户查看自己的充值记录
     * <p>
     * if test #{id}
     *
     * @param id
     * @return 2019年3月5日下午7:17:49
     * @author : YZH
     */
    List<ErpAccount> lists_recharge(Integer id, Integer audit, String date1, String date2,String user_company_name);

    List<ErpAccount> lists();

    int insert(ErpAccount account);

    /**
     * 插入数据
     * @param account
     * @return
     */
    int insertService(ErpAccount account);

    /**
     * 根据搜索类内的条件进行条件筛选并返回集合
     * @param search        搜索类
     * @return
     */
    List<ErpAccountDto> selectBySearch(ErpAccountSearch search);

    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    int countBySearch(ErpAccountSearch search);
    /**
     * 根据搜索类内的条件进行条件筛选并返回总充值金额
     * @param search        搜索类
     * @return
     */
    String sumAccountPriceBySearch(ErpAccountSearch search);

    /**
     * 根据条件搜索类统计用户支出/入账总数
     * @param search
     * @return
     */
    ErpAccountCountTotalDto countInAndOutTotal(ErpAccountSearch search);

    /**
     * 根据搜索类内的条件进行条件筛选返回已分页数据
     * @param search        搜索类
     * @return
     */
    List<ErpAccountDto> selectBySearchOfPage(ErpAccountSearch search);

}
