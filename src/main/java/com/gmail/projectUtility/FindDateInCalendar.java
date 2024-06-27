package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class FindDateInCalendar {
	
	//VARIABLE AND CONSTRUCTER DECLARATIONS
	public static final Logger logger = LogManager.getLogger(FindDateInCalendar.class);
	static Actions action;
	public static SoftAssert softAssert = new SoftAssert();
	public static JavascriptExecutor jsExecutor;

	
	 public static boolean findDateInCallendar(WebDriver driver, String dateValue, String monthYearAddress,String backButtonAddress,String nextButtonAddress, List<WebElement> path_DateList,String wantToClickOnDateOrDateBox) throws InterruptedException 
	 {	
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("FindDateInCalendar caller method name: "+callerMethodName);
 		action = new Actions(driver);
 		String date[] =  dateValue.split(" ");
 		String dt = date[0];
 		String month = date[1];
 		String year = date[2];
 		String inputMonthYear = month+" "+year;
 		logger.info("inputMonthYear : "+inputMonthYear);
 		String monthYear[]  = driver.findElement(By.xpath(monthYearAddress)).getText().split(" ");
 		
 		//TO SELECT THE YEAR
 		if(Integer.parseInt(year) == Integer.parseInt(monthYear[1])){
 			logger.info("Year matched: "+monthYear[1]);
 		}else {
 			
 			while(Integer.parseInt(year) > Integer.parseInt(monthYear[1]))
 			{
 				Thread.sleep(500);
 				driver.findElement(By.xpath(backButtonAddress)).click();
 				Thread.sleep(1000);
 				String monthYear2[]  = driver.findElement(By.xpath(monthYearAddress)).getText().split(" ");
 				monthYear[1] = monthYear2[1];
 				
 			}
 			
 			while(Integer.parseInt(year) < Integer.parseInt(monthYear[1]))
 			{
 				Thread.sleep(500);
 				driver.findElement(By.xpath(nextButtonAddress)).click();
 				Thread.sleep(1000);
 				String monthYear2[]  = driver.findElement(By.xpath(monthYearAddress)).getText().split(" ");
 				monthYear[1] = monthYear2[1];
 			}
 			
 			if(Integer.parseInt(year) == Integer.parseInt(monthYear[1])){
 	 			logger.info("Year matched: "+monthYear[1]);
 	 		}else {
 	 			logger.info("Year not matched: "+monthYear[1]);
 	 			softAssert.assertTrue(false,"Year not matched: "+monthYear[1]);
 	 		}
 			
 		}
 		
 		
 		
 		
 		//=========START======TO SELECT THE  MONTH=============//
        logger.info("Displayed month: "+monthYear[0]+" and year: " + monthYear[1]);
        String monthArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int displayedMonth = 0;
        int inputMonth = 0;
        for (int i = 1; i < monthArray.length; i++) {
            if (monthYear[0].equalsIgnoreCase(monthArray[i])) {
                displayedMonth = i;
            }
            if (month.equalsIgnoreCase(monthArray[i])) {
                inputMonth = i;
            }
        }
        while (displayedMonth > inputMonth) {
            displayedMonth = displayedMonth - 1;
            driver.findElement(By.xpath(backButtonAddress)).click();
            Thread.sleep(2000);
        }
        while (displayedMonth < inputMonth) {
            displayedMonth = displayedMonth + 1;
            driver.findElement(By.xpath(nextButtonAddress)).click();
            Thread.sleep(2000);
            
        }
        
        if (displayedMonth == inputMonth) {
            logger.info("selected month name is: " + monthArray[inputMonth]);
        } else {
            logger.info("Month not selected");
            softAssert.assertTrue(false,"Month not selected: "+inputMonth);
        }
        //=========END======TO SELECT THE  MONTH=============//
        
 		//TO CLICK ON THE DATE
        boolean flag = false;
        for (WebElement element : path_DateList)
		{	//REMOVE THE COMMENT, TO CHECK LSIT OPTIONS
			//logger.info("List options: "+element.getText());
        	
			if (element.getText().equalsIgnoreCase(dt) || element.getText().trim().contains(dt.trim()))
			{
				logger.info("Entered inside [if block], matched list value: " + element.getText());
				try
				{
					if (element.isEnabled() && element.isDisplayed())
					{
						Thread.sleep(300);
						action.moveToElement(element).build().perform();
						Thread.sleep(1000);
						
						if(wantToClickOnDateOrDateBox.equals("date")) {
							element.click();
							logger.info("Clicked on the given date");
						}else if(wantToClickOnDateOrDateBox.equals("dateBox")){
							action.moveToElement(element, 10, -10).click().build().perform();
							logger.info("Clicked on the date box");
						}
						
						Thread.sleep(2000);
						flag = true;
						break;
					}
				}
				catch (Exception e){
					logger.info("Exception While selecting list from the dropdown: " + e.getMessage());
				}
			}
		}
        if(flag==false) {
        	logger.info("Not click on the given date: "+dt);
        	softAssert.assertTrue(false,"Not click on the given date: "+dt);
        }
        
        softAssert.assertAll();
        return flag;
	   
      
	 }
	 
	 public static boolean clickOnCalendarDateBox(WebDriver driver, String datebox_address, String dateOnly) 
	{
		boolean flag  = false;
		List <WebElement> options = driver.findElements(By.xpath(datebox_address));
		for (WebElement element : options)
		{	logger.info("Calendar date box items: "+element.getText());
			String calendarDate[] = element.getText().split(" ");
			
			if (calendarDate[0].equalsIgnoreCase(dateOnly) || calendarDate[0].contains(dateOnly.trim()))
			{
				logger.info("Entered inside [if block], matched list value: " + element.getText());
				try
				{
					Thread.sleep(500);
					action.moveToElement(element).click().build().perform();
					Thread.sleep(500);
					flag = true;
					logger.info("Click on the element");
				}
				catch (Exception e){
					logger.info("Exception While selecting list from the Calendar date: " + e.getMessage());
				}
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}
	 
