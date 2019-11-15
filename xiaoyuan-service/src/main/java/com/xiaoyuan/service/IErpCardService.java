package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpCard;

public interface IErpCardService {

	int insert(ErpCard ErpCard);

	int delete(Integer id);

	int update(ErpCard ErpCard);

	List<ErpCard> listErpCard();

	ErpCard get(Integer csCarId);

	List<ErpCard> lists();

	int updateByDriverId(ErpCard card);

	/**
	 * 报错，SQL找到多条，，
	 * 
	 * @param car_id
	 * @return
	 *
	 * 		2019年3月5日下午5:02:38
	 * @author : YZH
	 */
	ErpCard getCardByCarid(Integer car_id);



	/**
	 * 新增卡片信息
	 * @param card
	 * @return
	 */
	int insertCardService(ErpCard card);

	/**
	 * 根据主键修改卡片信息
	 * @param card
	 * @return
	 */
	int updateCardService(ErpCard card);
}
