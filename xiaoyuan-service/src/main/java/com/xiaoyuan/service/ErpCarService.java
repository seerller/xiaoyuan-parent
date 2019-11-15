package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpCarMapper;
import com.xiaoyuan.model.ErpCar;
@Service
public class ErpCarService implements IErpCarService {
	@Autowired
	ErpCarMapper tlerpcar;

	@Override
	public int insert(ErpCar tlErpCar) {
		int insert =tlerpcar.insert(tlErpCar);
		if(insert>0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = tlerpcar.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpCar tlErpCar) {
		int update =tlerpcar.updateByPrimaryKeySelective(tlErpCar);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public List<ErpCar> lists() {
		List<ErpCar> list = tlerpcar.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpCar get(Integer cId) {
		ErpCar car  = tlerpcar.get(cId);
		if(car!=null){
			return car;
		}
		return null;
	}

	@Override
	public ErpCar getIdByUserId(Integer id) {
		ErpCar car  = tlerpcar.getIdByUserId(id);
		if(car!=null){
			return car;
		}
		return null;
	}

	

	@Override
	public List<ErpCar> lists_car_state() {
		List<ErpCar> list = tlerpcar.lists_car_state();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<ErpCar> lists_car_pick() {
		List<ErpCar> list = tlerpcar.lists_car_pick();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpCar getIdByDriverId(Integer id) {
		ErpCar car  = tlerpcar.getIdByDriverId(id);
		if(car!=null){
			return car;
		}
		return null;
	}

	@Override
	public int updateByDriverId(ErpCar car) {
		int update =tlerpcar.updateByDriverId(car);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public ErpCar getCar(Integer id) {
		ErpCar car  = tlerpcar.getCar(id);
		if(car!=null){
			return car;
		}
		return null;
	}

	@Override
	public ErpCar getCarByMsg(ErpCar car) {
		// TODO Auto-generated method stub
		return tlerpcar.getCarByMsg(car);
	}


	@Override
	public int updateServiceByPrimaryKey(ErpCar car) {
		// TODO Auto-generated method stub
		return tlerpcar.updateServiceByPrimaryKey(car);
	}

	@Override
	public int insertService(ErpCar car) {
		// TODO Auto-generated method stub
		return tlerpcar.insertService(car);
	}


}
