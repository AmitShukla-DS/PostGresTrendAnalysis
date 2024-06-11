package com.lnt.postanalog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.postanalog.entity.DataPoints;
import com.lnt.postanalog.repository.DataPointRepository;
@RestController
@RequestMapping("/datapoints")
public class DataPointController {
	
	@Autowired
    private DataPointRepository dataPointRepository;

    @GetMapping
    public List<DataPoints> getAllDataPoints() {
        return dataPointRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataPoints> getDataPointById(@PathVariable Long id) {
        Optional<DataPoints> dataPointOptional = dataPointRepository.findById(id);
        return dataPointOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
