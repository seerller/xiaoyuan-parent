package com.xiaoyuan.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.dto.ErpDriverDto;
import com.xiaoyuan.model.search.ErpDriverSearch;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpDriverMapper;
import com.xiaoyuan.model.ErpDriver;

@Service
public class ErpDriverService implements IErpDriverService {
    @Autowired
    ErpDriverMapper driverMapper;

    @Override
    public int insert(ErpDriver driver) {
        int insert = driverMapper.insert(driver);
        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        int delete = driverMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public int update(ErpDriver driver) {
        int update = driverMapper.updateByPrimaryKeySelective(driver);
        return 0;
    }

    @Override
    public List<ErpDriver> list() {
        List<ErpDriver> list = driverMapper.selectAll();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpDriver get(Integer clUser) {
        ErpDriver get = driverMapper.get(clUser);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public int updateByUserid(ErpDriver driver) {
        return driverMapper.updateByUserid(driver);
    }

    @Override
    public ErpDriver getDriverByUserId(Integer id) {
        ErpDriver get = driverMapper.getDriverByUserId(id);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public List<ErpDriver> carsn(Integer id) {
        List<ErpDriver> carsn = driverMapper.carsn(id);
        if (carsn.size() > 0) {
            return carsn;
        }
        return null;
    }

    @Override
    public List<ErpDriver> lists_car(Integer car_cate, String search,Integer audit) {
        List<ErpDriver> list = driverMapper.lists_car(car_cate, search,audit);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpDriver driverConfirm(ErpDriver driver) {
        ErpDriver get = driverMapper.driverConfirm(driver);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public ErpDriver getCarsnByRfid(String card_rfid) {
        ErpDriver get = driverMapper.getCarsnByRfid(card_rfid);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public ErpDriver driverChoosesn(ErpDriver driver) {
        ErpDriver get = driverMapper.driverChoosesn(driver);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public int deleteByUserId(Integer user_id) {
        // TODO Auto-generated method stub
        return driverMapper.deleteByUserId(user_id);
    }

    @Override
    public ErpDriver getQueueByRfid(String card_rfid) {
        ErpDriver get = driverMapper.getQueueByRfid(card_rfid);
        if (get != null) {
            return get;
        }
        return null;
    }
    /**
     * 根据search类查询相关信息
     * @param search
     * @return
     */
    @Override
    public List<ErpDriverDto> selectBySearch(ErpDriverSearch search) {
        return driverMapper.selectBySearch(search);
    }


    @Override
    public int countBySearch(ErpDriverSearch search) {
        return driverMapper.countBySearch(search);
    }

    @Override
    public ErpDriverDto selectFirstBySearch(ErpDriverSearch search) {
        List<ErpDriverDto> list = selectBySearch(search);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据设备主键查询相应的设备信息。
     * @param id
     * @return
     */
    @Override
    public ErpDriver getDriverById(Integer id) {
        return driverMapper.getDriverById(id);
    }
    /**
     * 根据search条件搜索类查询并返回分页数据
     * @param search
     * @return
     */
    @Override
    public List<ErpDriverDto> selectBySearchOfPage(ErpDriverSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpDriverDto> list = selectBySearch(search);
        if(list.size()>0){
            PageBean<ErpDriverDto> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
            pageData.setItems(list);
            return pageData.getItems();
        }
        return list;
    }

    @Override
    public int updateServiceByPrimaryKey(ErpDriver driver) {
        return driverMapper.updateServiceByPrimaryKey(driver);
    }
    /**
     * 根据传入对象插入数据
     * @param driver
     * @return
     */
    @Override
    public int insertService(ErpDriver driver) {
        return driverMapper.insertService(driver);
    }

    @Override
    public int removeDriver(Integer id) {
        return driverMapper.removeDriver(id);
    }


}
