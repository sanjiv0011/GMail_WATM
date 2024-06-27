package com.gmail.projectUtility;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.reactivex.rxjava3.functions.Consumer;

public class DatePicker {

	public static final Logger logger = LogManager.getLogger(DatePicker.class);
	public static Actions action;
	public static WebDriverWait wait;
	public static SoftAssert softAssert = new SoftAssert();
	// Create a JavascriptExecutor instance
	public static JavascriptExecutor js;

	// DATE PLACE HOLDER ADDRESS THIS IS USED CLEAR ALREADY PRESENT DATE
	public static String textDatePlaceHolder_1_RU = "(//input[@placeholder='MM/DD/YYYY'])[1]";
	public static String textDatePlaceHolder_2_RU = "(//input[@placeholder='MM/DD/YYYY'])[2]";
	public static String textDatePlaceHolder_3_RU = "(//input[@placeholder='MM/DD/YYYY'])[3]";

	// DATE ICON ADDRESS
	public static String iconDate_1_RU = "(//button[@aria-label='Choose date'])[1]";
	public static String iconDate_2_RU = "(//button[@aria-label='Choose date'])[2]";
	public static String iconDate_3_RU = "(//button[@aria-label='Choose date'])[3]";

	// DATE TOGGLE BUTTON, YEAR LIST, DATE LIST, NEXT BUTTON, PREVIOUS BUTTON,
	// CURRENT MONTH YEAR DISPLAYED ADDRESS
	public static String toggleBtnYearAndDate = "//button[@aria-label=\"calendar view is open, switch to year view\"]";
	public static String selectYear = "//div[contains(@class, 'MuiYearCalendar-root')]//div";
	public static String arrowNextMonth = "//button[@title=\"Next month\"]";
	public static String arrowPreviousMonth = "//button[@title=\"Previous month\"]";
	public static String selectDate = "//div[@role='row']//button";
	public static String elementCurrentMonthYearDisplayed = "(//div[contains(@class,'MuiPickersFadeTransitionGroup-root')])[1]";

	public static String btnCross_address = "(//button[@title='Close'])[1]";

