package com.selveng.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.selveng.testComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
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
    public void validatingInputFieldErrors(HashMap<String, String> dataMap) throws IOException {
        System.out.println("Test Desc: " + dataMap.get("testDesc"));
        loginPage.loginApplication(dataMap.get("username"), dataMap.get("userPassword"));
        getScreenShot(dataMap.get("testDesc"));
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

    public void getScreenShot(String testCaseName) throws IOException {
        Instant instant = Instant.now();
        long timeStampInMilli = instant.toEpochMilli();
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File ss = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File("src/resources/screenshots/" + testCaseName + "_" + timeStampInMilli + ".png");
        FileUtils.copyFile(ss, file);
        System.out.println("Screenshot saved at: " + file);
    }
}
