package com.gmail.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetDataIntoTextInputField {

	public Logger logger = LogManager.getLogger(getClass());
	public WebDriverWait wait;
	public Actions action;

	// USE THIS TO FILL THE DATA IN THE INPUT FILED ONLY FOR TEXT FIELD
	public boolean callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(WebDriver driver, String fieldName,
			String xpathAddress, String value) throws InterruptedException {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info(
				"Method called 'callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue' and Caller method name: "
						+ callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);

		boolean isClickedOnButton = false;
		try {
			logger.info("Element name and xpath >>" + fieldName + " and >> " + xpathAddress);
			WebElement inputField = driver.findElement(By.xpath(xpathAddress));
			wait.until(ExpectedConditions.elementToBeClickable(inputField));
			action.moveToElement(inputField).build().perform();
			Thread.sleep(200);
			inputField.sendKeys(Keys.CONTROL, "a");
			inputField.sendKeys(Keys.DELETE);
			Thread.sleep(200);
			inputField.sendKeys(value);
			logger.info("Value Enteterd into >> " + fieldName + " with data >> " + value);
			Thread.sleep(200);
			isClickedOnButton = true;
			Thread.sleep(1000);

		} catch (Exception e) {
			logger.info(
					"Exception from callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue >> " + e.getMessage());
		}
		return isClickedOnButton;
	}

	// [FOR MULTIPLE ENTRYIES] USE THIS TO FILL THE DATA IN THE INPUT FILED ONLY FOR
	// TEXT FIELD
	public boolean callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(WebDriver driver, String[] fieldName,
			String[] xpathAddress, String[] value) throws InterruptedException {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info(
				"Method called 'callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue' and Caller method name: "
						+ callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);

		boolean isDataEntered = false;
		int totalInputField = fieldName.length;
		logger.info("totalInputField: " + totalInputField);
		int iteratorCount = 0;

		while (totalInputField != 0) {
			// TO EXTRACT INPUT FIELDS
			String fieldName_Lable = fieldName[iteratorCount];

			// TO EXTRACT XPATH
			String xpathAddress_Lable = xpathAddress[iteratorCount];

			// TO EXTRACT VALUES
			String value_Lable = value[iteratorCount];

			try {
				logger.info("Element name is >>" + fieldName_Lable + " and address is >> " + xpathAddress_Lable
						+ " and values is >> " + value_Lable);
				WebElement inputField = driver.findElement(By.xpath(xpathAddress_Lable));
				wait.until(ExpectedConditions.elementToBeClickable(inputField));
				action.moveToElement(inputField).build().perform();
				Thread.sleep(200);
				inputField.sendKeys(Keys.CONTROL, "a");
				inputField.sendKeys(Keys.DELETE);
				Thread.sleep(200);
				inputField.sendKeys(value_Lable);
				logger.info("Value Enteterd in the input field: " + fieldName_Lable + " with iteration count: "
						+ iteratorCount);
				isDataEntered = true;
				Thread.sleep(200);

			} catch (Exception e) {
				logger.info("Exception from callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue >> "
						+ e.getMessage());
			}

			iteratorCount++;
			totalInputField--;
		}

		return isDataEntered;
	}
}
