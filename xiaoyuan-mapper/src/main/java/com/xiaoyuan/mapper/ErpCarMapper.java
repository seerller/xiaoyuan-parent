package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpCar;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpCarMapper extends MapperConfig<ErpCar> {
	List<ErpCar> lists();
	
	int insert(ErpCar car);
	
	ErpCar getIdByUserId(Integer id);
	
	

	List<ErpCar> lists_car_state();

	List<ErpCar> lists_car_pick();

	ErpCar getIdByDriverId(@Param("driver_id")Integer driver_id);

	int updateByDriverId(ErpCar car);
	
	ErpCar get(Integer id);
	//车辆信息维护
	ErpCar getCar(Integer id);
	
	int deleteCar (@Param("user_id")Integer user_id);
	
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