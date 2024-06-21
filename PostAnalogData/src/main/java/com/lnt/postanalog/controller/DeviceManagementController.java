package com.lnt.postanalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.postanalog.config.SecureActionDefinition;
import com.lnt.postanalog.constants.AuditCodes;
import com.lnt.postanalog.constants.CommonMessages;
import com.lnt.postanalog.constants.RESTUrls;
import com.lnt.postanalog.dto.AssetListDtoIdOnly;
import com.lnt.postanalog.dto.ResponseObject;
import com.lnt.postanalog.service.DeviceManagementService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping(value = RESTUrls.DEVICE_MANAGEMENT)
public class DeviceManagementController {
	static Logger logger = LogManager.getLogger(DeviceManagementController.class);
	@Autowired
	private DeviceManagementService deviceManagementService;

	
	
	@GetMapping(value = RESTUrls.GET_DEVICES_AND_MEASUREMENTS_BY_HIERARCHY, produces = MediaType.APPLICATION_JSON_VALUE)
	@SecureActionDefinition(code = AuditCodes.GET_DEVICES_AND_MEASUREMENTS_BY_HIERARCHY)
	public ResponseEntity<ResponseObject> getHierarchicalDevicesAndMeasurements(@RequestParam("assetClass") String assetClass) {
		ResponseObject response = new ResponseObject();
		logger.debug("Getting Assets Measurements By hierarchy in DeviceManagement.......!");
		List<AssetListDtoIdOnly> assetListDtoIdOnly = deviceManagementService.getHierarchicalAssetMeasurement(assetClass);
		//List<AssetListDtoIdOnly> assetListDtoIdOnly = deviceManagementService.getHierarchicalAssetMeasurement(assetClass);
		if (!assetListDtoIdOnly.isEmpty()) {
			response.setData(assetListDtoIdOnly);
			response.setMessage(CommonMessages.SUCCESS);
			logger.debug("Getting  Devices Successfully......!");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
