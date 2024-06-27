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


public class Action_Restore {
	
	public static final Logger logger = LogManager.getLogger(Action_Restore.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();

	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	public static boolean restore(WebDriver driver, String message) throws InterruptedException
	{
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Action_Restore caller method name: "+callerMethodName);
		
		boolean flag = false;
		try {
			ruae = new ReUseAbleElement(driver);
			clickOnRestoreAction(driver);
			
			boolean isYesBtnPresent = ReUseAbleElement.clickOnBtnYes_RU(driver);
			
			if(!isYesBtnPresent) {
				logger.info("Searching for Restore button");
				driver.findElement(By.xpath("(//span[normalize-space()='Restore'])[1]")).click();
			}
			
	    	//CHECK THE ACTIVATE CONFIRMATIONS MESSAGES
	    	String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
	    	if(alretMsg.equals(message)) {
	    		logger.info("===>>> "+message);
	    		flag = true;
	    	}else {
	    		logger.info("Alert message content: "+alretMsg);
	    	}
	    	softAssert.assertEquals(message, alretMsg,"RESTORED successfully");
			
		}catch(Exception e) {
			logger.info("Exception from Action_Restore: "+e.getMessage());
			softAssert.assertTrue(false,"You want Restore, but restore button not present");
		}
    	softAssert.assertAll();
    	return flag;
	}
	
	//RESTORE Action JKL => To use this first search list item so that it comes at first position
		public static boolean clickOnRestoreAction(WebDriver driver) throws InterruptedException {
	    
	    	int rowListCount = 1;
			jsExecutor  = (JavascriptExecutor)driver;
			action = new Actions(driver);
			String btnRestore_address = null;
			WebElement btnRestore = null;
			boolean flag = false;
			int loopcount = 0;
			
	    	while(true) 
	       {
	    		loopcount++;
	    		try {
					btnRestore_address = "(//*[normalize-space(text())='Restore'])["+rowListCount+"]";
	    			//btnRestore_address = "(//*[contains(normalize-space(),'Restore')])["+rowListCount+"]";
					//logger.info("btnRestore_address:- "+btnRestore_address);
		  			btnRestore = driver.findElement(By.xpath(btnRestore_address));
				}catch(Exception e) {
					if(loopcount >20) {
						logger.info("Exception from clickOnThreeDotActionbtnRestore: "+e.getMessage());
						softAssert.assertTrue(false,"Action button Restore address not present");
						ruae.clickOnP360Logo_RU();
						break;
					}
				}
				
	  			
				if(btnRestore.isDisplayed() && btnRestore.isEnabled()) {
					logger.info("Is Restore button displayed and enabled: "+btnRestore.isDisplayed());
					logger.info("btnRestore_address:- "+btnRestore_address);
					logger.info("rowListCount: "+rowListCount);
					action.moveToElement(btnRestore).build().perform();
	  	  			Thread.sleep(300);
	  	  			jsExecutor.executeScript("arguments[0].click();", btnRestore);
	  	  			//action.moveToElement(btnRestore).click().build().perform();
		  	  		logger.info("Clicked on Restore action button present under three dot");
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
