package com.lnt.postanalog.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name= "lntds_analog_data")
@IdClass(AnalogId.class)
public class AnalogData {
	public AnalogData(String assetId, Timestamp recordTimestamp, Double measurementAnalogValue, Integer measId) {
		super();
		
		this.assetId = assetId;
		
		this.recordTimestamp = recordTimestamp;
		
		this.measurementAnalogValue = measurementAnalogValue;
		
		this.measId = measId;
	}
	public AnalogData(String assetId) {
		
	}

	

	@Id
	@Column(name="asset_id", length=64, nullable=false)
	private String assetId;

	

	@Id
	@Column(name="record_timestamp", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp recordTimestamp;
	
	@Column(name="measurement_analog_value")
	private Double measurementAnalogValue;

	
	@Id
	@Column(name="meas_id")
	private Integer measId;

	public Integer getMeasId() {
		return measId;
	}

	public void setMeasId(Integer measId) {
		this.measId = measId;
	}

	

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	

	public Timestamp getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Timestamp recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	
	public Double getMeasurementAnalogValue() {
		return measurementAnalogValue;
	}

	public void setMeasurementAnalogValue(Double measurementAnalogValue) {
		this.measurementAnalogValue = measurementAnalogValue;
	}

	
	public AnalogData() {
		super();
		// TODO Auto-generated constructor stub
	}

   
	
	
}



