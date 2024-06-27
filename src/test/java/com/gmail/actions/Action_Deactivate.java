package com.gmail.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;


public class Action_Deactivate {
	
	public static final Logger logger = LogManager.getLogger(Action_Deactivate.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	public static boolean  deactivate(WebDriver driver, String message) throws InterruptedException
	{
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Action_Deactivate caller method name: "+callerMethodName);
		
		boolean flag = false;
		ruae = new ReUseAbleElement(driver);
		
		try {
			boolean bol = clickOnDeactivateAction_RU(driver);;
			if(bol) {
				
					boolean isYesBtnPresent = ReUseAbleElement.clickOnBtnYes_RU(driver);
					if(!isYesBtnPresent) {
						logger.info("Searching for deactivate button");
						driver.findElement(By.xpath("(//span[normalize-space()='Deactivate'])[1]")).click();
					}
					
			}else {
				logger.info("Not click on the deactivate button");
			}
			
	    	//CHECK THE DEACTIVATE CONFIRMATIONS MESSAGES
	    	String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
	    	if(alretMsg.equals(message)) {
	    		
	    		logger.info("===>>> "+message);
	    		flag = true;
	    	}else {
	    		logger.info("Alert message content: "+alretMsg);
	    	}
			
	    	softAssert.assertEquals(message, alretMsg,"DEACTIVATED successfully");
	    	
		}catch(Exception e) {
			logger.info("Exception from Action_Deactivate: "+e.getMessage());
			softAssert.assertTrue(false,"You want to Deactivate but deacitvate button not present");
		}
		softAssert.assertAll();
    	return flag;
	}
	
	//DEACTIVATE Action P360 => To use this first search list item so that it comes at first position
	public static boolean clickOnDeactivateAction_RU(WebDriver driver) throws InterruptedException {
    
    	int rowListCount = 1;
		jsExecutor  = (JavascriptExecutor)driver;
		action = new Actions(driver);
		String btnDeactivate_address = null;
		WebElement btnDeactivate = null;
		boolean flag = false;
		int loopcount = 0;
		
    	while(true) 
       {
    		loopcount++;
    		try {
				btnDeactivate_address = "(//*[normalize-space(text())='Deactivate'])["+rowListCount+"]";
				//logger.info("btnDeactivate_address:- "+btnDeactivate_address);
	  			btnDeactivate = driver.findElement(By.xpath(btnDeactivate_address));
			}catch(Exception e) {
				if(loopcount >20) {
					logger.info("Exception from clickOnThreeDotActionbtnDeactivate: "+e.getMessage());
					softAssert.assertTrue(false,"Action button Deactivate address not present");
					ruae.clickOnP360Logo_RU();
					break;
				}
			}
			
  			
			if(btnDeactivate.isDisplayed() && btnDeactivate.isEnabled()) {
				logger.info("Is Deactivate button displayed and enabled: "+btnDeactivate.isDisplayed());
				logger.info("btnDeactivate_address:- "+btnDeactivate_address);
				logger.info("rowListCount: "+rowListCount);
				action.moveToElement(btnDeactivate).build().perform();
  	  			Thread.sleep(300);
  	  			jsExecutor.executeScript("arguments[0].click();", btnDeactivate);
  	  			//action.moveToElement(btnDeactivate).click().build().perform();
	  	  		logger.info("Clicked on Deactivate action button present under three dot");
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
