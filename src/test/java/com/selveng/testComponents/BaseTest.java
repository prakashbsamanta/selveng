package com.selveng.testComponents;

import com.selveng.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;
    public FileInputStream fis;

    public LoginPage loginPage;

    public BaseTest() {
        prop = new Properties();
        try {
            fis = new FileInputStream("src/main/java/com/selveng/resources/globalData.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public WebDriver initializeDriver() {
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driver = new FirefoxDriver(options);
        } else {
            System.out.println("Invalid Browser Name Specified");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        String appURL = prop.getProperty("appURL");

        loginPage.goTo(appURL);
        return loginPage;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
