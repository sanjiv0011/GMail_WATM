package com.gmail.projectUtility;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollPageUpDown {

	public static Logger logger = LogManager.getLogger(ScrollPageUpDown.class);
	public static Actions action;
	public static JavascriptExecutor jsExecutor = null;
	public static WebDriverWait wait = null;
	public static void scrollPageUpDown(WebDriver driver, String staticElement_Address, String upDownType, int upDownPixcel,boolean wantToScrollTillSpeficElement, String scrollElmentVisibleText)
			throws InterruptedException {
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method called ScrollPageUpDown and caller method name: "+callerMethodName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		if(wantToScrollTillSpeficElement) {
			// Find the element by its visible text
			logger.info("Start finding your given text");
			String xpath = "//*[text()='"+scrollElmentVisibleText+"']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); // Added a closing parenthesis here
			WebElement scrollTillElement = driver.findElement(By.xpath(xpath));
			action.scrollToElement(scrollTillElement);
			logger.info("Found your given text: " + scrollTillElement.getText());

			Thread.sleep(2000);
			

		}else {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(staticElement_Address)));
			WebElement scrollAbleArea = driver.findElement(By.xpath(staticElement_Address));
			
			action.moveToElement(scrollAbleArea).perform();
			Thread.sleep(500);

			if (upDownType.equals("down")) {
				jsExecutor.executeScript("window.scrollBy(0," + upDownPixcel + ");");
				logger.info("Page scroll down by: " + upDownPixcel + " pixcel");
				Thread.sleep(1000);
			} else if (upDownType.equals("up")) {
				jsExecutor.executeScript("window.scrollBy(0," + "-" + upDownPixcel + ");");
				logger.info("Page scroll up by: " + upDownPixcel + " pixcel");
				Thread.sleep(1000);
			}
		}

	}

}
