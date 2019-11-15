package com.xiaoyuan.tools;

/**
 * @author Administrator
 */
public class MessageBean {
    private int status;
    private String msg;
    private String token;
    private Object data;
    private Object user;

    public MessageBean(int status, String msg, Object data, Object user) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.user = user;
    }

    public MessageBean(int status, String msg, Object data, String token, Object user) {
        this.status = status;
        this.msg = msg;
        this.token = token;
        this.data = data;
        this.user = user;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public MessageBean(int status, String msg, String token) {
        super();
        this.status = status;
        this.msg = msg;
        this.token = token;
    }

    public MessageBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MessageBean(Object data) {
        super();
        this.data = data;
    }

    public MessageBean(int status, String msg, Object data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public MessageBean(int status, String msg, String token, Object data) {
        super();
        this.status = status;
        this.msg = msg;
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
