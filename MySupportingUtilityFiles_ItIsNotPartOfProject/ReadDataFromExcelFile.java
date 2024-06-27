package com.hrms.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// IT IS USED TO READ THE DATA FROM THE EXCEL FILE WHILE DATA DRIVEN TESTING
public class ReadDataFromExcelFile {

	//EXCEL FILE ADDRESS
	public String filePath;// = "./"+"//ExcelData//Excel_Data_For_Test.xlsx";
	
	//TO CONSTRUCTOR IT TAKES ARGUMENTS AS FILE PATH(BY THIS WE CAN ACCESS ALL THE METHODS AND VARIBLE IN THE DATA PROVIDERS METHODS)
	public ReadDataFromExcelFile(String filePath)
	{
		this.filePath = filePath;
	}
	
	//VARIALES DECLARATIONS
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	
	//TO GET THE ROW COUNT IN THE EXCEL FILE (TILL ROW DATA EXIST)
	public int getRowCount(String sheetName) throws IOException
	{
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	//TO GET THE  CELL COUNT/COLUMN COUNT IN THE EXCEL SHEET(COLUMN TILL THE DATA EXIST)
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		
		int cellCount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}
	
	//TO SET THE CELL DATA FROM THE EXCEL FILE
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File src = new File(filePath);
		
		if(!src.exists())
		{
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
		}
		
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetName)== -1)// if sheet not exists then create new sheet
		{
			workbook.createSheet(sheetName);
		}
		
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)== null) // if row not exists then create new row
		{
			sheet.createRow(rownum);
		}
		
		row = sheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		
		cell.setCellValue(data);
		
		fos = new FileOutputStream(filePath);
		
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	
	// TO GET THE CELL DATA FORM THE EXCEL FILE
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data  = formatter.formatCellValue(cell);
			//return the formatted value of the cell as string regardless of its types
		}
		catch(Exception e)
		{
			data = "";
			System.out.println(e.getCause());
		}
		workbook.close();
		fis.close();
		return data;
				
		
	}
	
	
	//TO FILL GREEN COLOR IN THE EXCEL FILE(CELL)
		public void fillGreenColor(String sheetName, int rownum, int column) throws IOException
		{
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			
			sheet = workbook.getSheet(sheetName);
			
			row = sheet.getRow(rownum);
			cell = row.getCell(column);
			
			style = workbook.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
		
			workbook.write(fos);
			workbook.close();
			fis.close();
			fos.close();
			
		
		}
		
		//TO FILL RED COLOR IN THE EXCEL FILE(CELL)
		public void fillRedColor(String xlsheet, int rownum, int column) throws IOException
		{
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			
			sheet = workbook.getSheet(xlsheet);
			
			row = sheet.getRow(rownum);
			cell = row.getCell(column);
			
			style = workbook.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
		
			workbook.write(fos);
			workbook.close();
			fis.close();
			fos.close();

		}
	
}


