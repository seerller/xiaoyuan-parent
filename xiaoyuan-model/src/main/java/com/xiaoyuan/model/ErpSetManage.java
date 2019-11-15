package com.xiaoyuan.model;
import com.alibaba.fastjson.JSON;
import com.xiaoyuan.model.setManageEntity.CommonSetEntity;
import com.xiaoyuan.model.setManageEntity.GpsSetEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tl_erp_gps")
public class ErpSetManage {
    /**
     * 数据类型常量
     */
    private final static String TYPE_VALUE_JSON = "JSON";

    /**
     * 配置管理表主键
     */
    @Id
    private Integer id;
    /**
     * 配置管理关键字
     */
    private String key_name;
    /**
     * 配置管理的值
     */
    private String value;
    /**
     * 创建时间
     */
    private int createtime;
    /**
     * 修改时间
     */
    private int updatetime;
    /**
     * 修改人用户名
     */
    private String updateUsername;
    /**
     * 创建人用户名
     */
    private String createUsername;
    /**
     * 该键值对的数据类型。
     */
    private String type;
    /**
     * 预留字段，该配置表内允许关联用户表，但全局的配置在该字段的值为null或0
     */
    private Integer user_id;

    public ErpSetManage(){

    }


    /**
     * 自定义构造函数
     * @param entity
     */
    public ErpSetManage(CommonSetEntity entity,String createUsername){
        //entity.setLimitStartDay();
        this.value              = JSON.toJSONString(entity);
        this.key_name                = CommonSetEntity.SET_VALUE_KEY;
        this.createtime         = (int)(System.currentTimeMillis()/1000);
        this.createUsername     = createUsername;
        this.type = TYPE_VALUE_JSON;
    }
    /**
     * 自定义构造函数
     * @param entity
     */
    public ErpSetManage(GpsSetEntity entity, String createUsername){
        //entity.setLimitStartDay();
        this.value              = JSON.toJSONString(entity);
        this.key_name                = GpsSetEntity.SET_VALUE_KEY;
        this.createtime         = (int)(System.currentTimeMillis()/1000);
        this.createUsername     = createUsername;
        this.type = TYPE_VALUE_JSON;
    }

    /**
     * 自定义一个构造器专门处理传入参数为对象的并进行转换
     * @param object
     */
    public ErpSetManage setValue(Object object){
        this.value = JSON.toJSONString(object);
        return this;
    }
    public ErpSetManage setValue(String value){
        this.value = value;
        return this;
    }
    public ErpSetManage setUpdatetime(int time){
        this.updatetime = time;
        return this;
    }
    public ErpSetManage setUpdateUsername(String username){
        this.updateUsername = username;
        return this;
    }
}
