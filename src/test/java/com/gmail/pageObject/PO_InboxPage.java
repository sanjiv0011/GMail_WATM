package com.gmail.pageObject;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.pageLocators.PL_InboxPage;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

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

	// TO DELETE EMAILS
	public PO_InboxPage deleleEmails(String searchWantToDelete) throws InterruptedException {
		boolean isEmailFound = true;

		ruae.searchBox_1_RU(driver, searchWantToDelete);
		Thread.sleep(2000);
		while (isEmailFound) {
			try {
				WebElement emailFound = driver.findElement(By.xpath(PL_InboxPage.addressMessageNoEmailFound));
				logger.info("1");
				isEmailFound = !(emailFound.isEnabled() && emailFound.isDisplayed());
				logger.info("2");
			} catch (Exception e) {
				clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Checkbox",
						PL_InboxPage.addressCheckboxSelect);
				Thread.sleep(1000);
				boolean flag = clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Delete",
						PL_InboxPage.addressButtonDeleteEmail);
				if(driver.getPageSource().contains("Some messages in Trash or Spam match your search.")) {
					isEmailFound = false;
					break;
				}

				if (flag) {
					String alertMsg = snakeAlertMessagesDisplayedContent_RU();
					logger.info("Alert Message: " + alertMsg);
					if (alertMsg.contains(PL_InboxPage.addressAlertConversationMovedToTrace)) {
						softAssert.assertTrue(false, "To check conversationd deleted successfully or not");
					} else {
						softAssert.assertFalse(true, "To check conversationd deleted successfully or not");
					}

				}

			}
		}
		if(!isEmailFound) {
			logger.info("All the email deleted or No Email presence now...");
		}

		softAssert.assertAll();
		return new PO_InboxPage(driver);
	}
}
