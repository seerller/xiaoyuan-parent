package com.xiaoyuan.service;

import com.xiaoyuan.model.ErpPrice;

public interface IErpPriceService {
	ErpPrice selectByPriceMsg(ErpPrice price);
	
	int save(ErpPrice price);
}
