package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpCarOrderMapper;
import com.xiaoyuan.model.ErpCarOrder;

@Service
public class ErpCarOrderService implements IErpCarOrderService {
    @Autowired
    ErpCarOrderMapper carOrderMapper;

    @Override
    public int insert(ErpCarOrder carOrder) {
        int insert = carOrderMapper.insert(carOrder);

        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        int delete = carOrderMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public int update(ErpCarOrder carOrder) {
        int update = carOrderMapper.updateByPrimaryKeySelective(carOrder);
        if (update > 0) {
            return update;
        }
        return 0;
    }

    @Override
    public List<ErpCarOrder> lists(Integer id, String sn) {
        List<ErpCarOrder> list = carOrderMapper.lists(id, sn);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpCarOrder get(Integer id) {
        ErpCarOrder get = carOrderMapper.get(id);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public List<ErpCarOrder> lists_order(Integer id, String car_order_pickdate,Integer is_car_order) {
        List<ErpCarOrder> list = carOrderMapper.lists_order(id, car_order_pickdate,is_car_order);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
    @Override
    public ErpCarOrder selectByPickgoodsId(Integer pickgoods_id) {
        return this.carOrderMapper.selectByPickgoodsId(pickgoods_id);
    }
    @Override
    public int updateOrder(ErpCarOrder erpCarOrder) {
        int update = carOrderMapper.updateCarOrder(erpCarOrder);
        if (update > 0) {
            return update;
        }
        return 0;
    }

    @Override
    public List<ErpCarOrder> queueList() {
        List<ErpCarOrder> list = carOrderMapper.queueList();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpCarOrder getCarOrder(ErpCarOrder carOrder) {
        ErpCarOrder get = carOrderMapper.getCarOrder(carOrder);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public Integer getMaxQueue_sn() {
        // TODO Auto-generated method stub
        return carOrderMapper.getMaxQueue_sn();
    }

    @Override
    public List<ErpCarOrder> lists_order_ingoods(Integer id, String car_order_pickdate) {
        List<ErpCarOrder> list = carOrderMapper.lists_order_ingoods(id, car_order_pickdate);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ErpCarOrder carOrder) {
        return carOrderMapper.updateByPrimaryKeySelective(carOrder);
    }

    @Override
    public int insertService(ErpCarOrder carOrder) {
        return carOrderMapper.insertSelective(carOrder);
    }


}
