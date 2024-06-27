package com.gmail.testCases;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_Inner_DashboardPage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.PO_VendorsPage;
import com.gmail.pageObject.pageLocators.PL_Inner_DashboardPage;
import com.gmail.utilities.ClickOnAnyButton;

public class TC_Vendors extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_Vendors() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_VendorsPage po_vendor;
	public PO_Inner_DashboardPage innerDashboard;
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	// VARIABLE VENDOR DETAILS
	String vendorName = "Tata Croma Product";
	String vendorEmail = faker.internet().emailAddress();
	String vendorPhoneNumber = "917894654123";
	String vendorWhatsAppNumber = "917894560123";
	String vendorWebsite = "https://www.google.com/";
	String vendorType = "Individual"; // "Agency"

	// VARIABLE VENDOR ADDRESS
	String vendorAddress1 = faker.address().streetAddress();
	String vendorAddress2 = faker.address().fullAddress();
	String vendorCity = faker.address().cityName();
	String vendorPostalCode = "799854"; // text or number;
	String vendorState = faker.address().state();
	String vendorCountry = faker.address().country(); // text or number;
	String vendoroverview = faker.lorem().sentence();

	// VARIABLE VENDOR CONTACT
	String vendorContactName = faker.name().fullName();
	String vendorContactNumber = "917894561230";
	String vendorContactEmail = faker.internet().emailAddress();
	String vendorContactComment = faker.lorem().sentence();

	String vendorName_Update = vendorName + " Updated";

	// VARIABLES FOR SEARCHING
	String searchKey = vendorName;
	int searchKeyColumnIndex = 1;
	boolean wantToClickOnThreeDot = false;
	boolean wantToclickOnFindSearckKey = false;
	String searchKey_assetConfiguration = null;
	String searchKey_Vendor = "Tata Croma Product Updated";
	boolean wantToclickOnFindSearckKey_Vendor = true;
	int searchKeyColumnIndex_Vendor = 1;
	boolean wantToClickOnThreeDot_Vendor = false;

	// TO ADD
	//@Test(priority = 1)
	public void test_AddVendor() throws Throwable {
		po_vendor = callMeBeforePerformAnyAction();
		po_vendor.addOrEditVendor(vendorName, vendorEmail, vendorPhoneNumber, vendorWhatsAppNumber, vendorWebsite,
				vendorType, vendorAddress1, vendorAddress2, vendorCity, vendorPostalCode, vendorState, vendorCountry,
				vendoroverview, vendorContactName, vendorContactNumber, vendorContactEmail, vendorContactComment,
				searchKey, searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
	}

	// TO EDIT
	//@Test(priority = 2)
	public void test_EditVendor() throws Throwable {
		po_vendor = callMeBeforePerformAnyAction();
		po_vendor.addOrEditVendor(vendorName_Update, vendorEmail, vendorPhoneNumber, vendorWhatsAppNumber,
				vendorWebsite, vendorType, vendorAddress1, vendorAddress2, vendorCity, vendorPostalCode, vendorState,
				vendorCountry, vendoroverview, vendorContactName, vendorContactNumber, vendorContactEmail,
				vendorContactComment, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToclickOnFindSearckKey);
		searchKey = vendorName_Update;
	}

	// TO ADD VENDOR CONTACT
	//@Test(priority = 3)
	public void test_AddVendorContact() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		po_vendor.addOrEditVendorContact(vendorContactName, vendorContactNumber, vendorContactEmail,
				vendorContactComment,searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToclickOnFindSearckKey);
	}

	// TO EDIT VENDOR CONTACT
	@Test(priority = 4)
	public void test_EditVendorContact() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		vendorContactEmail = "viva.beer@gmail.com";
		searchKey = vendorContactEmail;
		po_vendor.addOrEditVendorContact(vendorContactName, vendorContactNumber, vendorContactEmail,
				vendorContactComment,searchKey, 4, false,false);
	}

	// TO DEACTIVATE
	//@Test(priority = 5)
	public void test_DeactivateVendorContact() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		vendorContactEmail = "viva.beer@gmail.com";
		searchKey = vendorContactEmail;
		po_vendor.deactivateVendorContact(vendorContactEmail, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
	}

	// TO ACTIVATE
	//@Test(priority = 6)
	public void test_ActivateVendorContact() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		vendorContactEmail = "viva.beer@gmail.com";
		searchKey = vendorContactEmail;
		po_vendor.activateVendorContact(vendorContactEmail, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot, wantToclickOnFindSearckKey);
	}

	// TO FIND THE ASSET CATEGORY FROM THE LIST AND CLICK ON THE THREE DOT ACTION
	// BUTTON
	// @Test(priority = 10)
	public void test_FindDataFromListAndClickOnThreeDotButton() throws Throwable {
		po_vendor = callMeBeforePerformAnyAction();
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		if (callerMethodName.contains("test_Restore")) {
			ReUseAbleElement ruae = new ReUseAbleElement(driver);
			ruae.clickONBtnTooltip_RU("Show Archived", driver);
		}
		po_vendor.findDataFromRowListAndClickOnThreeDot(vendorName, searchKey_Vendor, searchKeyColumnIndex_Vendor,
				wantToClickOnThreeDot_Vendor, wantToclickOnFindSearckKey_Vendor);
	}

	// CALL ME IN EVERY @TEST METHODS EXCEPT LOGIN AND LOGOUT AND ASSUME_ROLE
	public PO_VendorsPage callMeBeforePerformAnyAction() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		innerDashboard = new PO_Inner_DashboardPage(driver);
		Thread.sleep(1000);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Vendors",
				PL_Inner_DashboardPage.ADDRESS_VENDORS);
		Thread.sleep(3000);
		return new PO_VendorsPage(driver);
	}

}
