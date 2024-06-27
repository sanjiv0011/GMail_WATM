package com.gmail.pageObject.pageLocators;

public class PL_CustomersPage {

	// ADDRESSES
	public static final String ADD_CUSTOMER_NAME = "//input[@placeholder='Enter Customer Name']";
	public static final String ADD_CUSTOMERS_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";

	// ALERT MESSAGES
	public static final String pleaseEnterLabelName = "Please enter label.";
	public static final String customersAdded = "Customer created successfully.";
	public static final String customersUpdated = "Customer updated successfully.";
	public static final String customerDeactivate = "Customer deactivated successfully.";
	public static final String customerArchived = "Customer archived successfully.";
	public static final String customerRestored = "Customer restored successfully.";
	public static final String customerActivated = "Customer activated successfully.";

	// USER | SUGGEGETIONS MESSAGES
	public static final String customeNotFound = "No customers found";
}
