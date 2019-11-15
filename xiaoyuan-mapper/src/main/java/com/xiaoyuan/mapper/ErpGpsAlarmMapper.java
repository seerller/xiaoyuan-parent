package com.xiaoyuan.mapper;

import com.xiaoyuan.model.ErpGpsAlarm;
import com.xiaoyuan.model.dto.ErpGpsAlarmDto;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;
import com.xiaoyuan.tools.MapperConfig;

import java.util.List;


/**
 * Gps设备dao层
 */
public interface ErpGpsAlarmMapper extends MapperConfig<ErpGpsAlarm> {
    /**
     * 根据条件搜索类进行查询用户
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
     * 插入一条新数据
     * @param device
     * @return
     */
    int insertService(ErpGpsAlarm device);

    /**
     * 根据自增主键修改属性不为空的数据
     * @param device
     * @return
     */
    int updateService(ErpGpsAlarm device);

    /**
     * 根据gps设备自增主键删除gps设备数据
     * @param id
     * @return
     */
    int deleteById(Integer id);

}
