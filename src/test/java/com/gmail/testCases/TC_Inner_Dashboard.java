package com.gmail.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.gmail.pageObject.PO_CustomersPage;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_Inner_DashboardPage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.pageObject.PO_TenantsPage;

public class TC_Inner_Dashboard extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_Inner_Dashboard() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_CustomersPage cp;
	public PO_TenantsPage tp;
	public TC_AssumeRole tc_assumeRole = new TC_AssumeRole();
	public PO_Inner_DashboardPage i_dp;

	//VARIABLES TO ASSUME A ROLE
	String tenantName = "Tata Steel 4"; // "WESTWOOD_"+faker.name().firstName();
	String customerName = "Tata Groups";
	String searchKey = customerName;
	String assureRoleAsTenant = searchKey;
	boolean wantToClickOnThreeDot = true;
	int searchKeyColumnIndex = 1;
	boolean wantToClickOnSearchKey = false;

	//TO ASSUME A ROLE AS TENANTS
	@BeforeClass
	public void assumeRole() throws Throwable {
		tc_assumeRole.viewCustomers(customerName, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToClickOnSearchKey, tenantName);
		Thread.sleep(1000);
		searchKey = tenantName;

		tc_assumeRole.assumeRoleAsTenant(tenantName, searchKey, searchKeyColumnIndex, wantToClickOnThreeDot,
				wantToClickOnSearchKey);
		Thread.sleep(2000);
		logger.info("Role Assumed Successfully...");
		
		i_dp = new PO_Inner_DashboardPage(driver);
	}

	// TO CHECK DASHBOARD COUNT
	@Test(priority = 1)
	public void test_CheckDashboardCount() throws Throwable {
		i_dp.checkDashboardCounts();
	}

	// TO CHECK DASHBOARD INNER MENUS
	@Test(priority = 2)
	public void test_CheckDashboardMenus() throws Throwable {
		i_dp.checkDashboardInnerMenus();
	}

}
