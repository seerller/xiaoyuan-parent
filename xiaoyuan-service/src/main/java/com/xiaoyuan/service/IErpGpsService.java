package com.xiaoyuan.service;
import java.util.List;

import com.xiaoyuan.model.ErpGPS;

public interface IErpGpsService {
	int insert(ErpGPS ErpGps);
	int delete(Integer id);
	int update(ErpGPS ErpGps);
	List<ErpGPS> listErpGps();
	ErpGPS get (Integer gId);
	
	List<ErpGPS> lists();
	
	List<ErpGPS> lists_alarm();
	
	List<ErpGPS> lists_real_information();
	
	List<ErpGPS> lists_alarm_statistical();
	int updateByCarId(ErpGPS gps);
	
	ErpGPS getGpsByCarid(Integer car_id);

	/**
	 * 根据车辆信息主键查询gps设备主键
	 * @param driverId
	 * @return
	 */
	ErpGPS resultGpsSnByDriverId(Integer driverId);

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

}
