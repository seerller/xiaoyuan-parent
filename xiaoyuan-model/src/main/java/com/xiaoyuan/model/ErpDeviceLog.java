package com.xiaoyuan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备日志类？
 * 
 * @author YZH
 * @version 2019年3月8日 下午2:40:41
 *
 */
@Entity
@Table(name = "tl_erp_device_log")
public class ErpDeviceLog {
	@Id
	private Integer id;
	private String RunMode;
	private Double CameraNum;
	private String CameraIP;
	private String DeviceID;
	private String DeviceIP;
	private String DeviceName;
	private String DeviceValue;
	private String SeqNo;
	private String MSG;
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCameraNum() {
		return CameraNum;
	}

	public void setCameraNum(Double cameraNum) {
		CameraNum = cameraNum;
	}

	public String getCameraIP() {
		return CameraIP;
	}

	public void setCameraIP(String cameraIP) {
		CameraIP = cameraIP;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public String getDeviceIP() {
		return DeviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		DeviceIP = deviceIP;
	}

	public String getDeviceName() {
		return DeviceName;
	}

	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}

	public String getSeqNo() {
		return SeqNo;
	}

	public void setSeqNo(String seqNo) {
		SeqNo = seqNo;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getDeviceValue() {
		return DeviceValue;
	}

	public void setDeviceValue(String deviceValue) {
		DeviceValue = deviceValue;
	}

	public String getRunMode() {
		return RunMode;
	}

	public void setRunMode(String runMode) {
		RunMode = runMode;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
