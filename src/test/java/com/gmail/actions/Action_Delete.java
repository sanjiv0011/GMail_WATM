package com.gmail.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;


public class Action_Delete {
	
	public static final Logger logger = LogManager.getLogger(Action_Delete.class);
	public ReUseAbleElement ruae;
	public SoftAssert softAssert = new SoftAssert();
	
	public boolean delete(String searchKey,WebDriver driver, String message) throws InterruptedException
	{
		boolean flag = false;
		ruae = new ReUseAbleElement(driver);
		ruae.searchBox_1_RU(driver,searchKey);
    	if(!ruae.isSearchKeysNotFound_RU()){
    			ruae.clickOnActionButton_RU();
    			ruae.clickOnBtnDelete_RU();;
    			ReUseAbleElement.clickOnBtnYes_RU(driver);
    	    	
    	    	//CHECK THE DELETED CONFIRMATIONS MESSAGES
    	    	String alretMsg = ruae.snakeAlertMessagesDisplayedContent_RU();
    	    	if(alretMsg.equals(message)) {
    	    		softAssert.assertEquals(alretMsg, message,"DELETED successfully");
    	    		logger.info("===>>> "+message);
    	    		flag = true;
    	    	}else {
    	    		logger.info("Alert message content: "+alretMsg);
    	    	}
    	}
    	softAssert.assertAll();
    	return flag;
	}
	
}
