package com.gmail.projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import io.reactivex.rxjava3.functions.Action;

public class FindThreeDotBasedOnSearchKeyAndClick {
	
		public static final Logger logger = LogManager.getLogger(FindThreeDotBasedOnSearchKeyAndClick.class);
		public static SoftAssert softAssert = new SoftAssert();
		public static Actions action = null;
		public static JavascriptExecutor jsExecutor;
		
		public static int findThreedActionButtonAndClick(List<WebElement> listName, WebDriver driver, String searchKey,int searchKeyColumnIndex, boolean wantToClickOnThreeDot,boolean wantToclickOnFindSearckKey) throws InterruptedException 
		{
			
			action = new Actions(driver);
			jsExecutor  = (JavascriptExecutor)driver;
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
	    	String callerMethodName = stackTraceElements[2].getMethodName();
			logger.info("findThreedActionButtonAndClick method called and Caller method name: "+callerMethodName);
			logger.info("wantToClickOnThreeDot: "+wantToClickOnThreeDot);
			logger.info("wantToclickOnFindSearckKey: "+wantToclickOnFindSearckKey);
			
			//POINTER ADDRESSES
			String MemberCreditpointer_address = "((//div[contains(@class,'grid grid-cols-1 gap-2')])[3]//div[contains(@class,'flex-row')]//*[@class='pointer'])";
			String PaymentHistorypointer_address = "((//div[contains(@class,'grid grid-cols-1 gap-2')])[2]//div[contains(@class,'flex-row')]//*[@class='pointer'])";
			String locatinListPointer_address  = "((//div[contains(@class,'w-full flex flex-col')])[2]//div[contains(@class,'p-2 flex gap-2 flex-row')]//*[@class='pointer'])";
			String regionListPointer_address = "((//div[contains(@class,'w-full flex flex-col')])[3]//div[contains(@class,'p-2 flex gap-2 flex-row')]//*[@class='pointer'])";
			
		
		
			if(callerMethodName.equalsIgnoreCase("findMyRegisteredClassAndonThreeDotOption")) {
				String searchKeyFormat[] = searchKey.split(" ");
				String newSearchKey = null;
				if (Integer.parseInt(searchKeyFormat[1].replace(",", "")) < 10) {
				    searchKeyFormat[1] = String.valueOf(Math.abs(Integer.parseInt(searchKeyFormat[1].replace(",", ""))));
				    
					StringBuilder builder = new StringBuilder();
			        for (int i = 0; i < searchKeyFormat.length; i++) {
			            builder.append(searchKeyFormat[i]);
			            if (i == 1) {
			                builder.append(",");
			            }
			            if (i < searchKeyFormat.length - 1) {
			                builder.append(" ");
			            }
			        }
			        newSearchKey = builder.toString();
			        searchKey = newSearchKey;
			       // logger.info("New searchKey: "+searchKey);
				}
			}
		
		
			Thread.sleep(500);
			boolean flag = false;
			boolean confirmationGiverValue = false;
			int listRowCount = 0;
				for(WebElement element : listName)
				{	
					listRowCount++;
					String[] text = element.getText().split("\\n");
					String firstColumnText = text[0];
					String formatText = "";
					int columnIndexCount = 0;
					for(String st : text)
					{	columnIndexCount++;
						
						if(columnIndexCount == searchKeyColumnIndex) 
						{
							formatText = st.trim();
							
							logger.info("formatText: "+formatText);
			    			
							if(formatText.equalsIgnoreCase(searchKey))
							{
								String btnActionAddress = null;
								if(callerMethodName.equals("findMemberCreditAndClickOnThreeDotOption")){
									btnActionAddress = MemberCreditpointer_address+"["+listRowCount+"]";
								}else if(callerMethodName.equals("findHistoryAndClickOnThreeDotOption")) {
									btnActionAddress = PaymentHistorypointer_address+"["+listRowCount+"]";
								}else if(callerMethodName.equals("findLocationFromRowListAndClickOnThreeDot")) {
									btnActionAddress = locatinListPointer_address+"["+listRowCount+"]";
								}else if(callerMethodName.equals("findRegionFromRowListAndClickOnThreeDot")) {
									btnActionAddress = regionListPointer_address+"["+listRowCount+"]";
								}else {
									btnActionAddress = "(//div[@class='pointer'])["+listRowCount+"]";
								}
								
								logger.info("Three dot actoin button address: "+btnActionAddress);
								flag = true;
								Thread.sleep(300);
								try {
									WebElement btnThreeDot = element.findElement(By.xpath(btnActionAddress));
									if(btnThreeDot.isDisplayed()) {
										//logger.info("Given DateAndTime : "+searchKey);
										logger.info("Given value matched with the list value: "+formatText);
										confirmationGiverValue =  true;
										if(wantToClickOnThreeDot) {
											btnThreeDot.click();
											logger.info("Clicked on the three dot option button");
										}else if(wantToclickOnFindSearckKey) {
											logger.info("Text present at given column index: "+formatText);
											String yourUniqueValue = null;
											if(callerMethodName.equals("findPackageBuyersUserFromList")) {
												yourUniqueValue = firstColumnText.toLowerCase().trim(); //formatText;
											}else {
												yourUniqueValue = formatText;
											}
											WebElement elementFindKey = driver.findElement(By.xpath("(//div[.='"+yourUniqueValue+"'])[2]"));
											logger.info("elementFindKey_address: "+elementFindKey);
											action.moveToElement(elementFindKey).build().perform();
											logger.info("Mouse moved to the find searck key element: "+elementFindKey.getText());//div[.='susan5 davis5']
											Thread.sleep(500);
											//jsExecutor.executeScript("arguments[0].click();", elementFindKey);
											//elementFindKey.click();
											action.moveToElement(elementFindKey).click().build().perform();
											logger.info("Clicked on the findSearchKey: "+elementFindKey.getText());
											Thread.sleep(2000);
											
										}else {
											logger.info("Text present at given column index: "+formatText);
										}
										Thread.sleep(200);
									}
								}catch(Exception e) {
									logger.info("Exception from FindThreeDotBasedOnSearchKeyAndClick: "+e.getMessage());
									softAssert.assertTrue(false,"Three dot Action button not present");
									return -1;
								}
			        			break;
			        		}
						}
					}
					if(flag) {
						break;
					}
				}
				if(!confirmationGiverValue) {
					logger.info("Given confirmationGiverValue not matched: "+searchKey);
					softAssert.assertTrue(false,"Given confirmationGiverValue not matched");
				}
				
			Thread.sleep(200);
			return listRowCount;
	}
		
	public void clickOnUniqueSearchKey() {
		
	}
	
}
