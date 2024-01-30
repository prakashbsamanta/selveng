package com.selveng;

import com.selveng.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

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
        ProductCatalogue productCatalogue = loginPage.loginApplication("modestlamport@justzeus.com", "V@VjcZz3xyGD2H%j");

        List<WebElement> productsList = productCatalogue.getProductsList();
        NavigationHeader navigationHeader = productCatalogue.addProductToCart(productName);

        CartPage cartPage = navigationHeader.goToCartPage();

        Boolean productMatched = cartPage.productDisplayed(productName);
        Assert.assertTrue(productMatched);
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();

        checkoutPage.selectCountry("India");
        OrderConfirmation orderConfirmation = checkoutPage.placeOrder();

        String orderConfirmationText = orderConfirmation.getOrderConfirmText();
        Assert.assertEquals(orderConfirmationText, "THANKYOU FOR THE ORDER.");

        driver.quit();
    }
}
