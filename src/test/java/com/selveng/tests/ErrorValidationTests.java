package com.selveng.tests;

import com.selveng.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest {

    @Test(groups = {"errorHandling"})
    public void validatingLoginErrors() {
        String validationError = "Incorrect email or password.";
        loginPage.loginApplication("abc@abc.com", "abcdefg");
        String errorText = loginPage.getToastContainerText(loginPage.toastContainerText);
        Assert.assertEquals(errorText, validationError);
    }

    @Test
    public void validatingLoginErrors2() {
        String emailError = "*Email is required";
        loginPage.loginApplication("", "123456");
        String errorText = loginPage.getToastContainerText(loginPage.emailRequiredError);
        Assert.assertEquals(errorText, emailError);
    }
}
