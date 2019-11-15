package com.xiaoyuan.service;

import java.util.List;

import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.model.dto.ErpDriverDto;
import com.xiaoyuan.model.search.ErpDriverSearch;

public interface IErpDriverService {

    /**
     * 注册司机只有这一步持久化操作
     * <p>
     * 查看SQL语句
     *
     * @param tlErpPrice
     * @return 2019年2月28日下午2:38:34
     * @author : YZH
     */
    int insert(ErpDriver tlErpPrice);

    int delete(Integer id);

    int update(ErpDriver tlErpPrice);

    List<ErpDriver> list();

    ErpDriver get(Integer id);

    int updateByUserid(ErpDriver driver);

    ErpDriver getDriverByUserId(Integer id);

    /**
     * 加一个id参数，当id非空时，查此id除外的记录，修改车牌用，去重但不包括自己，
     *
     * @param id
     * @return 2019年3月4日下午4:59:18
     * @author : YZH
     */
    List<ErpDriver> carsn(Integer id);

    /**
     * 车辆列表
     * <p>
     * 供应商预约送货（录入车牌号）没有显示供应商司机
     *
     * @param car_cate
     * @return 2019年2月28日下午2:10:35
     * @author : YZH
     * <p>
     * 园区登录，车辆管理，列表，查看SQL语句，并查看CRUD语句
     * <p>
     * 2019年3月5日下午3:37:50
     * @author : YZH
     * <p>
     * 只查客户车辆，加传参，不行就换控制类方法，
     * <p>
     * 2019年3月7日上午10:42:03
     * @author : YZH
     */
    //20190331 18:00 加search
    List<ErpDriver> lists_car(Integer car_cate,String search,Integer audit);

    ErpDriver driverConfirm(ErpDriver driver);

    ErpDriver driverChoosesn(ErpDriver driver);

    ErpDriver getCarsnByRfid(String card_rfid);

    int deleteByUserId(Integer user_id);

    ErpDriver getQueueByRfid(String card_rfid);


    /**
     * 根据search类查询相关信息
     * @param search
     * @return
     */
    List<ErpDriverDto> selectBySearch(ErpDriverSearch search);

    int countBySearch(ErpDriverSearch search);

    /**
     * 根据search类查询相关信息
     * @param search
     * @return
     */
    ErpDriverDto selectFirstBySearch(ErpDriverSearch search);

    /**
     * 根据设备主键查询相应的设备信息。
     * @param id
     * @return
     */
    ErpDriver getDriverById(Integer id);

    /**
     * 根据search条件搜索类查询并返回分页数据
     * @param search
     * @return
     */
    List<ErpDriverDto> selectBySearchOfPage(ErpDriverSearch search);

    /**
     * 根据主键修改信息
     * @return
     */
    int updateServiceByPrimaryKey(ErpDriver driver);
    /**
     * 根据传入对象插入数据
     * @param driver
     * @return
     */
    int insertService(ErpDriver driver);

    int removeDriver(Integer id);
}
