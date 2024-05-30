package com.lnt.postanalog.dto;

import java.util.List;

public class AssetMeasList {
	private String deviceId;
    private List<String> measurementName;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public List<String> getMeasurementName() {
		return measurementName;
	}
	public void setMeasurementName(List<String> measurementName) {
		this.measurementName = measurementName;
	}

}
