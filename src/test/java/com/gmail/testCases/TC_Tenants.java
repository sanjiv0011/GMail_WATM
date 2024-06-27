package com.gmail.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.actions.Action_ClickOnAnyColumnElementBasedOnSelectedRowItem;
import com.gmail.pageObject.PO_CustomersPage;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.PO_TenantsPage;
import com.gmail.pageObject.pageLocators.PL_HomePage;
import com.gmail.utilities.ClickOnAnyButton;

public class TC_Tenants extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_Tenants() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_CustomersPage cp;
	public PO_TenantsPage tp;
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	// VARIABLES
	String tenantName = "Tata Steel 4"; // "WESTWOOD_"+faker.name().firstName();
	String tenantAssetCode = faker.random().hex(3).toString() + "AB";
	String tenantDescription = faker.lorem().sentence();
	String tenantImage = "C:\\Users\\User\\Downloads\\download (2).jpeg";

	String edit_tenantName = tenantName + " Updated";
	String customerName = "Tata Groups";

	String searchKey = tenantName; // "WESTWOOD READY 2";
	String assureRoleAsTenant = searchKey;
	boolean wantToClickOnThreeDot = true;
	int searchKeyColumnIndex = 1;
	boolean wantToClickOnSearchKey = false;



	// TO VIEW SPECIFIC CUSTOMERS
	@Test(priority = 1)
	public void test_ViewCustomers() throws Throwable {
		cp = customerPageAccess();
		logger.info("driver handle: " + Thread.currentThread().getName());
		test_FindDataFromListAndClickOnThreeDotButton();
		tp = cp.viewCustomerAndTenantPageOjbect();
	}

	// TO ADD
	@Test(priority = 2)
	public void test_AddTenant() throws Throwable {

		tp.addOrEditTenant(tenantName, tenantAssetCode, tenantDescription, tenantImage, searchKey, searchKeyColumnIndex,
				wantToClickOnThreeDot, wantToClickOnSearchKey);
	}

	// TO EDIT
	@Test(priority = 3)
	public void test_EditTenant() throws Throwable {
		tp.addOrEditTenant(edit_tenantName, tenantAssetCode, tenantDescription, tenantImage, searchKey,
				searchKeyColumnIndex, wantToClickOnThreeDot, wantToClickOnSearchKey);
		searchKey = edit_tenantName;
	}

	// TO DEACTIVATE
	@Test(priority = 4)
	public void test_DeactivateTenant() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		tp.deactivateTenant();
	}

	// TO ACTIVATE
	@Test(priority = 5)
	public void test_ActivateTenant() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		tp.activateTenant();
	}

	// TO ARCHIVE
	@Test(priority = 6)
	public void test_ArchiveTenant() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		tp.archiveTenant();
	}

	// TO RESTORE
	@Test(priority = 7)
	public void test_RestoreTenant() throws Throwable {
		test_FindDataFromListAndClickOnThreeDotButton();
		tp.restoreTenant();
	}

	// TO ASSUME A ROLE AS TENANTS
	@Test(priority = 8)
	public void test_AssumeRoleAsTenant() throws Throwable {
		int rowListcount = tp.findTenantFromRowListAndClickOnThreeDot(tenantName, searchKey, searchKeyColumnIndex,
				false, false);
		tp.assumeRoleAsTenant(assureRoleAsTenant, rowListcount, 5,  true, driver);
	}
	
	
	
	// TO FIND FROM THE LIST AND CLICK ON THE THREE DOT ACTION BUTTON
	// @Test(priority = 10)
	public void test_FindDataFromListAndClickOnThreeDotButton() throws Throwable {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
//		if(!callerMethodName.equals("test_ViewCustomers")) {
//			//callMeBeforePerformAnyAction();
//		}
		if (callerMethodName.contains("test_Restore")) {
			ReUseAbleElement ruae = new ReUseAbleElement(driver);
			logger.info("callerMethodName: " + callerMethodName);
			ruae.clickONBtnTooltip_RU("Show Archived", driver);
			Thread.sleep(1000);
		}

		if (callerMethodName.equals("test_ViewCustomers")) {
			cp.findCustomerFromRowListAndClickOnThreeDot(customerName, customerName, searchKeyColumnIndex,
					wantToClickOnThreeDot, wantToClickOnSearchKey);
		} else {
			tp.findTenantFromRowListAndClickOnThreeDot(tenantName, searchKey, searchKeyColumnIndex,
					wantToClickOnThreeDot, wantToClickOnSearchKey);
		}

	}
	
	// TO ACCESS CUSTOMERS PAGE OBJECTS
	public PO_CustomersPage customerPageAccess() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		hp = new PO_HomePage(driver);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Dashboard Tab", PL_HomePage.add_tab_dashaboard);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Customer Tab", PL_HomePage.add_tab_customer);
		Thread.sleep(4000);
		return new PO_CustomersPage(driver);
	}

}
