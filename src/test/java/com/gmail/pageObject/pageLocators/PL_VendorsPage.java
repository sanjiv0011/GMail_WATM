package com.gmail.pageObject.pageLocators;

public class PL_VendorsPage {

	// ICONS
	public static final String IOCN_LIST_VIEW = "//div[contains(@class,'neu-bulleted-list-2 px-3 py-2 flex cursor-pointer')]";
	public static final String IOCN_GRID_VIEW = "//div[contains(@class,'neu-menu px-3 py-2 flex border-common-r cursor-pointer')]";
//	public static final String ICON_SHOW_ARCHIVED = "//div[@class='px-3 py-2 flex cursor-pointer text-green hover:text-green']";
//	public static final String ICON_HIDE_ARCHIVED = "//div[@class='px-3 py-2 flex cursor-pointer text-red hover:text-red']";
	public static final String ICON_CLEAR_ALL_FILTER = "//div[@class='px-3 py-2 flex cursor-pointer']";

	// VENDOR DETAILS TAB
	public static final String ADDRESS_VENDOR_NAME = "(//div[starts-with(@class,'Input_input')])[1]//input";
	public static final String ADDRESS_VENDOR_EMAIL = "(//div[starts-with(@class,'Input_input')])[2]//input";
	public static final String ADDRESS_VENDOR_PHONENUMBER = "(//div[starts-with(@class,'Input_input')])[3]//input";
	public static final String ADDRESS_VENDOR_WHATSAPPNUMBER = "(//div[starts-with(@class,'Input_input')])[4]//input";
	public static final String ADDRESS_VENDOR_WEBSITE = "(//div[starts-with(@class,'Input_input')])[5]//input";

	// VENDOR ADDRESS TAB
	public static final String ADDRESS_VENDOR_ADDRESS1 = "(//div[starts-with(@class,'Input_input')])[1]//input";
	public static final String ADDRESS_VENDOR_ADDRESS2 = "(//div[starts-with(@class,'Input_input')])[2]//input";
	public static final String ADDRESS_VENDOR_CITY = "(//div[starts-with(@class,'Input_input')])[3]//input";
	public static final String ADDRESS_VENDOR_POSTALCODE = "(//div[starts-with(@class,'Input_input')])[4]//input";
	public static final String ADDRESS_VENDOR_STATE = "(//div[starts-with(@class,'Input_input')])[5]//input";
	public static final String ADDRESS_VENDOR_COUNTRY = "(//div[starts-with(@class,'Input_input')])[6]//input";
	public static final String ADDRESS_VENDOR_OVERVIEW = "(//div[starts-with(@class,'Input_input')])[7]//textarea";

	// VENDOR CONTACT TAB
	public static final String ADDRESS_VENDOR_CONTACT_NAME = "(//div[starts-with(@class,'Input_input')])[1]//input";
	public static final String ADDRESS_VENDOR_CONTACT_NUMBER = "(//div[starts-with(@class,'Input_input')])[2]//input";
	public static final String ADDRESS_VENDOR_CONTACT_EMAIL = "(//div[starts-with(@class,'Input_input')])[3]//input";
	public static final String ADDRESS_VENDOR_CONTACT_COMMENT = "(//div[starts-with(@class,'Input_input')])[4]//textarea";

	// VENDOR LIST PAGE
	public static final String ADDRESS_VENDOR_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";

	// ALERT MESSAGES
	public static final String MESSAGE_VENDOR_CREATEDED = "Vendor created successfully.";
	public static final String MESSAGE_VENDOR_UPDATED = "Vendor updated successfully.";
	public static final String MESSAGE_VENDOR_CONTACT_CREATED = "Vendor contact created successfully.";
	public static final String MESSAGE_VENDOR_CONTACT_UPDATED = "Vendor contact updated successfully.";
	public static final String MESSAGE_VENDOR_CONTACT_DEACTIVATED = "Vendor Contact deactivated successfully.";
	public static final String MESSAGE_VENDOR_CONTACT_ACTIVATED = "Vendor Contact activated successfully.";

	//ADD VENDOR CONTACT PAGE
	public static final String ADDRESS_ADD_CONTACT = "(//div[normalize-space()='Add Contact'])[1]";
	public static final String ADDRESS_VENDOR_CONTACT_PAGE_NAME = "(//div[starts-with(@class,'Input_input')])[3]//input";
	public static final String ADDRESS_VENDOR_CONTACT_PAGE_NUMBER = "(//div[starts-with(@class,'Input_input')])[4]//input";
	public static final String ADDRESS_VENDOR_CONTACT_PAGE_EMAIL = "(//div[starts-with(@class,'Input_input')])[5]//input";
	public static final String ADDRESS_VENDOR_CONTACT_PAGE_COMMENT = "(//div[starts-with(@class,'Input_input')])[6]//textarea";
	public static final String ADDRESS_VENDOR_CONTACT_LIST = "//div[contains(@class,'flex flex-col items-start gap-2 w-full')]//a";
	public static final String ADDRESS_VENDOR_CONTACT_THREEDOTBUTTON = "//div[@class='absolute top-[4px] right-1']";
	
}
