package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpCard;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpCardMapper extends MapperConfig<ErpCard> {
	List<ErpCard> lists();

	int insert(ErpCard card);

	int updateByDriverId(ErpCard card);

	ErpCard get(Integer id);

	/**
	 * 
	 * 报错，SQL找到多条，，
	 * 
	 * @param car_id
	 * @return
	 *
	 * 		2019年3月5日下午5:03:21
	 * @author : YZH
	 */
	ErpCard getCardByCarid(@Param("car_id") Integer car_id);

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