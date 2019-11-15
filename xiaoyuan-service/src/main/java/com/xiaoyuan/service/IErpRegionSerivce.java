package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpRegion;
import com.xiaoyuan.model.search.ErpRegionSearch;

public interface IErpRegionSerivce {
	List<ErpRegion> lists(ErpRegion region);
	
	ErpRegion get_address(ErpRegion region);

	/**
	 * 根据search条件搜索类查询数据
	 * @param search
	 * @return
	 */
	List<ErpRegion> selectBySearch(ErpRegionSearch search);
}
