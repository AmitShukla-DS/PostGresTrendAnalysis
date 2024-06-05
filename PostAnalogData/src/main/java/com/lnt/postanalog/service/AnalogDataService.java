package com.lnt.postanalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.postanalog.entity.AnalogData;
import com.lnt.postanalog.repository.AnalogDataRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class AnalogDataService {

	@Autowired
    private AnalogDataRepository analogDataRepository;
	@PersistenceContext
	private EntityManager entityManager;

    public List<AnalogData> getAllAnalogData() {
        return analogDataRepository.findAll();
    }

  
       
        
        
        public List<AnalogData> getAnalogByAssetId(String userId) {
        	Query analogquery = entityManager.createNativeQuery(
        	"select * from lntds_Analog_Data where asset_id=?1",AnalogData.class);
        	analogquery.setParameter(1, userId);
        	List<AnalogData> analogdata = analogquery.getResultList();
			return analogdata;
    }
}
