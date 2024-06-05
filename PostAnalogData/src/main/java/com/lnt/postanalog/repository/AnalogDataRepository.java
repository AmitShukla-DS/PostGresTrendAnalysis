package com.lnt.postanalog.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lnt.postanalog.entity.AnalogData;

@Repository
public interface AnalogDataRepository extends JpaRepository<AnalogData, String> {
	
	AnalogData findByAssetId(String assetId);
	AnalogData findByAssetIdAndRecordTimestampAndMeasId(String assetId, Timestamp recordTimestamp, Integer measId);

}
