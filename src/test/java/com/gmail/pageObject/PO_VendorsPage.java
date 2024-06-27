package com.gmail.pageObject;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.gmail.actions.Action_Activate;
import com.gmail.actions.Action_Deactivate;
import com.gmail.actions.Action_Edit;
import com.gmail.pageObject.pageLocators.PL_AssetCategoriesPage;
import com.gmail.pageObject.pageLocators.PL_TenantsPage;
import com.gmail.pageObject.pageLocators.PL_VendorsPage;
import com.gmail.projectUtility.FindThreeDotAndClick;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.ClickOnAnyColumnElementBasedOnSelectedRowItem;
import com.gmail.utilities.FindAndClickOnThreeDotButtonInGridView;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

public class PO_VendorsPage extends ReUseAbleElement {

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
	public ClickOnAnyColumnElementBasedOnSelectedRowItem clickOnAnyColumnItems = new ClickOnAnyColumnElementBasedOnSelectedRowItem();
	public FindAndClickOnThreeDotButtonInGridView findThreeDotAndClickInGridView = new FindAndClickOnThreeDotButtonInGridView();

	// CONSTRUCTOR CREATION
	public PO_VendorsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// ======START======PAGE OBJECT AND ACTOIN METHODS==========//

	public void selectAssteCategroy(String assetCategory) throws InterruptedException {
		if (ruae.clickOnDropdown_1_RU(driver)) {
			selectAnyValueFromDropdownList(driver, assetCategory);
		}
	}

	public void selectVendorsTypes(String vendorType) throws InterruptedException {
		if (ruae.clickOnDropdown_1_RU(driver)) {
			selectAnyValueFromDropdownList(driver, vendorType);
		}
	}

	public void selectVendorTypeRadioButton(String vendorType) throws InterruptedException {
		if (vendorType.equals("Agency")) {
			ruae.clickOnRadioButton_1_RU();
		} else if (vendorType.equals("Individual")) {
			ruae.clickOnRadioButton_2_RU();
		} else {
			logger.warn("Wrong vendor type" + vendorType);
		}
	}

	// VENDOR CONTACT LIST
	@FindBy(xpath = PL_VendorsPage.ADDRESS_VENDOR_CONTACT_LIST)
	@CacheLookup
	List<WebElement> listVendorContact;

	public int findVendorConatctFromGridListAndClickOnThreeDot(String vendorEmail, String searchKey,
			int searchKeyColumnIndex, boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey)
			throws InterruptedException {

		searchBox_1_RU(driver, searchKey);

		if (!driver.getPageSource().contains("No vendors found")) {
			int iterationCount = 0;
			try {
				Thread.sleep(2000);

				for (WebElement ele : listVendorContact) {
					iterationCount++;
					String capturedElement = ele.getText().trim();
					if (searchKey.equals(capturedElement)) {
						logger.info("Matched recored: " + capturedElement + " and matched records list count: "
								+ iterationCount);

						clickOnAnyColumnItems.clickOnSelectedColumnElementBasedOnGivenRowCount(
								PL_VendorsPage.ADDRESS_VENDOR_CONTACT_THREEDOTBUTTON, iterationCount, 1, true, driver);
						break;
					}
				}
			} catch (Exception e) {
				logger.info("Exception from findVendorConatctFromGridListAndClickOnThreeDot: " + e.getMessage());
			}
			logger.info("listRowCount: " + iterationCount);
			return iterationCount;
		} else {
			logger.info("No vendors contact found");
			return -1;
		}
	}

	
	// VENDOR LIST
	@FindBy(xpath = PL_VendorsPage.ADDRESS_VENDOR_LIST)
	@CacheLookup
	List<WebElement> listVendor;

