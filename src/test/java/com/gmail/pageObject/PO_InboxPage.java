package com.gmail.pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.pageLocators.PL_InboxPage;
import com.gmail.utilities.CheckElementIsEnabled;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;
import com.gmail.projectUtility.FindThreeDotAndClick;

public class PO_InboxPage extends ReUseAbleElement {

	// CONSTRUCTOR DECLARATION
	public WebDriver driver;
	public Logger logger = LogManager.getLogger(getClass());
	public JavascriptExecutor jsExecutor;
	public ReUseAbleElement ruae;
	public WebDriverWait wait;
	public PO_LoginPage lp;
	public Actions action;
	public SoftAssert softAssert = new SoftAssert();

	public SetDataIntoTextInputField setDataIntoTextInputField = new SetDataIntoTextInputField();
	public NavigateToNewOpenTab navigateToNewTab = new NavigateToNewOpenTab();
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();
	public CheckElementIsEnabled checkElementEnableState = new CheckElementIsEnabled();

	// HOMEPAGE CONSTRUCTOR CREATION
	public PO_InboxPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// EMAIL LIST
	@FindBy(xpath = PL_InboxPage.addressEmailList)
	@CacheLookup
	List<WebElement> listEmails;

	public ArrayList<Integer> findEmailFromRowList(String EmailSenderName, String searchKey, int searchKeyColumnIndex,
			boolean wantToGetMatchedRecords) throws InterruptedException {

		ArrayList<Integer> rowNumber = new ArrayList<>();

		try {
			Thread.sleep(1000);
			rowNumber = FindThreeDotAndClick.findMatchedUnMatchedRowListCountNumber(PL_InboxPage.addressEmailList,
					listEmails, driver, searchKey, searchKeyColumnIndex, wantToGetMatchedRecords);

		} catch (Exception e) {
			logger.info("Exception from findTenantsFromRowListAndClickOnThreeDot: " + e.getMessage());
		}
		if (wantToGetMatchedRecords) {
			logger.info("MatchRowNumber: " + rowNumber);
		} else {
			logger.info("UnmatchRowNumber: " + rowNumber);
		}

		return rowNumber;

	}

	// TO DELETE EMAILS
	public PO_InboxPage deleleEmails(String EmailSenderName, String searchKey, int searchKeyColumnIndex,
			boolean wantToGetMatchedRecords) throws InterruptedException {

		boolean isEmailFound = true;
		ruae.searchBox_1_RU(driver, searchKey);
		Thread.sleep(1000);
		int breakLoopCounter = 0;

		while (isEmailFound) {
			ArrayList<Integer> rowNumber = findEmailFromRowList(EmailSenderName, searchKey, searchKeyColumnIndex,
					wantToGetMatchedRecords);

			try {
				WebElement emailFound = driver.findElement(By.xpath(PL_InboxPage.addressMessageNoEmailFound));
				isEmailFound = !(emailFound.isEnabled() && emailFound.isDisplayed());
			} catch (Exception e) {
//				clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Checkbox",
//						PL_InboxPage.addressCheckboxSelect);
//				Thread.sleep(2000);
				if (!rowNumber.isEmpty()) {
					for (int rowNumberCounter : rowNumber) {
						String checkBoxFormattedAddress = "(" + PL_InboxPage.addressCheckboxList + ")["
								+ rowNumberCounter + "]";
						WebElement checkboxCount = driver.findElement(By.xpath(checkBoxFormattedAddress));
						// logger.info("checkbox address: "+checkBoxFormattedAddress);
						Thread.sleep(500);
						if (checkboxCount.isDisplayed() && checkboxCount.isEnabled()) {
							action.moveToElement(checkboxCount).click().build().perform();
							Thread.sleep(200);
						}
					}

					boolean flag = clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Delete",
							PL_InboxPage.addressButtonDeleteEmail);

					if (flag) {
						snakeAlertMessagesDisplayedContent_RU();
					}
				}

			} finally {
				try {
					boolean isNextButtonEnabled = checkElementEnableState.isElementEnabled(driver,
							PL_InboxPage.addressButton1_50_OfMany_NextArrow, "aria-disabled");
					if (isNextButtonEnabled) {
						clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver,
								"Older button", PL_InboxPage.addressButton1_50_OfMany_NextArrow);
					} else {
						breakLoopCounter++;
						if (breakLoopCounter == 2) {
							logger.info("No more mail present and isNextButtonEnabled: " + isNextButtonEnabled);
							break;
						}
					}

				} catch (Exception e) {
					isEmailFound = false;
					logger.info("Exception while chacking '1-50 of Many' >> " + e.getMessage());
					break;
				}
			}

		}
		if (!isEmailFound) {
			logger.info("All the email deleted or No Email presence now...");
		}

		softAssert.assertAll();
		return new PO_InboxPage(driver);
	}
}
