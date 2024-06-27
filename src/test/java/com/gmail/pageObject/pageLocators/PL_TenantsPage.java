package com.gmail.pageObject.pageLocators;

public class PL_TenantsPage {

	//ELEMENTS ADDRESSES
	public static final String ADD_PAGETITLE = "//div[@class='capitalize heading-18 sm:heading-22 ml-4']";
	public static final String ADD_TENANT_TAB = "(//div[@class='flex w-full items-center'])[1]";
	public static final String ADD_ADMIN_TAB = "(//div[@class='flex w-full items-center'])[2]";
	public static final String ADD_TENANT_LIST = "//div[contains(@class,'flex flex-col gap-2 items-center')]";
	public static final String ADD_TENANT_NAME = "(//div[contains(@class,'Input_mainContainer')])[3]//input";
	public static final String ADD_TENANT_ASSET_CODE = "(//div[contains(@class,'Input_mainContainer')])[4]//input";
	public static final String ADD_TENANT_DESCRIPTION = "(//div[contains(@class,'Input_mainContainer')])[5]//textarea";
	public static final String ADD_TENANT_IMAGE = "//input[@type='file']";

	//ALERT MESSAGES
	public static final String tenantsAdded = "Tenant created successfully.";
	public static final String tenantsUpdated = "Tenant updated successfully.";
	public static final String tenantDeactivated = "Tenant deactivated successfully.";
	public static final String tenantArchived = "Tenant archived successfully.";
	public static final String tenantRestored = "Tenant restored successfully.";
	public static final String tenantActivated = "Tenant activated successfully.";
	public static final String tenantNotFound = "No tenants found";

}
