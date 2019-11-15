package com.xiaoyuan.model.search;

import lombok.Data;
import lombok.ToString;

/**
 * 统计接口条件搜索类
 */
@Data
@ToString(callSuper = true)
public class ErpAccountSearch extends BaseSearch {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 充值类型
     */
    private Integer accountType;
    /**
     * 用户id
     * 根据当前登录账号角色判断是前端传入还是后端设置
     */
    private Integer userId;
    /**
     * 明细类型
     * 是消费还是充值
     */
    private Integer accountCate;

    public ErpAccountSearch(){}


    public ErpAccountSearch(ErpPickGoodsTotalSearch search){
        this.endTime = search.getEndTime();
        this.startTime  = search.getStartTime();
    }

}
