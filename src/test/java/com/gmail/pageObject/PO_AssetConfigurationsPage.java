package com.gmail.pageObject;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.actions.Action_Archive;
import com.gmail.actions.Action_Edit;
import com.gmail.actions.Action_Restore;
import com.gmail.pageObject.pageLocators.PL_AssetConfigurationsPage;
import com.gmail.projectUtility.FindThreeDotAndClick;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

public class PO_AssetConfigurationsPage extends ReUseAbleElement {

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
	
	// CONSTRUCTOR CREATION
	public PO_AssetConfigurationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// ======START======PAGE OBJECT AND ACTOIN METHODS==========//

	@FindBy(xpath = PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_DATATYPE_DROPDOWN_IOCN)
	@CacheLookup
	public WebElement dropdownIcon_ASSET_CONFIGURATION_DATATYPE_DROPDOWN_IOCN;
	public boolean clickOnDropdownIconDataType() throws InterruptedException {
		action.moveToElement(dropdownIcon_ASSET_CONFIGURATION_DATATYPE_DROPDOWN_IOCN).build().perform();
		Thread.sleep(1000);
		action.moveToElement(dropdownIcon_ASSET_CONFIGURATION_DATATYPE_DROPDOWN_IOCN).click().build().perform();
		Thread.sleep(500);
		return true;
	}
	
	
	public void selectAssetConfigurationsTypes(String configurationsTypes) throws InterruptedException {
		
		if(clickOnDropdownIconDataType()) {
			if (configurationsTypes.equals("number")) {
				WebElement configurationDataType_Number = driver.findElement(By.xpath(PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_DATATYPE_NUMBER));
				configurationDataType_Number.click();
				Thread.sleep(200);
			} else if(configurationsTypes.equals("text")){
				WebElement configurationDataType_Text = driver.findElement(By.xpath(PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_DATATYPE_TEXT));
				configurationDataType_Text.click();
				Thread.sleep(200);
				
			}
		}
			
	}

	
	
	@FindBy(xpath = PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_VALUES)
	@CacheLookup
	public WebElement field_ASSET_CONFIGURATION_VALUES;

	public void setAssetConfigurationValues(String[] assetConfigurationsValues, boolean isEditAction) throws InterruptedException {
		int configurationValueCount = assetConfigurationsValues.length;
		String ADDRESS_ASSET_CONFIGURATION_VALUES = PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_VALUES;
		int baseCount = 8;
		for(String configurationValue : assetConfigurationsValues) {
			configurationValueCount--;
			String updatedAddress = ADDRESS_ASSET_CONFIGURATION_VALUES+"["+baseCount+"]"+"//input";
			logger.info("configurationValue: "+configurationValue+" And Address: "+updatedAddress);
			
			WebElement fieldAssetConfig = driver.findElement(By.xpath(updatedAddress));
			fieldAssetConfig.sendKeys(Keys.CONTROL, "a");
			fieldAssetConfig.sendKeys(Keys.DELETE);
			fieldAssetConfig.sendKeys(configurationValue);
			Thread.sleep(500);
			logger.info("Entered configurationValue");
			if(configurationValueCount>0) {
				baseCount++;
				if(!isEditAction) {
					logger.info("1");
					clickOnConfigurationsValuesPlusButton();
					logger.info("2");
				}
			}
			
		}
	}
	

	public boolean clickOnConfigurationsValuesPlusButton() throws InterruptedException {
		WebElement btn_ASSET_CONFIGURATION_VALUES_PLUSBUTTON = driver.findElement(By.xpath(PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_VALUES_PLUSBUTTON));
		action.moveToElement(btn_ASSET_CONFIGURATION_VALUES_PLUSBUTTON).build().perform();
		Thread.sleep(500);
		action.moveToElement(btn_ASSET_CONFIGURATION_VALUES_PLUSBUTTON).click().build().perform();
		Thread.sleep(500);
		return true;
	}

	// ASSET CONFIGURATIONS LIST
	@FindBy(xpath = PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_LIST)
	@CacheLookup
	List<WebElement> listAssetConfigurations;

	public int findDataFromRowListAndClickOnThreeDot(String assetConfigurationName, String searchKey,
			int searchKeyColumnIndex, boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey)
			throws InterruptedException {
		
		searchBox_1_RU(driver, searchKey);
		
		if (!driver.getPageSource().contains("No asset configuration found")) {
			int listRowCount = 0;
			try {
				Thread.sleep(2000);
				listRowCount = FindThreeDotAndClick.findThreedActionButtonAndClick(
						PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_LIST, listAssetConfigurations, driver, searchKey,
						searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);

			} catch (Exception e) {
				logger.info("Exception from findAssetCategoryFromRowListAndClickOnThreeDot: " + e.getMessage());
			}
			logger.info("listRowCount: " + listRowCount);
			return listRowCount;
		} else {
			logger.info("AssetCategoryNotFound");
			return -1;
		}

	}
	// ======END======PAGE OBJECT AND ACTOIN METHODS==========//

//	// TO ASSUME A ROLE AS TENANTS
//	public PO_AssetConfigurationsPage assumeRoleAsTenant(String tenantName, int rowListCount, int columnCount,
//			boolean wantToclickColumnELement, WebDriver driver) throws InterruptedException {
//		Action_ClickOnAnyColumnElementBasedOnSelectedRowItem.clickOnSelectedColumnElementBasedOnGivenRowCount(
//				PL_TenantsPage.ADD_TENANT_LIST, rowListCount, columnCount, true, driver);
//		String address_selectedTenant = "//*[text()='" + tenantName + "']";
//
//		String selectedTenant = driver.findElement(By.xpath(address_selectedTenant)).getText();
//		logger.info("Role Assumed for: " + selectedTenant);
//		softAssert.assertEquals(tenantName, selectedTenant);
//		softAssert.assertAll();
//		return new PO_AssetConfigurationsPage(driver);
//	}
	

