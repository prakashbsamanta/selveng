package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));

    @FindBy(css = ".mb-3")
    List<WebElement> productsList;

    @FindBy(className = "ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector("button:last-child");
    By toastContainer = By.id("toast-container");

    public List<WebElement> getProductsList() {

        waitForElementToAppear(productsBy);
        return productsList;
    }

    public WebElement getProductByName(String productName) {
        return getProductsList().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText()
                        .equals(productName))
                .findFirst()
                .orElse(null);

    }

    public NavigationHeader addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitForElementToAppear(toastContainer);
        waitForElementToDisappear(spinner);
        return new NavigationHeader(driver);
    }
}
