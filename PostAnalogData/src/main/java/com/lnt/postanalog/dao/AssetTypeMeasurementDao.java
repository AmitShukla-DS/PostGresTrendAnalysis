package com.lnt.postanalog.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lnt.postanalog.entity.AssetTypeMeasurement;
public interface AssetTypeMeasurementDao  extends CrudRepository<AssetTypeMeasurement, Integer>{

	
	 @Query("Select m from AssetTypeMeasurement m where m.assetTypes.assetTypeSeq=:assetTypeSeq")
	    public List<AssetTypeMeasurement> findByAssetTypeSeq(@Param("assetTypeSeq") Integer assetTypeSeq);

	    @Query("Select m from AssetTypeMeasurement m where  m.assetTypes.assetTypeSeq=:assetTypeSeq And m.assetMeasurementName=:assetMeasurementName And m.assetTagName=:assetTagName")
	    public AssetTypeMeasurement findByAssetTypeSeqAndAssetMeasurementNameAndAssetTagName(Integer assetTypeSeq,
	                                                                                         String assetMeasurementName, String assetTagName);

	    @Query("Select m from AssetTypeMeasurement m where  m.assetTypes.assetTypeSeq=:assetTypeSeq And m.assetMeasurementName=:assetMeasurementName ")
	    public List<AssetTypeMeasurement> findByAssetTypeSeqAndMeasurementName(Integer assetTypeSeq, String assetMeasurementName);

	    public AssetTypeMeasurement findByAssetMeasurementName(String assetMeasurementName);

	    public List<AssetTypeMeasurement> findAllById(Integer id);

	    //@Modifying
	    //@Query("delete m from AssetTypeMeasurement m where m.id in (:ids)")
	    //public void deleteAllById(@Param("ids") List<Integer> ids);

	    @Modifying
	    public void deleteByIdIn(List<Integer> ids);
	    
	    @Query("Select m.assetTypes.assetTypeSeq, m.assetMeasurementName, m.displayMeasurementName,m.uom from AssetTypeMeasurement m")
	    public List<Object[]> findAllAssetTypeMeasurements();
}
