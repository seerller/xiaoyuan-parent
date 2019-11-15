package com.xiaoyuan.mapper;

import com.xiaoyuan.model.ErpPrice;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpPriceMapper extends MapperConfig<ErpPrice>{
	ErpPrice selectByPriceMsg(ErpPrice price);
}
