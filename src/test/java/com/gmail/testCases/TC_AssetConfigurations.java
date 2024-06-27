package com.gmail.testCases;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.PO_AssetConfigurationsPage;
import com.gmail.pageObject.PO_CustomersPage;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_Inner_DashboardPage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.PO_TenantsPage;
import com.gmail.pageObject.pageLocators.PL_Inner_DashboardPage;
import com.gmail.utilities.ClickOnAnyButton;

public class TC_AssetConfigurations extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_AssetConfigurations() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_CustomersPage cp;
	public PO_TenantsPage tp;
	public TC_AssumeRole assumeRole = new TC_AssumeRole();
	public PO_AssetConfigurationsPage assetConfiration;
	public PO_Inner_DashboardPage innerDashboard;
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	// VARIABLE
	String assetConfigurationsName = "RAM Size";
	String assetConfigurationsDescriptionCode = faker.lorem().sentence();
	String assetConfigurationsUnit = "GB";
	String  assetConfigurationDataType = "number"; //text or number;
	String assetConfigurationPrefix = "GB";
	String assetConfigurationPostfix = "";
	String [] assetConfiguratinValues = {"1","2","3"};
	
 	String AssetConfigurationName_Update = assetConfigurationsName + " Updated";
 	
 	String searchKey = assetConfigurationsName;
	int searchKeyColumnIndex = 1;
	boolean wantToClickOnThreeDot = true;
	boolean wantToclickOnFindSearckKey = false;
	String searchKey_assetConfiguration = null;

	// TO ADD
	@Test(priority = 1)
	public void test_AddAssetConfiguration() throws Throwable {
		assetConfiration = callMeBeforePerformAnyAction();
		assetConfiration.addOrEditAssetConfiguration(AssetConfigurationName_Update, assetConfigurationsDescriptionCode,
				assetConfigurationsUnit, assetConfigurationDataType, assetConfigurationPrefix, assetConfigurationPostfix, assetConfiguratinValues, searchKey,
				searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
	}

	// TO EDIT
	@Test(priority = 2)
	public void test_EditAssetConfiguration() throws Throwable {
		assetConfiration = callMeBeforePerformAnyAction();
		assetConfiration.addOrEditAssetConfiguration(AssetConfigurationName_Update, assetConfigurationsDescriptionCode,
				assetConfigurationsUnit, assetConfigurationDataType, assetConfigurationPrefix, assetConfigurationPostfix, assetConfiguratinValues, searchKey,
				searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
		searchKey_assetConfiguration = AssetConfigurationName_Update;
	}


	// TO ARCHIVE
	@Test(priority = 3)
	public void test_ArchiveAssetConfiguration() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		assetConfiration.archiveAssetConfiguration();
	}

	// TO RESTORE
	@Test(priority = 4)
	public void test_RestoreAssetConfiguration() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		assetConfiration.restoreAssetConfiguration();
	}
	
	
	

	// TO FIND THE ASSET CATEGORY FROM THE LIST AND CLICK ON THE THREE DOT ACTION BUTTON
	// @Test(priority = 10)
	public void test_FindDataFromListAndClickOnThreeDotButton() throws Throwable {
		assetConfiration = callMeBeforePerformAnyAction();
		// logger.info("1");
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		// logger.info("2");
		if (callerMethodName.contains("test_Restore")) {
			// logger.info("3");
			ReUseAbleElement ruae = new ReUseAbleElement(driver);
			// logger.info("callerMethodName: "+callerMethodName);
			ruae.clickONBtnTooltip_RU("Show Archived", driver);
			// logger.info("4");
		}
		// logger.info("5");
		assetConfiration.findDataFromRowListAndClickOnThreeDot(AssetConfigurationName_Update, searchKey_assetConfiguration, searchKeyColumnIndex,
				wantToClickOnThreeDot, wantToclickOnFindSearckKey);
	}

	// CALL ME IN EVERY @TEST METHODS EXCEPT LOGIN AND LOGOUT AND ASSUME_ROLE
	public PO_AssetConfigurationsPage callMeBeforePerformAnyAction() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		innerDashboard = new PO_Inner_DashboardPage(driver);
		Thread.sleep(1000);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Asset Configurations", PL_Inner_DashboardPage.ADDRESS_ASSET_CONFIGURATIONS);
		Thread.sleep(3000);
		return new PO_AssetConfigurationsPage(driver);
	}
	
}
