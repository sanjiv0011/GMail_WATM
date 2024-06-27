package com.gmail.pageObject.pageLocators;

public class PL_InvoicesPage {

	// ICONS
	public static final String IOCN_LIST_VIEW = "//div[contains(@class,'px-3 py-2 flex cursor-pointer hover:text-orange text-orange')]";
	public static final String IOCN_GRID_VIEW = "//div[contains(@class,'px-3 py-2 flex border-common-r cursor-pointer hover:text-orange')]";
	public static final String ICON_SHOW_ARCHIVED = "//div[contains(@class,'flex items-center border-common rounded1 hover:border-green')]";
	public static final String ICON_SHOW_RESTORED = "//div[contains(@class,'px-3 py-2 flex cursor-pointer text-red hover:text-red')]";

	// ELEMENT ADDRESSS
	public static final String ADDRESS_PAGE_TITLE = "//div[@class='capitalize heading-18 sm:heading-22 ml-4']";

	public static final String ADDRESS_INVOICE_NAME = "(//div[contains(@class,'Input_inputContainer')])[3]//input";
	public static final String ADDRESS_INVOICE_CODE = "(//div[contains(@class,'Input_inputContainer')])[4]//input";
	public static final String ADDRESS_INVOICE_DESCRIPTION = "(//div[contains(@class,'Input_inputContainer')])[5]//textarea";

	public static final String ADDRESS_INVOICE_IMAGE = "//input[@type='file']";

	public static final String ADDRESS_ASSIGNMENT_TYPE_CHECKBOX_LIST = "//div[contains(@class,'w-max flex items-center gap-2')]//*[name()='svg']";

	public static final String ADDRESS_INVOICE_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";

	// ALERT MESSAGES
	public static final String MESSAGE_INVOICE_CREATEDED = "Asset category added successfully.";
	public static final String MESSAGE_INVOICE_UPDATED = "Asset category updated successfully.";
	public static final String MESSAGE_INVOICE_DEACTIVATED = "Asset category deactivated successfully.";
	public static final String MESSAGE_INVOICE_ARCHIVED = "Asset category archived successfully.";
	public static final String MESSAGE_INVOICE_RESTORED = "Asset category restored successfully.";
	public static final String MESSAGE_INVOICE_ACTIVATED = "Asset category activated successfully.";

}
