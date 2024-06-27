package com.gmail.pageObject.pageLocators;

public class PL_AssetCategoriesPage {

	// ICONS
	public static final String IOCN_LIST_VIEW = "//div[contains(@class,'neu-bulleted-list-2 px-3 py-2 flex cursor-pointer')]";
	public static final String IOCN_GRID_VIEW = "//div[contains(@class,'neu-menu px-3 py-2 flex border-common-r cursor-pointer')]";
	public static final String ICON_SHOW_ARCHIVED = "//div[@class='px-3 py-2 flex cursor-pointer text-green hover:text-green']";
	public static final String ICON_SHOW_RESTORED = "//div[@class='px-3 py-2 flex cursor-pointer text-red hover:text-red']";

	// ELEMENT ADDRESSS
	public static final String ADDRESS_PAGE_TITLE = "//div[@class='capitalize heading-18 sm:heading-22 ml-4']";

	public static final String ADDRESS_ASSET_CATEGORY_NAME = "(//div[contains(@class,'Input_inputContainer')])[3]//input";
	public static final String ADDRESS_ASSET_CATEGORY_CODE = "(//div[contains(@class,'Input_inputContainer')])[4]//input";
	public static final String ADDRESS_ASSET_CATEGORY_DESCRIPTION = "(//div[contains(@class,'Input_inputContainer')])[5]//textarea";

	public static final String ADDRESS_ASSET_CATEGORY_IMAGE = "//input[@type='file']";

	public static final String ADDRESS_ASSIGNMENT_TYPE_CHECKBOX_LIST = "//div[contains(@class,'w-max flex items-center gap-2')]//*[name()='svg']";

	public static final String ADDRESS_ASSET_CATEGORY_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";

	// ALERT MESSAGES
	public static final String MESSAGE_ASSET_CATEGORY_CREATEDED = "Asset category added successfully.";
	public static final String MESSAGE_ASSET_CATEGORY_UPDATED = "Asset category updated successfully.";
	public static final String MESSAGE_ASSET_CATEGORY_DEACTIVATED = "Asset category deactivated successfully.";
	public static final String MESSAGE_ASSET_CATEGORY_ARCHIVED = "Asset category archived successfully.";
	public static final String MESSAGE_ASSET_CATEGORY_RESTORED = "Asset category restored successfully.";
	public static final String MESSAGE_ASSET_CATEGORY_ACTIVATED = "Asset category activated successfully.";

}
