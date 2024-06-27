package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class MyCalendarPicker {
	
	//VARIABLE AND CONSTRUCTER DECLARATIONS
	public static final Logger logger = LogManager.getLogger(MyCalendarPicker.class);
	static Actions action;
	public static SoftAssert softAssert = new SoftAssert();
	
	 public static void moveToCorrectDateInCalendarAndClickOnContentMonthView(WebDriver driver, String dateValue,String dateContent, List<WebElement> path_DateList, List<WebElement> path_DateListContent,WebElement path_showMoreBtn, List<WebElement> path_listShowMoreContent) throws InterruptedException 
	    {		
	 		action = new Actions(driver);
	 		logger.info("Entered inside methods moveToCorrectDateInCalendarAndClickOnContent");
    
	    	for(WebElement date : path_DateList )
	    	{	//logger.info("Entered inside first for loop with path_DateList Date: "+date.getText());
	    		boolean flag = false;
	    		if(date.getText().equals(dateValue))
	    		{	
	    			logger.info("Entered inside first if blcok and date value from page is: "+date.getText());
	    			action.moveToElement(date).build().perform();
	    			logger.info("Curser moved to the given date: "+dateValue);
	    	    	Thread.sleep(1000);
	    	    	
	    			for(WebElement elementDateContent : path_DateListContent)
	    			{	logger.info("Entered inside second for loop with path_DateListContent: "+elementDateContent.getText());
	    				
	    				try {
		    					if(elementDateContent.getText().equals(dateContent))
		        				{	
		    						logger.info("Entered inside second if blcok and content value from page is: "+elementDateContent.getText());
		        					Thread.sleep(1000);
		    						action.moveToElement(elementDateContent).click().build().perform();
		        					logger.info("Clicked on the given date content: "+dateContent);
		        			    	Thread.sleep(3000);
		        			    	driver.navigate().refresh();
		        			    	Thread.sleep(4000);
		        			    	flag = true;
		        			    	break; // TO EXIT SECONDS FOR LOOP
		        				}
	    					
	    					}catch(Exception e)
		    					{
		    						logger.info("Message from my calendar date content: "+e.getMessage());
		    					}
	    			}
	    			
	    			//TO EXIT THE FIRTS FOR LOOP
	    			if(flag) {
	    				break;
	    			}
	    		}
	    		
	    	}
	    	
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
	 
