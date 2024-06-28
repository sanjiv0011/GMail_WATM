package com.gmail.utilities;

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

public class ClickOnAnyButton {

	public Logger logger = LogManager.getLogger(getClass());
	public WebDriverWait wait;
	public Actions action;
	public JavascriptExecutor jsExecutor;
	public CheckElementIsEnabled elementEnableState;

	// USE THIS TO CLICK ON ANY BUTTON TYPE ELEMENTS
	public boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String buttonName, String xpathAddress)
			throws InterruptedException {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info(
				"Method called 'callMeToClickOnAnyButtonWithNameAndXpath' and Caller method name: " + callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		boolean isClickedOnButton = false;
		try {
			logger.info("Element name and xpath >> " + buttonName + " and >> " + xpathAddress);
			WebElement button = driver.findElement(By.xpath(xpathAddress));
			if (button.isEnabled() && button.isDisplayed()) {

				wait.until(ExpectedConditions.elementToBeClickable(button));
				// button.click();
				action.moveToElement(button).build().perform();
				Thread.sleep(200);
				action.moveToElement(button).click().build().perform();
				isClickedOnButton = true;
				logger.info("clicke on button >> " + buttonName);
				Thread.sleep(1000);

			}

		} catch (Exception e) {
			logger.info("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
		}
		return isClickedOnButton;
	}

	// [FOR MULTIPLE BUTTON]USE THIS TO CLICK ON ANY BUTTON TYPE ELEMENTS
	public boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String[] buttonName,
			String[] xpathAddress) throws InterruptedException {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info(
				"Method called 'callMeToClickOnAnyButtonWithNameAndXpath' and Caller method name: " + callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		boolean isClickedOnButton = false;
		int totalButton = buttonName.length;
		logger.info("totalButton: " + totalButton);
		int iteratorCount = 0;

		while (totalButton != 0) {
			// TO EXTRACT BUTTONS
			String button_Lable = buttonName[iteratorCount];

			// TO EXTRACT XPATH
			String xpathAddress_Lable = xpathAddress[iteratorCount];

			try {
				logger.info("Element name is >>" + button_Lable + " and address is >> " + xpathAddress_Lable);
				WebElement button = driver.findElement(By.xpath(xpathAddress_Lable));
				if (button.isEnabled() && button.isDisplayed()) {
					wait.until(ExpectedConditions.elementToBeClickable(button));
					action.moveToElement(button).build().perform();
					Thread.sleep(200);
					action.moveToElement(button).click().build().perform();
					isClickedOnButton = true;
					logger.info("clicked on button >> " + button_Lable);
					isClickedOnButton = true;
					Thread.sleep(200);

				}

			} catch (Exception e) {
				logger.info("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
			}

			iteratorCount++;
			totalButton--;
		}

		return isClickedOnButton;
	}
}
