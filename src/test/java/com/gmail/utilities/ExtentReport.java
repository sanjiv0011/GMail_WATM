package com.gmail.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.gmail.testCases.BaseClass;



public class ExtentReport extends BaseClass implements ITestListener {
	 
	//CONTRUCTOR DECLARATIONS
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    
    public Logger logger = LogManager.getLogger(getClass());
  
	// screenshots path 
	public String screenshot_path;

    String repName;
   

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Tets-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/" + repName);
        sparkReporter.config().setDocumentTitle("GMail_WATM");
        sparkReporter.config().setReportName("GMail_WATM Testing Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Applcation name", "gmail.com");
        extentReports.setSystemInfo("System user name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Operating system name", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Reporter name", "Sanjiv");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS,MarkupHelper.createLabel("Test Passed:- "+result.getName(), ExtentColor.GREEN));
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS, "Test Passed");
       
    }
    
    public void onTestFailure(ITestResult result) {
    	//logger.info("onTestFailure Thread name: "+Thread.currentThread().getName());
    	
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL,MarkupHelper.createLabel("Test Failed:- "+result.getName(), ExtentColor.RED));
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
        extentTest.log(Status.FAIL, "Test Failed");
        
        // to capture screen
		try {
			if (BaseClass.driver != null) {
                captureScreen(driver, result.getName());
            } else {
                logger.error("WebDriver is null during screenshot capture.");
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		File f = new File(screenshot_path);
        
        
        if(f.exists()){
        	extentTest.fail("Screenshot is below: "+extentTest.addScreenCaptureFromPath(screenshot_path));
        }
        else{
        	logger.info("I am from Extent-Report onTestFailure and calling methods name is: "+result.getName());
        	extentTest.log(Status.FAIL, "Sreenshots path not found");
        }
      
	}
    
    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        //extentTest.log(Status.SKIP, "Test Skipped");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
        extentTest.log(Status.SKIP,MarkupHelper.createLabel("Test Skipped:- "+result.getName(), ExtentColor.PURPLE));
    }

    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }

	
	
	// to capture the screenshots
	public String captureScreen(WebDriver driver, String targetName) throws IOException
	{
		String repName;
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "_Screenshots" /*+ timeStamp*/ + ".png";
        screenshot_path = System.getProperty("user.dir")+"//Screenshots//"+targetName+repName;
        
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(screenshot_path);
		
		FileUtils.copyFile(source, target);
		System.out.println("Screenshots taken and return path is "+screenshot_path);
		return screenshot_path;
		
	}

}