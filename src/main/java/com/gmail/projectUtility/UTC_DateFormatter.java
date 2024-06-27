package com.gmail.projectUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class UTC_DateFormatter {

	    public static String DateToUTC(String startEndType, String inputDate) throws ParseException {
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
	
	        LocalDateTime localDateTime = LocalDateTime.parse(inputDate, inputFormatter);
	
			if ("start".equalsIgnoreCase(startEndType)) {
			    localDateTime = localDateTime.withHour(18).withMinute(30).withSecond(0);
			} else if ("end".equalsIgnoreCase(startEndType)) {
			    localDateTime = localDateTime.withHour(18).withMinute(29).withSecond(59);
			}
	
			ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
	
			return outputFormatter.format(zonedDateTime);
	    }
	    
	    //TO FORMAT DATE INTO DATABASE FORMAT
	    public static String formatDate(String startEndType, String date) {
	        String inputDate = date;
	
	        // DATETIME STAMP UTC FORMAT
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	        String utcTimestamp = null;
	        try {
	            // Parse the input date string
	            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // Set the time zone to UTC
	            calendar.setTime(dateFormat.parse(inputDate));

	            if ("start".equals(startEndType)) {
	                // Check if the time is earlier than 18:30:00, and if so, subtract a day
	                if (calendar.get(Calendar.HOUR_OF_DAY) < 18 || (calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) < 30)) {
	                    calendar.add(Calendar.DAY_OF_MONTH, -1);
	                }
	                // Set the time to 18:30:00
	                calendar.set(Calendar.HOUR_OF_DAY, 18);
	                calendar.set(Calendar.MINUTE, 30);
	                calendar.set(Calendar.SECOND, 0);
	            } else if ("end".equals(startEndType)) {
	                // Set the time to 18:29:59
	                calendar.set(Calendar.HOUR_OF_DAY, 18);
	                calendar.set(Calendar.MINUTE, 29);
	                calendar.set(Calendar.SECOND, 59);
	            }

	            // Convert to the desired output format
	            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	            utcTimestamp = outputFormat.format(calendar.getTime());

	        } catch (ParseException e) {
	            // Handle parsing errors
	            System.err.println("Error parsing date: " + e.getMessage());
	        }

	        return utcTimestamp;
	    }
	
	  		
  		public static void main(String[]args) {
  			String date = formatDate("start","15 September 2023");
  			System.out.println(date);
  		}
}
