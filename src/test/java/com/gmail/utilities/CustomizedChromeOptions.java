package com.gmail.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions; // Correct import

public class CustomizedChromeOptions {

	// THIS IS USED FOR THE CHROME OPTIONS TO BLOCK THE ADS, NOTIFICATIONS,
	// POP-BLOCKING, TO RUN INTO INCOGNITO MODE, OR HEADER LESS BROWSING, OR RUN IN
	// THE DEBUGGER MODE.
	public Logger logger = LogManager.getLogger(this.getClass());

	public ChromeOptions customizedChromeOptions(boolean blockAdsAndNotifications, boolean headlessBrowsing,
			boolean incognitoMode, boolean debuggerMode, int debuggerPort) {
		
		// TO INITIALIZE CHROME OPTIONS
		ChromeOptions options = new ChromeOptions(); // Use the correct class name and variable

		if (blockAdsAndNotifications) {
			// Disable pop-ups and intrusive ads
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-ads");
			logger.info("Disabled Ads and Notifications");
		}

		if (headlessBrowsing) {
			// FOR HEADER LESS BROWSING
			options.addArguments("--headless");
			logger.info("Entered into headless browsing");
		}

		if (incognitoMode) {
			// TO OPEN CHROME DRIVER INTO INCOGNITO MODE
			options.addArguments("--incognito");
			logger.info("Entered into incognito mode");
		}

		if (debuggerMode) {
			// TO USE CHROME DRIVER IN DEBUGGER MODE
			options.setExperimentalOption("debuggerAddress", "localhost:"+debuggerPort);
			logger.info("Entered into Debugging mode with port:"+debuggerPort);
		}
		
		return options;
	}
}
