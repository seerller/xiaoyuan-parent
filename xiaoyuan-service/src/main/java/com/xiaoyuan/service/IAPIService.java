package com.xiaoyuan.service;

import java.util.ArrayList;

import com.xiaoyuan.model.API;
import com.xiaoyuan.model.APICate;

public interface IAPIService {

	/**
	 * 一级菜单
	 * 
	 * @param condition
	 * @return
	 *
	 * 		2019年2月27日下午4:19:18
	 * @author : YZH
	 */
	ArrayList<APICate> lists(String condition);

	/**
	 * 二级菜单
	 * 
	 * @param c
	 * @param listsorder
	 * @param condition
	 * @return
	 *
	 * 		2019年2月27日下午4:19:19
	 * @author : YZH
	 */
	ArrayList<API> listsbycategroy(String c, Integer listsorder, String condition);

	ArrayList<APICate>first_api();
	ArrayList<API>second_api();
	ArrayList<API>third_api();
}
