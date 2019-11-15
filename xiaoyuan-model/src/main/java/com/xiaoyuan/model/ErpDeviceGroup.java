package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备组类？这个还真不怎么注意到
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:40:11
 *
 *          调用视图没用到？
 * @author YZH
 * @version 2019年3月8日 下午4:23:48
 *
 */
@Entity
@Table(name = "tl_erp_device_group")
public class ErpDeviceGroup {
	@Id
	private Integer id;
	private String groupname;
	private Integer SeqID;
	private String deviceId;
	private String deviceType;
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getSeqID() {
		return SeqID;
	}

	public void setSeqID(Integer seqID) {
		SeqID = seqID;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
