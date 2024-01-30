package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text() = 'My Cart']")
    WebElement myCartHeader;

    @FindBy(css = ".cartSection h3")
    List<WebElement> productNamesInCart;

    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;

    public void cartPageSuccessfullyOpened() {
        waitForElementToAppear(myCartHeader);
        System.out.println("✔ Cart page is successfully opened");
    }

    public Boolean productDisplayed(String productName) {
        cartPageSuccessfullyOpened();
        return verifyProductMatchFromList(productName, productNamesInCart);
    }

    public CheckoutPage clickOnCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

}
