package com.xiaoyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpLoggerMapper;
import com.xiaoyuan.model.ErpLogger;
@Service
public class ErpLoggerService implements IErpLoggerService {
	@Autowired
	ErpLoggerMapper loggerMapper;
	@Override
	public int insert(ErpLogger log) {
		// TODO Auto-generated method stub
		return loggerMapper.insert(log);
	}

}
