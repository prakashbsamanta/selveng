package com.selveng.tests;

import com.selveng.pageObjects.*;
import com.selveng.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SubmitOrderTest extends BaseTest {
    private NavigationHeader navigationHeader;
    private OrdersPage ordersPage;
    private final String productName = "ZARA COAT 3";


    @Test
    public void submitOrder() {
        ProductCatalogue productCatalogue = loginPage.loginApplication("modestlamport@justzeus.com", "V@VjcZz3xyGD2H%j");

        List<WebElement> productsList = productCatalogue.getProductsList();
        navigationHeader = productCatalogue.addProductToCart(productName);

        CartPage cartPage = navigationHeader.goToCartPage();

        Boolean productMatched = cartPage.productDisplayed(productName);
        Assert.assertTrue(productMatched);
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();

        checkoutPage.selectCountry("India");
        OrderConfirmation orderConfirmation = checkoutPage.placeOrder();

        String orderConfirmationText = orderConfirmation.getOrderConfirmText();
        Assert.assertEquals(orderConfirmationText, "THANKYOU FOR THE ORDER.");

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrders() {
        ordersPage = navigationHeader.goToOrdersPage();
        String lastOrderedProductName = ordersPage.getLastOrderedProductName();
        Assert.assertEquals(lastOrderedProductName, productName);
    }

    @Test(dependsOnMethods = {"verifyOrders"})
    public void deleteAllOrders() {
        String noOrdersLeft = "You have No Orders to show at this time.\nPlease Visit Back Us";
        NavigationHeader navigationHeader = new NavigationHeader(driver);

        ordersPage = navigationHeader.goToOrdersPage();
        ordersPage.deleteAllExistingOrders();
        String noOrdersText = ordersPage.getNoOrdersText();
        Assert.assertEquals(noOrdersText, noOrdersLeft);
    }
}
