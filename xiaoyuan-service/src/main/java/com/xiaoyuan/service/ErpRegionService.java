package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.search.ErpRegionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpRegionMapper;
import com.xiaoyuan.model.ErpRegion;
@Service
public class ErpRegionService implements IErpRegionSerivce{
	@Autowired
	private ErpRegionMapper regionMapper;
	@Override
	public List<ErpRegion> lists(ErpRegion region) {
		List<ErpRegion> lists = regionMapper.lists(region);
		if (lists.size()>0){
			return lists;
		}
		return null;
	}

	@Override
	public ErpRegion get_address(ErpRegion region) {
		ErpRegion address = regionMapper.get_address(region);
		if (address != null){
			return address;
		}
		return null;
	}
	/**
	 * 根据search条件搜索类查询数据
	 * @param search
	 * @return
	 */
	@Override
	public List<ErpRegion> selectBySearch(ErpRegionSearch search) {
		return regionMapper.selectBySearch(search);
	}



}
