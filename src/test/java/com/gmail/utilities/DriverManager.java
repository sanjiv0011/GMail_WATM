package com.gmail.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();
	
	//TO GET THE DRIVER
	public static WebDriver getDriver() {
		return driverThreadLocal.get();
	}
	
	//TO SET THE DRIVER
	public static void setDriver(WebDriver driver) {
		driverThreadLocal.set(driver);
	}
	
	//TO UNLOAD DRIRVER
	public static void unloadDriver() {
		driverThreadLocal.remove();
	}
}
