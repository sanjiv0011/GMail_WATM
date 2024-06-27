package com.gmail.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabaseConnectionAndQuery_GenericMethods {

//CALL THIS METHODS WHERE YOU WANT TO IMPLEMENT DATABASE CONCEPT BY PASSING DATABASE QUERRY AND IT WILL RETURN THE RESULT SET
public static ResultSet dataBaseCollectionAndQuerry(String querry) throws SQLException{
	
	Logger logger = LogManager.getLogger(ReUseAbleElement.class);
	Connection con = null;
	ResultSet resultSet = null;
	
	logger.info("Entered inside dataBaseCollectionAndQuerry methods");
	
	//========================== Data Base===============//
		try{
			//Step1
			// Step 1: Define database URL, username, and password
            String url = "jdbc:postgresql://htdevpgsql.postgres.database.azure.com:5432/p360dev"; // Note the correct URL format
            String username = "htadmin";
            String password = "gaMcsqyARA3";
			con = DriverManager.getConnection(url,username,password);
			
			// check connection is done or not
			if(!con.isClosed()){
				logger.info("Connection is successfull...");
			}else{
				logger.info("Connection is failed!!!");
			}

			Statement stm = con.createStatement();	// Step2
			resultSet = stm.executeQuery(querry);	// Step3 &	// Step4
		}
		catch(Exception e)	{
			e.getCause();
		}finally{
			//Step5
			if (con != null) {
                con.close();
                if (con.isClosed()) {
                	logger.info("Connection closed successfully...");
                } else {
                	logger.info("Connection not closed!!!");
                }
            }
		}
		return resultSet;
	}
}
