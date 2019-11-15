package com.xiaoyuan.model.search;

import lombok.Data;
import lombok.ToString;

/**
 * 获取省市区条件搜索类
 */
@Data
@ToString(callSuper = true)
public class ErpRegionSearch extends BaseSearch {
    /**
     *  默认查询所有省
     */
    private Integer parentId = 1;
}
