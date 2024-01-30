package com.selveng.tests;

import com.selveng.pageObjects.*;
import com.selveng.testComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException {
        String productName = "ZARA COAT 3";
//        String username=prop.getProperty(username);
//        String loginPassword=prop.getProperty(password);
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

    }
}
