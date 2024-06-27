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

public class Action_View {
	
	public static final Logger logger = LogManager.getLogger(Action_Archive.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	
	//VIEW Action JKL => To use this first search list item so that it comes at first position
	public static boolean view(WebDriver driver) throws InterruptedException {
    
    	int rowListCount = 1;
		jsExecutor  = (JavascriptExecutor)driver;
		action = new Actions(driver);
		String btnView_address = null;
		WebElement btnView = null;
		boolean flag = false;
		int loopcount = 0;
		
    	while(true) 
       {
    		loopcount++;
    		try {
				btnView_address = "(//*[normalize-space(text())='View'])["+rowListCount+"]";
    			//btnView_address = "(//*[contains(normalize-space(),'Restore')])["+rowListCount+"]";
				//logger.info("btnView_address:- "+btnView_address);
	  			btnView = driver.findElement(By.xpath(btnView_address));
			}catch(Exception e) {
				if(loopcount >25) {
					logger.info("Exception from clickOnThreeDotActionbtnView: "+e.getMessage());
					softAssert.assertTrue(false,"Action button View address not present");
					ruae.clickOnP360Logo_RU();
					break;
				}
			}
			
  			
			if(btnView.isDisplayed() && btnView.isEnabled()) {
				logger.info("Is Restore button displayed and enabled: "+btnView.isDisplayed());
				logger.info("btnView_address:- "+btnView_address);
				logger.info("rowListCount: "+rowListCount);
				action.moveToElement(btnView).build().perform();
  	  			Thread.sleep(300);
  	  			jsExecutor.executeScript("arguments[0].click();", btnView);
  	  			//action.moveToElement(btnView).click().build().perform();
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
