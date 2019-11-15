package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpCardMapper;
import com.xiaoyuan.model.ErpCard;

@Service
public class ErpCardService implements IErpCardService {
	@Autowired
	ErpCardMapper cardMapper;
	@Override
	public int insert(ErpCard card) {
		int insert = cardMapper.insert(card);
		if(insert>0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = cardMapper.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpCard card) {
		int update =cardMapper.updateByPrimaryKeySelective(card);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpCard> listErpCard() {
		List<ErpCard> list = cardMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpCard get(Integer csCarId) {
		ErpCard state=cardMapper.get(csCarId);
		if (state != null){
			return state;
		}
		return null;
	}

	@Override
	public List<ErpCard> lists() {
		List<ErpCard> list = cardMapper.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int updateByDriverId(ErpCard card) {
		int update =cardMapper.updateByDriverId(card);
		if(update>0){
			return update;
		}
		return 0;
		
	}

	@Override
	public ErpCard getCardByCarid(Integer car_id) {
		ErpCard state=cardMapper.getCardByCarid(car_id);
		if (state != null){
			return state;
		}
		return null;
	}

	/**
	 * 新增卡片信息
	 * @param card
	 * @return
	 */
	@Override
	public int insertCardService(ErpCard card) {
		return cardMapper.insertCardService(card);
	}
	/**
	 * 根据主键修改卡片信息
	 * @param card
	 * @return
	 */
	@Override
	public int updateCardService(ErpCard card) {
		return cardMapper.updateCardService(card);
	}

}
