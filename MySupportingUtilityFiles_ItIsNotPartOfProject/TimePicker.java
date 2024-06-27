package projectUtility;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TimePicker {
	
	public TimePicker()
	{
		super();
	}
	//VARIABLE AND CONSTRUCTER DECLARATIONS
	public static final Logger logger = LogManager.getLogger(TimePicker.class);
	public static WebDriverWait wait = null;
	static Actions action;
	
	//TIME PLACE HOLDER ADDRESS THIS IS USED CLEAR ALREADY PRESENT TIME
	public static String textTimePlaceHolder_1_RU = "(//input[@placeholder='hh:mm aa'])[1]";
	public static String textTimePlaceHolder_2_RU = "(//input[@placeholder='hh:mm aa'])[2]";
			
	//TIME ICON ADDRESS
    public static String iconTime_1_RU = "(//button[@aria-label='Choose time'])[1]";
	public static String iconTime_2_RU = "(//button[@aria-label='Choose time'])[2]";

	
	//TIME BUTTON(OK, NEXT, PREVIOUS, AM, PM) HOURS LIST AND MINUTES LIST ADDRESS
	public static String btnOkTimePicker = "//button[normalize-space()='OK']";
	public static String selectHoursFromList = "//div[@role='listbox']//span";
	public static String selectMinutesFromList = "//div[contains(@role,'listbox')]//span";
	public static String btnArrowNext = "(//button[@title='open next view'])[1]";
	public static String btnArrowPrevious = "(//button[@title='open previous view'])[1]";
	public static String btnAM  = "(//span[contains(@class,'MuiTouchRipple-root')])[11]";
	public static String btnPM  = "(//span[contains(@class,'MuiTouchRipple-root')])[12]";
	
	//REQUIRED MESSAGES ADDRESS
	public String endTimeRequired = "//p[contains(.,'End time is required')]";
	public String startTimeRequired = "//p[contains(.,'Start time is required')]";
		
	
	//============START======= ACTION METHODS TO SELECT THE TIME FROM==========WAY-2=========//
	//IT BYPASS THE WAY-1 PROBLEMS
    public static void selectTime(WebDriver driver, String ValueHours, String ValueMinutes, String AMPM, int x ) throws InterruptedException
    {  	
    	action = new Actions(driver);
    	wait = new WebDriverWait (driver, Duration.ofSeconds(10));
    	logger.info("User given hours: "+ValueHours+", minutes: "+ValueMinutes+", AP-PM: " + AMPM+", and selected time picker: "+ x);
    	boolean flag = false;
    	
    	//TO SELECT TIME ICONS FIRST/SECONDS
    	if(x == 1)
    	{	
    		try {
    			//TO CLICK ON THE START TIME ICON
        		WebElement timeIconStart = driver.findElement(By.xpath(iconTime_1_RU));
        		if(timeIconStart.isEnabled() && timeIconStart.isDisplayed()) {
        			wait.until(ExpectedConditions.elementToBeClickable(timeIconStart));
            		action.moveToElement(timeIconStart).click().build().perform();
            		flag = true;
            		Thread.sleep(500);
            		logger.info("Clicked on start time icon button");
        		}else {
        			logger.info("Start time selector icon not interactable");
        		}
    		}catch(Exception e) {
    			logger.info(e.getCause());
    		}
    	}
    	else if(x == 2 ) 
    	{	
        	 try {
    			//TO CLICK ON THE END TIME ICON
        		WebElement timeIconEnd = driver.findElement(By.xpath(iconTime_2_RU));
        		if(timeIconEnd.isEnabled() && timeIconEnd.isDisplayed()) {
        			wait.until(ExpectedConditions.elementToBeClickable(timeIconEnd));
            		action.moveToElement(timeIconEnd).click().build().perform();
            		flag = true;
            		Thread.sleep(500);
            		logger.info("Clicked on end time icon button");
        		}else {
        			logger.info("End time selector icon not interactable");
        		}
    		}catch(Exception e) {
    			logger.info(e.getCause());
    		}
    		
    	}else {
    		logger.info("Invalid time int value it should be 1 or 2");
    	}
  
    	
    	//CHECK TIME SELECTOR MUST BE OPENED
    	if(flag)
    	{
    		//TO SELECT AM AND PM
        	if (AMPM.equals("AM"))
        	{
            	WebElement elementBtnAM = driver.findElement(By.xpath(btnAM));
            	//wait.until(ExpectedConditions.elementToBeClickable(elementBtnAM));
        		if(elementBtnAM.isSelected()) {
        			logger.info("Already AM button is selected: "+elementBtnAM.isSelected());
        		}else {
        			 logger.info("Already AM button is selected: "+elementBtnAM.isSelected());
        			 action.moveToElement(elementBtnAM).doubleClick().build().perform();
        			 logger.info("Clicked on the AM button");
        		}
            }
        	else if (AMPM.equals("PM"))
            {
    	        	WebElement elementBtnPM = driver.findElement(By.xpath(btnPM));
    	    		//wait.until(ExpectedConditions.elementToBeClickable(elementBtnPM));
    	    		if(elementBtnPM.isSelected()) {
    	    			logger.info("Already PM button is selected: "+elementBtnPM.isSelected());
    	    		}else {
    	    			 logger.info("Already PM button selected: "+elementBtnPM.isSelected());
    	    			 action.moveToElement(elementBtnPM).doubleClick().build().perform();
    	    			 logger.info("Clicked on the PM button");
    	    		}
            } 
        	else 
        	{
                logger.info("Invalid AMPM format");
            }
    	}
    	
    	
    	
    	//TO SELECT HOURS
    	List <WebElement> pathHours = driver.findElements(By.xpath(selectHoursFromList));
    	logger.info("Start selecting hours");
    	TimePicker.selectTimeHoursOrMinutes(pathHours,ValueHours);
    	Thread.sleep(1000);
    	
    	//TO SELECT MINUTES
    	List <WebElement> pathMinutes= driver.findElements(By.xpath(selectMinutesFromList));
    	logger.info("Start selecting minutes");
    	TimePicker.selectTimeHoursOrMinutes(pathMinutes,ValueMinutes);
    	Thread.sleep(1000);
    }

	//SELECT HOURS AND MINUTES FROM THE LIST
    public static void selectTimeHoursOrMinutes(List<WebElement> options, String value) throws InterruptedException
    {	logger.info("Entered inside methods selectTimeHoursOrMinutes");
    	String vr = null;
    	boolean flag = false;
    	
    	try {
			for(WebElement element : options) {	
    			//logger.info(element.getText());
        		if(element.getText().equals(value)) {
        			//wait.until(ExpectedConditions.elementToBeClickable(element));
        			Thread.sleep(500);
        			vr = element.getText();
        			action.moveToElement(element).click().build().perform();
        			Thread.sleep(500);
        			flag = true;
        			break;
        		}
			}
    	}
    	catch(Exception e) {
    			logger.info("Message from the selectTimeHoursOrMinutes methods: "+e.getCause());
    		}
    
    	if(!flag) {
    		logger.info("Option is not selected: "+vr);
    	}else {
    		logger.info("Option is selected: "+vr);
    	}
    	
    }
  //============END======= ACTION METHODS TO SELECT THE TIME FROM============WAY-2=======//
	
	
	
//	//============START======= ACTION METHODS TO SELECT THE TIME FROM==========WAY-1=========//
//	//WAY 1 BUT PROBLEM IS ELEMENT STALE REFERENCE EXCEPTION GETTING BY THIS WAY
//    public static void selectTime(WebDriver driver, String ValueHours, String ValueMinutes, String AMPM, WebElement path_AM, WebElement path_PM, List <WebElement> path_hours, List <WebElement> path_minutes ) throws InterruptedException
//    {  	
//    	action = new Actions(driver);
//    	wait = new WebDriverWait (driver, Duration.ofSeconds(10));
//    	 
//    	if (AMPM.equals("AM")) {
//    		wait.until(ExpectedConditions.elementToBeClickable(path_AM));
//    		Thread.sleep(300);
//            action.moveToElement(path_AM).doubleClick().build().perform();
//           
//            logger.info("Clicked on the AM button: " + path_AM.getText());
//        } else if (AMPM.equals("PM")) {
//        	wait.until(ExpectedConditions.elementToBeClickable(path_PM));
//        	Thread.sleep(300);
//            action.moveToElement(path_PM).doubleClick().build().perform();
//            
//            logger.info("Clicked on the PM button: " + path_PM.getText());
//        } else {
//            logger.info("Invalid AMPM format");
//        }
//  
//    	//TO SELECT HOURS
//    	TimePicker.selectTimeHoursOrMinutes(path_hours,ValueHours);
//    	Thread.sleep(2000);
//    	
//    	//TO SELECT MINUTES
//    	TimePicker.selectTimeHoursOrMinutes(path_minutes,ValueMinutes);
//    	Thread.sleep(2000);
//    	
//    }
//
//	//SELECT HOURS AND MINUTES FROM THE LIST
//    public static void selectTimeHoursOrMinutes(List<WebElement> options, String value) throws InterruptedException
//    {	logger.info("Entered inside methods selectTimeHoursOrMinutes");
//    	String vr = null;
//    	boolean flag = false;
//    	
//    	try {
//			for(WebElement element : options) {	
//    			//logger.info(element.getText());
//        		if(element.getText().equals(value)) {
//        			wait.until(ExpectedConditions.elementToBeClickable(element));
//        			Thread.sleep(3000);
//        			vr = element.getText();
//        			action.moveToElement(element).click().build().perform();
//        			Thread.sleep(1000);
//        			flag = true;
//        			break;
//        		}
//			}
//    	}
//    	catch(Exception e) {
//    			logger.info("Message from the selectTimeHoursOrMinutes methods: "+e.getCause());
//    		}
//    
//    	if(!flag) {
//    		logger.info("Option is not selected: "+vr);
//    	}else {
//    		logger.info("Option is selected: "+vr);
//    	}
//    	
//    }
//  //============END======= ACTION METHODS TO SELECT THE TIME FROM============WAY-1=======//
    
}
