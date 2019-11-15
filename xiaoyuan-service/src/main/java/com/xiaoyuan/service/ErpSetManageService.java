package com.xiaoyuan.service;
import com.alibaba.fastjson.JSON;
import com.xiaoyuan.mapper.ErpSetManageMapper;
import com.xiaoyuan.model.ErpSetManage;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpSetManageSearch;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErpSetManageService implements IErpSetManageService {
    @Autowired
    ErpSetManageMapper mapper;

    /**
     * 根据条件搜索类进行查询并返回集合
     * @param search
     * @return
     */
    @Override
    public List<ErpSetManage> selectBySearch(ErpSetManageSearch search) {
        return mapper.selectBySearch(search);
    }
    /**
     * 根据条件搜索类进行查询并返回集合里的第一个值
     * @param search
     * @return
     */
    @Override
    public ErpSetManage selectFirstBySearch(ErpSetManageSearch search) {
        List<ErpSetManage> list = selectBySearch(search);
        if(list.size()<=0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 获取通用的设置管理
     * @return
     */
    @Override
    public ErpSetManage getCommonSet() {
        return getCommonSet(CommonSetEntity.SET_VALUE_KEY);
    }

    /**
     * 传入关键字参数，获取常用的设置管理
     * @param keyName
     * @return
     */
    @Override
    public ErpSetManage getCommonSet(String keyName) {
        if(StringUtils.isBlank(keyName)){
            throw new LogicException("请传入正确的关键字进行搜索");
        }
        //创建管理设置条件搜索类
        ErpSetManageSearch search = new ErpSetManageSearch();
        //设置该接口设置的数据并传入数据库进行查询，判断是否存在数据
        search.setKey_name(keyName);
        return selectFirstBySearch(search);
    }

    /**
     * 获取常用的配置信息 -》 管理设置
     * @return
     */
    @Override
    public CommonSetEntity getCommonEntitySet() {
        ErpSetManage set = getCommonSet();
        if(null==set || StringUtils.isBlank(set.getValue())){
            return null;
        }
        return JSON.parseObject(set.getValue(),CommonSetEntity.class);
    }

    /**
     * 传入对象判断对象属性内是否为空进行sql语句的拼接插入
     * @param erpSetManage
     * @return
     */
    @Override
    public int insertService(ErpSetManage erpSetManage) {
        return mapper.insertService(erpSetManage);
    }

    /**
     * 入对象判断对象属性内是否为空进行sql语句的拼接根据表内主键进行修改
     * @param erpSetManage
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ErpSetManage erpSetManage) {
        return mapper.updateByPrimaryKeySelective(erpSetManage);
    }
}
