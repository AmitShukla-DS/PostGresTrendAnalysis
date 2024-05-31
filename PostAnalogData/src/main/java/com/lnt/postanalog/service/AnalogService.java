package com.lnt.postanalog.service;

import java.sql.Timestamp;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnt.postanalog.datasource.DataSourceContextHolder;
import com.lnt.postanalog.dto.AnalogDataDTO;
import com.lnt.postanalog.dto.AssetMeasList;
import com.lnt.postanalog.dto.DeviceMeasDto;
import com.lnt.postanalog.dto.TrendAnalysisResponseDto;
import com.lnt.postanalog.entity.AnalogData;
import com.lnt.postanalog.repository.AnalogRepository;
import com.lnt.postanalog.util.DateTimeConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Transactional
public class AnalogService {

	static Logger logger = LogManager.getLogger(AnalogService.class);
	@Autowired
	private AnalogRepository repository;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@PersistenceContext
	private EntityManager em;

	public AnalogData save(AnalogData analog) {
		return repository.save(analog);
	}

	public List<AnalogData> getAllAnalogData() {
		return repository.findAll();
	}

	public List<TrendAnalysisResponseDto> getAnalogByDeviceAndMeasurementAndTimestampNew(DeviceMeasDto deviceMeasDto,
			String database) throws InterruptedException, ExecutionException {
		// int noOfDaysCompleteData = archivalConfigDao.findThresholdByProfileType();

		final String finalArchdatabase = "arch" + database;
		final String tablename = "lntds_analog_data";
		final String tableName1= "lntds_asset_types_measurements";
		final String archtablename = "lntds_archived_analog_interval_data";

		// int completableFuture =
		// deviceMeasDto.getAssetMeasList().stream().mapToInt(assetMeas ->
		// assetMeas.getMeasurementName().size()).sum();
		int completableFuture = deviceMeasDto.getAssetMeasList().size();
		// Determine the threshold date for splitting the query
		Calendar thresholdDate = Calendar.getInstance();
		// thresholdDate.add(Calendar.DAY_OF_MONTH, -(noOfDaysCompleteData));
		Timestamp thresholdTimestamp = new Timestamp(thresholdDate.getTime().getTime());

		Timestamp startDate = deviceMeasDto.getStartDate();
		Timestamp endDate = deviceMeasDto.getEndDate();
		System.out.println(startDate);
		System.out.println(endDate);
				

		if (startDate != null && startDate.before(thresholdTimestamp) && endDate != null
				&& endDate.after(thresholdTimestamp)) {
			completableFuture *= 1;
		}
		logger.info("No of completableFuture = {}", completableFuture);
		logger.info("ThresholdDateTime = {}", thresholdTimestamp);

		CompletableFuture<List<TrendAnalysisResponseDto>>[] futures = new CompletableFuture[completableFuture];
		int k = 0;

		try {
			logger.info("No of completableFuture = {}", deviceMeasDto.getAssetMeasList());
			for (AssetMeasList assetMeas : deviceMeasDto.getAssetMeasList()) {
				String deviceId = assetMeas.getDeviceId();
				logger.info("Deviceid {}", deviceId);

				logger.info("Meass List {}", deviceMeasDto.getAssetMeasList());
				// for (String measName : assetMeas.getMeasurementName()) {
				/* if(endDate.after(thresholdTimestamp)){ */
				/*
				 * Timestamp start = startDate.before(thresholdTimestamp) ? thresholdTimestamp :
				 * startDate;
				 */
				futures[k] = CompletableFuture.supplyAsync(() -> {
					return runQuery(deviceId, assetMeas.getMeasurementName(), database, tablename,tableName1, startDate, endDate);
				}, taskExecutor);
				k++;

				/* } */
				/*
				 * if(startDate!=null && startDate.before(thresholdTimestamp)){ Timestamp end =
				 * endDate.after(thresholdTimestamp) ? thresholdTimestamp : endDate; futures[k]
				 * = CompletableFuture.supplyAsync(() -> { return runQuery(deviceId,
				 * assetMeas.getMeasurementName(), finalArchdatabase, archtablename, startDate ,
				 * end); }, taskExecutor); k++; }
				 */
				// }
			}

			// Wait for all futures to complete
			CompletableFuture.allOf(futures).join();

			// Create an ArrayList to store the results
			List<TrendAnalysisResponseDto> combinedResults = new ArrayList<>();

			// Copy results from completed futures to the ArrayList
			for (CompletableFuture<List<TrendAnalysisResponseDto>> future : futures) {
				if (future.isDone() && !future.isCompletedExceptionally()) {
					List<TrendAnalysisResponseDto> result = future.get(); // Retrieve the result of the completed future
					combinedResults.addAll(result); // Add the result to the combinedResults ArrayList
				}
			}
			return combinedResults;
		} catch (Exception e) {
			logger.error(e);
			// logger.info(e.getMessage());
		}
		return null;
	}

