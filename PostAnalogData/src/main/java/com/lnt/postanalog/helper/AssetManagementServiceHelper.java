package com.lnt.postanalog.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.postanalog.dao.AssetsDao;
import com.lnt.postanalog.dto.AssetListDtoIdOnly;
import com.lnt.postanalog.entity.Assets;

import jakarta.transaction.Transactional;

@Service
	@Transactional
	public class AssetManagementServiceHelper {

		@Autowired
		private AssetsDao assetsDao;

		public List<AssetListDtoIdOnly> recursiveFunc(List<Object[]> parentAssets, List<String> withChildAssets,
				String assetClass) {
			List<AssetListDtoIdOnly> list = new ArrayList<AssetListDtoIdOnly>(0);

			for (Object[] parentAsset : parentAssets) {
				AssetListDtoIdOnly obj = new AssetListDtoIdOnly();
				obj.setId((String) parentAsset[0]);
				obj.setName((String) parentAsset[1]);
				obj.setAssetTypeSeq(Integer.parseInt(parentAsset[2].toString()));
				obj.setDisplayField(obj.getId() + " - " + obj.getName());
				if (withChildAssets.contains((String) parentAsset[0])) {
					Assets assets = new Assets();
					assets.setAssetId((String) parentAsset[0]);
					List<Object[]> assetsList = null;
					if (assetClass.equalsIgnoreCase("Communication")) {
						assetsList = assetsDao.findAssetIdByParentAssetId((String) parentAsset[0]);
					} else {
						assetsList = assetsDao.findAssetIdByParentAssetIdEle((String) parentAsset[0]);
					}
					obj.setChildren(recursiveFunc(assetsList, withChildAssets, assetClass));
					list.add(obj);
				} else {
					obj.setChildren(new ArrayList<AssetListDtoIdOnly>(0));
					list.add(obj);
				}
			}
			return list;
		}

		public List<AssetListDtoIdOnly> recursiveFuncAssetMeas(List<Object[]> parentAssets, List<String> withChildAssets,
				String assetClass, Map<Integer, List<Object[]>> measMap) {
			List<AssetListDtoIdOnly> list = new ArrayList<AssetListDtoIdOnly>(0);

			for (Object[] parentAsset : parentAssets) {
				AssetListDtoIdOnly obj = new AssetListDtoIdOnly();
				obj.setId((String) parentAsset[0]);
				obj.setName((String) parentAsset[1]);
				obj.setAssetTypeSeq(Integer.parseInt(parentAsset[2].toString()));
				obj.setDisplayField(obj.getId());
				obj.setType("Asset");
				List<AssetListDtoIdOnly> children = new ArrayList<AssetListDtoIdOnly>(0);
				if (withChildAssets.contains((String) parentAsset[0])) {
					Assets assets = new Assets();
					assets.setAssetId((String) parentAsset[0]);
					List<Object[]> assetsList = null;
					if (assetClass.equalsIgnoreCase("Communication")) {
						assetsList = assetsDao.findAssetIdByParentAssetId((String) parentAsset[0]);
					} else {
						assetsList = assetsDao.findAssetIdByParentAssetIdEle((String) parentAsset[0]);
					}
					children = recursiveFuncAssetMeas(assetsList, withChildAssets, assetClass, measMap);
				}
				List<Object[]> measurements = measMap.get(obj.getAssetTypeSeq());
				if(measurements!=null) {
					for(Object[] measurement : measurements) {
						AssetListDtoIdOnly obj1 = new AssetListDtoIdOnly();
						obj1.setId((String) parentAsset[0] + "." + (String) measurement[1]);
						obj1.setName((String) measurement[1]);
						if(measurement[2]!=null) {
							obj1.setDisplayField((String) measurement[2]);
						}
						else obj1.setDisplayField((String) measurement[1]);
						obj1.setUom((String) measurement[3]);
						obj1.setAssetTypeSeq((Integer) measurement[0]);
						obj1.setType("Measurement");
						obj1.setChildren(new ArrayList<AssetListDtoIdOnly>(0));
						children.add(obj1);
					}
				}
				obj.setChildren(children);
				list.add(obj);
			}
			return list;
		}

		public Map<Integer, List<Object[]>> getMeasMap(List<Object[]> assetTypeMeasurements) {
			Map<Integer, List<Object[]>> measMap = new HashMap<Integer, List<Object[]>>(0);
			for(Object[] assetMeasurement : assetTypeMeasurements) {
				List<Object[]> childList;
				if(measMap.containsKey((Integer) assetMeasurement[0])) {
					childList = measMap.get((Integer) assetMeasurement[0]);
				}
				else {
					childList = new ArrayList<Object[]>(0);
				}
				childList.add(assetMeasurement);
				measMap.put((Integer) assetMeasurement[0], childList);
			}
			return measMap;
		}
	}


