package com.gmail.projectUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptureAlertMessage {

	public static Logger logger = LogManager.getLogger(CaptureAlertMessage.class);
  	public static String snakeAlertMessagesDisplayedContent_RU(WebDriver driver, String alertAddress, String userMessage) throws InterruptedException
  	{
  		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
  		String callerMethodName = stackTraceElement[2].getMethodName();
  		logger.info("Caller method name: "+callerMethodName);
  		
  		//ALARTS ADDRESS AND ACTIONS METHODS
  		if(alertAddress == "") {
  			alertAddress = "//div[@role=\"alert\"]";
  		}
  		
  	  	String alertContent = null;
  	  	
  		//logger.info("Alert - Try");
  			WebElement alertSnakeMessage = null;
  			int alertLoopCount = 0;
  			String exception = null;
  			boolean flag = false;
  			while(alertLoopCount <= 5000)
  			{	alertLoopCount++;
  				try {
  					alertSnakeMessage = driver.findElement(By.xpath(alertAddress));
  					flag = alertSnakeMessage.isDisplayed();
	  					if(flag) {
	  						logger.info("Checking alert on intervel of 150 milliSeconds and loop count is: "+alertLoopCount);
	  						logger.info("Alert message is displayed: "+flag);
	  						alertContent = alertSnakeMessage.getText();
	  			  			logger.info("===>>> Alert Message Content: "+alertContent);
	  					}else {
	  						Thread.sleep(50);
	  					}
  				}catch(Exception e) {
  					exception = e.getMessage();
  				}
  				
  				if(flag) {
  					Thread.sleep(500);
  					break;
  				}
  				if(alertLoopCount == 5000) {
  					logger.info("Alert message check frequency is 150 milli seconds and loop count is: "+alertLoopCount);
  				}
  			}
  		
  		logger.info("Alert not cought exception: "+exception);
  		Thread.sleep(2000);
  		return alertContent;
  	}
  	
}
