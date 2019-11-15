package com.xiaoyuan.service;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.dto.ErpPickgoodsTotalDto;
import com.xiaoyuan.model.dto.ErpOrderAmountDto;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpPickGoodsTotalSearch;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpPickgoodsMapper;
import com.xiaoyuan.model.ErpPickgoods;

@Service
public class ErpPickgoodsService implements IErpPickgoodsService {
    @Autowired
    private ErpPickgoodsMapper pickgoodsMapper;

    @Override
    public int updateById(ErpPickgoods erpPickgoods) {
        return this.pickgoodsMapper.updateById(erpPickgoods);
    }

    @Override
    public int insert(ErpPickgoods tlErpPickgoods) {
        int insert = pickgoodsMapper.insert(tlErpPickgoods);
        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        int delete = pickgoodsMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return delete;
        }
        return 0;
    }

    @Override
    public int update(ErpPickgoods erpPickgoods) {
        //TODO 20190328 前端传参有user_company_name，自动生成SQL语句报错，unknown column
//        int update = pickgoodsMapper.updateByPrimaryKeySelective(tlErpPickgoods);
        System.out.println(erpPickgoods);
        return this.pickgoodsMapper.updateById(erpPickgoods);
//        if (result > 0) {
//        return result;
//        }
//        return 0;
    }

    @Override
    public List<ErpPickgoods> lists(String driver_phone,Integer is_car_order, Integer is_end, Integer user_id, Integer status_, String date1, String date2, String customer_name,
                                    String pickgoods_send_company,
                                    String pickgoods_car_sn) {
        List<ErpPickgoods> list = pickgoodsMapper.lists(driver_phone,is_car_order, is_end, user_id, status_, date1, date2, customer_name, pickgoods_send_company, pickgoods_car_sn);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public ErpPickgoods get(Integer pMaterialId) {
        ErpPickgoods get = pickgoodsMapper.get(pMaterialId);
        if (get != null) {
            return get;
        }
        return null;
    }

    @Override
    public List<ErpPickgoods> lists_statistice() {
        List<ErpPickgoods> list = pickgoodsMapper.lists_statistice();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }



    @Override
    public ErpPickgoods getByCarOrderSn(String car_order_sn, String pickgoods_car_sn) {
        ErpPickgoods get2 = pickgoodsMapper.getByCarOrderSn(car_order_sn, pickgoods_car_sn);
        if (get2 != null) {
            return get2;
        }
        return null;
    }

    @Override
    public ErpPickgoods out_goods(String car_order_sn, String pickgoods_car_sn) {
        ErpPickgoods get3 = pickgoodsMapper.out_goods(car_order_sn, pickgoods_car_sn);
        if (get3 != null) {
            return get3;
        }
        return null;
    }

    @Override
    public List<ErpPickgoods> list_out_goods() {
        List<ErpPickgoods> listout = pickgoodsMapper.list_out_goods();
        if (listout.size() > 0) {
            return listout;
        }
        return null;
    }

    @Override
    public ErpPickgoods getPickgoodsByMessage(ErpPickgoods pickgoods) {
        ErpPickgoods get3 = pickgoodsMapper.getPickgoodsByMessage(pickgoods);
        if (get3 != null) {
            return get3;
        }
        return null;
    }

    @Override
    public ErpPickgoods getPound(ErpPickgoods pickgoods) {
        ErpPickgoods get3 = pickgoodsMapper.getPound(pickgoods);
        if (get3 != null) {
            return get3;
        }
        return null;
    }

    @Override
    public Integer maxOrderSn(Integer o_id) {
        Integer maxOrderSn = pickgoodsMapper.maxOrderSn(o_id);
        return maxOrderSn;
    }

    @Override
    public List<ErpPickgoods> lists_audit() {
        // TODO Auto-generated method stub
        return pickgoodsMapper.lists_audit();
    }

    @Override
    public ErpPickgoods getPriceByMessage(ErpPickgoods ingoods) {
        // TODO Auto-generated method stub
        return pickgoodsMapper.getPriceByMessage(ingoods);
    }

    @Override
    public List<ErpPickgoods> lists_price(Integer s_id) {
        // TODO Auto-generated method stub
        return pickgoodsMapper.lists_price(s_id);
    }

    @Override
    public List<ErpPickgoods> lists_ingoods(String pickgoods_car_sn) {
        // TODO Auto-generated method stub
        return pickgoodsMapper.lists_ingoods(pickgoods_car_sn);
    }

    @Override
    public List<ErpPickgoods> lists_statistical() {
        // TODO Auto-generated method stub
        return pickgoodsMapper.lists_statistical();
    }

    @Override
    public ErpPickgoods in_warehouse(String car_order_sn, String pickgoods_car_sn) {
        // TODO Auto-generated method stub
        return pickgoodsMapper.in_warehouse(car_order_sn, pickgoods_car_sn);
    }

    @Override
    public ErpPickgoods purchase(String car_order_sn, String pickgoods_car_sn) {
        // TODO Auto-generated method stub
        return pickgoodsMapper.purchase(car_order_sn, pickgoods_car_sn);
    }
    /**
     * 根据自定义的条件搜索类进行查询集合。
     * @param search        条件搜索类
     * @return
     */
    @Override
    public List<ErpPickgoodsDto> selectOrderBySearch(ErpPickGoodsSearch search) {
        return pickgoodsMapper.selectOrderBySearch(search);
    }
    /**
     * 统计search条件查询类查询后的总数据数。
     * @param search
     * @return
     */
    @Override
    public int countBySearch(ErpPickGoodsSearch search) {
        return pickgoodsMapper.countBySearch(search);
    }

    @Override
    public List<ErpPickgoodsDto> selectOrderBySearchOfPage(ErpPickGoodsSearch search) {
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        List<ErpPickgoodsDto> list = selectOrderBySearch(search);
        if(list.size()>0) {
            PageBean<ErpPickgoodsDto> pageData = new PageBean<>(search.getCurrentPage(), search.getPageSize(), list.size());
            pageData.setItems(list);
            return pageData.getItems();
        }
        return list;
    }

    /**
     * 根据自定义的条件搜索类进行查询集合，并取出集合中的第一个元素。
     * @param search        条件搜索类
     * @return
     */
    @Override
    public ErpPickgoodsDto selectFirstOrderBySearch(ErpPickGoodsSearch search) {
        List<ErpPickgoodsDto> pickgoods = selectOrderBySearch(search);
        if(pickgoods.size()<=0){
            return null;
        }
        return pickgoods.get(0);
    }
    /**
     *  根据自定义的条件搜索类进行订单数量统计
     * @return
     */
    @Override
    public ErpOrderAmountDto countOrderAmount(ErpPickGoodsSearch search) {
        return pickgoodsMapper.countOrderAmount(search);
    }
    /**
     * 根据自定义的条件搜索类统计完成订单数量、提货数量与消费金额
     * @return
     */
    @Override
    public ErpPickgoodsTotalDto countPickgoodsAccountTotal(ErpPickGoodsTotalSearch search) {
        return pickgoodsMapper.countPickgoodsAccountTotal(search);
    }
    /**
     * 根据对象内属性数据是否存在生成数据库数据。
     * @param goods
     * @return
     */
    @Override
    public int insertByPickgoods(ErpPickgoods goods) {
        return pickgoodsMapper.insertByPickgoods(goods);
    }
    /**
     * 根据对象内属性数据是否存在修改数据库数据
     * @param pickgoods
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ErpPickgoods pickgoods) {
        //该方法必须依靠条件主键进行修改，若主键为空或小于等于零，则不允许继续执行代码。
        if(null == pickgoods.getId()||pickgoods.getId() <=0)
        {
            return 0;
        }
        return pickgoodsMapper.updateByPickGoods(pickgoods);
    }
    /**
     * 根据传入参数查询是否存在正在排队中的订单，存在则不允许进行再次排队
     * @param search
     * @return
     */
    @Override
    public int selectIsConfirmQueueByTime(ErpPickGoodsSearch search) {
        return pickgoodsMapper.selectIsConfirmQueueByTime(search);
    }
    /**
     * 根据用户id统计今天该用户的商品总数量
     * @param userId
     * @return
     */
    @Override
    public BigDecimal sumCurrentPickGoodsNum(Integer userId) {
        return pickgoodsMapper.sumCurrentPickGoodsNum(userId);
    }
    /**
     * 根据主键修改数据
     * @param order
     * @param ids
     * @return
     */
    @Override
    public int updateByIds(ErpPickgoods order , List<Integer> ids) {
        return pickgoodsMapper.updateByIds( order ,  ids);
    }


    /**
     * 根据用户id统计是否存在已进场，但未出厂的销货单
     * @param userId
     * @return
     */
    @Override
    public int countIsConfirmQueueNum(Integer userId) {
        return pickgoodsMapper.countIsConfirmQueueNum( userId);
    }

}
