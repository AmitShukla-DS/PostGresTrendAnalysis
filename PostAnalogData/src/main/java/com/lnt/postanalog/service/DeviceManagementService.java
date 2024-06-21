package com.lnt.postanalog.service;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.postanalog.dao.AssetTypeMeasurementDao;
import com.lnt.postanalog.dao.AssetsDao;
import com.lnt.postanalog.dto.AssetListDtoIdOnly;
import com.lnt.postanalog.helper.AssetManagementServiceHelper;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

@Transactional
@Service
public class DeviceManagementService {
	
	
	static Logger logger = LogManager.getLogger(DeviceManagementService.class);
	@Autowired
	private AssetsDao assetsDao;
	@Autowired
	private AssetManagementServiceHelper assetManagementServiceHelper;
	@Autowired
	private AssetTypeMeasurementDao assetTypeMeasurementDao;
	
	@SuppressWarnings("unchecked")
	public List<AssetListDtoIdOnly> getHierarchicalAssetMeasurement(String assetClass) {
		List<AssetListDtoIdOnly> lntdsDeviceListDtoIdOnly = new ArrayList<AssetListDtoIdOnly>(0);
		List<Object[]> parentOnlyDevices = null;
		List<String> withChildDevices = null;
		List<Object[]> assetTypeMeasurements = (List) assetTypeMeasurementDao.findAllAssetTypeMeasurements();
		Map<Integer, List<Object[]>> measMap = assetManagementServiceHelper.getMeasMap(assetTypeMeasurements);
		if (assetClass.equalsIgnoreCase("Communication")) {
			parentOnlyDevices = assetsDao.findAssetidByParentAssetidNull();
			withChildDevices = assetsDao.findDistinctParentdeviceid();
		} else {
			parentOnlyDevices = assetsDao.findAssetidByParentAssetidEleNull();
			withChildDevices = assetsDao.findDistinctParentdeviceidEle();
		}

		lntdsDeviceListDtoIdOnly = assetManagementServiceHelper.recursiveFuncAssetMeas(parentOnlyDevices, withChildDevices,
				assetClass, measMap);
		return lntdsDeviceListDtoIdOnly;
	}
}
