package com.gmail.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;

public class Action_Archive {
	
	public static final Logger logger = LogManager.getLogger(Action_Archive.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();

	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	public static boolean archive(WebDriver driver, String message) throws InterruptedException
	{	
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Action_Archive caller method name: "+callerMethodName);
	
		boolean flag = false;
		ruae = new ReUseAbleElement(driver);
		try {
			clickOnArchiveAction(driver);
			
			boolean isYesButtonPresent = ReUseAbleElement.clickOnBtnYes_RU(driver);
			if(!isYesButtonPresent) {
				logger.info("Searching for Archibe button");
				driver.findElement(By.xpath("(//span[normalize-space()='Archive'])[1]")).click();
				
			}
			
			
			
	    	//CHECK THE ARCHIVED CONFIRMATIONS MESSAGES
	    	String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
	    	if(alretMsg.equals(message)) {
	    		logger.info("===>>> "+message);
	    		flag = true;
	    	}else {
	    		logger.info("Alert message content: "+alretMsg);
	    	}
	    	softAssert.assertEquals(message, alretMsg,"ARCHIVED successfully");
		}catch(Exception e) {
			logger.info("Exeption from Action_Archive: "+e.getMessage());
			softAssert.assertTrue(false, "You want archive but, this button not present");
		}
    	softAssert.assertAll();
    	return flag;
	}
	
	//ARCHIVE Action P360 => To use this first search list item so that it comes at first position
		public static boolean clickOnArchiveAction(WebDriver driver) throws InterruptedException {
	    
	    	int rowListCount = 1;
			jsExecutor  = (JavascriptExecutor)driver;
			action = new Actions(driver);
			String btnArchive_address = null;
			WebElement btnArchive = null;
			boolean flag = false;
			int loopcount = 0;
			
	    	while(true) 
	       {
	    		loopcount++;
	    		try {
					btnArchive_address = "(//*[normalize-space(text())='Archive'])["+rowListCount+"]";
					//btnArchive_address = "(//*[contains(normalize-space(),'Archive')])["+rowListCount+"]";
					//logger.info("btnArchive_address:- "+btnArchive_address);
		  			btnArchive = driver.findElement(By.xpath(btnArchive_address));
				}catch(Exception e) {
					if(loopcount >20) {
						logger.info("Exception from clickOnArchiveAction: "+e.getMessage());
						softAssert.assertTrue(false,"Action button Archive address not present");
						ruae.clickOnP360Logo_RU();
						break;
					}
				}
				
	  			
				if(btnArchive.isDisplayed() && btnArchive.isEnabled()) {
					logger.info("Is Archive button displayed and enabled: "+btnArchive.isDisplayed());
					logger.info("btnArchive_address:- "+btnArchive_address);
					logger.info("rowListCount: "+rowListCount);
					action.moveToElement(btnArchive).build().perform();
	  	  			Thread.sleep(300);
	  	  			jsExecutor.executeScript("arguments[0].click();", btnArchive);
	  	  			//action.moveToElement(btnArchive).click().build().perform();
		  	  		logger.info("Clicked on Archive action button present under three dot");
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
