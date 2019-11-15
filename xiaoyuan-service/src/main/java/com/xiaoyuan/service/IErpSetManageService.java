package com.xiaoyuan.service;

import com.xiaoyuan.model.ErpSetManage;
import com.xiaoyuan.model.search.ErpSetManageSearch;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;

import java.util.List;

public interface IErpSetManageService  {
    /**
     * 根据条件搜索类进行查询并返回集合
     * @param search
     * @return
     */
    List<ErpSetManage> selectBySearch(ErpSetManageSearch search);

    /**
     * 根据条件搜索类进行查询并返回集合
     * @param search
     * @return
     */
    ErpSetManage selectFirstBySearch(ErpSetManageSearch search);

    /**
     * 获取常用的设置管理
     * @return
     */
    ErpSetManage getCommonSet();

    /**
     * 传入关键字参数，获取常用的设置管理
     * @param keyName
     * @return
     */
    ErpSetManage getCommonSet(String keyName);

    /**
     * 获取常用的配置信息 -》 管理设置
     * @return
     */
    CommonSetEntity getCommonEntitySet();
    /**
     * 传入对象判断对象属性内是否为空进行sql语句的拼接插入
     * @param erpSetManage
     * @return
     */
    int insertService(ErpSetManage erpSetManage);
    /**
     * 入对象判断对象属性内是否为空进行sql语句的拼接根据表内主键进行修改
     * @param erpSetManage
     * @return
     */
    int updateByPrimaryKeySelective(ErpSetManage erpSetManage);
}
