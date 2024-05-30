package com.lnt.postanalog.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lnt.postanalog.entity.AnalogData;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AnalogDataDao extends CrudRepository<AnalogData, String> {

	   
	    
	}


