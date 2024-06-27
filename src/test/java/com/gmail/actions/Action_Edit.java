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


public class Action_Edit {

		public static final Logger logger = LogManager.getLogger(Action_Archive.class);
		public static ReUseAbleElement ruae;
		public static SoftAssert softAssert = new SoftAssert();
		public static JavascriptExecutor jsExecutor;
		public static Actions action;
		
		//TO CLICK ON Change ACTION BUTTON FROM THE LIST PRESENT UNDER THE THREE DOT BUTTON
		public static boolean edit(WebDriver driver) throws InterruptedException 
		{
			StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
			String callerMethodName = stackTraceElement[2].getMethodName();
			logger.info("Action_Edit caller method name: "+callerMethodName);
		
			
			boolean flag = false;
			Thread.sleep(500);
			int rowListCount = 1;
			jsExecutor  = (JavascriptExecutor)driver;
			action = new Actions(driver);
			String btnEdit_address = null;
			WebElement btnEdit = null;
			int loopcount = 0;
			
			while(true)
			{
				loopcount++;
				try {
					btnEdit_address = "(//*[contains(normalize-space(text()),'Edit')])["+rowListCount+"]";
		  			//logger.info("btnEdit_address:- "+btnEdit_address);
		  			btnEdit = driver.findElement(By.xpath(btnEdit_address));
				}catch(Exception e) {
					if(loopcount>20) {
						logger.info("Exception from clickOnThreeDotActionBtnChange: "+e.getMessage());
						softAssert.assertTrue(false,"Action button EDIT address not present");
						ruae.clickOnP360Logo_RU();
						break;
					}
				}
	  			
	  			if(btnEdit.isDisplayed() && btnEdit.isEnabled())
	  			{
	  				logger.info("Is edit button displayed and enabled: "+btnEdit.isDisplayed());
	  				logger.info("btnEdit_address:- "+btnEdit_address);
	  				action.moveToElement(btnEdit).build().perform();
	  				logger.info("rowListCount: "+rowListCount);
	  	  			Thread.sleep(300);
	  	  			jsExecutor.executeScript("arguments[0].click();", btnEdit);
	  	  			//action.moveToElement(btnEdit).click().build().perform();
		  	  		logger.info("Clicked on the Edit action button present under three dots");
		  			flag  = true;
		  			Thread.sleep(1000);
		  			break;
	  			}else {
	  				rowListCount++;
	  			}
			}
			
			softAssert.assertAll();
			return flag;
			
		}
}
