package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpGPS;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpGpsMapper extends MapperConfig<ErpGPS> {
	List<ErpGPS> lists();
	
	List<ErpGPS> lists_alarm();
	
	List<ErpGPS> lists_real_information();
	
	List<ErpGPS> lists_alarm_statistical();
	
	int insert(ErpGPS gps);

	int updateByCarId(ErpGPS gps); 
	
	ErpGPS get(Integer id);
	
	ErpGPS getGpsByCarid(@Param("car_id") Integer car_id);

	/**
	 * 根据主键修改信息
	 * @return
	 */
	int updateServiceByPrimaryKey(ErpGPS gps);

	/**
	 * 根据传入对象插入数据
	 * @param gps
	 * @return
	 */
	int insertService(ErpGPS gps);


	/**
	 * 根据车辆信息主键查询gps设备主键
	 * @param driverId
	 * @return
	 */
	ErpGPS resultGpsSnByDriverId(Integer driverId);
}