	public int findDataFromRowListAndClickOnThreeDot(String vendorName, String searchKey, int searchKeyColumnIndex,
			boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey) throws InterruptedException {

		ruae.clickOnIconListViewGridViewShowArhciveRestored(driver, PL_VendorsPage.IOCN_LIST_VIEW);
		searchBox_1_RU(driver, searchKey);

		if (!driver.getPageSource().contains("No vendors found")) {
			int listRowCount = 0;
			try {
				Thread.sleep(2000);
				listRowCount = FindThreeDotAndClick.findThreedActionButtonAndClick(PL_VendorsPage.ADDRESS_VENDOR_LIST,
						listVendor, driver, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
						wantToclickOnFindSearckKey);
				clickOnAnyColumnItems.clickOnSelectedColumnElementBasedOnGivenRowCount(
						PL_VendorsPage.ADDRESS_VENDOR_LIST, listRowCount, 6, true, driver);

			} catch (Exception e) {
				logger.info("Exception from findDataFromRowListAndClickOnThreeDot: " + e.getMessage());
			}
			logger.info("listRowCount: " + listRowCount);
			return listRowCount;
		} else {
			logger.info("No vendors found");
			return -1;
		}

	}
	// ======END======PAGE OBJECT AND ACTOIN METHODS==========//

//	// TO ASSUME A ROLE AS TENANTS
//	public PO_VendorsPage assumeRoleAsTenant(String tenantName, int rowListCount, int columnCount,
//			boolean wantToclickColumnELement, WebDriver driver) throws InterruptedException {
//		Action_ClickOnAnyColumnElementBasedOnSelectedRowItem.clickOnSelectedColumnElementBasedOnGivenRowCount(
//				PL_TenantsPage.ADD_TENANT_LIST, rowListCount, columnCount, true, driver);
//		String address_selectedTenant = "//*[text()='" + tenantName + "']";
//
//		String selectedTenant = driver.findElement(By.xpath(address_selectedTenant)).getText();
//		logger.info("Role Assumed for: " + selectedTenant);
//		softAssert.assertEquals(tenantName, selectedTenant);
//		softAssert.assertAll();
//		return new PO_VendorsPage(driver);
//	}

