package com.xiaoyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpPriceMapper;
import com.xiaoyuan.model.ErpPrice;

@Service
public class ErpPriceService implements IErpPriceService {
	@Autowired
	private ErpPriceMapper priceMapper;

	@Override
	public ErpPrice selectByPriceMsg(ErpPrice price) {
		// TODO Auto-generated method stub
		return priceMapper.selectByPriceMsg(price);
	}

	@Override
	public int save(ErpPrice price) {
		// TODO Auto-generated method stub
		return priceMapper.insert(price);
	}

}
