package com.gmail.pageObject;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.pageLocators.PL_HomePage;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

public class PO_HomePage extends ReUseAbleElement {

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
	public PO_HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// =========START========ACTION METHODS FOR HOME PAGE OBJECTS=============//

	// TO LOGOUT
	public PO_LoginPage UserLogout() throws InterruptedException {
		System.out.println("5");
		logger.info("Method called: Logout");
		try {
			Thread.sleep(2000);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "User Logo", PL_HomePage.addressUserLogo);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Logout",
					PL_HomePage.addressButtonLogout);
			Thread.sleep(5000);
			if (driver.getPageSource().contains("Choose an account")) {
				softAssert.assertTrue(true);
				logger.info("... LOGOUT DONE ...");
			} else {
				softAssert.assertTrue(false);
				logger.info("!!! LOGOUT FAILEED !!!");
			}
		} catch (Exception e) {
			logger.info("Logout Exception: " + e.getMessage());
			softAssert.assertTrue(false, "After logout it lookin for [Choose an account] text");
		}
		softAssert.assertAll();
		return new PO_LoginPage(driver);
	}

	// TO CHECK THE MENUS
	public PO_HomePage checkMenus() throws InterruptedException {

		// USE THIS ONLY FOR BACK TO BACK ENTRY
		String[] tabNames = { "Inbox", "Starred", "Snoozed", "Sent", "Drafts" };
		String[] inputFiledAddresses = { PL_HomePage.addressButtonInbox, PL_HomePage.addressButtonStarred,
				PL_HomePage.addressButtonSent, PL_HomePage.addressButtonDrafts, PL_HomePage.addressButtonSnoozed };
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, tabNames, inputFiledAddresses);

		return new PO_HomePage(driver);
	}
}
