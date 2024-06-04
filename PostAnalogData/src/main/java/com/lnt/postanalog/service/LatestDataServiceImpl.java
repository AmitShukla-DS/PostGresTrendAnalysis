package com.lnt.postanalog.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnt.postanalog.dao.LatestDataDao;
import com.lnt.postanalog.dto.AggregationDto;
import com.lnt.postanalog.dto.DeviceMeasDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
@Service
public class LatestDataServiceImpl {
	static Logger logger = LogManager.getLogger(LatestDataServiceImpl.class);

	@Autowired
	private LatestDataDao latestDataDao;

	@PersistenceContext
	private EntityManager em;
	public List<AggregationDto> getLatestByDeviceAndMeasurement(DeviceMeasDto deviceMeasDto) throws ParseException {

		List<AggregationDto> analogList = new ArrayList<AggregationDto>(0);
		List<Object[]> objectArray = new ArrayList<Object[]>(0);
		List<Object[]> displayObjectArray = new ArrayList<Object[]>(0);
		String str = deviceMeasDto.getDeviceMeasList();
		String[] strArray = str.split(",");
		ArrayList<String> deviceArray = new ArrayList<String>();
		ArrayList<String> measeArray = new ArrayList<String>();
		for (int i = 0; i < strArray.length; i++) {
			String s = strArray[i];
			s = s.substring(s.indexOf("'") + 1);
			if (!s.isEmpty()) {

				s = s.substring(0, s.indexOf("'"));
				if (i % 2 == 0 || i == 0) {
					deviceArray.add(s);

				} else {
					measeArray.add(s);

				}
			}
		}
		if(!deviceArray.isEmpty()) {

			String sql = null;
			sql = "SELECT a.id, a.asset_id, a.record_timestamp, a.measurement_name, a.measurement_value, a.unit_of_measurement FROM lntds_latest_data a \r\n"
					+ "WHERE (";
			for (int i = 0; i < deviceArray.size(); i++) {
				sql = sql + "(a.asset_id = '" + deviceArray.get(i) + "' AND a.measurement_name = '" + measeArray.get(i)
						+ "')";
				if (i + 1 < deviceArray.size()) {
					sql = sql + " OR ";
				}
			}
			sql = sql + ");";

			Query query = em.createNativeQuery(sql);
			objectArray = query.getResultList();
			
			String queryForGetDisplaynameAndType = "select b.asset_id, a.measurement_name, a.display_measurement_name, a.measurement_type, a.asset_tag_name from lntds_asset_types_measurements a \r\n"
					+ "JOIN lntds_assets b ON b.asset_type_seq = a.asset_type_seq and (";
			for (int i = 0; i < deviceArray.size(); i++) {
				queryForGetDisplaynameAndType = queryForGetDisplaynameAndType + "(b.asset_id = '" + deviceArray.get(i) + "' AND a.measurement_name = '" + measeArray.get(i)
						+ "')";
				if (i + 1 < deviceArray.size()) {
					queryForGetDisplaynameAndType = queryForGetDisplaynameAndType + " OR ";
				}
			}
			queryForGetDisplaynameAndType = queryForGetDisplaynameAndType + ");";
			
			Query queryy = em.createNativeQuery(queryForGetDisplaynameAndType);
			displayObjectArray = queryy.getResultList();
			
			for (Object[] obj : objectArray) {
				AggregationDto analogDataDto = new AggregationDto();
				analogDataDto.setId(Integer.parseInt(obj[0].toString()));
				analogDataDto.setDeviceId(obj[1].toString());
				analogDataDto.setRecordTimestamp((Timestamp) obj[2]);
				analogDataDto.setMeasurementName(obj[3].toString());
				analogDataDto.setAnalogValue(Double.parseDouble(obj[4].toString()));
				analogDataDto.setUom(obj[5] == null ? null : obj[5].toString());

				for (Object[] ob : displayObjectArray) {
					if(ob[0].toString().equalsIgnoreCase(analogDataDto.getDeviceId()) && ob[1].toString().equalsIgnoreCase(analogDataDto.getMeasurementName())) {
						analogDataDto.setDisplayName(ob[2] == null ? "" : ob[2].toString());
						analogDataDto.setType(ob[3].toString());
						analogDataDto.setAssetTagName(ob[4].toString());
					}
				}
				analogList.add(analogDataDto);
			}
		}
		return analogList;
	}
}
