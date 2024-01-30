package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmation extends AbstractComponents {
    public OrderConfirmation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By toastContainer = By.id("toast-container");

    @FindBy(className = "hero-primary")
    WebElement orderConfirmationText;

    public String getOrderConfirmText() {
        waitForElementToAppear(toastContainer);
        return orderConfirmationText.getText();
    }

}
