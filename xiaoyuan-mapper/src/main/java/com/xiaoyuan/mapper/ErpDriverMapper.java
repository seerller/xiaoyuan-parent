package com.xiaoyuan.mapper;

import java.util.List;

import com.xiaoyuan.model.dto.ErpDriverDto;
import com.xiaoyuan.model.search.ErpDriverSearch;
import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpDriverMapper extends MapperConfig<ErpDriver> {

    int updateByUserid(ErpDriver driver);

    int insert(ErpDriver driver);// 自动生成。。。。

    ErpDriver getDriverByUserId(@Param("user_id") Integer user_id);

    /**
     * 加一个id参数，当id非空时，查此id除外的记录，修改车牌用，去重但不包括自己，
     *
     * @param id
     * @return 2019年3月4日下午5:00:43
     * @author : YZH
     */
    List<ErpDriver> carsn(@Param("id") Integer id);

    ErpDriver get(Integer id);
    ErpDriver getDriverById(Integer id);
    /**
     * 车辆列表
     * <p>
     * 供应商预约送货（录入车牌号）没有显示供应商司机
     *
     * @return 2019年3月7日上午10:43:06
     * @author : YZH
     * <p>
     * 园区登录，车辆管理，列表，查看SQL语句，并查看CRUD语句，待会还会过来这个类。。
     * <p>
     * 2019年3月5日下午3:39:16
     * @author : YZH
     * <p>
     * ，，，只查客户车辆，加传参car_cate，不行就换控制类方法，可能还有其他，，，
     * 这个点前端似乎也能实现，更表层，加个类别下拉框，并且指定，，选择又多又少~
     * @author : YZH
     */
    List<ErpDriver> lists_car(@Param("car_cate") Integer car_cate, @Param("search") String search, @Param("audit") Integer audit);

    ErpDriver driverConfirm(ErpDriver driver);

    ErpDriver driverChoosesn(ErpDriver driver);

    ErpDriver getCarsnByRfid(@Param("car_sn") String car_sn);

    int deleteByUserId(@Param("user_id") Integer user_id);

    ErpDriver getQueueByRfid(@Param("car_sn") String car_sn);

    List<ErpDriverDto> selectBySearch(ErpDriverSearch search);
    int countBySearch(ErpDriverSearch search);


    int updateServiceByPrimaryKey(ErpDriver driver);

    /**
     * 根据传入对象插入数据
     * @param driver
     * @return
     */
    int insertService(ErpDriver driver);

    /**
     * 清空驾驶员数据
     * @param id
     * @return
     */
    int removeDriver(Integer id);
}