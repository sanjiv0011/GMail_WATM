package com.gmail.dataBaseTesting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.gmail.dataBase.DatabaseConnectionAndQuery_GenericMethods;
import com.gmail.projectUtility.UTC_DateFormatter;

public class DBT_User_Membership {
	public WebDriver driver;
	public static SoftAssert softAssert = new SoftAssert();
	public static final Logger logger = LogManager.getLogger(DBT_User_Membership.class);
		
		
		public static String findUserIdFromDataBase(String userEmailAddress) throws SQLException {
			//TO VERIFY CALLER METHOD NAME
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String callerMethodName = stackTraceElements[2].getMethodName();
			logger.info("test_DBT_RegisterAndCacleClass method called and Caller method name: "+callerMethodName);
		
			//BY USING USER EMAIL ID FIND OUT USER ID FROM THE DATA BASE
			logger.info("User given userEmailAddress: "+userEmailAddress);
			String queryFindUniqueUser = "SELECT * FROM public.user WHERE email_address = "+"'"+userEmailAddress+"'"+" ORDER BY updated_at";
			logger.info("Query string: "+queryFindUniqueUser);
			ResultSet resultsetFindUniqueUser = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(queryFindUniqueUser);
			String userId = null;
			while(resultsetFindUniqueUser.next()) {	
					//DATA FROM THE DATABASE
					userId = resultsetFindUniqueUser.getString("id");
					logger.info("userId: "+userId+" for the given userEmail: "+userEmailAddress);
					break;
			}
			softAssert.assertAll();
			return userId;
		}
		
		//CROSS VERIFY DATABASE ONCE MEMBERSHIP CHNAGED
		public static void test_DBT_ChangeMembership(String userEmailAddress, String packageName) throws SQLException, InterruptedException
		{		Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
				
				String userId = findUserIdFromDataBase(userEmailAddress);
				//BY USING USER PACKAGE NAME FIND OUT PACKAGE ID FROM THE DATA BASE
				logger.info("User given packageName: "+packageName);
				String queryFindPackageId = "SELECT * FROM public.membership_packages WHERE title ILIKE "+"'%"+packageName+"%'"+" ORDER BY id ASC";
				logger.info("Query string: "+queryFindPackageId);
				ResultSet resultsetFindPackage = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(queryFindPackageId);
				String packageId = null;
				while(resultsetFindPackage.next()) {	
						//DATA FROM THE DATABASE
						packageId = resultsetFindPackage.getString("id");
						logger.info("packageId : "+packageId+" for the given packageName: "+packageName);
				}
				
				//TO NEW CREATED USER MEMBERSHIP ID
				String queryUserMembership = "SELECT * FROM public.user_membership_disclaimer WHERE user_id = " + "'" +userId+ "'"  + " AND package_id = " + "'" + packageId + "'"+"ORDER BY created_at DESC LIMIT 10";
				logger.info("Query string: "+queryUserMembership);
				ResultSet resultsetUserMembership = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(queryUserMembership);
				String userMembershipId=null;
				while(resultsetUserMembership.next()) {	
						userMembershipId = resultsetUserMembership.getString("id");
						logger.info("New ctreated user membership id: "+userMembershipId);
						softAssert.assertTrue(userMembershipId != null, "To check new created user membership id is not null");
						logger.info("===>>> DataBase testing DONE");
						break;
				}
				softAssert.assertAll();
		}
		
		//CROSS VERIFY DATABASE ONCE MEMBERSHIP PAUSED
		public static void test_DBT_PauseMembership(String userEmailAddress, String pauseStartDate,String pauseEndDate) throws SQLException, InterruptedException, ParseException
		{		Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
		
				String userId = findUserIdFromDataBase(userEmailAddress);
				String queryPauseMembership = "SELECT * FROM public.user_subscription_pause_details WHERE user_id = " + "'" +userId+ "'"  + " ORDER BY updated_at DESC";
				logger.info("Query string: "+queryPauseMembership);
				ResultSet resultsetPauseMembership = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(queryPauseMembership);
				
				//DATE FORMAT CONVERSION
				String input_StartDate = UTC_DateFormatter.formatDate("start", pauseStartDate);
				String input_EndDate = UTC_DateFormatter.formatDate("end",pauseEndDate);
				
				while(resultsetPauseMembership.next()) {	
						String db_pauseStartDate = resultsetPauseMembership.getString("pause_start_date");
						String db_pauseEndDate = resultsetPauseMembership.getString("pause_end_date");
						String db_pauseMembeshipCreatedId = resultsetPauseMembership.getString("id");
						logger.info("From database, Pause start date: "+db_pauseStartDate+", Pause end date: "+db_pauseEndDate);
						logger.info("User given, Pause start date: "+input_StartDate+", Pause end date: "+input_EndDate+" UTC format");
						logger.info("Pause membership created Id from database: "+db_pauseMembeshipCreatedId);
						
						//ASSERTION 
						softAssert.assertEquals(input_StartDate,db_pauseStartDate, "To match pause membership start date");
						softAssert.assertEquals(input_EndDate,db_pauseEndDate, "To match pause membership end date");
						softAssert.assertTrue(db_pauseMembeshipCreatedId != null, "To check new created pause membership id is not null");
						
						logger.info("===>>> DataBase testing DONE");
						break;
				}
				softAssert.assertAll();
		}
		
		//CROSS VERIFY DATABASE ONCE MEMBERSHIP RESUMED
		public static void test_DBT_ResumeMembership(String userEmailAddress) throws SQLException, InterruptedException
		{		Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
		
				String userId = findUserIdFromDataBase(userEmailAddress);
				String queryResumeMembership = "SELECT * FROM public.user_subscription_pause_details WHERE user_id = " + "'" +userId+ "'"  + " ORDER BY updated_at DESC";
				logger.info("Query string: "+queryResumeMembership);
				ResultSet resultsetResumeMembership = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(queryResumeMembership);
				while(resultsetResumeMembership.next()) {	
						Boolean db_resumeStatus = resultsetResumeMembership.getBoolean("resume_processed");
						logger.info("DataBase Resumed Status: "+db_resumeStatus);
						softAssert.assertTrue(db_resumeStatus,"To resumed status");
						logger.info("===>>> DataBase testing DONE");
						break;
				}
				softAssert.assertAll();
		}
		
		
		
		
}
