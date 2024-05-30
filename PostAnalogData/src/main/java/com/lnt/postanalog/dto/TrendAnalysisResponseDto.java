package com.lnt.postanalog.dto;

public class TrendAnalysisResponseDto {
	 private String deviceId;

	    private String measurementName;

	    public Object valuesDto;


	    public String getDeviceId() {
	        return deviceId;
	    }

	    public void setDeviceId(String deviceId) {
	        this.deviceId = deviceId;
	    }

	    public String getMeasurementName() {
	        return measurementName;
	    }

	    public void setMeasurementName(String measurementName) {
	        this.measurementName = measurementName;
	    }

	    public Object getValuesDto() {
	        return valuesDto;
	    }

	    public void setValuesDto(Object valuesDto) {
	        this.valuesDto = valuesDto;
	    }

	}

