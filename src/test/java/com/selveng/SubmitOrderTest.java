package com.selveng;

import com.selveng.pageObjects.LoginPage;
import com.selveng.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    public static void main(String[] args) {
        String productName = "ZARA COAT 3";

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized"
        );
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);

        loginPage.goTo("https://rahulshettyacademy.com/client");
        loginPage.loginApplication("modestlamport@justzeus.com", "V@VjcZz3xyGD2H%j");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);

        List<WebElement> productsList = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);

    }
}
