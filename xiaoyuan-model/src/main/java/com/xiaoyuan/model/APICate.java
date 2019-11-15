package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 大菜单类，对应大菜单表
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:13:21
 *
 */
@Entity
@Table(name = "tl_api_cate")
public class APICate {
	@Id
	private Integer id;
	private String title;
	private String c;
	private String listsorder;
	private String app;
	private String condition;

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

	public String getListsorder() {
		return listsorder;
	}

	public void setListsorder(String listsorder) {
		this.listsorder = listsorder;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "APICate [id=" + id + ", title=" + title + ", c=" + c + ", listsorder=" + listsorder + ", app=" + app
				+ "]";
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
