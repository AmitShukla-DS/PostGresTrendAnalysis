package com.lnt.postanalog.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeConverter {
	  public static String convertUtcToIst(String utcDateTimeStr) throws ParseException {
	        // Define timezones
		
		        // Define timezones
		        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
		        TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata"); // Indian Standard Time (IST)

		        // Create date formatter for UTC timezone
		        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        utcFormat.setTimeZone(utcTimeZone);

		        // Parse UTC datetime string
		        Date utcDateTime = utcFormat.parse(utcDateTimeStr);

		        // Set timezone to IST
		        DateFormat istFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		        istFormat.setTimeZone(istTimeZone);

		        // Format datetime in Indian format
		        String istDateTimeStr = istFormat.format(utcDateTime);

		        return istDateTimeStr;
		    

	    }
	}

