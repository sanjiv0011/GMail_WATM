package com.gmail.pageObject.pageLocators;

public class PL_Inner_DashboardPage {

	// DASHBOARD COUNT
	public static final String ADDRESS_LOGGED_USED_NAME = "//h4[@class='text-xl font-medium text-slate-50']//div[2]";
	public static final String ADDRESS_TOTAL_CATEGORY_COUNT = "(//div[contains(@class,'dashboardCard')])[1]//span//span[1]";
	public static final String ADDRESS_TOTAL_ASSET_COUNT = "(//div[contains(@class,'dashboardCard')])[2]//span//span[1]";
	public static final String ADDRESS_TOTAL_AVAILABLE_ASSET_COUNT = "(//div[contains(@class,'dashboardCard')])[3]//span//span[1]";
	public static final String ADDRESS_TOTAL_USED_ASSET_COUNT = "(//div[contains(@class,'dashboardCard')])[4]//span//span[1]";

	// DASHBOARD MENUS ITEMS
	public static final String ADDRESS_DASHBOARD = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Dashbaord')])[1]";
	public static final String ADDRESS_ASSETS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Assets')])[1]";
	public static final String ADDRESS_ASSET_CATEGORIES = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Asset Categories')])[1]";
	public static final String ADDRESS_REPORTS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Reports')])[1]";
	public static final String ADDRESS_ASSET_CONFIGURATIONS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Asset Configurations')])[1]";
	public static final String ADDRESS_VENDORS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Vendors')])[1]";
	public static final String ADDRESS_USERS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Users')])[1]";
	public static final String ADDRESS_INVOICES = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Invoices')])[1]";
	public static final String ADDRESS_SERVICES = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Services')])[1]";
	public static final String ADDRESS_SETTINGS = "(//div[contains(@class, 'showOptions')]//*[contains(text(), 'Settings')])[1]";
	
	public static final String ADDRESS_LOCATIONS = "(//div[contains(@class,'flex flex-col gap-2')]//span[text()='Locations'])[1]";
	public static final String ADDRESS_DEPARTMENTS = "(//div[contains(@class,'flex flex-col gap-2')]//span[text()='Departments'])[1]";
	
	
	

}
