package com.lnt.postanalog.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class DataPoints {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private Timestamp time;
    public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public DataPoints(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public DataPoints() {
		super();
		// TODO Auto-generated constructor stub
	}
}
