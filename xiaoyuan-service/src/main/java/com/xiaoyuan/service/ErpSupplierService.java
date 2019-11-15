package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpSupplierMapper;
import com.xiaoyuan.model.ErpSupplier;

@Service
public class ErpSupplierService implements IErpSupplierService {
	@Autowired
	ErpSupplierMapper supplierMapper;

	@Override
	public ErpSupplier selectByUserId(int userId) {
		return supplierMapper.selectByUserId(userId);
	}

	@Override
	public int insert(ErpSupplier tlErpSupplier) {
		int insert = supplierMapper.insert(tlErpSupplier);
		if (insert > 0) {
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = supplierMapper.deleteByPrimaryKey(id);
		if (delete > 0) {
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpSupplier tlErpSupplier) {
		int update = supplierMapper.updateByPrimaryKeySelective(tlErpSupplier);
		if (update > 0) {
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpSupplier> list(String name,String search) {
		List<ErpSupplier> list = supplierMapper.lists(name,search);
		if (list.size() > 0) {// 在这里判断，可以，减少数据传输,呵呵，我信你个鬼，
			return list;
		}
		return null;
	}

	@Override
	public ErpSupplier get(Integer sId) {
		ErpSupplier get = supplierMapper.get(sId);
		if (get != null) {
			return get;
		}
		return null;
	}

	@Override
	public int updateByUserid(ErpSupplier supplier) {
		return supplierMapper.updateByUserid(supplier);
	}

	@Override
	public int deleteByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return supplierMapper.deleteByUserId(user_id);
	}

}
