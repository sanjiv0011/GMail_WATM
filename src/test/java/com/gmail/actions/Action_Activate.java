package com.gmail.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;


public class Action_Activate {
	
	public static final Logger logger = LogManager.getLogger(Action_Activate.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	//TO ACTIVATE
	public static boolean activate(WebDriver driver, String message) throws InterruptedException
	{
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Action_Activate caller method name: "+callerMethodName);
			
		boolean flag = false;
		ruae = new ReUseAbleElement(driver);
		try {
			boolean bol = clickOnActivateAction(driver);
			if(bol) {
			
					boolean isYesBtnPresent = ReUseAbleElement.clickOnBtnYes_RU(driver);
				
					if(!isYesBtnPresent) {
						logger.info("Searching for Activate button");
						driver.findElement(By.xpath("(//span[normalize-space()='Activate'])[1]")).click();
					}
				
			}else {
				logger.info("Not click on the Acitvate button");
			}
			
					
	    	//CHECK THE ACTIVATE CONFIRMATIONS MESSAGES
	    	String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
	    	if(alretMsg.equals(message)) {
	    		logger.info("===>>> "+message);
	    		flag = true;
	    	}else {
	    		logger.info("Alert message content: "+alretMsg);
	    	}
	    	softAssert.assertEquals(alretMsg, message,"ACTIVATED successfully");
	    	
		}catch(Exception e) {
			logger.info("Exception from Action_Activate: "+e.getMessage());
			softAssert.assertTrue(false,"You want Activate, but Activate button not present");
		}
		softAssert.assertAll();
    	return flag;
	}
	
	//ACTIVATE Action P360 => To use this first search list item so that it comes at first position
	public static boolean clickOnActivateAction(WebDriver driver) throws InterruptedException {
    
    	int rowListCount = 1;
		jsExecutor  = (JavascriptExecutor)driver;
		action = new Actions(driver);
		String btnActivate_address = null;
		WebElement btnActivate = null;
		boolean flag = false;
		int loopcount = 0;
		
    	while(true) 
       {
    		loopcount++;
    		try {
    			btnActivate_address = "(//*[normalize-space(text())='Activate'])["+rowListCount+"]";
				//logger.info("btnDeactivate_address:- "+btnDeactivate_address);
    			btnActivate = driver.findElement(By.xpath(btnActivate_address));
			}catch(Exception e) {
				if(loopcount>20) {
					logger.info("Exception from clickOnActivateAction: "+e.getMessage());
					logger.info("Activate action button not present");
					softAssert.assertTrue(false,"You want to Activat, but activate button not present");
					ruae.clickOnP360Logo_RU();
					break;
				}
			}
			
  			
		if(btnActivate.isDisplayed() && btnActivate.isEnabled()) {
			logger.info("Is Activate button displayed and enabled: "+btnActivate.isDisplayed());
			logger.info("btnActivate_address:- "+btnActivate_address);
			logger.info("rowListCount: "+rowListCount);
			action.moveToElement(btnActivate).build().perform();
  			Thread.sleep(300);
  			jsExecutor.executeScript("arguments[0].click();", btnActivate);
  			//action.moveToElement(btnDeactivate).click().build().perform();
	  	  		logger.info("Clicked on Activate action button present under three dot");
	  			flag  = true;
	  			Thread.sleep(1000);
	  			break;
			}else {
				rowListCount++;
			}

       }
    	return flag;
    }
		 
		
	
}
