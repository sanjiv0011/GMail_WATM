package com.gmail.pageObject;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			logger.info("Method called Login");
			//clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Sing In", PL_LoginPage.addressButtonSingIn);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Email", PL_LoginPage.addressFieldEmail, userEmail);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Sing In", PL_LoginPage.addressButtonNext);
			setDataIntoTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Password", PL_LoginPage.addressFieldPassword, userPassword);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Sing In", PL_LoginPage.addressButtonNext);
			
			try {
				WebElement compose = driver.findElement(By.xpath(PL_LoginPage.addressButtonCompose));
				wait.until(ExpectedConditions.elementToBeClickable(compose));
				Thread.sleep(500);
				if(driver.getPageSource().contains("Compose")) {
					softAssert.assertTrue(true);
					logger.info("...LOGIN DONE...");
				} else {
					softAssert.assertTrue(false);
					logger.info("!!!LOGIN FAILED!!!");
				}
			}catch(Exception e) {
				logger.info("Login exception message: "+e.getMessage());
				softAssert.assertEquals(driver.getPageSource().contains("Compose"),"To check the login");
			}
		}catch(Exception e) {}
		
		softAssert.assertAll();
		return new PO_HomePage(driver);
	}
	
}
