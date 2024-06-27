package com.gmail.utilities;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateToNewOpenTab {

	public Logger logger = LogManager.getLogger(getClass());
	public WebDriverWait wait;
	public Actions action;
	public ClickOnAnyButton clickOnAnyButton  = new ClickOnAnyButton();
	
	// USE THIS TO NAVIGATE NEW WINDOW
	public void changeBetweenTabs(WebDriver driver, String buttonNameOnclickNewTabOpen, String xpathAddressOnclickNewTabOpen)
			throws InterruptedException {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
		action = new Actions(driver);
		
		// Capture the original window handle[use it for come back]
		String originalWindow = driver.getWindowHandle();
		logger.info("originalWindow: " + driver.getTitle());
		// Capture all the open window tabs[show the number of current window opened]
		Set<String> existingWindows = driver.getWindowHandles();
		for (String tabs : existingWindows) {
			logger.info("Open Windows: " + tabs);
		}

		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver,buttonNameOnclickNewTabOpen, xpathAddressOnclickNewTabOpen);
		// Get the new window handle
		Set<String> newWindowHandles = driver.getWindowHandles();
		newWindowHandles.removeAll(existingWindows);
		String newWindowHandle = newWindowHandles.iterator().next();

		// Switch to the new tab
		driver.switchTo().window(newWindowHandle);
		logger.info("newWindowHandle: " + driver.getTitle());
		driver.getCurrentUrl();
		logger.info("New Window: " + driver.getTitle());
		logger.info("Switched to the new tab");
	}

}