	@Async
		public List<TrendAnalysisResponseDto> runQuery(String deviceId, List<String> measName, String database, String tablename, String tableName1, Timestamp startDate, Timestamp endDate) {
			//DataSourceContextHolder.setDataSourceContext(database);
			logger.info("Current Thread Name = {}", Thread.currentThread().getName());
			List<TrendAnalysisResponseDto> analogList = new ArrayList<>(0);
            System.out.println(analogList);
			EntityManager localEntityManager  = em.getEntityManagerFactory().createEntityManager();
			try {

				Timestamp endDateTime = null;
				Timestamp startDateTime = null;
				
				  if (startDate == null && endDate == null) { 
						
						  Calendar c = Calendar.getInstance(); c.setTime(new Date()); 
						  endDateTime = new Timestamp(c.getTime().getTime());
						  c.add(Calendar.HOUR, -24);
						  startDateTime =  new Timestamp(c.getTime().getTime());
						 
				  
							  } else { 
					  startDateTime =startDate; 
					  endDateTime =  endDate; 
					   }
				
				  //String startDateTimeString = startDateTime.toString();
				  
					/*
					 * String endDateTimeString = endDateTime.toString(); DateTimeFormatter
					 * inputFormatter = new DateTimeFormatterBuilder()
					 * .appendPattern("yyyy-MM-dd HH:mm:ss.S")
					 * .parseDefaulting(ChronoField.OFFSET_SECONDS, 0) .toFormatter();
					 * 
					 * LocalDateTime localDateTime = LocalDateTime.parse(startDateTimeString,
					 * inputFormatter); ZonedDateTime zonedDateTime =
					 * localDateTime.atZone(ZoneId.systemDefault()); ZonedDateTime utcDateTime =
					 * zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
					 * System.out.println("Parsed zonedDateTime: " + zonedDateTime);
					 * System.out.println("Parsed utcDateTime: " + utcDateTime);
					 * System.out.println("Parsed LocalDateTime: " + localDateTime);
					 */
				  
				  
					/*
					 * DateTimeFormatter inputendFormatter = new DateTimeFormatterBuilder()
					 * .appendPattern("yyyy-MM-dd HH:mm:ss.S")
					 * .parseDefaulting(ChronoField.OFFSET_SECONDS, 0) .toFormatter(); LocalDateTime
					 * endDateLocalTime = LocalDateTime.parse(endDateTimeString, inputendFormatter);
					 * System.out.println("Parsed end LocalDateTime: " + endDateLocalTime);
					 */
				  
					
					  String sql=  "SELECT  a.record_timestamp, m.measurement_name, a.measurement_analog_value FROM "
					  + tablename +" a JOIN "+
					  tableName1+" m ON a.meas_id = m.id WHERE a.asset_id=?1 AND m.measurement_name in (?2)  AND   a.record_timestamp BETWEEN ?3  AND ?4";
					  ;
					 
					
				  
		
					 System.out.println("select query "+sql); 

					
				 
				 Query query = localEntityManager.createNativeQuery(sql);
				 query.setParameter(1, deviceId);
				 query.setParameter(2, measName); // Assuming measurementNames is a List<String>
				 query.setParameter(3, startDateTime);
				 query.setParameter(4, endDateTime);
				 
					/*
					 * Query query = localEntityManager.createNativeQuery(sql);
					 * query.setParameter(1, deviceId); query.setParameter(2, measName);
					 * query.setParameter(3, startDateTimeFilter); query.setParameter(4,
					 * endDateTimeFilter);
					 * System.out.println("StartDateTIME STRING===="+startDateTime);
					 */
				//System.out.println("EndDateTime STRING====="+ endDateLocalTime);
				logger.info(sql);
				List<Object[]> resultObjArr = query.getResultList();// ... (your query results)
				
				System.out.println(resultObjArr);
				Map<String, List<Object>> trendAnalysisResponseMap = new HashMap<>();
		
				// Iterate through the query results and populate the map
				for (Object[] objArr : resultObjArr) {
					Instant timestamp = (Instant) objArr[0]; 
					Double value = (Double) objArr[2];
					String measurementName = (String) objArr[1];
					 
				
					List<Object> innerList=Arrays.asList(timestamp.toString(),value);
					//List<Object> innerList=Arrays.asList(value);
					System.out.println(innerList);
		
					// Check if the measurement name is already in the map
					if (trendAnalysisResponseMap.containsKey(measurementName)) {
						trendAnalysisResponseMap.get(measurementName).add(innerList);
						System.out.println("Trend Analysis "+trendAnalysisResponseMap);
					} else {
						// If not, create a new list and add the TrendAnalysisResponseDto object to it
						List<Object> innerListList = new ArrayList<>();
						innerListList.add(innerList);
						trendAnalysisResponseMap.put(measurementName, innerListList);
					}

				}
				for (Map.Entry<String, List<Object>> entry : trendAnalysisResponseMap.entrySet()) {


					TrendAnalysisResponseDto aggregationDto = new TrendAnalysisResponseDto();
						aggregationDto.setDeviceId(deviceId);
						aggregationDto.setMeasurementName(entry.getKey());
					 	aggregationDto.setValuesDto(entry.getValue());
					 	analogList.add(aggregationDto);
					 	System.out.println(analogList);


				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			finally{
				if (localEntityManager != null && localEntityManager.isOpen()) {
					localEntityManager.close(); // Close the EntityManager when done
				}
			}
			return analogList;
		}
}
