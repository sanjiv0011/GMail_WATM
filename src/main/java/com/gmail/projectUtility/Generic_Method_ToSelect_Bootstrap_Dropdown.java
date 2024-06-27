package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class Generic_Method_ToSelect_Bootstrap_Dropdown 
{	
	// VARIABLE AND CONSTRUCTOR DECLARATIONS
	public static final Logger logger = LogManager.getLogger(Generic_Method_ToSelect_Bootstrap_Dropdown.class);
	public static Actions action;
	public static final String closeTheCurrentListPopup_address = "(//div[contains(@class,'Cmt-header')])[1]";
	public static SoftAssert softAssert = new SoftAssert();
	
	public static boolean selectOptionFromDropdown(WebDriver driver, String listAddress, String value)
	{
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElements[2].getMethodName();
		logger.info("Enter inside method selectOptionFromDropdown and caller method's name: " + callerMethodName);
		
		
		action = new Actions(driver);
		boolean flag = false;
		logger.info("Value that user want to select from the dropdown list: "+value);
		List<WebElement> options = driver.findElements(By.xpath(listAddress));
	try 
	{
		
		for (WebElement element : options)
		{	//REMOVE THE COMMENT, TO CHECK LSIT OPTIONS
			//logger.info("List options: "+element.getText());
			if (element.getText().equalsIgnoreCase(value) || element.getText().trim().contains(value.trim()))
			{
				logger.info("Entered inside [if block], matched list value: " + element.getText());
				try
				{
					if (element.isEnabled())
					{
						if (!element.isSelected())
						{
							Thread.sleep(500);
							element.click();
							logger.info("Click on the element");
						}
						else
						{
							Thread.sleep(500);
							action.moveToElement(element).doubleClick().build().perform();
							Thread.sleep(500);
							logger.info("Element already selected");
						}
					}
				}
				catch (Exception e)
				{
					logger.info("Exception While selecting list from the dropdown: " + e.getMessage());
				
				}
				flag = true;
				break;
			}
			else if (element.getText().replaceAll("[\\(\\)_]", "").equalsIgnoreCase(value.replaceAll("[\\(\\)_]", "")) || element.getText().replaceAll("[\\(\\)_]", "").contains(value.replaceAll("[\\(\\)_]", "")))
			{
				logger.info("Entered inside [else if block], matched list value: " + element.getText());
				try
				{
					if (element.isEnabled())
					{
						if (!element.isSelected())
						{
							Thread.sleep(500);
							element.click();
							logger.info("Click on the element");
						}
						else
						{
							Thread.sleep(500);
							action.moveToElement(element).doubleClick().build().perform();
							Thread.sleep(500);
							logger.info("Element already selected");
						}
					}
				}
				catch (Exception e)
				{
					logger.info("Exception While selecting list from the dropdown: " + e.getMessage());
				}
				flag = true;
				break;
			}
			else if (callerMethodName.equalsIgnoreCase("selectMonthAndDate"))
			{
				logger.info("Entered inside [else if block] selectMonthAndDate");
				String[] text = element.getText().split("\\n");
				String format = "";
				for (String st : text)
				{
					format = format + st + " ";
				}
				String formatText = text[0] + " " + text[1] + " " + text[2];
				try
				{
					if (formatText.trim().equalsIgnoreCase(value.trim()) || formatText.trim().contains(value.trim()))
					{
						logger.info("Formatted (selectMonthAndDate) matched list value: " + formatText);
						if (element.isEnabled())
						{
							if (!element.isSelected() || element.isSelected())
							{
								Thread.sleep(500);
								element.click();
								Thread.sleep(500);
								logger.info("Click on the element");
							}
							else
							{
								logger.info("Element already selected");
							}
						}
						flag = true;
						break;
					}
				}
				catch (Exception e)
				{
					logger.info("Exception While selecting list from the dropdown: " + e.getMessage());
				}
			}
			else if (callerMethodName.equalsIgnoreCase("selectMembershipPackage"))
			{
				logger.info("Entered inside [else if block] selectMembershipPackage");
				String[] text = element.getText().split("\\(");
				String formatText = text[0].trim();
				logger.info("formatText: "+formatText);
				try
				{
					if (formatText.equalsIgnoreCase(value) || formatText.contains(value))
					{
						logger.info("Formatted (selectMembershipPackage) matched list value: " + formatText);
						if (element.isEnabled())
						{
							if (!element.isSelected() || element.isSelected())
							{
								Thread.sleep(500);
								element.click();
								Thread.sleep(500);
								logger.info("Click on the element");
							}
							else
							{
								Thread.sleep(500);
								action.moveToElement(element).doubleClick().build().perform();
								Thread.sleep(500);
								logger.info("Element already selected");
							}
						}
						flag = true;
						break;
					}
				}
				catch (Exception e)
				{
					logger.info("Exception While selecting list from the dropdown: " + e.getMessage());
				}
			}
			
		}
		if (!flag){
			logger.info("Given value not selected from the dropdown: " + value);
			
		}
		
	}catch(Exception e) {
		logger.info("Exceptino from : "+e.getMessage());
		softAssert.assertEquals(true,flag,"Given value not in the dropdown list : "+value);
	}
		
		return flag;
	}
}