	// WAY 3
	// ======================= this is used when month and year are in the grid
	// format==================//
	public static void DatePicker_GenericMethod_WhenYearAndDateListPresent(WebDriver driver, String yourDate, int x)
			throws InterruptedException {
		js = (JavascriptExecutor) driver;

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String callerMethodName = stackTraceElements[2].getMethodName();
			action = new Actions(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			logger.info("Enter inside method DatePicker class and caller methods name: " + callerMethodName);

			// my date setting
			String myDate[] = yourDate.split("[\\s\\-\\.]");
			String year = myDate[2];
			String month = myDate[1];
			String date = myDate[0];
			logger.info("User given Date: " + date + " Month: " + month + " year: " + year);

			// ========START=========TO SELECT DATE PICKER ICON 1,2,3=============//
			if (x == 1) {
				WebElement datePlaceholder1 = driver.findElement(By.xpath(textDatePlaceHolder_1_RU));
				datePlaceholder1.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(500);
				datePlaceholder1.sendKeys(Keys.DELETE);
				Thread.sleep(500);
				driver.findElement(By.xpath(iconDate_1_RU)).click();
				Thread.sleep(1000);
				logger.info("Clicked on the date picker icon: " + x);
			} else if (x == 2) {
				WebElement datePlaceholder2 = driver.findElement(By.xpath(textDatePlaceHolder_2_RU));
				datePlaceholder2.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(500);
				datePlaceholder2.sendKeys(Keys.DELETE);
				Thread.sleep(500);
				driver.findElement(By.xpath(iconDate_2_RU)).click();
				Thread.sleep(1000);
				logger.info("Clicked on the date picker icon: " + x);
			} else if (x == 3) {
				WebElement datePlaceholder3 = driver.findElement(By.xpath(textDatePlaceHolder_3_RU));
				datePlaceholder3.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(500);
				datePlaceholder3.sendKeys(Keys.DELETE);
				Thread.sleep(500);
				driver.findElement(By.xpath(iconDate_3_RU)).click();
				Thread.sleep(1000);
				logger.info("Clicked on the date picker icon: " + x);
			}
			// ========END=========TO SELECT DATE PICKER ICON 1,2,3=============//

			// to click on the toggle button for year and date selection
			Thread.sleep(1000);
			driver.findElement(By.xpath(toggleBtnYearAndDate)).click();
			logger.info("Clicked on the toggle button for date and year selection");
			Thread.sleep(2000);

			// TO MATCH AND PICK THE YEAR
			List<WebElement> yearGrid = driver.findElements(By.xpath(selectYear));
			boolean flag = false;
			for (WebElement yearElement : yearGrid) {
				String yr = yearElement.getText();
				if (yr.equals(year)) {
					Thread.sleep(500);
					logger.info("Selected year: " + yr);
					yearElement.click();
					Thread.sleep(1000);
					flag = true;
					break;
				}
			}
			if (flag != true) {
				logger.info("Year not selected");
			}

			// =========START======TO SELECT THE MONTH=============//
			// to match month find already present month "path_currentMonthYearDisplayed"
			Thread.sleep(500);
			String monthyear = driver.findElement(By.xpath(elementCurrentMonthYearDisplayed)).getText();
			String arr[] = monthyear.split(" ");
			String mon = arr[0];
			logger.info("Displayed month and year: " + monthyear);
			String monthArray[] = { "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };

			int displayedMonth = 0;
			int inputMonth = 0;
			for (int i = 1; i < monthArray.length; i++) {
				if (mon.equalsIgnoreCase(monthArray[i])) {
					displayedMonth = i;
				}
				if (month.equalsIgnoreCase(monthArray[i])) {
					inputMonth = i;
				}
			}
			while (displayedMonth > inputMonth) {
				displayedMonth = displayedMonth - 1;
				driver.findElement(By.xpath(arrowPreviousMonth)).click();
				Thread.sleep(2000);
			}
			while (displayedMonth < inputMonth) {
				displayedMonth = displayedMonth + 1;
				driver.findElement(By.xpath(arrowNextMonth)).click();
				Thread.sleep(2000);

			}
			if (displayedMonth == inputMonth) {
				logger.info("selected month name is: " + monthArray[inputMonth]);
			} else {
				logger.info("Month not selected");
			}
			// =========END======TO SELECT THE MONTH=============//

			// TO MATCH AND SELECT THE DATE
			List<WebElement> allDates = driver.findElements(By.xpath(selectDate));
			Thread.sleep(1000);
			boolean flag2 = false;
			for (WebElement dateElement : allDates) {
				String dt = dateElement.getText();
				if (dt.equals(date)) {
					try {
						Thread.sleep(500);
						flag2 = true;

						if (dateElement.isEnabled()) {
							dateElement.click();
							logger.info("Selected date: " + dt);
						}
						Thread.sleep(1000);
						break;
					} catch (Exception e) {
						softAssert.assertEquals(flag2, true, "Check date is click able or not");
						logger.info("Exception from DatePicker_GenericMethod_WhenYearAndDateListPresent: "
								+ e.getMessage());
					}
				}
			}
			if (flag2 != true) {
				logger.info("Date not selected");
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			softAssert.assertTrue(false, "To check the date");
			logger.info("Exception from DatePicker_GenericMethod_WhenYearAndDateListPresent: " + e.getMessage());
		}
	}

	/*
	 * //WAY 2 //======================= this is used when there is no dropdown for
	 * the month and year ==================// public static void
	 * DatePicker_GenericMethod_WithoutDropDown(
	 * 
	 * WebElement path_YearDateToggleBtn, WebElement selectMonth, WebElement
	 * iconNextMonth, WebElement iconPreviousMonth, List<WebElement> path_dateGrid,
	 * List<WebElement> path_yearGrid, String yourDate) throws InterruptedException
	 * {
	 * 
	 * // Split the given date String myDate[] = yourDate.split(" "); String year =
	 * myDate[2]; int int_year = Integer.parseInt(year); String month = myDate[1];
	 * String date = myDate[0]; String usergiven_month_year = month+" "+year;
	 * logger.info("User given year: " + year + " Month: " + month + " Date: " +
	 * date);
	 * 
	 * // to match month find already present month "path_currentMonthYearDisplayed"
	 * Thread.sleep(500); String monthyear = selectMonth.getText();
	 * //logger.info(monthyear); String arr[] = monthyear.split(" "); String mon =
	 * arr[0]; String yr = arr[1]; logger.info("Displayed month name: " +
	 * mon+" Displayed year: "+yr); int int_yr = Integer.parseInt(yr); String
	 * monthArray[] = {"January", "February", "March", "April", "May", "June",
	 * "July", "August", "September", "October", "November", "December"};
	 * 
	 * //TO MATCH YEAR while(true) { Thread.sleep(500); String currentmonthyear =
	 * selectMonth.getText(); String log =
	 * ("Displayed month year: "+currentmonthyear+" User month year: "
	 * +usergiven_month_year);
	 * 
	 * 
	 * if(currentmonthyear.equals(usergiven_month_year)) { log =
	 * ("Displayed month year: "+currentmonthyear+" User month year: "
	 * +usergiven_month_year+" Matched"); logger.info(log); Thread.sleep(1000);
	 * break; }else { if(int_year == int_yr) { logger.info("Year matched"); int
	 * displayedMonth = 0; int inputMonth = 0; for (int i = 1; i <
	 * monthArray.length; i++) { if (mon.equalsIgnoreCase(monthArray[i])) {
	 * displayedMonth = i; } if (month.equalsIgnoreCase(monthArray[i])) { inputMonth
	 * = i; } } while (displayedMonth > inputMonth) { displayedMonth =
	 * displayedMonth - 1; iconPreviousMonth.click(); Thread.sleep(1000); } while
	 * (displayedMonth < inputMonth) { displayedMonth = displayedMonth + 1;
	 * iconNextMonth.click(); Thread.sleep(1000);
	 * 
	 * } if (displayedMonth == inputMonth) {
	 * logger.info("Month is selected and name is: " + monthArray[inputMonth]); }
	 * else { logger.info("Month not selected"); }
	 * 
	 * } else if(int_year > int_yr) { iconNextMonth.click(); Thread.sleep(500); }
	 * else if(int_year < int_yr) { iconPreviousMonth.click(); Thread.sleep(500); }
	 * 
	 * } }
	 * 
	 * 
	 * //TO MATCH DATE for (WebElement dateElement : path_dateGrid) { String dt =
	 * dateElement.getText(); if (dt.equals(date)) { dateElement.click();
	 * System.out.println("User date: "+dt+" Matched"); Thread.sleep(1000); break; }
	 * }
	 * 
	 * }
	 * 
	 * 
	 */

//		//WAY 4
//		//======================= used when drop down present for month and year==================//
//	
//	public static void DatePicker_GenericMethod_WithDropDown()
//	{
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver= new ChromeDriver();
//		
//		// path for date picker "path_DatePicker"
//		driver.findElement(By.xpath("")).click();
//		
//		// use it  if drop come under select tab"path_monthDropDown"
//		Select month_drp = new Select(driver.findElement(By.xpath("")));
//		month_drp.selectByVisibleText("July");
//		
//		// use it  if drop come under select tab"path_yearDropDown"
//		Select year_drp = new Select(driver.findElement(By.xpath("")));
//		year_drp.selectByVisibleText("2023");
//		
//		
//		
//	}

	// WAY 1
	// SELECT THE DATE WHEN DATE IS IN GRID FORMAT ONLY AND MONTH YEAR IN BETWEEN
	// NEXT AND PRIVIOUS BUTTON
	public static void DatePicker_GenericMethod_WhenDateGridOnlyPresent(WebDriver driver, String yourDate)
			throws Throwable {
		js = (JavascriptExecutor) driver;

		try {
			// TO TRACK THE CALLER METHOD NAME
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String callerMethodName = stackTraceElements[2].getMethodName();
			action = new Actions(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			logger.info("Enter inside method DatePicker class and caller methods name: " + callerMethodName);

			String monthYear_address = "//div[contains(@class,'MuiPickersCalendarHeader-transitionContainer')]";
			String previousButton_address = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton'])[1]";
			String nextButton_address = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton'])[2]";
			String dateList_address = "//div[@class='MuiPickersCalendar-week']//div[@role='presentation']";

			// User given date setting
			String myDate[] = yourDate.split(" ");
			String year = myDate[2];
			String month = myDate[1];
			String date = myDate[0];
			logger.info("User given year: " + year + " Month: " + month + " Date: " + date);

			// to match month find already present month "path_currentMonthYearDisplayed"
			Thread.sleep(500);
			String monthyear = driver.findElement(By.xpath(monthYear_address)).getText();
			// Displayed date setting
			String arr[] = monthyear.split(" ");
			String yr = arr[1];
			String mon = arr[0];
			logger.info("Displayed month name: " + mon + " and year is: " + yr);
			String monthArray[] = { "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };

			// TO SELECT THE YEAR
			if (Integer.parseInt(year) == Integer.parseInt(yr)) {
				logger.info("Year Matched: " + year);
				softAssert.assertEquals(year, yr, "To match year value: ");
			} else {
				while (Integer.parseInt(year) > Integer.parseInt(yr)) {
					try {
						driver.findElement(By.xpath(nextButton_address)).click();
						Thread.sleep(500);
					} catch (Exception e) {
						logger.info("Exception from Year checking : " + e.getMessage());
					}
				}
				while (Integer.parseInt(year) < Integer.parseInt(yr)) {
					try {
						driver.findElement(By.xpath(previousButton_address)).click();
						Thread.sleep(500);
					} catch (Exception e) {
						logger.info("Exception from Year checking : " + e.getMessage());
					}
				}
			}

			// TO FIND OUT USER GIVEN MONTH POSITION THE MONTH
			int displayedMonth = 0;
			int inputMonth = 0;
			for (int i = 0; i < monthArray.length; i++) {
				
				if (mon.equalsIgnoreCase(monthArray[i])) {
					displayedMonth = i + 1; // IT WILL SELECT THE DISPLAYED MONTH POSITION
					logger.info("displayedMonth int postition: " + displayedMonth);
				}
				
				if (month.equalsIgnoreCase(monthArray[i])) {
					inputMonth = i + 1; // IT WILL GIVE THE GIVEN MONTH POSITION
					logger.info("inputMonth int postition: " + inputMonth);
				}
			}

			// TO SELECT THE CORRECT MONTH
			boolean monthconfirmation = false;

			if (displayedMonth == inputMonth) {
				logger.info("selected month name is: " + monthArray[inputMonth - 1]);
				monthconfirmation = true;
			} else {
				WebElement btnPrevious = null;
				WebElement btnNext = null;
				while (displayedMonth > inputMonth) {
					try {
						
						if(callerMethodName.equals("setPaymentStartDate") || callerMethodName.equals("setPaymentEndDate")) {
							btnPrevious = driver.findElement(By.xpath(previousButton_address));
						}else {
							btnPrevious = driver.findElement(By.xpath("(//span[@class='MuiIconButton-label'])[5]"));
						}
						
						if (btnPrevious.isEnabled()) {
							action.moveToElement(btnPrevious).build().perform();
							Thread.sleep(1000);
							btnPrevious.click();

							Thread.sleep(500);
							logger.info("clicked on the monthYear Privious button");
							displayedMonth = displayedMonth - 1;
						}
					} catch (Exception e) {
						logger.info("User given month is not select-able: " + month);
						logger.info("Exception Month Selection : " + e.getMessage());
						break;
					}

				}
				while (displayedMonth < inputMonth) {
					try {
						
						if(callerMethodName.equals("setPaymentStartDate") || callerMethodName.equals("setPaymentEndDate")) {
							btnNext = driver.findElement(By.xpath(nextButton_address));
						}else {
							btnNext = driver.findElement(By.xpath("(//span[@class='MuiIconButton-label'])[6]"));
						}
						
						
						if (btnNext.isEnabled()) {
							action.moveToElement(btnNext).build().perform();
							Thread.sleep(1000);
							btnNext.click();
							Thread.sleep(500);
							displayedMonth = displayedMonth + 1;
							logger.info("clicked on the monthYear Next button");
						}
					} catch (Exception e) {
						logger.info("User given month is not select-able: " + month);
						logger.info("Exception Month Selection: " + e.getMessage());
						break;
					}
				}

			}
			if (!monthconfirmation && displayedMonth == inputMonth) {
				logger.info("selected month name is: " + monthArray[inputMonth - 1]);
			} else if (displayedMonth != inputMonth) {
				logger.info("Month not selected");
				softAssert.assertTrue(false, "User given month is not select-able: ");
			}

			// TO SELECT DATE
			List<WebElement> allDates = driver.findElements(By.xpath(dateList_address));
			Thread.sleep(1000);
			boolean flag2 = false;
			for (WebElement dateElement : allDates) {
				String dt = dateElement.getText();
				String dateCssValue[] = null;
				if (dt.equals(date)) {

					try {

						// Function to get the mouse cursor property using JavaScript
						final String getCursorPropertyScript = "return getComputedStyle(arguments[0]).cursor;";
						Consumer<WebElement> captureMouseBehavior = (element) -> {
							Thread.sleep(1000);
							String cursorProperty = (String) js.executeScript(getCursorPropertyScript, element);
							logger.info("Cursor Behavior over Element: " + cursorProperty);
						};

						// Perform the mouseover actions and capture cursor behavior
						String dateCssColorBefore = dateElement.getCssValue("Background");
						System.out.println("dateCssColor :" + dateCssColorBefore);
						action.moveToElement(dateElement).build().perform();
						Thread.sleep(1000);
						String dateCssColorAfter = dateElement.getCssValue("Background");
						System.out.println("dateCssColor :" + dateCssColorAfter);
						Thread.sleep(1000);
						hoverOverElement(driver, dateElement);
						Thread.sleep(500);
						captureMouseBehavior.accept(dateElement);
						dateCssValue = dateElement.getCssValue(dt).split("-");
						System.out.println("dateCssValue: " + dateCssValue[0]);

						// DATE CSS VALUE
						Set<String> valuesToCheck = new HashSet<String>(
								Arrays.asList("background", "baseline", "backface", "align", "app"));
						try {
							if (valuesToCheck.contains(dateCssValue[0])) {
								Thread.sleep(500);
								dateElement.click();
								flag2 = true;
								logger.info("Clicked on the given date: " + dt);
								Thread.sleep(500);
								softAssert.assertTrue(true, "User given date is clickable: " + dt + " ==>>");
							} else if (callerMethodName.equals("setWorkoutPublishDate")
									&& dateCssValue[0].equals("animation")) {
								Thread.sleep(500);
								dateElement.click();
								flag2 = true;
								logger.info("Clicked on the given date: " + dt);
								Thread.sleep(500);
								softAssert.assertTrue(true, "User given date is clickable: " + dt + " ==>>");
							} else if (callerMethodName.equals("setPaymentEndDate")
									&& dateCssValue[0].equals("animation")) {
								Thread.sleep(500);
								dateElement.click();
								flag2 = true;
								logger.info("Clicked on the given date: " + dt);
								Thread.sleep(500);
								softAssert.assertTrue(true, "User given date is clickable: " + dt + " ==>>");
							} else if (dateCssValue[0].equals("animation")) {
								logger.info("User given date is not clickable: " + dt);
								softAssert.assertTrue(false, "User given date is not clickable: " + dt + " ==>>");
							}
						} catch (AssertionError ae) {
							logger.info("AssertionError while date selection: " + ae.getMessage());
							softAssert.assertTrue(flag2,
									"AssertionError:- User given date is NOT clickable: " + dt + " ==>>");
						}

					} catch (Exception e) {
						logger.info("Excepton while date selection: " + e.getMessage());
						softAssert.assertTrue(flag2, "Exception:- User given date is NOT clickable: " + dt + " ==>>");
					}
				}
			}

		} catch (Exception e) {
			logger.info("Exception from DatePicker_GenericMethod_WhenDateGridOnlyPresent: " + e.getMessage());
		}
	}

	// WAY 5
	// TO SELECT THE DATE BY DIRECT SENDING DATA IN THE TEXT BOX
	public static void setDateByDirectSendingDateVauleInTheTextBox(WebDriver driver, String leaveEndDate, int y)
			throws InterruptedException {
		js = (JavascriptExecutor) driver;

		try {

			// TO SELECT DATE FIRST DATE WITHOUT USING DATE PICKER
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String callerMethodName = stackTraceElements[2].getMethodName();
			action = new Actions(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			logger.info("Enter inside method DatePicker class and caller methods name: " + callerMethodName);

			String firstDateHolder_RU = "(//input[contains(@class,'MuiInput-inputMarginDense')])[1]";
			// TO SELECT DATE SECONDS DATE WITHOUT USING DATE PICKER
			String SecondsDateHolder_RU = "(//input[contains(@class,'MuiInput-inputMarginDense')])[2]";
			String thirdDateHolder_RU = "(//input[contains(@placeholder,'MM/DD/YYYY')])[3]";
			WebElement dateHolder = null;
			if (y == 1) {
				dateHolder = driver.findElement(By.xpath(firstDateHolder_RU));
			} else if (y == 2) {
				dateHolder = driver.findElement(By.xpath(SecondsDateHolder_RU));
			} else if (y == 3) {
				dateHolder = driver.findElement(By.xpath(thirdDateHolder_RU));
			}

			dateHolder.sendKeys(Keys.CONTROL, "a");
			dateHolder.sendKeys(Keys.DELETE);
			String[] date = leaveEndDate.split("[\\s\\-\\.]");
			String dt = date[0];
			String mt = date[1];
			String yr = date[2];
			System.out.println("Input date: " + dt + " month: " + mt + " year: " + yr);
			int x = 0;
			String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
					"October", "November", "December" };
			for (String mon : months) {
				x++;
				if (mon.equals(mt)) {
					break;
				}
			}
			String z = null;
			if (x < 10) {
				z = "0" + x;
			} else {
				z = String.valueOf(x);
			}
			String xAsString = String.valueOf(z);
			dateHolder.sendKeys(xAsString);
			Thread.sleep(300);
			dateHolder.sendKeys(dt);
			Thread.sleep(300);
			dateHolder.sendKeys(yr);
			Thread.sleep(300);
		} catch (Exception e) {
			softAssert.assertTrue(false, "To check the date");
			logger.info("Exception from setDateByDirectSendingDateVauleInTheTextBox: " + e.getMessage());
		}
		softAssert.assertAll();
	}

	// Function to simulate mouse hover using JavaScript
	public static void hoverOverElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "var element = arguments[0];" + "var mouseEvent = document.createEvent('MouseEvents');"
				+ "mouseEvent.initEvent('mouseover', true, true);" + "element.dispatchEvent(mouseEvent);";
		js.executeScript(script, element);
	}

}
