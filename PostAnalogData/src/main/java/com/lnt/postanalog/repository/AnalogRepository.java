package com.lnt.postanalog.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lnt.postanalog.dto.AnalogDataDTO;
import com.lnt.postanalog.entity.AnalogData;


public interface AnalogRepository extends JpaRepository<AnalogData, Long>{



}