package com.lnt.postanalog.dto;

import java.io.Serializable;
import java.util.List;

public class AnalogDataDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String assetId;

    private String lntdMmeasurementName;

    private String inputMmeasurementName;

    private Double measurementAnalogValue;

    //private Timestamp recordTimestamp;
    private String recordTimestamp;
    //private Timestamp transmitTimestamp;
    private String transmitTimestamp;
    private String unitOfMeasurement;

    private String measurementInputValue ;
     private String measId;

    public String getMeasId() {
		return measId;
	}

	public void setMeasId(String measId) {
		this.measId = measId;
	}

	public AnalogDataDTO() {
    }

   

    @Override
	public String toString() {
		return "AnalogDataDTO [id=" + id + ", assetId=" + assetId + ", lntdMmeasurementName=" + lntdMmeasurementName
				+ ", inputMmeasurementName=" + inputMmeasurementName + ", measurementAnalogValue="
				+ measurementAnalogValue + ", recordTimestamp=" + recordTimestamp + ", transmitTimestamp="
				+ transmitTimestamp + ", unitOfMeasurement=" + unitOfMeasurement + ", measurementInputValue="
				+ measurementInputValue + ", measId=" + measId + "]";
	}

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

    public String getLntdMmeasurementName() {
        return lntdMmeasurementName;
    }

    public void setLntdMmeasurementName(String lntdMmeasurementName) {
        this.lntdMmeasurementName = lntdMmeasurementName;
    }

    public String getInputMmeasurementName() {
        return inputMmeasurementName;
    }

    public void setInputMmeasurementName(String inputMmeasurementName) {
        this.inputMmeasurementName = inputMmeasurementName;
    }

    public Double getMeasurementAnalogValue() {
        return measurementAnalogValue;
    }

    public void setMeasurementAnalogValue(Double measurementAnalogValue) {
        this.measurementAnalogValue = measurementAnalogValue;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

    public String getTransmitTimestamp() {
        return transmitTimestamp;
    }

    public void setTransmitTimestamp(String transmitTimestamp) {
        this.transmitTimestamp = transmitTimestamp;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getMeasurementInputValue() {
        return measurementInputValue;
    }

    public void setMeasurementInputValue(String measurementInputValue) {
        this.measurementInputValue = measurementInputValue;
    }

	public void add(List<AnalogDataDTO> collection) {
		// TODO Auto-generated method stub
		collection.add(this);
	}
}


