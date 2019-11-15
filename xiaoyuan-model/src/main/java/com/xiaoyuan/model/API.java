package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 小菜单类，对应小菜单表
 *
 * @author YZH
 * @version 2019年3月8日 下午2:12:52
 */
@Entity
@Table(name = "tl_api")
public class API {
    @Id
    private Integer id;
    private String title;
    private String c;
    private String a;
    private String param;
    private String content;
    private String listsorder;
    private Integer api_cate_id;
    private String api_cate_title;
    private Integer p_id;
    private String str;

    @Override
    public String toString() {
        return "API{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", c='" + c + '\'' +
                ", a='" + a + '\'' +
                ", param='" + param + '\'' +
                ", content='" + content + '\'' +
                ", listsorder='" + listsorder + '\'' +
                ", api_cate_id=" + api_cate_id +
                ", api_cate_title='" + api_cate_title + '\'' +
                ", p_id=" + p_id +
                ", str='" + str + '\'' +
                '}';
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setApi_cate_id(Integer api_cate_id) {
        this.api_cate_id = api_cate_id;
    }

    public void setApi_cate_title(String api_cate_title) {
        this.api_cate_title = api_cate_title;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getListsorder() {
        return listsorder;
    }

    public void setListsorder(String listsorder) {
        this.listsorder = listsorder;
    }

    public Integer getApi_cate_id() {
        return api_cate_id;
    }

    public String getApi_cate_title() {
        return api_cate_title;
    }

}
