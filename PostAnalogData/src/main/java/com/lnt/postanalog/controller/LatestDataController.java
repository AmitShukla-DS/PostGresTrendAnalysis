package com.lnt.postanalog.controller;
import com.lnt.postanalog.constants.CommonMessages;
import com.lnt.postanalog.constants.RESTUrls;
import com.lnt.postanalog.dto.AggregationDto;
import com.lnt.postanalog.dto.DeviceMeasDto;
import com.lnt.postanalog.model.ResultModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lnt.postanalog.service.LatestDataServiceImpl;

@RestController
@RequestMapping(value = RESTUrls.LATEST_DATA)
public class LatestDataController {


	static Logger logger = LogManager.getLogger(LatestDataController.class);

	@Autowired
	private LatestDataServiceImpl latestDataServiceImpl;
	@PostMapping(value = RESTUrls.GET_NEWLATEST_BY_DEVICE_AND_MEASUREMENT, produces = MediaType.APPLICATION_JSON_VALUE)
//	@SecureActionDefinition(code =AuditCodes.GET_LATEST_BY_DEVICE_AND_MEASUREMENT)
	public ResponseEntity<ResultModel> getLatestByDeviceAndMeasurement(@RequestBody DeviceMeasDto deviceMeasDto) {
		ResultModel resultModel = new ResultModel();
		logger.debug("Getting Latest Data by Device And Measurement From MemsAnalogData.......");
		try {
			List<AggregationDto> response = latestDataServiceImpl.getLatestByDeviceAndMeasurement(deviceMeasDto);
			resultModel.setData(response);
			resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			logger.debug(CommonMessages.GETTING_DATA_SUCCESSFULLY);
		} catch (Exception e) {
			resultModel.setMessage(CommonMessages.MSG_ERROR);
			logger.error(String.format("Error Occure %s", e));
			return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}

}

