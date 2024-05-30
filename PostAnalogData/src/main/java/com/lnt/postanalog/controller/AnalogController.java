package com.lnt.postanalog.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.postanalog.constants.CommonMessages;
import com.lnt.postanalog.constants.RESTUrls;
import com.lnt.postanalog.dto.AnalogDataDTO;
import com.lnt.postanalog.dto.DeviceMeasDto;
import com.lnt.postanalog.dto.TrendAnalysisResponseDto;
import com.lnt.postanalog.entity.AnalogData;
import com.lnt.postanalog.model.ResultModel;
import com.lnt.postanalog.repository.AnalogRepository;
import com.lnt.postanalog.service.AnalogService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/analogDataTest")
public class AnalogController {
	
	@Autowired
	private AnalogService analogService;
	
	@Autowired
	//private AnalogDataServiceImpl analogDataServiceImpl;
	static Logger logger = LogManager.getLogger(AnalogController.class);
	 @PostMapping("/entities")
	    public AnalogData createEntity(@RequestBody AnalogData analog) {
	        return analogService.save(analog);
	    }
	 
	 @GetMapping("/analogData")
	    public List<AnalogData> getAllAnalogData() {
	        return analogService.getAllAnalogData();
	    }
	 
	 
	 
	// @PostMapping(value = RESTUrls.GET_ANALOG_BY_DEVICE_AND_MEASUREMENT_AND_TIMESTAMP_NEW, produces = MediaType.APPLICATION_JSON_VALUE)
//		@SecureActionDefinition(code =AuditCodes.GET_ANALOG_BY_DEVICE_AND_MEASUREMENT_AND_TIMESTAMP)
	 @PostMapping("/Optimize")
		public ResponseEntity<ResultModel> getAnalogByDeviceAndMeasurementAndTimestampNew(
				@RequestBody DeviceMeasDto deviceMeasDto,@RequestHeader("database") String database) {
			ResultModel resultModel = new ResultModel();
			logger.info("Getting Data From MemsAnalogData.......");
			try {
				logger.info("Getting Data From AnalogData.......");
				System.out.println(deviceMeasDto.getStartDate());
				System.out.println(deviceMeasDto.getEndDate());
				List<TrendAnalysisResponseDto> response = analogService
						.getAnalogByDeviceAndMeasurementAndTimestampNew(deviceMeasDto,database);
				logger.info("Getting Data From Device Measurement.......");
				resultModel.setData(response);
				logger.info("Getting Data From Device Measurement......."+response);
				resultModel.setMessage(CommonMessages.MSG_SUCCESS);
			//	logger.debug(CommonMessages.GETTING_DATA_SUCCESSFULLY);
			} catch (Exception e) {
				resultModel.setMessage(CommonMessages.MSG_ERROR);
				//logger.error(String.format("Error Occure %s", e));
				return new ResponseEntity<>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return new ResponseEntity<>(resultModel, HttpStatus.OK);
		}
	}
	 
	
	
	
	