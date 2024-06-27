package com.gmail.pageObject.pageLocators;

public class PL_HomePage {

	// ALERT MESSAGES
	public static final String alertMsgCardAddedSuccesfully = "Card Added Successfully.";

	// =========START========HOME PAGE OBJECTS=============//
	public static final String add_tab_dashaboard = "//div[contains(@class,'sidebarCategory')]//span[contains(text(),'Dashboard')]";
	public static final String add_tab_customer = "//div[contains(@class,'sidebarCategory')]//span[contains(text(),'Customers')]";
	public static final String add_userlogo = "//div[@class='flex items-center gap-2']";
	public static final String add_bnt_profile = "//li[contains(@role,'menuitem')]//div[contains(text(),'Profile')]";
	public static final String add_bnt_settings = "//li[contains(@role,'menuitem')]//div[contains(text(),'Settings')]";
	public static final String add_bnt_lockScreen = "//li[contains(@role,'menuitem')]//div[contains(text(),'Lock Screen')]";
	public static final String add_bnt_logout = "//li[contains(@role,'menuitem')]//div[contains(text(),'Logout')]";
	public static final String add_btn_yes = "//button//div[text()='Yes']";
	public static final String add_btn_no = "//button//div[text()='No']";
}
