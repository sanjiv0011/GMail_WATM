package com.gmail.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;

public class Action_Updated {
	
	public static final Logger logger = LogManager.getLogger(Action_Updated.class);
	public ReUseAbleElement ruae;
	public SoftAssert softAssert = new SoftAssert();
	
	public boolean updated(WebDriver driver, String messageUpdate, String messageAlreadyExist) throws InterruptedException
	{
		boolean flag = false;
		ruae = new ReUseAbleElement(driver);
		if(!ruae.isRequiredOrInvalidMessageDisplayed_RU()) {
			String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
			if(alretMsg.equals(messageUpdate)) {
	    		softAssert.assertEquals(messageUpdate, alretMsg, "UPDATED successfully");
	    		logger.info("===>>> "+messageUpdate);
	    		flag = true;
		   	}else if(alretMsg.equals(messageAlreadyExist)){
        		Assert.assertEquals(alretMsg,messageAlreadyExist,"Already Exist");
        		logger.info("===>>> "+messageAlreadyExist);
        		Thread.sleep(500);
        		ruae.clickOnCancelButton_1_RU();
        	}else {
		    		logger.info("Alert message content: "+alretMsg);
		    	}	
		}
		else {
			ruae.clickOnCancelButton_1_RU();
		}
		softAssert.assertAll();
		return flag;
	}
	
}
