package com.xiaoyuan.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 干嘛用的呢
 * 
 * @author YZH
 * @version 2019年3月8日 下午4:26:07
 *
 */
@WebService(name = "IBaseService", // 暴露服务名称
		targetNamespace = "http://webservice.leftso.com/"// 命名空间,一般是接口的包名倒序
)
public interface IBaseService {

	/**
	 * 
	 * 干嘛用的呢，，
	 * 
	 * @param parameters
	 * @return
	 *
	 * 		2019年3月8日下午4:25:53
	 * @author : YZH
	 */
	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String EDCStdcall(@WebParam(name = "parameters") String parameters);
}
