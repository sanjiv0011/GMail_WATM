package com.gmail.pageObject.pageLocators;

public class PL_HomePage {

	public static final String addressButtonCompose = "(//div[contains(text(),'Compose')])[1]";
	public static final String addressButtonInbox = "//div[contains(@class,'aim')]//*[text()='Inbox']";
	public static final String addressButtonStarred = "//div[contains(@class,'aim')]//*[text()='Starred']";
	public static final String addressButtonSnoozed = "//div[contains(@class,'aim')]//*[text()='Snoozed']";
	public static final String addressButtonSent = "//div[contains(@class,'aim')]//*[text()='Sent']";
	public static final String addressButtonDrafts = "//div[contains(@class,'aim')]//*[text()='Drafts']";
	public static final String addressButtonMore = "(//span[@class='CJ'][normalize-space()='More'])[1]";
	
	public static final String addressUserLogo = "//a[starts-with(@aria-label, 'Google Account')]";
	public static final String addressUserAccountFrame = "(//iframe[contains(@name,'account')])[1]";
	public static final String addressButtonLogout = "//*[contains(normalize-space(),'Sign out')]";
}
