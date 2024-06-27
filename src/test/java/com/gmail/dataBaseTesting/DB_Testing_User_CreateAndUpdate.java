package com.gmail.dataBaseTesting;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.gmail.dataBase.DatabaseConnectionAndQuery_GenericMethods;

public class DB_Testing_User_CreateAndUpdate {
	public WebDriver driver;
	public SoftAssert softAssert = new SoftAssert();
	public static final Logger logger = LogManager.getLogger(DB_Testing_User_CreateAndUpdate.class);
		
		//CROSS VERIFY DATABASE ONCE NEW USER CREATED
		public void test_DB_createUser(String userNameToCreate,String organizationName,String firstName,String lastName,String emailAddress,String userRole) throws SQLException, InterruptedException
		{		Thread.sleep(2000); //WAITNG TO AVOID DATA INCONSISTANCY PROBLEM
				//For DataBase Testing
				String querryOrg = "SELECT * FROM public.organizations ORDER BY id ASC";
				ResultSet resultSetOrg = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querryOrg);
				String db_orgId = null;
				while(resultSetOrg.next()) {
					if(organizationName.equals(resultSetOrg.getString("name"))){
						db_orgId = resultSetOrg.getString("id");
					}
				}
				
				String querryUser = "select * from public.users order by updated_at desc limit 1";
				
				ResultSet resultsetUser = DatabaseConnectionAndQuery_GenericMethods.dataBaseCollectionAndQuerry(querryUser);
		
				int count =0;
				while(resultsetUser.next())
				{	
					if(userNameToCreate.equals(resultsetUser.getString("user_name")) && db_orgId.equals(resultsetUser.getString("organization_id")))
					{	count++;
						logger.info("Select querry runs and  filter data at the top of the list and row count: "+count);
						
						//DATA FROM THE DATABASE
						String db_userNameToCreate = resultsetUser.getString("user_name");
						String db_firstName = resultsetUser.getString("first_name");
						String db_lastName = resultsetUser.getString("last_name");
						String db_emailAddress = resultsetUser.getString("email_address");
						
						//USER ROLES IN DB IS A JSON OBJECT THAT'S WHY WE HAVE TO CONVERT IT
						String db_userRole = resultsetUser.getString("userRoles");
						
						   
						softAssert.assertEquals(db_userRole, "{"+userRole+"}", "To match user role");
						//CROSS VERIFICATIONS(IT WILL MATCH USER INPUT DATA WITH DATABASE ENTRY)
						softAssert.assertEquals(db_userNameToCreate, userNameToCreate, "To match userNameToCreate");
						softAssert.assertEquals(db_firstName, firstName, "To match firstName");
						softAssert.assertEquals(db_lastName, lastName, "To match lastName");
						softAssert.assertEquals(db_emailAddress, emailAddress, "To match emailAddress");
						logger.info("===>>> test_DB_createUser: DataBase Data Cross Verification matched");
						softAssert.assertAll();
						break;
				}
			}
			softAssert.assertAll();
			}
}
