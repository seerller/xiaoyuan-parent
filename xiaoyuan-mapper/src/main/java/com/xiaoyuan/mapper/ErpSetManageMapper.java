package com.xiaoyuan.mapper;

import com.xiaoyuan.model.ErpSetManage;
import com.xiaoyuan.model.search.ErpSetManageSearch;
import com.xiaoyuan.tools.MapperConfig;

import java.util.List;

public interface ErpSetManageMapper extends MapperConfig<ErpSetManage> {

    List<ErpSetManage> selectBySearch(ErpSetManageSearch search);

    int insertService(ErpSetManage manage);

    int updateByPrimaryKeySelective(ErpSetManage manage);
}
