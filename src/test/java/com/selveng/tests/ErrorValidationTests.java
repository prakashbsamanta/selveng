package com.selveng.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.selveng.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class ErrorValidationTests extends BaseTest {

    @Test(groups = {"errorHandling"})
    public void validatingLoginErrors() {
        loginPage.loginApplication("abc@abc.com", "password1");
        String errorText = loginPage.getToastContainerText(loginPage.toastContainerText);
        Assert.assertEquals(errorText, "Incorrect email or password.");
    }

    @Test(dataProvider = "validatingInputFieldErrorsData")
    public void validatingInputFieldErrors(HashMap<String, String> dataMap) {
        System.out.println("Test Desc: " + dataMap.get("testDesc"));
        loginPage.loginApplication(dataMap.get("username"), dataMap.get("userPassword"));
        String errorText = loginPage.getToastContainerText(loginPage.emailRequiredError);
        Assert.assertEquals(errorText, dataMap.get("errorText"));
    }

    @DataProvider
    private Object[][] validatingInputFieldErrorsData() throws JsonProcessingException {
        List<HashMap<String, String>> dataObj = getJsonDataToMap("src/test/java/com/selveng/data/dataSet1.json");
        return new Object[][]{
                {dataObj.get(0)},
                {dataObj.get(1)},
                {dataObj.get(2)},
        };
    }
}
