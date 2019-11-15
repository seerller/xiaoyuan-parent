package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.dto.ErpAccountCountTotalDto;
import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.search.ErpAccountSearch;
import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpAccount;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpAccountMapper extends MapperConfig<ErpAccount> {

    int updateAccountByUserid(ErpAccount account);

    /**
     * 园区查看所有充值记录，客户查看自己的充值记录
     * <p>
     * if test #{id}
     *
     * @param user_id
     * @return 2019年3月5日下午7:18:34
     * @author : YZH
     */
    List<ErpAccount> lists_recharge(@Param("user_id") Integer user_id, @Param("audit") Integer audit,
                                    @Param("date1") String date1, @Param("date2") String date2,
                                    @Param("user_company_name")String user_company_name);

    List<ErpAccount> lists();

    @Override
    int insert(ErpAccount account);

    ErpAccount get(Integer id);

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

    ErpAccountCountTotalDto countInAndOutTotal(ErpAccountSearch search);

}