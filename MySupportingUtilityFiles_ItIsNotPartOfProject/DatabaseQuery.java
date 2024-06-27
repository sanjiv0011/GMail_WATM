package com.hrms.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabaseQuery {
	
public static ResultSet dataBaseCollectionAndQuerry(String querry) throws SQLException{
	Connection con = null;
	ResultSet resultSet = null;
	//========================== Data Base===============//
		try{
			//Step1
			// Step 1: Define database URL, username, and password
            String url = "jdbc:postgresql://localhost:5432/hrms"; // Note the correct URL format
            String username = "postgres";
            String password = "Qwer4321!";
			con = DriverManager.getConnection(url,username,password);
			
			// check connection is done or not
			if(!con.isClosed()){
				System.out.println("Connection is successfull...");
			}else{
				System.out.println("Connection is failed!!!");
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
                    System.out.println("Connection closed successfully...");
                } else {
                    System.out.println("Connection not closed!!!");
                }
            }
		}
		return resultSet;
	}
}