	// TO ADD | EDIT
	public PO_VendorsPage addOrEditVendor(String vendorName, String vendorEmail, String vendorPhoneNumber,
			String vendorWhatsAppNumber, String vendorWebsite, String vendorType, String vendorAddress1,
			String vendorAddress2, String vendorCity, String vendorPostalCode, String vendorState, String vendorCountry,
			String vendoroverview, String vendorContactName, String vendorContactNumber, String vendorContactEmail,
			String vendorContactComment, String searchKey, int searchKeyColumnIndex, boolean wantToClickOnThreeDot,
			boolean wantToclickOnFindSearckKey) throws Throwable {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Caller methods name: " + callerMethodName);

		boolean flag = false;
		boolean isClickedOnAddOrEditButton = false;
		if (callerMethodName.contains("test_Add")) {
			isClickedOnAddOrEditButton = ruae.clickOnAdd_RU();

			Thread.sleep(1000);
		} else if (callerMethodName.contains("test_Edit")) {

			int isItemfindInList = findDataFromRowListAndClickOnThreeDot(vendorName, searchKey, searchKeyColumnIndex,
					wantToClickOnThreeDot, wantToclickOnFindSearckKey);
			logger.info("Item found in row number: " + isItemfindInList);
			if (isItemfindInList != -1) {
				isClickedOnAddOrEditButton = Action_Edit.edit(driver);
			}
		}

		logger.info("isClickedOnAddOrEditButton: " + isClickedOnAddOrEditButton);

		try {

			if (isClickedOnAddOrEditButton) {
				Thread.sleep(2000);
				boolean isClickedOnNextBtn = false;
				try {
					// for vendor details
					setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
							"Vendor Name", PL_VendorsPage.ADDRESS_VENDOR_NAME, vendorName);
					setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
							"Vendor Email", PL_VendorsPage.ADDRESS_VENDOR_EMAIL, vendorEmail);
					setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
							"Vendor PhoneNumber", PL_VendorsPage.ADDRESS_VENDOR_PHONENUMBER, vendorPhoneNumber);
					setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
							"Vendor WhatsNumber", PL_VendorsPage.ADDRESS_VENDOR_WHATSAPPNUMBER, vendorWhatsAppNumber);
					setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
							"Vendor Website", PL_VendorsPage.ADDRESS_VENDOR_WEBSITE, vendorWebsite);

					selectVendorTypeRadioButton(vendorType);
					logger.info("Vendor Details added");
					isClickedOnNextBtn = ruae.clickOnBtnNext_RU();
					Thread.sleep(2000);
				} catch (Exception e) {
					logger.info("Exception from Vendor details: " + e.getMessage());
				}

				if (isClickedOnNextBtn) {
					try {
						// for vendor address
						isClickedOnNextBtn = false;

						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Address-1", PL_VendorsPage.ADDRESS_VENDOR_ADDRESS1, vendorAddress1);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Address-2", PL_VendorsPage.ADDRESS_VENDOR_ADDRESS2, vendorAddress1);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor City", PL_VendorsPage.ADDRESS_VENDOR_CITY, vendorCity);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Postal Code", PL_VendorsPage.ADDRESS_VENDOR_POSTALCODE, vendorPostalCode);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor State", PL_VendorsPage.ADDRESS_VENDOR_STATE, vendorState);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Country", PL_VendorsPage.ADDRESS_VENDOR_COUNTRY, vendorCountry);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Overview", PL_VendorsPage.ADDRESS_VENDOR_OVERVIEW, vendoroverview);

						isClickedOnNextBtn = ruae.clickOnBtnNext_RU();
						logger.info("Vendor Address details");
						Thread.sleep(2000);
					} catch (Exception e) {
						logger.info("Exception from Vendor address: " + e.getMessage());
					}
				}

				if (isClickedOnNextBtn) {
					try {
						isClickedOnNextBtn = false;
						// for vendor contacts
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Contact Name", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_NAME, vendorContactName);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Contact Number", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_NUMBER,
								vendorContactNumber);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Contact Email", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_EMAIL,
								vendorContactEmail);
						setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
								"Vendor Contact Comment", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_COMMENT,
								vendorContactComment);

						isClickedOnNextBtn = ruae.clickOnBtnPreview_RU();
						logger.info("Vendor Address contact");
						Thread.sleep(2000);
					} catch (Exception e) {
						logger.info("Exception from Vendor contact: " + e.getMessage());
					}
				}

				if (isClickedOnNextBtn) {
					boolean isClickedOnSaveButton = ruae.clickOnBtnSave_1_RU();
					if (isClickedOnSaveButton) {
						if (driver.getPageSource().contains("Please")
								|| driver.getPageSource().contains("allow only alphabets")) {
							clickOnCancelButton_1_RU();
							logger.info("Vendors not added");
							softAssert.assertTrue(false, "Vemdpr is emplty");
							return new PO_VendorsPage(driver);
						} else {

							String alertMsg = snakeAlertMessagesDisplayedContent_RU();
							logger.info("Alert Message: " + alertMsg);
							if (alertMsg.contains("already exists") || alertMsg.contains("error")) {
								clickOnCancelButton_1_RU();
							} else {
								if (callerMethodName.contains("test_Add")) {
									softAssert.assertEquals(alertMsg, PL_VendorsPage.MESSAGE_VENDOR_CREATEDED,
											"Check vendor added or not");
								} else if (callerMethodName.contains("test_Edit")) {
									softAssert.assertEquals(alertMsg, PL_VendorsPage.MESSAGE_VENDOR_UPDATED,
											"Check vendor updated or not");
								}
							}

						}
					}
				}

			}
		} catch (Exception e) {
			logger.warn("Exceptino form : addOrEditVendor >>" + e.getMessage());
		}

		softAssert.assertAll();
		return new PO_VendorsPage(driver);
	}

	// TO ADD | EDIT VENDOR CONTACT FROM VENDOR CONTACT PAGE
	public PO_VendorsPage addOrEditVendorContact(String vendorContactName, String vendorContactNumber,
			String vendorContactEmail, String vendorContactComment, String searchKey, int searchKeyColumnIndex,
			boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey) throws InterruptedException {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Caller methods name: " + callerMethodName);

		boolean flag = false;
		boolean isClickedOnAddOrEditButton = false;
		if (callerMethodName.contains("test_Add")) {
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Add Contact",
					PL_VendorsPage.ADDRESS_ADD_CONTACT);
			isClickedOnAddOrEditButton = true;
			Thread.sleep(1000);
		} else if (callerMethodName.contains("test_Edit")) {

//			int isItemfindInList = findVendorConatctFromGridListAndClickOnThreeDot(vendorContactEmail, searchKey,
//					searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
			
			int isItemfindInList = findThreeDotAndClickInGridView.findDataFromGridListAndClickOnThreeDot(driver, PL_VendorsPage.ADDRESS_VENDOR_CONTACT_LIST, searchKey, searchKeyColumnIndex, true, false);
			
			logger.info("Item found in row number: " + isItemfindInList);
			if (isItemfindInList != -1) {
				isClickedOnAddOrEditButton = Action_Edit.edit(driver);
			}
		}

		logger.info("isClickedOnAddOrEditButton: " + isClickedOnAddOrEditButton);

		if (isClickedOnAddOrEditButton) {
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
					"Vendor Contact Name", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_PAGE_NAME, vendorContactName);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
					"Vendor Contact Number", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_PAGE_NUMBER, vendorContactNumber);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
					"Vendor Contact Email", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_PAGE_EMAIL, vendorContactEmail);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver,
					"Vendor Contact Comment", PL_VendorsPage.ADDRESS_VENDOR_CONTACT_PAGE_COMMENT, vendorContactComment);
		}

		boolean isClickedOnSaveButton = ruae.clickOnBtnSave_1_RU();
		if (isClickedOnSaveButton) {
			if (driver.getPageSource().contains("Please")) {
				clickOnCancelButton_1_RU();
				logger.info("Vendor contact not added");
				softAssert.assertTrue(false, "Vendor contact is emplty");
				return new PO_VendorsPage(driver);
			} else {

				String alertMsg = snakeAlertMessagesDisplayedContent_RU();
				logger.info("Alert Message: " + alertMsg);
				if (alertMsg.contains("already exists") || alertMsg.contains("error")) {
					clickOnCancelButton_1_RU();
				} else {
					if (callerMethodName.contains("test_Add")) {
						softAssert.assertEquals(alertMsg, PL_VendorsPage.MESSAGE_VENDOR_CONTACT_CREATED,
								"Check vendor conatct added or not");
					} else if (callerMethodName.contains("test_Edit")) {
						softAssert.assertEquals(alertMsg, PL_VendorsPage.MESSAGE_VENDOR_CONTACT_UPDATED,
								"Check vendor contact updated or not");
					}
				}

			}
		}

		softAssert.assertAll();
		return new PO_VendorsPage(driver);
	}

	// TO DEACTIVATE
	public PO_VendorsPage deactivateVendorContact(String vendorContactEmail, String searchKey, int searchKeyColumnIndex,
			boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey) throws InterruptedException {

		int isItemfindInList = findVendorConatctFromGridListAndClickOnThreeDot(vendorContactEmail, searchKey,
				searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
		logger.info("Item found in row number: " + isItemfindInList);
		if (isItemfindInList != -1) {
			Action_Deactivate.deactivate(driver, PL_VendorsPage.MESSAGE_VENDOR_CONTACT_DEACTIVATED);
		}

		softAssert.assertAll();
		return new PO_VendorsPage(driver);
	}

	// TO ACTIVATE
	public PO_VendorsPage activateVendorContact(String vendorContactEmail, String searchKey, int searchKeyColumnIndex,
			boolean wantToClickOnThreeDot, boolean wantToclickOnFindSearckKey) throws InterruptedException {

		int isItemfindInList = findVendorConatctFromGridListAndClickOnThreeDot(vendorContactEmail, searchKey,
				searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
		logger.info("Item found in row number: " + isItemfindInList);
		if (isItemfindInList != -1) {
			Action_Activate.activate(driver, PL_VendorsPage.MESSAGE_VENDOR_CONTACT_ACTIVATED);
		}

		softAssert.assertAll();
		return new PO_VendorsPage(driver);
	}

}
