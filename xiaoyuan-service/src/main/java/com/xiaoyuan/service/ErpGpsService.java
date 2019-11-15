package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpGpsMapper;
import com.xiaoyuan.model.ErpGPS;

@Service
public class ErpGpsService implements IErpGpsService {
	@Autowired
	ErpGpsMapper gpsMapper;
	@Override
	public int insert(ErpGPS gps) {
		int insert = gpsMapper.insert(gps);
		if(insert>0){
			return insert;
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		int delete = gpsMapper.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return  0;
		
		
	}

	@Override
	public int update(ErpGPS gps) {
		int update = gpsMapper.updateByPrimaryKeySelective(gps);
		if(update>0){
			return update;
		}
		
		return 0;
		
		
	}

	@Override
	public List<ErpGPS> listErpGps() {
		
		List<ErpGPS> list = gpsMapper.selectAll();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public ErpGPS get(Integer gId) {
		ErpGPS get = gpsMapper.get(gId);
		if(get != null){
			return get;
		}
		return null;
	}

	@Override
	public List<ErpGPS> lists() {
		List<ErpGPS> list = gpsMapper.lists();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<ErpGPS> lists_alarm() {
		List<ErpGPS> list = gpsMapper.lists_alarm();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<ErpGPS> lists_real_information() {
		List<ErpGPS> list = gpsMapper.lists_real_information();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<ErpGPS> lists_alarm_statistical() {
		List<ErpGPS> list = gpsMapper.lists_alarm_statistical();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int updateByCarId(ErpGPS gps) {
		int update = gpsMapper.updateByCarId(gps);
		if(update>0){
			return update;
		}
		
		return 0;
	}

	@Override
	public ErpGPS getGpsByCarid(Integer car_id) {
		ErpGPS get = gpsMapper.getGpsByCarid(car_id);
		if(get != null){
			return get;
		}
		return null;
	}
	/**
	 * 根据车辆信息主键查询gps设备主键
	 * @param driverId
	 * @return
	 */
	@Override
	public ErpGPS resultGpsSnByDriverId(Integer driverId) {
		return gpsMapper.resultGpsSnByDriverId(driverId);
	}

	@Override
	public int updateServiceByPrimaryKey(ErpGPS gps) {
		return gpsMapper.updateServiceByPrimaryKey(gps);
	}
	/**
	 * 根据传入对象插入数据
	 * @param gps
	 * @return
	 */
	@Override
	public int insertService(ErpGPS gps) {
		return gpsMapper.insertService(gps);
	}


}
