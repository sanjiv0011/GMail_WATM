package com.gmail.projectUtility;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class FindThreeDotAndClick {

	public static final Logger logger = LogManager.getLogger(FindThreeDotAndClick.class);
	public static SoftAssert softAssert = new SoftAssert();
	public static Actions action = null;
	public static JavascriptExecutor jsExecutor;

	public static int findThreedActionButtonAndClick(String list_Address, List<WebElement> listElement,
			WebDriver driver, String searchKey, int searchKeyColumnIndex, boolean wantToClickOnThreeDot,
			boolean wantToclickOnFindSearckKey) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();
		logger.info("findThreedActionButtonAndClick method called and Caller method name: " + callerMethodName);
		logger.info("wantToClickOnThreeDot: " + wantToClickOnThreeDot);
		logger.info("wantToclickOnFindSearckKey: " + wantToclickOnFindSearckKey);

		List<WebElement> listElement_Inner = driver.findElements(By.xpath(list_Address));

		// List_address: //div[contains(@class,'flex flex-col gap-2 items-center')]
		// Pointer_address: //div[contains(@class,'neu-more-v')]

		// COMMAN POINTER ADDRESS
		String pointer_Address = "//div[contains(@class,'neu-more-v')]";
		String pointerList_Address = "(" + list_Address + pointer_Address + ")";
		for (WebElement item : listElement_Inner) {
			// logger.info("List items: "+item.getText());
		}

		int listRowCount = 0;
		boolean isThreeDotButtonPresent = false;

		if (callerMethodName.equalsIgnoreCase("findMyRegisteredClassAndonThreeDotOption")) {
			String searchKeyFormat[] = searchKey.split(" ");
			String newSearchKey = null;
			if (Integer.parseInt(searchKeyFormat[1].replace(",", "")) < 10) {
				searchKeyFormat[1] = String.valueOf(Math.abs(Integer.parseInt(searchKeyFormat[1].replace(",", ""))));

				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < searchKeyFormat.length; i++) {
					builder.append(searchKeyFormat[i]);
					if (i == 1) {
						builder.append(",");
					}
					if (i < searchKeyFormat.length - 1) {
						builder.append(" ");
					}
				}
				newSearchKey = builder.toString();
				searchKey = newSearchKey;
				// logger.info("New searchKey: "+searchKey);
			}
		}

		Thread.sleep(500);
		boolean confirmationGiverValue = false;
		boolean isThreeDotPresent = false;
		boolean isSearchKeyPresent = false;
		boolean isClickedOnThreeDotButton = false;
		boolean isSearchKeyMatched = false;

		for (WebElement element : listElement_Inner) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String firstColumnText = text[0];
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					logger.info("formatText: " + formatText);
					logger.info("searchKey: " + searchKey);

					String btnActionAddress = null;
					if (formatText.equalsIgnoreCase(searchKey)) {
						isSearchKeyMatched = true;
						logger.info("isSearchKeyMatched: " + isSearchKeyMatched);
						if (wantToClickOnThreeDot == false && wantToclickOnFindSearckKey == false) {
							return listRowCount;
						}

						try {
							if (wantToClickOnThreeDot) {
								btnActionAddress = pointerList_Address + "[" + listRowCount + "]";
								logger.info("Pointer_Address: " + btnActionAddress);

								WebElement btnThreeDot = element.findElement(By.xpath(btnActionAddress));
								if (btnThreeDot.isDisplayed()) {
									isThreeDotButtonPresent = true;
									// logger.info("Given DateAndTime : "+searchKey);
									logger.info("Given value matched with the list value: " + formatText);
									confirmationGiverValue = true;
									btnThreeDot.click();
									isThreeDotPresent = true;
									logger.info("Clicked on the three dot option button");
									isClickedOnThreeDotButton = true;
									break;
								}
							} else if (wantToclickOnFindSearckKey) {
								logger.info("Text present at given column index: " + formatText);
								confirmationGiverValue = true;
								String yourUniqueValue = null;
								WebElement elementFindKey = null;

								if (callerMethodName.equals("findPackageBuyersUserFromList")) {
									yourUniqueValue = firstColumnText.toLowerCase().trim(); // formatText;
									elementFindKey = driver.findElement(By.xpath(
											"(//*[translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
													+ yourUniqueValue + "'])[2]"));

								} else {
									yourUniqueValue = formatText.toLowerCase().trim();
									elementFindKey = driver.findElement(By.xpath(
											"(//*[translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
													+ yourUniqueValue + "'])[1]"));

								}

								logger.info("elementFindKey_address: " + elementFindKey);
								action.moveToElement(elementFindKey).build().perform();
								logger.info("Mouse moved to the find searck key element: " + elementFindKey.getText());// div[.='susan5
																														// davis5']
								Thread.sleep(500);
								// jsExecutor.executeScript("arguments[0].click();", elementFindKey);
								// elementFindKey.click();
								action.moveToElement(elementFindKey).click().build().perform();
								isSearchKeyPresent = true;
								logger.info("Clicked on the findSearchKey: " + elementFindKey.getText());
								Thread.sleep(2000);
								break;

							} else {
								logger.info("Text present at given column index: " + formatText);
							}
							Thread.sleep(200);

						} catch (Exception e) {
							logger.info("Exception from FindThreeDotBasedOnSearchKeyAndClick: " + e.getMessage());
							if (isThreeDotPresent == false) {
								softAssert.assertTrue(false, "Three dot Action button not present");
							}
							if (isSearchKeyPresent == false) {
								softAssert.assertTrue(false, "Three dot Action button not present");
							}

						}
					}

				}
				if (confirmationGiverValue) {
					break;// because list item matched
				}
			}
			if (isClickedOnThreeDotButton) {
				break;
			}
		}

		if (!confirmationGiverValue) {
			logger.info("Given confirmationGiverValue not matched: " + searchKey);
		}

		Thread.sleep(200);
		if (!isThreeDotButtonPresent) {
			return -1;
		} else {
			return listRowCount;
		}

	}

	// ==============================================================================
	public static ArrayList<Integer> findMatchedUnMatchedRowListCountNumber(String list_Address, List<WebElement> listElement,
			WebDriver driver, String searchKey, int searchKeyColumnIndex, boolean wantToGetMatchedRecords) throws InterruptedException {

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();
		logger.info("findThreedActionButtonAndClick method called and Caller method name: " + callerMethodName);
		logger.info("wantToGetMatchedRecords: " + wantToGetMatchedRecords);

		List<WebElement> listElement_Inner = driver.findElements(By.xpath(list_Address));
	

		ArrayList<Integer> matchedRowCount = new ArrayList<>();
		ArrayList<Integer> unmatchedRowCount = new ArrayList<>();
		int listRowCount = 0;
		boolean isSearchKeyMatched = false;

		for (WebElement element : listElement_Inner) {
			listRowCount++;
			String[] text = element.getText().split("\\n");
			String firstColumnText = text[0];
			String formatText = "";
			int columnIndexCount = 0;
			for (String st : text) {
				columnIndexCount++;

				if (columnIndexCount == searchKeyColumnIndex) {
					formatText = st.trim();

					//logger.info("formatText: " + formatText);
					//logger.info("searchKey: " + searchKey);

					if (formatText.equalsIgnoreCase(searchKey)) {
						isSearchKeyMatched = true;
						logger.info("isSearchKeyMatched: " + isSearchKeyMatched);
						matchedRowCount.add(listRowCount);
					}else {
						unmatchedRowCount.add(listRowCount);
					}

				}

			}
		}
		
		if(wantToGetMatchedRecords) {
			return matchedRowCount;
		}else {
			return unmatchedRowCount;
		}
	}

}
