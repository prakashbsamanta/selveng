package com.selveng.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationHeader {
    WebDriver driver;

    public NavigationHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink='/dashboard/cart']")
    WebElement buttonCart;


    public CartPage goToCartPage() {
        buttonCart.click();
        return new CartPage(driver);
    }
}
