package com.lnt.postanalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lntds_asset_measurement_extended")
public class AssetMeasurementExtended {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meas_id", nullable = false)
	private AssetTypeMeasurement assetTypeMeasurement;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "interprete_map")
	private String interpreteMap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public AssetTypeMeasurement getAssetTypeMeasurement() {
		return assetTypeMeasurement;
	}

	@JsonProperty
	public void setAssetTypeMeasurement(AssetTypeMeasurement assetTypeMeasurement) {
		this.assetTypeMeasurement = assetTypeMeasurement;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getInterpreteMap() {
		return interpreteMap;
	}

	public void setInterpreteMap(String interpreteMap) {
		this.interpreteMap = interpreteMap;
	}


}
