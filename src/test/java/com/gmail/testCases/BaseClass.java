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
	public ReadConfigFiles rcf = new ReadConfigFiles();
	public String baseUrl = rcf.getApplicationUrl();

	// WHILE COMMENTING THIS TWO LINE ENSURES FIRST, THIS TWO PARAMETER PASS THROUGH
	// DATA PROVIDES METHODS FOR THAT WHERE IT USING PASS THERE @DATAPROVIDER NAME
	public String userEmail = rcf.getUserEmail();
	public String userPassword = rcf.getUserPassword();
	public String adminEmail = rcf.getAdminEmail();
	public String adminPassword = rcf.getAdminPassword();

	// FAKER LIBRARY TO GENERATE RADOM DATA FOR THE TEST
	public Faker faker = new Faker();

	// TO CONFIRM LOGIN AND LOGOUT ACTIVITY
	private boolean wantToByPassLoginLogout;

	@BeforeClass
	public void confirmLoginLogoutByPass(ITestContext context) {
		String param = context.getCurrentXmlTest().getParameter("wantToByPassLoginLogout");
		wantToByPassLoginLogout = Boolean.parseBoolean(param);
		logger.info("wantToByPassLoginLogout: " + wantToByPassLoginLogout);
	}

	// TO SELECT THE BROWSER AND DRIVER
	@Parameters("browser")
	@BeforeTest
	public void Setup(String br) throws InterruptedException {
		System.out.println("Current thread name: " + Thread.currentThread().getName());

		logger.info("Base class started...");

		if (br.equalsIgnoreCase("chrome")) {
			// USE THIS LINE IF YOU WANT USE DRIVER FROM THE DRIVER FOLDER
			System.setProperty("webdriver.chromedriver", rcf.getChromePath());

			// OTHER WISE USE BELOW LINE IT WILL TAKES DRIVER FROM THE POM.XML FILES
			// WebDriverManager.chromedriver().setup();

			// TO INITIALIZE CHROME DRIVER
			driver = new ChromeDriver(cco.customizedChromeOptions(true, false, false, true, 9222));

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
		driver.get(baseUrl);
		logger.info("Login page started");

		// TO MAXIMISE WINDOW
		driver.manage().window().maximize();
		logger.info("Maximize the window");
		// Thread.sleep(5000);

	}

	// TO LOGIN
	@Parameters("loginUserType")
	@BeforeClass(dependsOnMethods = "confirmLoginLogoutByPass")
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
		if (!wantToByPassLoginLogout) {
			if (loginUserType.equalsIgnoreCase("user")) {
				hp.UserLogout();
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