	// TO ADD AND UPDATE
	public PO_AssetConfigurationsPage addOrEditAssetConfiguration(String assetConfigurationsName, String assetConfigurationsDescriptionCode,
			String assetConfigurationsUnit, String assetConfigurationDataType, String assetConfigurationPrefix,String assetConfigurationPostfix, String[] assetConfiguratinValues, String searchKey,
			int searchKeyColumnIndex, boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey)
			throws Throwable {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method Called: addOrEditAssetConfiguration and Caller methods name: " + callerMethodName);
		System.out.println("callerMethodName.contains(\"test_Edit\")"+callerMethodName.contains("test_Edit"));

		boolean flag = false;
		boolean isClickedOnAddOrEditButton = false;
		if (callerMethodName.contains("test_Add")) {
			isClickedOnAddOrEditButton = ruae.clickOnAdd_RU();

			Thread.sleep(1000);
		} else if (callerMethodName.contains("test_Edit")) {

			int isItemfindInList = findDataFromRowListAndClickOnThreeDot(assetConfigurationsName, searchKey,
					searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
			logger.info("Item found in row number: " + isItemfindInList);
			if (isItemfindInList != -1) {
				isClickedOnAddOrEditButton = Action_Edit.edit(driver);
			}
		}

		logger.info("isClickedOnAddOrEditButton: " + isClickedOnAddOrEditButton);
		
		try {
			
		if (isClickedOnAddOrEditButton) {
			Thread.sleep(2000);
			
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Asset Configuration name", PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_NAME, assetConfigurationsName);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Asset Configuration name", PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_DESCRIPTION, assetConfigurationsDescriptionCode);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Asset Configuration name", PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_UNIT, assetConfigurationsUnit);

			
			selectAssetConfigurationsTypes(assetConfigurationDataType);
			
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Asset Configuration Prifix", PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_PREFIX, assetConfigurationPrefix);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Asset Configuration Postfix", PL_AssetConfigurationsPage.ADDRESS_ASSET_CONFIGURATION_POSTFIX, assetConfigurationPostfix);
;
			
			setAssetConfigurationValues(assetConfiguratinValues,callerMethodName.contains("test_Edit"));

			if (callerMethodName.contains("test_Edit")) {
				flag = clickOnBtnUpdate_1_RU();
			} else {
				flag = clickOnBtnSave_1_RU();
			}

			if (flag) {
				if (driver.getPageSource().contains("Please")
						|| driver.getPageSource().contains("allow only alphabets")) {
					clickOnCancelButton_1_RU();
					logger.info("Asset Configuration not added");
					softAssert.assertTrue(false, "Asset Configuration is emplty");
					return new PO_AssetConfigurationsPage(driver);
				} else {

					String alertMsg = snakeAlertMessagesDisplayedContent_RU();
					logger.info("Alert Message: " + alertMsg);
					if (alertMsg.contains("already exists") || alertMsg.contains("error")) {
						clickOnCancelButton_1_RU();
					} else {
						if (callerMethodName.contains("test_Add")) {
							softAssert.assertEquals(alertMsg, PL_AssetConfigurationsPage.MESSAGE_ASSET_CONFIGURATION_CREATEDED,
									"Check user Asset Category added or not");
						} else if (callerMethodName.contains("test_Edit")) {
							softAssert.assertEquals(alertMsg, PL_AssetConfigurationsPage.MESSAGE_ASSET_CONFIGURATION_UPDATED,
									"Check user Asset Category updated or not");
						}
					}

				}
			}
		}
		}catch(Exception e) {
			logger.warn("Exceptino form : addOrEditAssetConfiguration >>"+e.getMessage());
		}

		softAssert.assertAll();
		return new PO_AssetConfigurationsPage(driver);
	}


	// TO ARCHIVE
	public PO_AssetConfigurationsPage archiveAssetConfiguration() throws InterruptedException {
		Action_Archive.archive(driver, PL_AssetConfigurationsPage.MESSAGE_ASSET_CONFIGURATION_ARCHIVED);
		softAssert.assertAll();
		return new PO_AssetConfigurationsPage(driver);
	}

	// TO RESTORE
	public PO_AssetConfigurationsPage restoreAssetConfiguration() throws InterruptedException {
		Action_Restore.restore(driver, PL_AssetConfigurationsPage.MESSAGE_ASSET_CONFIGURATION_RESTORED);
		softAssert.assertAll();
		return new PO_AssetConfigurationsPage(driver);
	}

}
