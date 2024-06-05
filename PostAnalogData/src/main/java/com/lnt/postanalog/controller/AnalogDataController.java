package com.lnt.postanalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.postanalog.entity.AnalogData;
import com.lnt.postanalog.service.AnalogDataService;
@RestController
@RequestMapping("/api/data")
public class AnalogDataController {

	@Autowired
    private AnalogDataService analogDataService;

    @GetMapping("/")
    public ResponseEntity<List<AnalogData>> getAllAnalogData() {
        List<AnalogData> data = analogDataService.getAllAnalogData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
	
    @GetMapping("/{assetId}")
    public ResponseEntity<List<AnalogData>> getAnalogByAssetId(@PathVariable String  assetId) {
    	 List<AnalogData>  data = (List<AnalogData>) analogDataService.getAnalogByAssetId(assetId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
