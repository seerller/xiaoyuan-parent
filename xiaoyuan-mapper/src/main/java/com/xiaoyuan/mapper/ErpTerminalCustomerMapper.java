package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.search.ErpTerminalCustomerSearch;
import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpTerminalCustomer;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpTerminalCustomerMapper extends MapperConfig<ErpTerminalCustomer> {

    List<String> customer_names(Integer id);

    int insert(ErpTerminalCustomer customer);

    /**
     * 客户查看终端客户列表
     *
     * @param user_id
     * @return 2019年3月4日下午4:10:58
     * @author : YZH
     * <p>
     * 审核查看客户的终端客户
     * <p>
     * 2019年3月6日下午4:35:55
     * @author : YZH
     */
    List<ErpTerminalCustomer> lists(@Param("user_id") Integer user_id, @Param("status_") Integer status_,
                                    @Param("customer_company_name") String customer_company_name);
    /**
     * 根据条件搜索类查询终端用户
     * @return
     */
    List<ErpCustomerDto> selectBySearch(ErpTerminalCustomerSearch search);
    int countBySearch(ErpTerminalCustomerSearch search);

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