package com.xiaoyuan.model.search;
import lombok.Data;

/**
 * 终端用户条件搜索类
 * @author  zhengweicheng
 * @time 2019-06-21
 */
@Data
public class ErpTerminalCustomerSearch extends BaseSearch {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 终端客户id
     * 查询特定的终端用户使用。
     */
    private Integer customerId;
    /**
     * 伪删除数据屏蔽属性
     */
    private Integer stop = 1;

    public ErpTerminalCustomerSearch(){

    }
    public ErpTerminalCustomerSearch(Integer userId){
        this.userId = userId;
    }
    public ErpTerminalCustomerSearch(Integer userId,Integer customerId){
        this.userId = userId;
        this.customerId = customerId;
    }


}
