package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[normalize-space()='Your Orders']")
    WebElement orderTitle;

    @FindBy(css = ".container .mt-4.ng-star-inserted")
    WebElement noOrdersText;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    WebElement latestOrderedProductName;

    @FindBy(xpath = "//tbody/tr/td[6]/button[1]")
    List<WebElement> deleteButtons;

    public void isOrdersPageOpenedSuccessfully() {
        waitForElementToAppear(orderTitle);
    }

    public String getLastOrderedProductName() {
        return latestOrderedProductName.getText().trim();
    }

    public String getNoOrdersText() {
        return noOrdersText.getText().trim();
    }

    public void deleteAllExistingOrders() {
        deleteButtons.forEach(WebElement::click);
    }


}
