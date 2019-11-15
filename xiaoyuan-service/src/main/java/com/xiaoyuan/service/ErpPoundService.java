package com.xiaoyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpPoundMapper;
import com.xiaoyuan.model.ErpPound;
@Service
public class ErpPoundService implements IErpPoundService{
	@Autowired
	private ErpPoundMapper mapper;
	@Override
	public int insertPound(ErpPound pound) {
		int result = mapper.insert(pound);
		return result;
	}

	@Override
	public int updatePound(ErpPound pound) {
		int update = mapper.updateByPrimaryKeySelective(pound);
		return update;
	}

	@Override
	public ErpPound getPoundMessageByPickgoodsid(ErpPound pound) {
		
		return mapper.getPoundMessageByPickgoodsid(pound);
	}

	@Override
	public ErpPound getPound(ErpPound pound) {
		// TODO Auto-generated method stub
		return mapper.getPound(pound);
	}

	/**
	 * 根据订单信息查询过磅信息
	 * @param orderSn
	 * @return
	 */
	@Override
	public ErpPound selectByOrderSn(String orderSn) {
		return mapper.selectByOrderSn(orderSn);
	}

}
