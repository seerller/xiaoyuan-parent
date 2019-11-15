package com.xiaoyuan.service;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.mapper.ErpGpsAlarmMapper;
import com.xiaoyuan.model.ErpGpsAlarm;
import com.xiaoyuan.model.dto.ErpGpsAlarmDto;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErpGpsAlarmService implements IErpGpsAlarmService {
    @Autowired
    ErpGpsAlarmMapper mapper;
    /**
     * 根据gps设备自增主键删除gps设备数据
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return 0;
    }
    /**
     * 根据search条件搜索类内属性筛选数据并返回集合
     * @param search
     * @return
     */
    @Override
    public List<ErpGpsAlarmDto> selectBySearch(ErpGpsAlarmSearch search) {
        return mapper.selectBySearch(search);
    }
    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    @Override
    public int countBySearch(ErpGpsAlarmSearch search) {
        return mapper.countBySearch(search);
    }

    /**
     * 根据search条件搜索类内属性筛选数据并返回集合内第一条数据
     * @param search
     * @return
     */
    @Override
    public ErpGpsAlarmDto selectFirstBySearch(ErpGpsAlarmSearch search) {
        List<ErpGpsAlarmDto> list = mapper.selectBySearch(search);
        if(list.size()<=0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public int updateByPrimaryKeySelective(ErpGpsAlarm gps) {
        return mapper.updateService(gps);
    }

    @Override
    public int insertSelective(ErpGpsAlarm gps) {
        return mapper.insertService(gps);
    }
     /**
     * 根据search条件搜索类内属性筛选数据并返回分页集合
     * @param search
     * @return
     */
    @Override
    public List<ErpGpsAlarmDto> selectBySearchOfPage(ErpGpsAlarmSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpGpsAlarmDto> list = selectBySearch(search);
        if(list.size()>0){
            PageBean<ErpGpsAlarmDto> pageData = new PageBean<ErpGpsAlarmDto>(search.getCurrentPage(), search.getPageSize(), list.size());
            pageData.setItems(list);
            return pageData.getItems();
        }
        return list;
    }
}
