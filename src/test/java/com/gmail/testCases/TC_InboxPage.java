package com.gmail.testCases;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_InboxPage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.pageLocators.PL_HomePage;
import com.gmail.utilities.ClickOnAnyButton;

public class TC_InboxPage extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_InboxPage() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_InboxPage po_inboxPage; // MAIN USER LABLES PAGE
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	// VARIABLES
	String searchKey = "Postman Community";
	String EmailSenderName = searchKey;
	int searchKeyColumnIndex = 1;
	boolean wantToGetMatchedRecords = true;


	// TO DELETE EMAILS
	@Test(priority = 1)
	public void test_DelteEmails() throws Throwable {
		po_inboxPage = callMeBeforePerformAnyAction();
		po_inboxPage.deleleEmails(EmailSenderName, searchKey,
				 searchKeyColumnIndex,  wantToGetMatchedRecords);
	}

	// CALL ME IN EVERY @TEST METHODS EXCEPT LOGIN AND LOGOUT
	public PO_InboxPage callMeBeforePerformAnyAction() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		hp = new PO_HomePage(driver);
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Inbox Tab", PL_HomePage.addressButtonInbox);
		Thread.sleep(4000);
		return new PO_InboxPage(driver);
	}

}
