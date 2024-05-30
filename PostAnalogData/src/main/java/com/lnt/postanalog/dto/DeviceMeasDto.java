package com.lnt.postanalog.dto;

import java.sql.Timestamp;
import java.util.List;

public class DeviceMeasDto {
	 private Timestamp startDate;

	    private Timestamp endDate;

	    private String deviceMeasList;

	    private List<AssetMeasList> assetMeasList;

	    private String meterId;

	    public Timestamp getStartDate() {
			return startDate;
		}

		public void setStartDate(Timestamp startDate) {
			this.startDate = startDate;
		}

		public Timestamp getEndDate() {
			return endDate;
		}

		public void setEndDate(Timestamp endDate) {
			this.endDate = endDate;
		}

		public String getDeviceMeasList() {
			return deviceMeasList;
		}

		public void setDeviceMeasList(String deviceMeasList) {
			this.deviceMeasList = deviceMeasList;
		}

		public List<AssetMeasList> getAssetMeasList() {
			return assetMeasList;
		}

		public void setAssetMeasList(List<AssetMeasList> assetMeasList) {
			this.assetMeasList = assetMeasList;
		}

		public String getMeterId() {
			return meterId;
		}

		public void setMeterId(String meterId) {
			this.meterId = meterId;
		}

		public String getAggregation() {
			return aggregation;
		}

		public void setAggregation(String aggregation) {
			this.aggregation = aggregation;
		}

		private String aggregation;
}
