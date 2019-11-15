package com.xiaoyuan.service;

import com.xiaoyuan.model.ErpPound;

public interface IErpPoundService {
	int insertPound(ErpPound pound);
	
	int updatePound(ErpPound pound);
	
	ErpPound getPoundMessageByPickgoodsid(ErpPound pound);
	
	ErpPound getPound(ErpPound pound);


	/**
	 * 根据订单信息查询过磅信息
	 * @param orderSn
	 * @return
	 */
	ErpPound selectByOrderSn(String orderSn);
}
