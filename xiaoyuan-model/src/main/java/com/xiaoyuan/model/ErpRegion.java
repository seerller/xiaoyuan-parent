package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地址类，对应地址表
 * 
 * 这个表放着中国所有地址，包括省份，城市，区县，街道，，
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:43:05
 *
 * @author YZH
 * @version 2019年3月8日 下午3:48:17
 *
 */
@Entity
@Table(name = "tl_erp_region")
public class ErpRegion {
	@Id
	private Integer id;
	private Integer parent_id;

	private String title;
	private String hushu;
	private String wuye;
	private Integer region_type;
	private Integer agency_id;
	private String location_x;
	private String location_y;
	private String pinyin;
	private String img;
	private String address;
	private String content;
	private String price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHushu() {
		return hushu;
	}

	public void setHushu(String hushu) {
		this.hushu = hushu;
	}

	public String getWuye() {
		return wuye;
	}

	public void setWuye(String wuye) {
		this.wuye = wuye;
	}

	public Integer getRegion_type() {
		return region_type;
	}

	public void setRegion_type(Integer region_type) {
		this.region_type = region_type;
	}

	public Integer getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(Integer agency_id) {
		this.agency_id = agency_id;
	}

	public String getLocation_x() {
		return location_x;
	}

	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}

	public String getLocation_y() {
		return location_y;
	}

	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
