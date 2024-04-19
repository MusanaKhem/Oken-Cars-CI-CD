package org.okenproject.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        // Configuration du pilote Selenium (ChromeDriver dans cet exemple)
        System.setProperty("webdriver.chrome.driver", "src/test/WebDrivers/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }



}
