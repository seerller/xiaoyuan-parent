package com.xiaoyuan.model.search;
import lombok.Data;

/**
 * 管理设置条件搜索类
 */
@Data
public class ErpSetManageSearch extends BaseSearch {
    /**
     * 关键字
     */
    private String key_name;
}
