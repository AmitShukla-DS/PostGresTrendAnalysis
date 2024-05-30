package com.lnt.postanalog.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnalogId implements Serializable {

	private String assetId;
	
	private Timestamp recordTimestamp;
	
	private Integer measId;
	
	public AnalogId(String assetId, Timestamp recordTimestamp, Integer measId) {
		this.assetId = assetId;
		this.recordTimestamp = recordTimestamp;
		this.measId = measId;
	}
}
