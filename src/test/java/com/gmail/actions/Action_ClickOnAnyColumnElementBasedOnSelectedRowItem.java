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

public class Action_ClickOnAnyColumnElementBasedOnSelectedRowItem {
	
	public static final Logger logger = LogManager.getLogger(Action_ClickOnAnyColumnElementBasedOnSelectedRowItem.class);
	public static ReUseAbleElement ruae;
	public static SoftAssert softAssert = new SoftAssert();
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	
	
	//VIEW Action JKL => To use this first search list item so that it comes at first position
	public static boolean clickOnSelectedColumnElementBasedOnGivenRowCount(String list_address, int rowCount, int columnNumber, boolean wantToClickOnGivenColumnNumber, WebDriver driver) throws InterruptedException {
		
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("clickOnSelectedColumnElementBasedOnGivenRowCount called and caller method name: "+callerMethodName);
		
		jsExecutor  = (JavascriptExecutor)driver;
		action = new Actions(driver);
		
		try {
			String columnElementAddress = "("+list_address+")"+"["+rowCount+"]"+"//div["+columnNumber+"]";
			logger.info("columnElementAddress that you want to clck: "+columnElementAddress);
			WebElement columnElement = driver.findElement(By.xpath(columnElementAddress));
			action.moveToElement(columnElement).build().perform();
			Thread.sleep(100);
			if(wantToClickOnGivenColumnNumber) {
				action.moveToElement(columnElement).click().build().perform();
				logger.info("Click on the given row and column matched element: "+"Rowcount: "+rowCount+" ColumnCount: "+columnNumber);
			}
			Thread.sleep(1000);
			return true;
		}catch(Exception e) {
			return false;
		}
		
		
	}
  		
}
