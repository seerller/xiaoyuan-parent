package com.xiaoyuan.model.search;

import lombok.Data;

import java.util.List;


/**
 * 用户查询搜索类
 * @author  zhengweicheng
 * @time 2019-06-21
 */
@Data
public class ErpUserSearch extends BaseSearch {
    /**
     * 用户类型
     */
    private Integer userCate;
    /**
     * 车辆性质
     * 是销货还是进货
     * 1是销货，2是进货
     */
    private Integer cateId;
    /**
     * 用户id
     * 后端传入。排除自己。
     */
    private Integer userId;

    /**
     * 用户id
     * 根据该id查询用户。
     */
    private Integer id;
    /**
     * 通过手机号查询用户信息。
     */
    private String phone;
    /**
     * 默认查询所有用户都是已审核状态
     * 有特殊需求的可传入该参数
     */
    private Integer audit = 1;
    /**
     * 根据该属性判断是否区分公司名称
     * 等于空则不通过group by来获得具体公司名称
     * 不等于则通过该group by来分组获得公司名称
     */
    private Integer groupBy = null;

    /**
     * 配合groupBy使用若为空则默认groupBy 公司名称
     */
    private Integer columnName = null;

    /**
     * 该字段为null时则什么都不变，忽略该属性
     * 为1时则排除cardId为空的数据
     * 为-1时则排除cardId不为空的数据
     */
    private Integer reomveNotCard;

    /**
     *  默认为1
     *  当该值存在时，则过滤掉所有被伪删除的用户
     *  并禁止登陆
     */
    private Integer stop = 1;

    /**
     * 剔除多数用户
     */
    private List<Integer> notIn;

    public ErpUserSearch(){

    }

    public ErpUserSearch(Integer userId){
        this.userId = userId;
    }



    public ErpUserSearch setUserCate(Integer userCate){
        this.userCate = userCate;
        return this;
    }
    public ErpUserSearch setUserId(Integer id){
        this.userId = id;
        return this;
    }
    public ErpUserSearch setId(Integer id){
        this.id = id;
        return this;
    }
}

