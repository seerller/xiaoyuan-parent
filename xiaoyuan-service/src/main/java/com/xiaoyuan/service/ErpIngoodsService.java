package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpIngoodsMapper;
import com.xiaoyuan.model.ErpIngoods;

@Service
public class ErpIngoodsService implements IErpIngoodsService {
	@Autowired
	ErpIngoodsMapper ingoodsMapper;

	@Override
	public int insert(ErpIngoods tlErpIngoods) {
		int insert = ingoodsMapper.insert(tlErpIngoods);
		if (insert > 0) {
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = ingoodsMapper.deleteByPrimaryKey(ingoodsMapper);
		if (delete > 0) {
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpIngoods tlErpIngoods) {
		int update = ingoodsMapper.updateByPrimaryKeySelective(tlErpIngoods);
		if (update > 0) {
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpIngoods> lists(String ingoods_car_sn) {
		List<ErpIngoods> list = ingoodsMapper.lists(ingoods_car_sn);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public ErpIngoods get(Integer iMaterialId) {
		ErpIngoods get = ingoodsMapper.get(iMaterialId);
		if (get != null) {
			return get;
		}
		return null;
	}

	@Override
	public List<ErpIngoods> lists_statistical() {
		List<ErpIngoods> list = ingoodsMapper.lists_statistical();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public ErpIngoods out_lists(String ingoods_car_sn) {
		ErpIngoods get = ingoodsMapper.out_lists(ingoods_car_sn);
		if (get != null) {
			return get;
		}
		return null;
	}

	@Override
	public ErpIngoods in_warehouse(String ingoods_sn, String ingoods_car_sn) {
		ErpIngoods get1 = ingoodsMapper.in_warehouse(ingoods_sn, ingoods_car_sn);
		if (get1 != null) {
			return get1;
		}
		return null;
	}

	@Override
	public ErpIngoods purchase(String ingoods_sn, String ingoods_car_sn) {
		ErpIngoods get2 = ingoodsMapper.purchase(ingoods_sn, ingoods_car_sn);
		if (get2 != null) {
			return get2;
		}
		return null;
	}

	@Override
	public List<ErpIngoods> lists_price(Integer supplier_id) {
		List<ErpIngoods> list = ingoodsMapper.lists_price(supplier_id);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<ErpIngoods> lists_audit() {
		return ingoodsMapper.lists_audit();
	}

	@Override
	public ErpIngoods getPriceByMessage(ErpIngoods ingoods) {
		// TODO Auto-generated method stub
		return ingoodsMapper.getPriceByMessage(ingoods);

	}

}
