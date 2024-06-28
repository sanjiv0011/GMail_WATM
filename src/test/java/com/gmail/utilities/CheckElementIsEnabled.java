package com.gmail.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckElementIsEnabled {

	public  Logger logger = LogManager.getLogger(getClass());
	public  WebDriverWait wait;
	public  Actions action;
	public  JavascriptExecutor jsExecutor;

	public  boolean isElementEnabled(WebDriver driver, String elementAddress, String enableDisableIdentifier ) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(elementAddress));
		Boolean isEnabled = (Boolean) jsExecutor.executeScript("return !arguments[0].hasAttribute('"+enableDisableIdentifier+"');", element);
		return isEnabled;
	}

}
