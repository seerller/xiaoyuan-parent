package com.xiaoyuan.model.exception;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
/**
 * 该类的作用在于当业务逻辑发生冲突或错误时，可通过捕获该错误获取状态码与错误信息并进行逻辑判断。
 * @author zhengweicheng
 */
@Data
public class LogicException extends RuntimeException {
    /**
     * 状态码
     */
    private String code = "10000";
    /**
     * 返回的错误信息
     */
    private String msg;
    /**
     * 存储对象
     */
    private String data;


    public LogicException setCodeResultThis(String code){
        this.code = code;
        return this;
    }
    public LogicException setMsgResultThis(String msg){
        this.msg = msg;
        return this;
    }
    public LogicException setDataResultThis(String data){
        this.data = data;
        return this;
    }
    public LogicException(){}
    public LogicException(String code,String msg){
        this.code = code;
        if(StringUtils.isBlank(msg)){
            msg = "操作失败,请稍后重试.";
        }
        this.msg  = msg;
    }
    public LogicException(String code,String msg,Object data){
        this.code = code;
        if(StringUtils.isBlank(msg)){
            msg = "操作失败,请稍后重试.";
        }
        this.msg  = msg;
        this.data = JSON.toJSONString(data);
    }
    public LogicException(String msg){
        if(StringUtils.isBlank(msg)){
            msg = "操作失败,请稍后重试.";
        }
        this.msg  = msg;
    }
    public LogicException(Integer code,String msg){
        this.code = code.toString();
        if(StringUtils.isBlank(msg)){
            msg = "操作失败,请稍后重试.";
        }
        this.msg  = msg;
    }
    public LogicException(String code,String msg,String data){
        this.code = code.toString();
        if(StringUtils.isBlank(msg)){
            msg = "操作失败,请稍后重试.";
        }
        this.msg  = msg;
        this.data = data;
    }

    /**
     * 在限制时间内提货量小于最小提货量时的异常状态码
     */
    public final static String LIMIT_MIN_CODE = "5001";
    /**
     * 在限制时间内提货量大于限制的最大提货量的异常状态码
     */
    public final static String LIMIT_MAX_CODE = "5002";

}
