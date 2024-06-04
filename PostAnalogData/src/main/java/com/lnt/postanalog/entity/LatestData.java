package com.lnt.postanalog.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
	@Table(name = "lntds_latest_data")
	public class LatestData {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "asset_id")
	    private String assetId;

	    @Column(name = "measurement_name")
	    private String lntdsMeasurementName;

	    @Column(name = "input_measurement_name")
	    private String inputMeasurementName;

	    @Column(name = "measurement_value")
	    private Double measurementValue;

	    @Column(name = "record_timestamp")
	    private Timestamp recordTimestamp;

	    @Column(name = "unit_of_measurement")
	    private String unitOfMeasurement;
	    
	    @Column(name = "measurement_input_value")
	    private Double measurementInputValue ;

		@Column(name = "measurement_type")
	    private String measurementType ;

	    @Transient
	    private String displayName;

	    @Transient
	    private String assetTagName;

	    @Transient
	    private String type;

	    @Transient
	    private Integer measurementId;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getAssetId() {
	        return assetId;
	    }

	    public void setAssetId(String assetId) {
	        this.assetId = assetId;
	    }

	    public String getLntdsMeasurementName() {
	        return lntdsMeasurementName;
	    }

	    public void setLntdsMeasurementName(String lntdMeasurementName) {
	        this.lntdsMeasurementName = lntdMeasurementName;
	    }

	    public String getInputMeasurementName() {
	        return inputMeasurementName;
	    }

	    public void setInputMeasurementName(String inputMeasurementName) {
	        this.inputMeasurementName = inputMeasurementName;
	    }

	    public Double getMeasurementValue() {
	        return measurementValue;
	    }

	    public void setMeasurementValue(Double measurementValue) {
	        this.measurementValue = measurementValue;
	    }

	    public Timestamp getRecordTimestamp() {
	        return recordTimestamp;
	    }

	    public void setRecordTimestamp(Timestamp recordTimestamp) {
	        this.recordTimestamp = recordTimestamp;
	    }

	    public String getUnitOfMeasurement() {
	        return unitOfMeasurement;
	    }

	    public void setUnitOfMeasurement(String unitOfMeasurement) {
	        this.unitOfMeasurement = unitOfMeasurement;
	    }

	    public Double getMeasurementInputValue() {
	        return measurementInputValue;
	    }

	    public void setMeasurementInputValue(Double measurementInputValue) {
	        this.measurementInputValue = measurementInputValue;
	    }

		public String getMeasurementType() {
			return measurementType;
		}

		public void setMeasurementType(String measurementType) {
			this.measurementType = measurementType;
		}

	    public String getDisplayName() {
	        return displayName;
	    }

	    public void setDisplayName(String displayName) {
	        this.displayName = displayName;
	    }
	    public Integer getMeasurementId() {
	        return measurementId;
	    }

	    public void setMeasurementId(Integer measurementId) {
	        this.measurementId = measurementId;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getAssetTagName() {
	        return assetTagName;
	    }

	    public void setAssetTagName(String assetTagName) {
	        this.assetTagName = assetTagName;
	    }

	}


