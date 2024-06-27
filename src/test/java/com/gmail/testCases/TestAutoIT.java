package com.gmail.testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gmail.utilities.ReadConfigFiles;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAutoIT {

    public static void main(String[] args) throws IOException, InterruptedException {

        // TO READ THE FILE FROM THE utilities.ReadConfigFiles
        ReadConfigFiles rcf = new ReadConfigFiles();

        // Use WebDriverManager to set up GeckoDriver (Firefox)
        System.setProperty("webdriver.edgedriver", rcf.getMsEdgePath());
        //WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();

        // Navigate to the file upload test page
        driver.get("https://ps.uci.edu/~franklin/doc/file_upload.html");

        // Locate the file input element and trigger the file upload dialog
        WebElement button = driver.findElement(By.xpath("//input[@name='userfile']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", button);

        Thread.sleep(1000);

        // Construct the path to the AutoIt executable
        String autoItExecutablePath = System.getProperty("user.dir") + "\\autoIt.exe";

        // Run the AutoIt script
        try {
            Runtime.getRuntime().exec(autoItExecutablePath);
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Quit the driver
        driver.quit();
    }
}
