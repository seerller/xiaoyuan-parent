package com.xiaoyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpDeviceLogMapper;
import com.xiaoyuan.model.ErpDeviceLog;
@Service
public class ErpDeviceLogService implements IErpDeviceLogService {
	@Autowired
	ErpDeviceLogMapper deviceLogMapper;
	@Override
	public int insert(ErpDeviceLog log) {
		// TODO Auto-generated method stub
		return deviceLogMapper.insert(log);
	}

}
