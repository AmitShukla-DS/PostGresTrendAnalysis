package com.lnt.postanalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
@Table(name = "lntds_modbus_slave_associated_assets")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ModbusSlavesAssociatedAssets {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "slave_server_id", referencedColumnName = "modbus_slave_id")
	private ModbusSlaves slaveServerId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "assetGroups", "vendors", "assetTypes", "assetTypeMeasurement" })
	@JoinColumn(name = "asset_id")
	private Assets assets;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModbusSlaves getSlaveServerId() {
		return slaveServerId;
	}

	public void setSlaveServerId(ModbusSlaves slaveServerId) {
		this.slaveServerId = slaveServerId;
	}

	@JsonProperty
	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

}
