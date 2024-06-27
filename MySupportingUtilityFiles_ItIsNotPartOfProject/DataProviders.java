package com.hrms.dataProviders.from_readDataFromExcelFile;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.hrms.utilities.ReadDataFromExcelFile;



// IT IS USED TO PROVIDE DATA TO THE TEST CASES CLASe BY USING CLASS "ReadDataFromExcelFile.java"
public class DataProviders {
	
	//TO LOG THE MESSAGES ON THE CONSOLE AND LOG FILES BOTH
	public static Logger logger = LogManager.getLogger();
	
	//CONSTRUCTOR TO ACCESS THE READ DATA FROM THE EXCEL FILES
	public static ReadDataFromExcelFile rdfef;

	//DATE PROVIDER METHODS CONCEPT TO ACCESS DATA FROM THE EXCEL FILES, FOR THIS IT IS ALSO CALLED TO THE ReadDataFromExcelFile CLASS AND NOTE ACTUAL DATA READING DONE AT(ReadDataFromExcelFile)
	public static String[][] dataProviderGetDataFromExcelFile(String fileNameOnly) throws IOException
	{
		try {
			String filePath = "./"+"//ExcelData//"+fileNameOnly+".xlsx";
			rdfef = new ReadDataFromExcelFile(filePath);
		}catch(Exception e) {
			logger.info(e.getCause());
		}
		
		int rownum = rdfef.getRowCount("Sheet1");
		int colcount = rdfef.getCellCount("Sheet1", 1);
		
		String UserData[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum;i++)
		{
			for(int j=0; j<colcount ;j++)
			{
				UserData[i-1][j] = rdfef.	getCellData("Sheet1",i,j);
			}
		}
		
		return UserData;
	}

}
