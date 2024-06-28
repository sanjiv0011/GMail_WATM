package com.gmail.pageObject.pageLocators;

public class PL_InboxPage {

	public static final String addressCheckboxSelect = "(//span[@role='checkbox'])[2]";
	public static final String addressButtonDeleteEmail = "(//div[@aria-label='Delete'])[2]";
	public static final String addressButton1_50_OfMany = "(//div[@aria-label='Show more messages'])[2]";
	public static final String addressButton1_50_OfMany_NextArrow ="(//div[@aria-label='Older'])[2]";
	public static final String addressButton1_50_OfMany_BackArrow ="(//div[@aria-label='Newer'])[2]";
	public static final String addressAlertConversationMovedToTrace =  "conversations moved to Trash";
	public static final String addressMessageNoEmailFound = "//span[contains(text(),'search options')]";
	public static final String addressEmailList = "//tr[contains(@class,'zA')]";
	public static final String addressCheckboxList = "//div[@role='checkbox']";

}

