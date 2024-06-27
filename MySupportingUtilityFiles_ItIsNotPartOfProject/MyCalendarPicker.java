package projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCalendarPicker {
	
	//VARIABLE AND CONSTRUCTER DECLARATIONS
	public static final Logger logger = LogManager.getLogger(TimePicker.class);
	static Actions action;
	
	 public static void moveToCorrectDateInCalendarAndClickOnContentMonthView(WebDriver driver, String dateValue,String dateContent, List<WebElement> path_DateList, List<WebElement> path_DateListContent,WebElement path_showMoreBtn, List<WebElement> path_listShowMoreContent) throws InterruptedException 
	    {		
		 		action = new Actions(driver);
		 		logger.info("Entered inside methods moveToCorrectDateInCalendarAndClickOnContent");
	    
		    	for(WebElement date : path_DateList )
		    	{	//logger.info("Entered inside first for loop with path_DateList Date: "+date.getText());
		    		boolean flag = false;
		    		if(date.getText().equals(dateValue))
		    		{	
		    			logger.info("Entered inside first if blcok and date value from page is: "+date.getText());
		    			action.moveToElement(date).build().perform();
		    			logger.info("Curser moved to the given date: "+dateValue);
		    	    	Thread.sleep(1000);
		    	    	
		    			for(WebElement elementDateContent : path_DateListContent)
		    			{	logger.info("Entered inside second for loop with path_DateListContent: "+elementDateContent.getText());
		    				
		    				try {
			    					if(elementDateContent.getText().equals(dateContent))
			        				{	
			    						logger.info("Entered inside second if blcok and content value from page is: "+elementDateContent.getText());
			        					Thread.sleep(1000);
			    						action.moveToElement(elementDateContent).click().build().perform();
			        					logger.info("Clicked on the given date content: "+dateContent);
			        			    	Thread.sleep(3000);
			        			    	driver.navigate().refresh();
			        			    	Thread.sleep(4000);
			        			    	flag = true;
			        			    	break; // TO EXIT SECONDS FOR LOOP
			        				}
		    					
		    					}catch(Exception e)
			    					{
			    						logger.info("Message from my calendar date content: "+e.getMessage());
			    					}
		    			}
		    			
		    			//TO EXIT THE FIRTS FOR LOOP
		    			if(flag) {
		    				break;
		    			}
		    		}
		    		
		    	}
	    	
		 	}
	   
}
	 
