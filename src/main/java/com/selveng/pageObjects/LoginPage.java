package com.selveng.pageObjects;

import com.selveng.utils.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends AbstractComponents {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    public WebElement userEmails = driver.findElement(By.id("userEmail"));

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginSubmitBtn;

    @FindBy(id = "toast-container")
    WebElement toastContainer;

    @FindBy(className = "toast-message")
    public WebElement toastContainerText;

    @FindBy(css = ".invalid-feedback div")
    public WebElement emailRequiredError;

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginSubmitBtn.click();
        return new ProductCatalogue(driver);
    }

    public void goTo(String uri) {
        driver.get(uri);
    }

    public String getToastContainerText(WebElement element) {
        return getContainerText(element);
    }
}
