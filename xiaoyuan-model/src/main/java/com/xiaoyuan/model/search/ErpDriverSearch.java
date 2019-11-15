package com.xiaoyuan.model.search;
import lombok.Data;
import lombok.ToString;

/**
 * 司机接口条件搜索类
 */
@Data
@ToString(callSuper = true)
public class ErpDriverSearch  extends BaseSearch{
    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 设备主键
     */
    private Integer driverId;
    /**
     * 1是销货车辆
     * 2是进货车辆
     * 默认销货车辆
     */
    private Integer cateId = 1;
    /**
     * 过滤伪删除用户
     * 默认为过滤掉伪删除用户
     * 当等于false时则查询所有
     */
    private boolean isStop = true;

    public ErpDriverSearch(){}
    public ErpDriverSearch(Integer driverId){
        this.driverId = driverId;
    }


    public ErpDriverSearch setUserId(Integer userId){
        this.userId = userId;
        return this;
    }
}
