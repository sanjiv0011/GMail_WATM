package com.gmail.testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.gmail.pageObject.PO_HomePage;
import com.gmail.pageObject.PO_LoginPage;
import com.gmail.utilities.CustomizedChromeOptions;
import com.gmail.utilities.ReadConfigFiles;

public class BaseClass {

	public static WebDriver driver;
	public PO_HomePage hp;
	public PO_LoginPage lp;
	public SoftAssert softAssert = new SoftAssert();
	public CustomizedChromeOptions cco = new CustomizedChromeOptions();

	// TO LOG THE MESSAGES ON THE CONSOLE AND LOG FILES BOTH
	public Logger logger = LogManager.getLogger(this.getClass());

	// TO READ THE FILE FROM THE utilities.ReadConfigFiles
	private ReadConfigFiles rcf = new ReadConfigFiles();
	private String baseUrl = rcf.getApplicationUrl();
	private String baseUrlDebuggerMode = rcf.getApplicationUrlDebuggerMode();
	

	// WHILE COMMENTING THIS TWO LINE ENSURES FIRST, THIS TWO PARAMETER PASS THROUGH
	// DATA PROVIDES METHODS FOR THAT WHERE IT USING PASS THERE @DATAPROVIDER NAME
	private String userEmail = rcf.getUserEmail();
	private String userPassword = rcf.getUserPassword();

	// FAKER LIBRARY TO GENERATE RADOM DATA FOR THE TEST
	public Faker faker = new Faker();

	// DATA FOR BROWSER SETTINGS FROM TEXT.XML FILES
	private boolean wantToByPassLoginLogout;
	private boolean wantToEnableDebuggerMode;
	private int debuggerPort;
	private boolean incognitoMode;
	private boolean headerLessBrowsing;
	private boolean wantToBlockAdsAndNotifications;

	@BeforeTest
	public void browserSettingAndLogin(ITestContext context) {
		logger.info("Browser and Login-Logout Settings");
		// TO ENABLE/DISABLE DEBUGGGER MODE
		String paramEnableDisableDebuggerMode = context.getCurrentXmlTest().getParameter("enableDisableDebuggerMode");
		String[] debuggerModeAndPort = paramEnableDisableDebuggerMode.split(",");
		wantToEnableDebuggerMode = Boolean.parseBoolean(debuggerModeAndPort[0].trim());
		debuggerPort = Integer.parseInt(debuggerModeAndPort[1].trim());
		logger.info("wantToEnableDebuggerMode: " + wantToEnableDebuggerMode + " And debugger post: " + debuggerPort);

		// FOR LOGIN BYPASS
		String paramLoginByPass = context.getCurrentXmlTest().getParameter("wantToByPassLoginLogout").trim();
		wantToByPassLoginLogout = Boolean.parseBoolean(paramLoginByPass);
		logger.info("wantToByPassLoginLogout: " + wantToByPassLoginLogout);

		// FOR INCOGNITO MODE
		String paramIncogenitoModde = context.getCurrentXmlTest().getParameter("incognitoMode").trim();
		incognitoMode = Boolean.parseBoolean(paramIncogenitoModde);
		logger.info("incognitoMode: " + paramIncogenitoModde);

		// FOR HEADERLESS BROWSING
		String paramHeaderLessBrowsing = context.getCurrentXmlTest().getParameter("headerLessBrowsing").trim();
		headerLessBrowsing = Boolean.parseBoolean(paramHeaderLessBrowsing);
		logger.info("headerLessBrowsing: " + headerLessBrowsing);

		// TO DISABLE ADS AND NOTIFICATIONS
		String paramWantToBlockAdsAndNotifications = context.getCurrentXmlTest()
				.getParameter("wantToBlockAdsAndNotifications").trim();
		incognitoMode = Boolean.parseBoolean(paramWantToBlockAdsAndNotifications);
		logger.info("wantToBlockAdsAndNotifications: " + wantToBlockAdsAndNotifications);

	}

	// TO SELECT THE BROWSER AND DRIVER
	@Parameters("browser")
	@BeforeTest(dependsOnMethods = "browserSettingAndLogin")
	public void Setup(String br) throws InterruptedException {
		System.out.println("Current thread name: " + Thread.currentThread().getName());

		logger.info("Base class started...");

		if (br.equalsIgnoreCase("chrome")) {
			// USE THIS LINE IF YOU WANT USE DRIVER FROM THE DRIVER FOLDER
			System.setProperty("webdriver.chromedriver", rcf.getChromePath());

			// OTHER WISE USE BELOW LINE IT WILL TAKES DRIVER FROM THE POM.XML FILES
			// WebDriverManager.chromedriver().setup();

			// TO INITIALIZE CHROME DRIVER
			driver = new ChromeDriver(
					cco.customizedChromeOptions(wantToBlockAdsAndNotifications, headerLessBrowsing, incognitoMode, wantToEnableDebuggerMode, debuggerPort));

			logger.info("Chrome driver selected");
		} else if (br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geckodriver", rcf.getFireFoxPath());
			driver = new FirefoxDriver();
			logger.info("Fire fox driver selected");
		} else if (br.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.msedgedriver", rcf.getMsEdgePath());
			driver = new EdgeDriver();
			logger.info("Edge driver selected");
		}

		// TO START BASE URL
		if(wantToEnableDebuggerMode) {
			driver.get(baseUrlDebuggerMode);
			logger.info("Login page started with Debugger mode");
		}else {
			driver.get(baseUrl);
			logger.info("Login page started");
		}
		

		// TO MAXIMISE WINDOW
		driver.manage().window().maximize();
		logger.info("Maximize the window");
		// Thread.sleep(5000);

	}

	// TO LOGIN
	@Parameters("loginUserType")
	@BeforeClass()
	public void Login(String loginUserType) throws InterruptedException {
		logger.info("loginUserType: " + loginUserType);
		if (!wantToByPassLoginLogout) {
			if (loginUserType.equalsIgnoreCase("user")) {
				lp = new PO_LoginPage(driver);
				logger.info("Login user Email: " + userEmail + " and Password: " + userPassword);
				hp = lp.Login(userEmail, userPassword);
			}
		}
	}

	// TO LOGOUT
	@Parameters("loginUserType")
	@AfterTest()
	public void Logout(String loginUserType) throws InterruptedException {
		if (wantToByPassLoginLogout) {
			if (loginUserType.equalsIgnoreCase("user")) {
				try {
					hp.UserLogout();
				}catch(Exception e) {
					logger.warn("Exception From: Logout >> "+e.getMessage());
				}
			}
		}
	}

	// TO CLOSE THE DIRVER
	@AfterTest(dependsOnMethods = "Logout")
	public void Teardown() {
		driver.quit();
		logger.info("Driver shutdown");
	}

	// TO GENERATES RANDOM STRING HAVING LENGTH 6 CHARACTER
	public static String randomString(int intLength) {
		String generatedstring = RandomStringUtils.randomAlphabetic(intLength);
		return generatedstring;
	}

	// TO GENERATES RANDOM STRING NUMBER WITH MIN AND MAX AS PER USER DATA
	public static String randomStringNumber(int min, int max) {
		String rdmStringNumber = RandomStringUtils.randomNumeric(min, max);
		return rdmStringNumber;
	}

	public static void main(String[] args) {
		System.out.println(randomStringNumber(5, 6));
	}
}