package com.gmail.pageObject.pageLocators;

public class PL_AssetConfigurationsPage {

	// ICONS
	//public static final String IOCN_LIST_VIEW = "//div[contains(@class,'neu-bulleted-list-2 px-3 py-2 flex cursor-pointer')]";
	//public static final String IOCN_GRID_VIEW = "//div[contains(@class,'neu-menu px-3 py-2 flex border-common-r cursor-pointer')]";
	public static final String ICON_SHOW_ARCHIVED = "//div[@class='px-3 py-2 flex cursor-pointer text-green hover:text-green']";
	public static final String ICON_HIDE_ARCHIVED = "//div[@class='px-3 py-2 flex cursor-pointer text-red hover:text-red']";

	// ELEMENT ADDRESSS
	public static final String ADDRESS_ASSET_CONFIGURATION_NAME = "(//div[starts-with(@class,'Input_input')])[2]//input";
	public static final String ADDRESS_ASSET_CONFIGURATION_DESCRIPTION = "(//div[starts-with(@class,'Input_input')])[3]//textarea";
	public static final String ADDRESS_ASSET_CONFIGURATION_UNIT = "(//div[starts-with(@class,'Input_input')])[4]//input";
	public static final String ADDRESS_ASSET_CONFIGURATION_DATATYPE_DROPDOWN_IOCN = "//div[starts-with(@class,'Input_inputContainer')]//*[name()='svg']";
	public static final String ADDRESS_ASSET_CONFIGURATION_DATATYPE_NUMBER = "//div[@role='listbox']//div[text()='Number']";
	public static final String ADDRESS_ASSET_CONFIGURATION_DATATYPE_TEXT = "//div[@role='listbox']//div[text()='Text']";
	
	public static final String ADDRESS_ASSET_CONFIGURATION_PREFIX = "(//div[starts-with(@class,'Input_input')])[6]//input";
	public static final String ADDRESS_ASSET_CONFIGURATION_POSTFIX = "(//div[starts-with(@class,'Input_input')])[7]//input";
	
	public static final String ADDRESS_ASSET_CONFIGURATION_VALUES = "(//div[starts-with(@class,'Input_input')])";	//[8]//input
	public static final String ADDRESS_ASSET_CONFIGURATION_VALUES_PLUSBUTTON = "(//*[name()='svg'][starts-with(@class,'Icon_adminIcon')])[4]";
	

	public static final String ADDRESS_ASSET_CONFIGURATION_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";

	// ALERT MESSAGES
	public static final String MESSAGE_ASSET_CONFIGURATION_CREATEDED = "Asset configuration saved successfully.";
	public static final String MESSAGE_ASSET_CONFIGURATION_UPDATED = MESSAGE_ASSET_CONFIGURATION_CREATEDED;
	public static final String MESSAGE_ASSET_CONFIGURATION_ARCHIVED = "Asset configuration archived successfully.";
	public static final String MESSAGE_ASSET_CONFIGURATION_RESTORED = "Asset configuration restored successfully.";
}
