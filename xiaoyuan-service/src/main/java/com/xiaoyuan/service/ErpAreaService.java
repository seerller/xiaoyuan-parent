package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpAreaMapper;
import com.xiaoyuan.model.ErpArea;
@Service
public class ErpAreaService implements IErpAreaService {
	@Autowired
     ErpAreaMapper areaMapper;
	@Override
	public int update(ErpArea tlErpArea) {
		int result = areaMapper.updateByPrimaryKeySelective(tlErpArea);
		if(result > 0){
			return result;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) { 
    int result =areaMapper.deleteByPrimaryKey(id);
     if(result>0){
    	 return result;
     }
		return 0;
	}

	@Override
	public List<ErpArea> list() {
		List<ErpArea> list = areaMapper.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpArea detail(Integer id) {
		ErpArea area = areaMapper.get(id);
		if(area != null){
			return area;
		}
		return null;
	}

	@Override
	public int updateByUserid(ErpArea area) {
		int result =areaMapper.updateByUserid(area);
	     if(result>0){
	    	 return result;
	     }
			return 0;
	}

}
