package com.gmail.testCases;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.PO_CustomersPage;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.pageLocators.PL_HomePage;
import com.gmail.utilities.ClickOnAnyButton;

public class TC_Customers extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_Customers() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_CustomersPage cp; // MAIN USER LABLES PAGE
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	// VARIABLES TO ADD PAYEMNTS
	String customerName = "Sri Ram Industries 2"; // "WESTWOOD_"+faker.name().firstName();
	String edit_customerName = customerName+" Updated";
	String searchKey = customerName; // "WESTWOOD READY 2";
	boolean wantToClickOnThreeDot = true;
	int searchKeyColumnIndex = 1;
	boolean wantToClickOnSearchKey = false;

	// TO ADD CUSTOMERS
	@Test(priority = 1)
	public void test_AddCustomer() throws Throwable {
		cp = callMeBeforePerformAnyAction();
		cp.addOrEditCustomer(customerName, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToClickOnSearchKey);
	}

	// TO EDIT CUSTOMERS
	@Test(priority = 2)
	public void test_EditCustomer() throws Throwable {
		cp = callMeBeforePerformAnyAction();
		cp.addOrEditCustomer(edit_customerName, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToClickOnSearchKey);
		searchKey = edit_customerName;
	}

	// TO DEACTIVATE
	@Test(priority = 3)
	public void test_DeactivateCustomer() throws Throwable {
		test_FindCustomerFromListAndClickOnThreeDotButton();
		cp.deactivateCustomer();
	}

	// TO ACTIVATE
	@Test(priority = 4)
	public void test_ActivateCustomer() throws Throwable {
		test_FindCustomerFromListAndClickOnThreeDotButton();
		cp.activateCustomer();
	}

	// TO ARCHIVE
	@Test(priority = 5)
	public void test_ArchiveCustomer() throws Throwable {
		test_FindCustomerFromListAndClickOnThreeDotButton();
		cp.archiveCustomer();
	}

	// TO RESTORE
	@Test(priority = 6)
	public void test_RestoreCustomer() throws Throwable {
		test_FindCustomerFromListAndClickOnThreeDotButton();
		cp.restoreCustomer();
	}

	// TO VIEW 
	@Test(priority = 7)
	public void test_ViewCustomer() throws Throwable {
		test_FindCustomerFromListAndClickOnThreeDotButton();
		cp.viewCustomer();
	}

	// TO FIND THE CUSTOMERS FROM THE LIST AND CLICK ON THE THREE DOT ACTION BUTTON
	// @Test(priority = 10)
	public void test_FindCustomerFromListAndClickOnThreeDotButton() throws Throwable {
		cp = callMeBeforePerformAnyAction();
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
		cp.findCustomerFromRowListAndClickOnThreeDot(customerName, searchKey, searchKeyColumnIndex,
				wantToClickOnThreeDot, wantToClickOnSearchKey);
	}

	// CALL ME IN EVERY @TEST METHODS EXCEPT LOGIN AND LOGOUT
	public PO_CustomersPage callMeBeforePerformAnyAction() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		hp = new PO_HomePage(driver);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Dashboard Tab", PL_HomePage.add_tab_dashaboard);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Customer Tab", PL_HomePage.add_tab_customer);
//		hp.clickOnMenuDashboard();
//		hp.clickOnMenuCustomer();
		Thread.sleep(4000);
		return new PO_CustomersPage(driver);
	}

}
