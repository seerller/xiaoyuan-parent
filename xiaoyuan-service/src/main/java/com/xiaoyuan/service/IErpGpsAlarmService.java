package com.xiaoyuan.service;

import com.xiaoyuan.model.*;
import com.xiaoyuan.model.dto.ErpGpsAlarmDto;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;

import java.util.List;

/**
 * gps设备service层
 * @author zhengweicheng
 */
public interface IErpGpsAlarmService {

	/**
	 * 根据search条件搜索类内属性筛选数据并返回集合
	 * @param search
	 * @return
	 */
	List<ErpGpsAlarmDto> selectBySearch(ErpGpsAlarmSearch search);
	/**
	 * 统计search条件查询类查询后的总数据数。
	 * @param search
	 * @return
	 */
	int countBySearch(ErpGpsAlarmSearch search);
	/**
	 * 根据search条件搜索类内属性筛选数据并返回集合内第一条数据
	 * @param search
	 * @return
	 */
	ErpGpsAlarmDto selectFirstBySearch(ErpGpsAlarmSearch search);

	/**
	 * 根据自增主键修改属性不为空的数据
	 * @param gps
	 * @return
	 */
	int updateByPrimaryKeySelective(ErpGpsAlarm gps);

	int insertSelective(ErpGpsAlarm gps);
	/**
	 * 根据search条件搜索类内属性筛选数据并返回分页集合
	 * @param search
	 * @return
	 */
	List<ErpGpsAlarmDto> selectBySearchOfPage(ErpGpsAlarmSearch search);
	/**
	 * 根据gps设备自增主键删除gps设备数据
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
}
