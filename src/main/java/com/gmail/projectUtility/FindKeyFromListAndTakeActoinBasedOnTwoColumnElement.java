package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FindKeyFromListAndTakeActoinBasedOnTwoColumnElement {

	
		public static Logger logger = LogManager.getLogger(FindKeyFromListAndTakeActoinBasedOnTwoColumnElement.class);
		public static Actions action; 
		
		public static boolean findAndCopyLinks(WebDriver driver, List<WebElement> list, String list_Address, String searchKey,int intSearchKeyColumnIndex, int actionFirstColumnIndex, int actionSecondColumnIndext,boolean wantToClickActionFirstColumnIndext, boolean wantToClickActionSecondColumnIndex,String actionFirstBaseAddress, String actionSecondBaseAddress ) throws InterruptedException {
			action = new Actions(driver);
			int rowListCouont = 0;
			String actionFirstBase_Address  = actionFirstBaseAddress;
			String actionSecondBase_Address = actionSecondBaseAddress;
			
			boolean flag1 = false;
			boolean flag2 = false;
			boolean isSearchKeyColumnIndexMatched = false;
			boolean isClickedOnFirstOrSecondActionColumn = false;
			
			logger.info("wantToClickActionFirstColumnIndext: " + wantToClickActionFirstColumnIndext+" | And column index number: "+actionFirstColumnIndex);
			logger.info("wantToClickActionSecondColumnIndex: " + wantToClickActionSecondColumnIndex+" | And column index number: "+actionSecondColumnIndext);

			try {
				for (WebElement linkText : list) {
					
					
					//logger.info(linkText.getText());
					
					rowListCouont++;
					String linkDataFromEachLink[] = linkText.getText().split("\\n");
					//logger.info("Total column count in the list: "+linkDataFromEachLink.length);
					
					int columnIndexCount = 0;
					
					for (String eachRowListContentText : linkDataFromEachLink) {
						columnIndexCount++;
						
						if (columnIndexCount == intSearchKeyColumnIndex) 
						{
							isSearchKeyColumnIndexMatched = true;
							
							if (eachRowListContentText.equals(searchKey)) 
							{
								logger.info("Search key matched in the list");
								logger.info("Total tabContent in the matched list: "+linkDataFromEachLink.length);
								
								for(int columnValueCounterInList = 0; columnValueCounterInList <= linkDataFromEachLink.length ;columnValueCounterInList++)
								{
									if(columnValueCounterInList == actionFirstColumnIndex) 
									{ 
										
										logger.info("actionFirstColumnIndex int value matched: "+columnValueCounterInList);
										
										if (wantToClickActionFirstColumnIndext) {
											actionFirstBase_Address = "(" + list_Address + ")[" + rowListCouont + "]"
													+ actionFirstBaseAddress;
											logger.info("actionFirstBase_Address: " + actionFirstBase_Address);
											WebElement actionFirstElement = driver.findElement(By.xpath(actionFirstBase_Address));
											action.moveToElement(actionFirstElement).build().perform();
											Thread.sleep(1000);
											logger.info("actionFirstElement text: "+actionFirstElement.getText());
											action.moveToElement(actionFirstElement).click().build().perform();
											flag1 = true;
											logger.info("Clicked on actionFirstBase_Address");
										}else {
											actionFirstBase_Address = "(" + list_Address + ")[" + rowListCouont + "]"
													+ actionFirstBaseAddress;
											logger.info("actionFirstBase_Address: " + actionFirstBase_Address);
											WebElement actionFirstElement = driver.findElement(By.xpath(actionFirstBase_Address));
											action.moveToElement(actionFirstElement).build().perform();
											logger.info("actionFirstElement text: "+actionFirstElement.getText());
											Thread.sleep(1000);
										}
									}
									
									if(columnValueCounterInList == actionSecondColumnIndext) 
									{
										logger.info("actionSecondColumnIndex int value matched: "+columnValueCounterInList);
										
										if (wantToClickActionSecondColumnIndex) {
											actionSecondBase_Address = "(" + list_Address + ")[" + rowListCouont
													+ "]" + actionSecondBaseAddress;
											logger.info("actionSecondBase_Address: " + actionSecondBase_Address);
											WebElement actionSecondElement = driver
													.findElement(By.xpath(actionSecondBase_Address));
											action.moveToElement(actionSecondElement).build().perform();
											Thread.sleep(1000);
											logger.info("actionSecondElement text: "+actionSecondElement.getText());
											action.moveToElement(actionSecondElement).click().build().perform();
											flag2 = true;
											logger.info("Clicked on actionSecondBase_Address");
										}else {
											actionSecondBase_Address = "(" + list_Address + ")[" + rowListCouont
													+ "]" + actionSecondBaseAddress;
											logger.info("actionSecondBase_Address: " + actionSecondBase_Address);
											WebElement actionSecondElement = driver
													.findElement(By.xpath(actionSecondBase_Address));
											action.moveToElement(actionSecondElement).build().perform();
											logger.info("actionSecondElement text: "+actionSecondElement.getText());
											Thread.sleep(1000);
										}
									}
								}

								

								if (flag1 == true || flag2 == true) {
									isClickedOnFirstOrSecondActionColumn = true;
									break;
								}
							} 
								
							
						}
					}
					if (isClickedOnFirstOrSecondActionColumn) {
						break;
					}

				}
				
				if(isSearchKeyColumnIndexMatched == false) {
					logger.info("ColumnIndexCount and user given SearchKeyColumnIndex not matched");
				}
				if(flag1 == false && flag2 == false) {
					logger.info("SearchKeyColumnIndex content and SearchKey content not matached");
				}

			} catch (Exception e) {
				
				logger.info("Exception from : FindKeyFromListAndTakeActoinBasedOnTwoColumnElement"+e.getMessage());
			}
			return isClickedOnFirstOrSecondActionColumn;
		}
}
