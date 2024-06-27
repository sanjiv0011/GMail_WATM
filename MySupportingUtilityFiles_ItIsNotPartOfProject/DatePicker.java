package projectUtility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePicker{
	
	public static final Logger logger = LogManager.getLogger(DatePicker.class);
	
	
	//DATE PLACE HOLDER ADDRESS THIS IS USED CLEAR ALREADY PRESENT DATE
	public static String textDatePlaceHolder_1_RU = "(//input[@placeholder='MM/DD/YYYY'])[1]";
	public static String textDatePlaceHolder_2_RU = "(//input[@placeholder='MM/DD/YYYY'])[2]";
	public static String textDatePlaceHolder_3_RU = "(//input[@placeholder='MM/DD/YYYY'])[3]";
			
	//DATE ICON ADDRESS
    public static String iconDate_1_RU = "(//button[@aria-label='Choose date'])[1]";
	public static String iconDate_2_RU = "(//button[@aria-label='Choose date'])[2]";
	public static String iconDate_3_RU = "(//button[@aria-label='Choose date'])[3]";
	
	//DATE TOGGLE BUTTON, YEAR LIST, DATE LIST, NEXT BUTTON, PREVIOUS BUTTON, CURRENT MONTH YEAR DISPLAYED ADDRESS
	public static String toggleBtnYearAndDate = "//button[@aria-label=\"calendar view is open, switch to year view\"]";
	public static String selectYear = "//div[contains(@class, 'MuiYearCalendar-root')]//div";
	public static String arrowNextMonth = "//button[@title=\"Next month\"]";
	public static String arrowPreviousMonth = "//button[@title=\"Previous month\"]";
	public static String selectDate = "//div[@role='row']//button";
	public static String elementCurrentMonthYearDisplayed  = "(//div[contains(@class,'MuiPickersFadeTransitionGroup-root')])[1]";
	
	
	// WAY 3
	//======================= this is used when month and year are in the grid format==================//
			public static void DatePicker_GenericMethod_WithoutDropDown(WebDriver driver, String yourDate, int x) throws InterruptedException
			{	logger.info("Enter inside date picker methods");
					
			    // my date setting
		        String myDate[] = yourDate.split(" ");
		        String year = myDate[2];
		        String month = myDate[1];
		        String date = myDate[0];
		        logger.info("User given Date: "+date+" Month: "+month+" year: " + year);
		        
		        //========START=========TO SELECT DATE PICKER ICON 1,2,3=============//
		        if(x == 1) 
		        {
		        	WebElement datePlaceholder1 = driver.findElement(By.xpath(textDatePlaceHolder_1_RU));
		        	datePlaceholder1.sendKeys(Keys.CONTROL,"a");
		        	Thread.sleep(300);
		        	datePlaceholder1.sendKeys(Keys.DELETE);
		        	Thread.sleep(300);
		        	driver.findElement(By.xpath(iconDate_1_RU)).click();
		        	Thread.sleep(1000);
		        	logger.info("Clicked on the date picker icon: "+ x);
		        }else if(x == 2){
		        	WebElement datePlaceholder2 = driver.findElement(By.xpath(textDatePlaceHolder_2_RU));
		        	datePlaceholder2.sendKeys(Keys.CONTROL,"a");
		        	Thread.sleep(300);
		        	datePlaceholder2.sendKeys(Keys.DELETE);
		        	Thread.sleep(300);
		        	driver.findElement(By.xpath(iconDate_2_RU)).click();
		        	Thread.sleep(1000);
		        	logger.info("Clicked on the date picker icon: "+ x);
		        }else if(x == 3) {
		        	WebElement datePlaceholder3 = driver.findElement(By.xpath(textDatePlaceHolder_3_RU));
		        	datePlaceholder3.sendKeys(Keys.CONTROL,"a");
		        	Thread.sleep(300);
		        	datePlaceholder3.sendKeys(Keys.DELETE);
		        	Thread.sleep(300);
		        	driver.findElement(By.xpath(iconDate_3_RU)).click();
		        	Thread.sleep(1000);
		        	logger.info("Clicked on the date picker icon: "+ x);
		        }
		        //========END=========TO SELECT DATE PICKER ICON 1,2,3=============//
		        
		        
		        // to click on the toggle button for year and date selection
		        Thread.sleep(1000);
		        driver.findElement(By.xpath(toggleBtnYearAndDate)).click();
		        logger.info("Clicked on the toggle button for date and year selection");
		        Thread.sleep(1000);

		        //TO MATCH AND PICK THE YEAR
		        List<WebElement> yearGrid = driver.findElements(By.xpath(selectYear));
		        boolean flag = false;
		        for (WebElement yearElement : yearGrid) {
		            String yr = yearElement.getText();
		            if (yr.equals(year)) {
		                Thread.sleep(500);
		                logger.info("Selected year: " + yr);
		                yearElement.click();
		                Thread.sleep(500);
		                flag = true;
		                break;
		            }
		        }
		        if (flag != true) {
		        	logger.info("Year not selected");
		        }

		        //=========START======TO SELECT THE  MONTH=============//
		        // to match month find already present month "path_currentMonthYearDisplayed"
		        Thread.sleep(500);
		        String monthyear = driver.findElement(By.xpath(elementCurrentMonthYearDisplayed)).getText();
		        String arr[] = monthyear.split(" ");
		        String mon = arr[0];
		        logger.info("Displayed month and year: " + monthyear);
		        String monthArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

		        int displayedMonth = 0;
		        int inputMonth = 0;
		        for (int i = 1; i < monthArray.length; i++) {
		            if (mon.equalsIgnoreCase(monthArray[i])) {
		                displayedMonth = i;
		            }
		            if (month.equalsIgnoreCase(monthArray[i])) {
		                inputMonth = i;
		            }
		        }
		        while (displayedMonth > inputMonth) {
		            displayedMonth = displayedMonth - 1;
		            driver.findElement(By.xpath(arrowPreviousMonth)).click();
		            Thread.sleep(1000);
		        }
		        while (displayedMonth < inputMonth) {
		            displayedMonth = displayedMonth + 1;
		            driver.findElement(By.xpath(arrowNextMonth)).click();
		            Thread.sleep(1000);
		            
		        }
		        if (displayedMonth == inputMonth) {
		            logger.info("selected month name is: " + monthArray[inputMonth]);
		        } else {
		            logger.info("Month not selected");
		        }
		        //=========END======TO SELECT THE  MONTH=============//
		        
		        
		        //TO MATCH AND SELECT THE DATE
		        List<WebElement> allDates = driver.findElements(By.xpath(selectDate));
		        Thread.sleep(1000);
		        boolean flag2 = false;
		        for (WebElement dateElement : allDates) {
		            String dt = dateElement.getText();
		            if (dt.equals(date)) {
		                Thread.sleep(500);
		                flag2 = true;
		                logger.info("Selected date: " + dt);
		                dateElement.click();
		                Thread.sleep(1000);
		                break;
		            }
		        }
		        if (flag2 != true) {
		        	logger.info("Date not selected");
		        }
		    }

			
/*	
    // WAY 1
	//======================= this is used when month and year are in the grid format==================//
	public static void DatePicker_GenericMethod_WithoutDropDown(WebDriver driver, String path_YearDateToggelBtn, String selectMonth ,String iconNextMonth, String iconPreviousMonth, String path_dateGrid, String path_yearGrid, String yourDate ) throws InterruptedException
	{	logger.info("Enter inside date picker methods");
			
	    // my date setting
        String myDate[] = yourDate.split(" ");
        String year = myDate[2];
        String month = myDate[1];
        String date = myDate[0];
        logger.info("User given year: " + year+" Month: "+month+" Date: "+date);

        // to click on the toggle button for year and date selection
        Thread.sleep(1000);
        driver.findElement(By.xpath(path_YearDateToggelBtn)).click();
        logger.info("Clicked on the toggle button for date and year selection");
        Thread.sleep(1000);

        // to match and pick year
        List<WebElement> yearGrid = driver.findElements(By.xpath(path_yearGrid));
        boolean flag = false;
        for (WebElement yearElement : yearGrid) {
            String yr = yearElement.getText();
            if (yr.equals(year)) {
                Thread.sleep(500);
                logger.info("Selected year: " + yr);
                yearElement.click();
                Thread.sleep(500);
                flag = true;
                break;
            }
        }
        if (flag != true) {
        	logger.info("Year not selected");
        }

        // to match month find already present month "path_currentMonthYearDisplayed"
        Thread.sleep(500);
        String monthyear = driver.findElement(By.xpath(selectMonth)).getText();
        logger.info(monthyear);
        String arr[] = monthyear.split(" ");
        String mon = arr[0];
        logger.info("Displayed month name: " + mon);
        String monthArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int displayedMonth = 0;
        int inputMonth = 0;
        for (int i = 1; i < monthArray.length; i++) {
            if (mon.equalsIgnoreCase(monthArray[i])) {
                displayedMonth = i;
            }
            if (month.equalsIgnoreCase(monthArray[i])) {
                inputMonth = i;
            }
        }
        while (displayedMonth > inputMonth) {
            displayedMonth = displayedMonth - 1;
            driver.findElement(By.xpath(iconPreviousMonth)).click();
            Thread.sleep(1000);
        }
        while (displayedMonth < inputMonth) {
            displayedMonth = displayedMonth + 1;
            driver.findElement(By.xpath(iconNextMonth)).click();
            Thread.sleep(1000);
            
        }
        if (displayedMonth == inputMonth) {
            logger.info("Month is selected and name is: " + monthArray[inputMonth]);
        } else {
            logger.info("Month not selected");
        }

        // Date picker "path_date"
        List<WebElement> allDates = driver.findElements(By.xpath(path_dateGrid));
        Thread.sleep(1000);
        boolean flag2 = false;
        for (WebElement dateElement : allDates) {
            String dt = dateElement.getText();
            if (dt.equals(date)) {
                Thread.sleep(500);
                flag2 = true;
                logger.info("Selected date: " + dt);
                dateElement.click();
                Thread.sleep(1000);
                break;
            }
        }
        if (flag2 != true) {
        	logger.info("Date not selected");
        }
    }
	

*/	
	
	
/*
	//WAY 2
	//======================= this is used when there is no dropdown for the month and year ==================//
	public static void DatePicker_GenericMethod_WithoutDropDown(
		
	        WebElement path_YearDateToggleBtn,
	        WebElement selectMonth,
	        WebElement iconNextMonth,
	        WebElement iconPreviousMonth,
	        List<WebElement> path_dateGrid,
	        List<WebElement> path_yearGrid,
	        String yourDate) throws InterruptedException {
	    
	    // Split the given date
	    String myDate[] = yourDate.split(" ");
	    String year = myDate[2];
	    int int_year = Integer.parseInt(year);
	    String month = myDate[1];
	    String date = myDate[0];
	    String usergiven_month_year = month+" "+year;
	    logger.info("User given year: " + year + " Month: " + month + " Date: " + date);
	    
	    // to match month find already present month "path_currentMonthYearDisplayed"
        Thread.sleep(500);
        String monthyear = selectMonth.getText();
        //logger.info(monthyear);
        String arr[] = monthyear.split(" ");
        String mon = arr[0];
        String yr = arr[1];
        logger.info("Displayed month name: " + mon+" Displayed year: "+yr);
        int int_yr = Integer.parseInt(yr);
        String monthArray[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        
        //TO MATCH YEAR
        while(true)
        {	Thread.sleep(500);
        	String currentmonthyear = selectMonth.getText();
        	String log = ("Displayed month year: "+currentmonthyear+" User month year: "+usergiven_month_year);
 
        	
        	if(currentmonthyear.equals(usergiven_month_year)) {
        		log = ("Displayed month year: "+currentmonthyear+" User month year: "+usergiven_month_year+" Matched");
        		logger.info(log);
        		Thread.sleep(1000);
        		break;
        	}else {
        		if(int_year == int_yr)
        		{	logger.info("Year matched");
        			 int displayedMonth = 0;
        		        int inputMonth = 0;
        		        for (int i = 1; i < monthArray.length; i++) {
        		            if (mon.equalsIgnoreCase(monthArray[i])) {
        		                displayedMonth = i;
        		            }
        		            if (month.equalsIgnoreCase(monthArray[i])) {
        		                inputMonth = i;
        		            }
        		        }
        		        while (displayedMonth > inputMonth) {
        		            displayedMonth = displayedMonth - 1;
        		            iconPreviousMonth.click();
        		            Thread.sleep(1000);
        		        }
        		        while (displayedMonth < inputMonth) {
        		            displayedMonth = displayedMonth + 1;
        		            iconNextMonth.click();
        		            Thread.sleep(1000);
        		            
        		        }
        		        if (displayedMonth == inputMonth) {
        		            logger.info("Month is selected and name is: " + monthArray[inputMonth]);
        		        } else {
        		            logger.info("Month not selected");
        		        }
        		        
        		} else if(int_year > int_yr) 
	        		{
	        			iconNextMonth.click();
	                    Thread.sleep(500);
	        		} else if(int_year < int_yr)
		        		{
		        			iconPreviousMonth.click();
		                    Thread.sleep(500);
		        		}
        		
        	}
        }
	    
        
	    //TO MATCH DATE
	    for (WebElement dateElement : path_dateGrid) {
	        String dt = dateElement.getText();
	        if (dt.equals(date)) {
	            dateElement.click();
	            System.out.println("User date: "+dt+" Matched");
	            Thread.sleep(1000);
	            break;
	        }
	    }
	    
	}

	
*/

		
//		//WAY 4
//		//======================= used when drop down present for month and year==================//
//	
//	public static void DatePicker_GenericMethod_WithDropDown()
//	{
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver= new ChromeDriver();
//		
//		// path for date picker "path_DatePicker"
//		driver.findElement(By.xpath("")).click();
//		
//		// use it  if drop come under select tab"path_monthDropDown"
//		Select month_drp = new Select(driver.findElement(By.xpath("")));
//		month_drp.selectByVisibleText("July");
//		
//		// use it  if drop come under select tab"path_yearDropDown"
//		Select year_drp = new Select(driver.findElement(By.xpath("")));
//		year_drp.selectByVisibleText("2023");
//		
//		
//		
//	}
	
	
}
