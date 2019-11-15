package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpCar;

public interface IErpCarService {
	int insert(ErpCar tlErpCar);
	
	int delete(Integer id);
	
	int update(ErpCar tlErpCar);
	
	List<ErpCar> lists();
	
	ErpCar get(Integer cId);
	
	ErpCar getIdByUserId(Integer id);
	
	
	
	List<ErpCar> lists_car_state();

	List<ErpCar> lists_car_pick();

	ErpCar getIdByDriverId(Integer id);

	int updateByDriverId(ErpCar car);
	
	ErpCar getCar(Integer id);
	
	ErpCar getCarByMsg(ErpCar car);

	/**
	 * 根据主键修改信息
	 * @return
	 */
	int updateServiceByPrimaryKey(ErpCar car);

	/**
	 * 根据传入对象插入数据
	 * @param car
	 * @return
	 */
	int insertService(ErpCar car);
}
