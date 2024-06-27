package com.gmail.utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;



public class FindAndClickOnThreeDotButtonInGridView {

	//OBECTS
	public static Logger logger = LogManager.getLogger(FindAndClickOnThreeDotButtonInGridView.class);
	public ClickOnAnyColumnElementBasedOnSelectedRowItem clickOnAnyColumnItems = new ClickOnAnyColumnElementBasedOnSelectedRowItem();
	public static SoftAssert softAssert = new SoftAssert();
	public static Actions action = null;
	public static JavascriptExecutor jsExecutor;
	public static ReUseAbleElement ruae;
	
	//METHOD
	public int findDataFromGridListAndClickOnThreeDot(WebDriver driver,String gridViewUniqueElementListAddress, String searchKey,
			int searchKeyColumnIndex, boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey)
			throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
  		String callerMethodName = stackTraceElement[2].getMethodName();
  		logger.info("Method called: findDataFromGridListAndClickOnThreeDot and Caller method name: "+callerMethodName);
  		
  		List<WebElement> gridViewUniqueElementList = driver.findElements(By.xpath(gridViewUniqueElementListAddress));
  		logger.info("WebElement List Address: "+gridViewUniqueElementList);
  		ruae.searchBox_1_RU(driver, searchKey);
  		logger.info("1");
		if (!driver.getPageSource().contains("Clear the filter to see all")) {
			logger.info("2");
			int iterationCount = 0;
			try {
				Thread.sleep(2000);

				for (WebElement ele : gridViewUniqueElementList) {
					logger.info("3");
					iterationCount++;
					String capturedElement = ele.getText().trim();
					if (searchKey.equals(capturedElement)) {
						logger.info("Matched recored: " + capturedElement + " and matched records list count: "
								+ iterationCount);

						clickOnAnyColumnItems.clickOnSelectedColumnElementBasedOnGivenRowCount(
								gridViewUniqueElementListAddress, iterationCount, 1, true, driver);
						break;
					}
				}
			} catch (Exception e) {
				logger.info("Exception from findDataFromGridListAndClickOnThreeDot: " + e.getMessage());
			}
			logger.info("listRowCount: " + iterationCount);
			return iterationCount;
		} else {
			logger.info("No Data Found");
			return -1;
		}

	}
	
}
