package com.selveng.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {
    WebDriver driver;
    WebDriverWait wait;


    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
    }

    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public Boolean verifyProductMatchFromList(String productToMath, List<WebElement> productsList) {
        return productsList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productToMath));
    }

    public String getContainerText(WebElement toastContainer) {
        waitForElementToAppear(toastContainer);
        return toastContainer.getText().trim();
    }
}
