package com.lnt.postanalog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.postanalog.entity.AssetTypes;
import com.lnt.postanalog.entity.Assets;
import com.lnt.postanalog.entity.Vendors;

/*import com.lnt.hmi.ppc.entities.AssetTypes;
import com.lnt.hmi.ppc.entities.Assets;
import com.lnt.hmi.ppc.entities.Vendors;*/

	
	



	@Repository
	public interface AssetsDao extends CrudRepository<Assets, String> {

		@Query(value = "Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where m.parentCommAssetId=:parentCommAssetId and m.assetClass='Communication' and m.assetTypes.assetTypeName not in('Bus')")
		public List<Object[]> findAssetIdByParentAssetId(@Param("parentCommAssetId") String parentAssetId);

		@Query(value = "Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where m.parentElecAssetId=:parentElecAssetId and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
		public List<Object[]> findAssetIdByParentAssetIdEle(@Param("parentElecAssetId") String parentAssetId);

		public Assets findByAssetName(String assetName);

		public List<Assets> findByVendors(Vendors vendors);

		@Query("select m.assetId, m.assetName from Assets m")
		public List<Object[]> findAllAssetIdAndName();

		@Query("select m from Assets m where m.assetTypes.assetTypeName=:assetTypeName")
		public List<Assets> findByAssetTypes(@Param("assetTypeName") String assetTypeName);

		@Query("select m.assetId from Assets m where m.assetTypes=:assetTypes")
		public List<String> findAssetsByAssetTypes(@Param("assetTypes") AssetTypes assetTypes);

		public Assets findByAssetId(String assetId);

		@Query("select m from Assets m where m.assetTypes.assetTypeName='Virtual Device'")
		public List<Assets> findVirtualAssets();

		@Query("select m.assetId, m.assetName from Assets m where m.parentCommAssetId is null and m.assetTypes.assetTypeName='Virtual Device'")
		public List<Object[]> findVirtualAssetIdByParentAssetIdNull();

		@Query("select distinct m.parentCommAssetId from Assets m where m.parentCommAssetId is not null and m.assetTypes.assetTypeName='Virtual Device'")
		public List<String> findDistinctVirtualParentAssetId();

		@Query("Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where (m.parentCommAssetId is null or m.parentCommAssetId='') and m.assetClass='Communication' and m.assetTypes.assetTypeName not in('Bus')")
		public List<Object[]> findAssetidByParentAssetidNull();

		@Query("Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where (m.parentElecAssetId is null or m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
		public List<Object[]> findAssetidByParentAssetidEleNull();
		
		@Query("Select m.assetId, m.assetName, m.assetTypes.assetTypeSeq From Assets m where (m.parentElecAssetId is null or m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')  and m.assetId= ?1")
		public List<Object[]> findSingleAssetidByParentAssetidEleNull(String assetId);

		@Query("Select distinct m.parentCommAssetId From Assets m where (m.parentCommAssetId is not null and not m.parentCommAssetId='') and m.assetTypes.assetTypeName not in('Bus')")
		public List<String> findDistinctParentdeviceid();

		@Query("Select distinct m.parentElecAssetId From Assets m where (m.parentElecAssetId is not null and not m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus')")
		public List<String> findDistinctParentdeviceidEle();
		
		@Query("Select distinct m.parentElecAssetId From Assets m where (m.parentElecAssetId is not null and not m.parentElecAssetId='') and m.assetClass='Electrical' and m.assetTypes.assetTypeName not in('Bus') and m.parentElecAssetId= ?1")
		public List<String> findSingleDistinctParentdeviceidEle(String assetId);

		public List<Assets> findByAssetTypes(AssetTypes assetTypes);

		@Query("select m.assetId from Assets m where m.assetTypes=:assetTypes")
		public List<String> findAssetIdByAssetTypes(AssetTypes assetTypes);

		@Query("select m.assetId from Assets m where m.assetId=:assetId")
		public String findAssetId(String assetId);

		@Query("select m.assetId from Assets m")
		public List<String> findAssetId();

		@Query(value="select count(*) from cpms.lntds_assets where asset_type_seq=?1",nativeQuery=true)
		public Integer getCountOfEvConnector(Integer seq);

		@Query(value="select asset_id from cpms.lntds_assets where parent_elec_asset_id=:parentElecAssetId",nativeQuery=true)
		public List<String> getEvConnectorsByParentAssetId(@Param("parentElecAssetId") String parentElecAssetId);

		@Query(value = "SELECT distinct (group_seq) FROM cpms.lntds_assets where asset_name=:assetId", nativeQuery = true)
		public int getAssetId(@Param("assetId")String assetId);

		@Query(value = "Select p from Assets p where p.assetGroups.groupSeq=:groupSeq and p.assetTypes.assetTypeName='EV Connector'")
		public List<Assets> findByGroupAndAssetType(@Param("groupSeq")Integer groupSeq);

		@Query(value = "select asset_type_seq from lntds_assets where asset_id=:parentElecAssetId", nativeQuery = true)
		Integer getAssetTypeSeqByAssetId(@Param("parentElecAssetId") String parentElecAssetId);
		
		
		@Query(value = "SELECT count(asset_id) FROM lntds_assets where group_seq=:groupSeq ", nativeQuery = true)
		public Integer findByAssetGroups(@Param("groupSeq") Integer groupSeq);
		
		@Modifying
		@Query(value = "delete from lntds_assets where asset_id=:id", nativeQuery = true)
		public void deleteAssetById(String id);
		
	}


