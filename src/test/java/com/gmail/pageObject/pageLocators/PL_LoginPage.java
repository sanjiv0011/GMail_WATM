package com.gmail.pageObject.pageLocators;

public class PL_LoginPage {

	public static final String addressButtonSingIn = "(//a[normalize-space()='Sign in'])[1]";
	public static final String addressAlreadyLoginEmail = "(//span[@role='checkbox'])[3]";
	public static final String addressFieldEmail= "(//input[@id='identifierId'])[1]";
	public static final String addressFieldPassword = "(//input[@name='Passwd'])[1]";
	public static final String addressButtonNext = "//span[contains(text(),'Next')]";
	public static final String addressTextYouAreSignedIn = "//div[normalize-space()='Complete a few suggestions to get the most out of your Google account']";
	public static final String addressButtonNotNow = "(//span[normalize-space()='Not now'])[1]";
	public static final String addressButtonUseAnotherAccount = "(//div[contains(text(),'Use another account')])[1]";
	public static final String addressButtonCompose = "(//div[contains(text(),'Compose')])[1]";

}

