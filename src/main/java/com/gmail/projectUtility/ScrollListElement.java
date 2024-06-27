package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollListElement {

	public static Logger logger = LogManager.getLogger(ScrollListElement.class);
	public static Actions action;
	public static JavascriptExecutor jsExecutor = null;
	public static WebDriverWait wait = null;
	public static void scrollPageUpDown(WebDriver driver, String list_address)
			throws InterruptedException {
		
		//TO TRACK THE CALLER METHOD NAME
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method called ScrollListElement and caller method name: "+callerMethodName);
		
		//TO INITIALIAZS ANCTION CLASS AND JAVA SCRIPT EXECUTOR
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		WebElement element = driver.findElement(By.xpath(list_address));
		List <WebElement> listELement = driver.findElements(By.xpath(list_address));
		action.click(element).perform();
		
		
		for (int listCount = 0; listCount < listELement.size(); listCount++) {
			
			if(listCount == 0) {
				logger.info("Start scrolling the page");
			}
			
		    action.sendKeys(Keys.ARROW_DOWN).perform();
		    Thread.sleep(1000);
		    
		    if(listCount == listELement.size() - 1) {
				logger.info("Page Scrolling end");
			}
		}

	}

}
