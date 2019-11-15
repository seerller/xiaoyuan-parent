package com.xiaoyuan.mapper;

import com.xiaoyuan.model.ErpPound;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpPoundMapper extends MapperConfig<ErpPound>{
	ErpPound getPoundMessageByPickgoodsid(ErpPound pound);
	
	ErpPound getPound(ErpPound pound);
	
	int insert(ErpPound pound);

	/**
	 * 根据订单信息查询过磅信息
	 * @param orderSn
	 * @return
	 */
	ErpPound selectByOrderSn(String orderSn);
}
