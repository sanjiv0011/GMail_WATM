package com.gmail.dataBaseTesting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.gmail.dataBase.DatabaseConnectionAndQuery_GenericMethods;

public class DBT_User_Classes {
	public WebDriver driver;
	public static SoftAssert softAssert = new SoftAssert();
	public static final Logger logger = LogManager.getLogger(DBT_User_Classes.class);
		
		//CROSS VERIFY DATABASE ONCE NEW CLIENT CREATED
		public static String test_DBT_RegisterClass(String time,String monthAndDate,String userEmailAddress) throws SQLException, InterruptedException
		{		
				StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		    	String callerMethodName = stackTraceElements[2].getMethodName();
				logger.info("test_DBT_RegisterAndCacleClass method called and Caller method name: "+callerMethodName);
			
				Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
				logger.info("User given email: "+userEmailAddress);
				String querryFindUniqueUser = "SELECT * FROM public.user WHERE email_address = "+"'"+userEmailAddress+"'"+" ORDER BY updated_at";
				logger.info("Qurry string: "+querryFindUniqueUser);
				ResultSet resultsetFindUniqueUser = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querryFindUniqueUser);
				String userId = null;
				while(resultsetFindUniqueUser.next()) {	
						//DATA FROM THE DATABASE
						userId = resultsetFindUniqueUser.getString("id");
						logger.info("userId: "+userId);
				}
				
				String querry = "SELECT * FROM public.class_registrations WHERE user_id = "+"'"+userId+"'"+" ORDER BY updated_at DESC LIMIT 1";
				logger.info("Qurry string: "+querry);
				ResultSet resultsetRegisterClass = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querry);

				// Given input
		        String inputDateAndTime = monthAndDate+" "+time; 	//"SEP 21 THU 2:00 AM";
		        
		        String outputDate=null;
		        // Get the current year
		        Calendar calendar = Calendar.getInstance();
		        int yearValue = calendar.get(Calendar.YEAR);

		        // Create a SimpleDateFormat for the input and output formats
		        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd EEE hh:mm a", Locale.ENGLISH);
		        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		        try {
		            Date date = inputFormat.parse(inputDateAndTime);
		            Calendar parsedCalendar = Calendar.getInstance();
		            parsedCalendar.setTime(date);

		            // Set the year to the current year
		            parsedCalendar.set(Calendar.YEAR, yearValue);

		            // Format the date in the desired output format
		            outputDate = outputFormat.format(parsedCalendar.getTime());

		            logger.info("Formatted user given data and time: " + outputDate);
		        } catch (ParseException e) {
		            System.err.println("Error parsing the date: " + e.getMessage());
		            e.printStackTrace();
		        }
		        
				while(resultsetRegisterClass.next()) {	
						//DATA FROM THE DATABASE
						String class_date_time = resultsetRegisterClass.getString("class_date_time");
						boolean isClassCanceled = Boolean.parseBoolean(resultsetRegisterClass.getString("is_canceled"));
						logger.info("class_date_time from DataBase: "+class_date_time+" and user given date time: "+inputDateAndTime);
						
						//CROSS VERIFICATIONS(IT WILL MATCH USER INPUT DATA WITH DATABASE ENTRY)
						if(callerMethodName.equals("registerClass")) {
							softAssert.assertEquals(isClassCanceled, false, "To match registered class status");
						}else if(callerMethodName.equals("cancelRegisteredClass")) {
							softAssert.assertEquals(isClassCanceled, true, "To match canceled class status");
						}
						
						softAssert.assertEquals(class_date_time, outputDate, "To class date and time of the registered class");
						logger.info("===>>> DataBase testing DONE");

				}
			
				String outputDate2 = null;
		        try {
		            Date date = inputFormat.parse(inputDateAndTime);
		            // Get the current year
		            calendar = Calendar.getInstance();
		            int currentYear = calendar.get(Calendar.YEAR);

		            // Create a SimpleDateFormat for the desired output format
		            SimpleDateFormat outputFormat2 = new SimpleDateFormat("MMM dd, " + currentYear + " | hh:mm a", Locale.ENGLISH);

		            // Format the date in the desired output format
		            outputDate2 = outputFormat2.format(date);
		            logger.info("Output date and  time that is present in the registered class list: "+outputDate2);
		        } catch (ParseException e) {
		            System.err.println("Error parsing the date: " + e.getMessage());
		            e.printStackTrace();
			        }
				    
		       softAssert.assertAll();
			   return outputDate2;
		}
	
		
		
		//CHECK THE CANCEL CLASS STATUS
	
		public static void test_DBT_CancelRegisteredClass(String dateAndTime,String userEmailAddress) throws SQLException, InterruptedException
		{		
				StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		    	String callerMethodName = stackTraceElements[2].getMethodName();
				logger.info("test_DBT_RegisterAndCacleClass method called and Caller method name: "+callerMethodName);
			
				Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
				logger.info("User given email: "+userEmailAddress);
				String querryFindUniqueUser = "SELECT * FROM public.user WHERE email_address = "+"'"+userEmailAddress+"'"+" ORDER BY updated_at";
				logger.info("Qurry string: "+querryFindUniqueUser);
				ResultSet resultsetFindUniqueUser = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querryFindUniqueUser);
				String userId = null;
				while(resultsetFindUniqueUser.next()) {	
						//DATA FROM THE DATABASE
						userId = resultsetFindUniqueUser.getString("id");
						logger.info("userId: "+userId);
				}
				
				//TO CONVERT USE INPUT DATE TIME INTO DATABASE DATETIME FORMAT 
				String inputDate = dateAndTime;
		        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy | hh:mm a");
		        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String outputDate = null;
		        try {
		            Date date = inputFormat.parse(inputDate);
		            outputDate = outputFormat.format(date);
		            //System.out.println(outputDate);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        
		        
				String querry = "SELECT * FROM public.class_registrations WHERE user_id = "+"'"+userId+"'"+" ORDER BY updated_at DESC";
				logger.info("Qurry string: "+querry);
				ResultSet resultsetCancelClass = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querry);
			
	            
				while(resultsetCancelClass.next()) {	
					//DATA FROM THE DATABASE
					String class_date_time = resultsetCancelClass.getString("class_date_time");
					if(outputDate.equals(class_date_time)) {
						boolean isClassCanceled = resultsetCancelClass.getBoolean("is_canceled");
						//CROSS VERIFICATIONS(IT WILL MATCH USER INPUT DATA WITH DATABASE ENTRY)
						logger.info("===>>> DataBase date time: "+class_date_time+ " matched with the requested date time: "+outputDate+" and Database is_canceled status: "+isClassCanceled);
						softAssert.assertEquals(isClassCanceled,true, "To match canceled class status");
						break;
					}
					logger.info("===>>> DataBase testing DONE");

				}
				softAssert.assertAll();
		}
		
}
