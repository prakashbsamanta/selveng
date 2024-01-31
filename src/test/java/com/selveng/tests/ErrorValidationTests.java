package com.selveng.tests;

import com.selveng.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest {

    @Test(groups = {"errorHandling"})
    public void validatingLoginErrors() {
        loginPage.loginApplication("abc@abc.com", "password1");
        String errorText = loginPage.getToastContainerText(loginPage.toastContainerText);
        Assert.assertEquals(errorText, "Incorrect email or password.");
    }

    @Test(dataProvider = "validatingInputFieldErrorsData")
    public void validatingInputFieldErrors(String userEmail, String userPassword, String expectedErrorText) {
        loginPage.loginApplication(userEmail, userPassword);
        String errorText = loginPage.getToastContainerText(loginPage.emailRequiredError);
        Assert.assertEquals(errorText, expectedErrorText);
    }

    @DataProvider
    private Object[][] validatingInputFieldErrorsData() {
        return new Object[][]{
                {"", "password2", "*Email is required"},
                {"user-email", "password3", "*Enter Valid Email"},
                {"user@email.com", "", "*Password is required"}
        };
    }
}
