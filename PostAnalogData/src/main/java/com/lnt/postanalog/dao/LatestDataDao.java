package com.lnt.postanalog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.postanalog.entity.LatestData;

import jakarta.transaction.Transactional;

@Repository
	@Transactional
	public interface LatestDataDao extends CrudRepository<LatestData, Integer>{

		@Query(value="select * from lntds_latest_data  where asset_id =:assetId and measurement_name=:lntdsMmeasurementName order by record_timestamp desc limit 1", nativeQuery = true)
		public LatestData findByAssetAndMeasurementName(@Param("assetId") String deviceId, @Param("lntdsMmeasurementName") String measurementName);
		@Query(value="select * from lntds_latest_data  where assetId =:assetId and lntdsMmeasurementName in :lntdsMmeasurementName ", nativeQuery = true)
		public List<LatestData> findByAssetAndMeasurementNameIn(@Param("assetId") String deviceId, @Param("lntdsMmeasurementName") List<String> measurementName);
		@Query(value="select * from lntds_latest_data  where assetId =:assetId ", nativeQuery = true)
		public List<LatestData> findByAssetIn(@Param("assetId") String deviceId);

	}


