package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日志类？基本没接触到，不过好像有日志文件？
 * 
 * @author YZH
 * @version 2019年3月8日 下午3:22:08
 *
 */
@Entity
@Table(name = "tl_erp_logger")
public class ErpLogger {
	@Id
	private Integer id;
	private String car_sn;
	private String card_rfid;
	private String device_id;
	private Double weight;
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCar_sn() {
		return car_sn;
	}

	public void setCar_sn(String car_sn) {
		this.car_sn = car_sn;
	}

	public String getCard_rfid() {
		return card_rfid;
	}

	public void setCard_rfid(String card_rfid) {
		this.card_rfid = card_rfid;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
