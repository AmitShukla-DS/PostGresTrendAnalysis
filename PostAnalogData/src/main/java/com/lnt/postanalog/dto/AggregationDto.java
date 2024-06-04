package com.lnt.postanalog.dto;

import java.sql.Timestamp;

public class AggregationDto {
	 private Integer id;

	    private Timestamp recordTimestamp;

	    private String recordTime;

	    private Double analogValue;

	    private String uom;

	    private String deviceId;

	    private String measurementName;
	    
	    private String displayName;
	    
	    private String assetTagName;
	    
	    private String type;
	    
	    public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Timestamp getRecordTimestamp() {
	        return recordTimestamp;
	    }

	    public void setRecordTimestamp(Timestamp recordTimestamp) {
	        this.recordTimestamp = recordTimestamp;
	    }

	    public String getRecordTime() {
	        return recordTime;
	    }

	    public void setRecordTime(String recordTime) {
	        this.recordTime = recordTime;
	    }

	    public Double getAnalogValue() {
	        return analogValue;
	    }

	    public void setAnalogValue(Double analogValue) {
	        this.analogValue = analogValue;
	    }

	    public String getUom() {
	        return uom;
	    }

	    public void setUom(String uom) {
	        this.uom = uom;
	    }

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

		public String getAssetTagName() {
			return assetTagName;
		}

		public void setAssetTagName(String assetTagName) {
			this.assetTagName = assetTagName;
		}

	    @Override
	    public String toString() {
	        return "AggregationDto{" +
	                "id=" + id +
	                ", recordTimestamp=" + recordTimestamp +
	                ", recordTime='" + recordTime + '\'' +
	                ", analogValue=" + analogValue +
	                ", uom='" + uom + '\'' +
	                ", deviceId='" + deviceId + '\'' +
	                ", measurementName='" + measurementName + '\'' +
	                ", displayName='" + displayName + '\'' +
	                ", assetTagName='" + assetTagName + '\'' +
	                ", type='" + type + '\'' +
	                '}';
	    }
	}


