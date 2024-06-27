package com.gmail.pageObject;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.gmail.ReUseAble.PageObject.ReUseAbleElement;
import com.gmail.pageObject.pageLocators.PL_LoginPage;
import com.gmail.utilities.ClickOnAnyButton;
import com.gmail.utilities.NavigateToNewOpenTab;
import com.gmail.utilities.SetDataIntoTextInputField;

public class PO_LoginPage extends ReUseAbleElement {
	
	public WebDriver driver;
	public Logger logger = LogManager.getLogger(getClass());
	public JavascriptExecutor jsExecutor;
	public ReUseAbleElement ruae;
	public WebDriverWait wait;
	public Actions action;
	public SoftAssert softAssert = new SoftAssert();
	
	public SetDataIntoTextInputField setDataIntoTextInputField = new SetDataIntoTextInputField();
	public NavigateToNewOpenTab navigateToNewTab = new NavigateToNewOpenTab();
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();
	
	
	
	public  PO_LoginPage(WebDriver driver)
	{   super(driver);
	    this.driver = driver;
	    jsExecutor  = (JavascriptExecutor)driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait (driver, Duration.ofSeconds(30));
		action = new Actions(driver);
	}
	

		
	//FOR USER LOGIN
	public PO_HomePage Login(String userEmail,String userPassword) throws InterruptedException {
		try {
			logger.info("Method called Login: Login");
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Sing In Button", PL_LoginPage.ADD_buttonSingInWithGoole);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Email", PL_LoginPage.ADD_fieldEmail, userEmail);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Password", PL_LoginPage.ADD_fieldPassword, userPassword);
			
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Submit", PL_LoginPage.ADD_buttonSubmit);
			
			try {
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Dashboard"));
				Thread.sleep(500);
				if(driver.getPageSource().contains("Welcome")) {
					softAssert.assertTrue(true);
					logger.info("...LOGIN DONE...");
				} else {
					softAssert.assertTrue(false);
					logger.info("!!!LOGIN FAILED!!!");
				}
			}catch(Exception e) {
				logger.info("Login exception message: "+e.getMessage());
				softAssert.assertEquals(driver.getPageSource().contains("Welcome"),"To check the login");
			}
		}catch(Exception e) {}
		
		softAssert.assertAll();
		return new PO_HomePage(driver);
	}
	
}
