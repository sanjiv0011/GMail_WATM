package com.gmail.pageObject;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.pageLocators.PL_Inner_DashboardPage;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

public class PO_Inner_DashboardPage extends ReUseAbleElement {

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
	public PO_Inner_DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// =========START========HOME PAGE OBJECTS=============//

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_LOGGED_USED_NAME)
	@CacheLookup
	WebElement loggedUserName;

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_TOTAL_ASSET_COUNT)
	@CacheLookup
	WebElement totalAssetCount;

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_TOTAL_AVAILABLE_ASSET_COUNT)
	@CacheLookup
	WebElement totalAvailableAssetCount;

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_TOTAL_CATEGORY_COUNT)
	@CacheLookup
	WebElement totalCategoryCount;

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_TOTAL_USED_ASSET_COUNT)
	@CacheLookup
	WebElement totalUsedAssetCount;

	@FindBy(xpath = PL_Inner_DashboardPage.ADDRESS_DASHBOARD)
	@CacheLookup
	WebElement menuDashboard;


	// =========END========HOME PAGE OBJECTS=============//

	// =========START========ACTION METHODS FOR HOME PAGE OBJECTS=============//

	public void getLoggedUserName() throws InterruptedException {
		String loggedUser = loggedUserName.getText().toString();
		logger.info("loggedUser: " + loggedUser);
	}

	public void getTotolCategoryCount() throws InterruptedException {
		String totalCategroy = totalCategoryCount.getText().toString();
		logger.info("totalCategoryCount: " + totalCategroy);
	}

	public void getTotalAssetsCount() throws InterruptedException {
		String totalAsset = totalAssetCount.getText().toString();
		logger.info("totalAssetCount: " + totalAsset);
	}

	public void getTotalAvailableAssetCount() throws InterruptedException {
		String totalAvailableAsset = totalAvailableAssetCount.getText().toString();
		logger.info("totalAvailableAsset: " + totalAvailableAsset);
	}

	public void getTotalUsedAssetCount() throws InterruptedException {
		String totalUsedAsset = totalUsedAssetCount.getText().toString();
		logger.info("totalUsedAsset: " + totalUsedAsset);
	}

	public void clickOnMenuDashbaord() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		menuDashboard.click();
		Thread.sleep(2000);
		logger.info("Clicked on the menuDashboard");
	}


	public boolean clickOnMenuSettings() throws InterruptedException {
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Settings", PL_Inner_DashboardPage.ADDRESS_SETTINGS);
		boolean flag = true;
		Thread.sleep(2000);
		logger.info("Clicked on the menuSettings");
		return flag;
	}

	// =========END========ACTION METHODS FOR HOME PAGE OBJECTS=============//

	// TO CHECK LOCKED USER AND IT'S COUNTS
	public PO_Inner_DashboardPage checkDashboardCounts() throws InterruptedException {
		logger.info("Method called: checkDashboardCount");
		Thread.sleep(1000);
		getLoggedUserName();
		getTotolCategoryCount();
		getTotalAssetsCount();
		getTotalAvailableAssetCount();
		getTotalUsedAssetCount();
		return new PO_Inner_DashboardPage(driver);
	}

	// TO CHECK THE MENUS
	public PO_Inner_DashboardPage checkDashboardInnerMenus() throws InterruptedException {
		//USE THIS ONLY FOR BACK TO BACK ENTRY
		String[] buttonNames = {"Assets","AssetCategories","Reports","AssetConfigurations","Vendors","Invoices","Users","Services"};
		String[] buttonAddresses = {PL_Inner_DashboardPage.ADDRESS_ASSETS,PL_Inner_DashboardPage.ADDRESS_ASSET_CATEGORIES, PL_Inner_DashboardPage.ADDRESS_REPORTS, PL_Inner_DashboardPage.ADDRESS_ASSET_CONFIGURATIONS,PL_Inner_DashboardPage.ADDRESS_VENDORS,PL_Inner_DashboardPage.ADDRESS_INVOICES,PL_Inner_DashboardPage.ADDRESS_USERS,PL_Inner_DashboardPage.ADDRESS_SERVICES};
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, buttonNames, buttonAddresses);

		boolean isClickOnSetting = clickOnMenuSettings();
		if (isClickOnSetting) {
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Locations", PL_Inner_DashboardPage.ADDRESS_LOCATIONS);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Departments", PL_Inner_DashboardPage.ADDRESS_DEPARTMENTS);
		}

		return new PO_Inner_DashboardPage(driver);
	}
}
