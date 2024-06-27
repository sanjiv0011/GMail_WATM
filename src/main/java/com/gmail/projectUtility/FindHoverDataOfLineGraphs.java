package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class FindHoverDataOfLineGraphs {

	//DECLARATIONS
	public static Logger logger = LogManager.getLogger(FindHoverDataOfLineGraphs.class);
	public static Actions action;
	public static JavascriptExecutor jsExecutor = null;
	
	//THIS METHOND FIND THE LINE GRAPHS TOOL TIP DATA, WHEN WE HOVER OVER X-AXIS ELEMENT
	public static void findLineGraphYAxisDataBasedOnXAxisDataFromLineGraphByHover(WebDriver driver,
			String listXAxisValues_Address, String xAxisUserValue,
			 String listGraphData_WRT_xaxis_Address, String toolTip_TitleAddress,
			String toolTips_ContentAddress) throws InterruptedException {
		
		//TO TRACE THE CALLING MEHTODS NAMES
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("findLineGraphYAxisDataBasedOnXAxisDataFromLineGraphByHover method called and caller method name : "+callerMethodName);
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor)driver;
		
		
		//TO FIND LIST OF X-AXIS WEBLEMEN ELEMENT
		List<WebElement> listXAxisValues = driver.findElements(By.xpath(listXAxisValues_Address));
		
		//TO FIND LIST OF GRAPHS DATA WITH RESPECT TO X-AXIS
		List<WebElement> listGraphData_WRT_xaxis = driver.findElements(By.xpath(listGraphData_WRT_xaxis_Address));
		
		
		logger.info("User given x-axis values: " + xAxisUserValue);
		boolean flag = false;

		if (xAxisUserValue == null || xAxisUserValue == "") {
			//THIS CONDITION WORK WHEN NOT PASSING X-AXIS VALUES, IN THIS CASE IT PRINT ALL THE CHART TOOL TIP CONTENT
			int xAxisValueCounter = 0;
			for (WebElement ydata : listGraphData_WRT_xaxis) {
				xAxisValueCounter++;
				logger.info("Iteration couont: "+xAxisValueCounter);
				try {
					action.moveToElement(ydata).build().perform();
					Thread.sleep(500);
					//action.moveByOffset(0, -20);
					Thread.sleep(300);
					tooltipTitle(driver, toolTip_TitleAddress);
					tooltipContent(driver, toolTips_ContentAddress);
					action.release(ydata);
					logger.info("Mouse release the hold element");
				} catch (MoveTargetOutOfBoundsException e) {
					logger.info("MoveTargetOutOfBoundsException: " + e.getMessage());
				}
			}
		} else {
			
			//THIS CONDITION WORK WHEN USER PASSING X-AXIS VALUES, IN THIS CASE IT WILL  PRINT ONLY USER SELECTED CHART TOOL TIP CONTENT
			int xAisValuesCounter = 0;
			for (WebElement ele_xaxis : listXAxisValues) {
				xAisValuesCounter++;
				String xAxisTextValue = ele_xaxis.getText();
				if (xAxisTextValue.equals(xAxisUserValue)) {
					logger.info("matched x-axis text values: " + xAxisTextValue);
					Thread.sleep(2000);
					String selectedDataFromGraph_WRT_xaxis_Address = "(" + listGraphData_WRT_xaxis_Address + ")[" + xAisValuesCounter + "]";
					WebElement hoverELement = driver.findElement(By.xpath(selectedDataFromGraph_WRT_xaxis_Address));
					logger.info("selectedDataFromGraph_WRT_xaxis_Address: " + selectedDataFromGraph_WRT_xaxis_Address);

					action.moveToElement(hoverELement).build().perform();
					Thread.sleep(1000);
					logger.info("Mouse hover over the element");
					action.clickAndHold(hoverELement).build().perform();
					Thread.sleep(500);
					logger.info("Mouse click on the hover element and hold it");
					action.moveByOffset(0, -20);
					logger.info("Mouse move 20 pixcel up side");
					Thread.sleep(500);
					flag = true;
					tooltipTitle(driver, toolTip_TitleAddress);
					tooltipContent(driver, toolTips_ContentAddress);
					Thread.sleep(500);
					action.release(hoverELement);
					logger.info("Mouse release the hold element");
				}
			}
			if (flag == false) {
				logger.info("Axis and user given values not matched");
			}
		}

	}

	//TO FIND TOOL-TIP CONTENT
	public static void tooltipContent(WebDriver driver, String toolTips_ContentAddress) throws InterruptedException {
		try {
			Thread.sleep(500);
			WebElement tooltips_Content = driver.findElement(By.xpath(toolTips_ContentAddress));
			Thread.sleep(500);
			String tooltip_Content[] = tooltips_Content.getText().split(":");
			String contentTitle = tooltip_Content[0].trim();
			String contentValue = tooltip_Content[1].trim();
			logger.info("Content Title: " + contentTitle + " and Content value: "
					+ contentValue);
			Thread.sleep(500);
		} catch (Exception e) {
			logger.info("Exception from tooltipContent: " + e.getMessage());
		}
	}

	//TO FIND TOOL-TIP TITLE
	public static void tooltipTitle(WebDriver driver, String toolTip_TitleAddress) throws InterruptedException {
		try {
			Thread.sleep(500);
			WebElement tooltips_Title = driver.findElement(By.xpath(toolTip_TitleAddress));
			Thread.sleep(500);
			String tooltip_TitleValues = tooltips_Title.getText();
			logger.info("tooltip_Title: " + tooltip_TitleValues);
			Thread.sleep(500);
		} catch (Exception e) {
			logger.info("Exception from tooltipTitle: " + e.getMessage());
		}
	}

}
