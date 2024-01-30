package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;
    Actions actions;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryInputBox;

    @FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']")
    WebElement searchResultSpan;

    @FindBy(css = ".ta-results.list-group.ng-star-inserted span")
    List<WebElement> countrySearchList;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement placeOrderBtn;

    public void selectCountry(String countryText) {
        actions.sendKeys(countryInputBox, countryText).build().perform();
        waitForElementToAppear(searchResultSpan);
        for (WebElement country : countrySearchList) {
            String countryName = country.getText().trim();
            System.out.println("Country Name: " + countryName);
            if (countryName.equals(countryText)) {
                country.click();
                break;
            }
        }
    }

    public OrderConfirmation placeOrder() {
        placeOrderBtn.click();
        return new OrderConfirmation(driver);
    }
}
