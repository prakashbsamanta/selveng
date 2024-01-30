package com.selveng.tests;

import java.time.Duration;
import java.util.List;

import com.selveng.pageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        ChromeOptions options = new ChromeOptions();
        options
                .addArguments(
//                        "--headless",
                        "--start-maximized"
                );
        WebDriver driver = new ChromeDriver(options);
        LoginPage loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
//        loginPage.userEmails.sendKeys("modestlamport@justzeus.com");
        driver.findElement(By.id("userPassword")).sendKeys("V@VjcZz3xyGD2H%j");
        driver.findElement(By.id("login")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
        List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
        System.out.println("productList: " + productList);
        WebElement zaraProduct = productList.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        System.out.println("zraProduct: " + zaraProduct);
        assert zaraProduct != null;
        zaraProduct.findElement(By.cssSelector("button:last-child")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        String toastContainerText = driver.findElement(By.id("toast-container")).getText();
        System.out.println("toastContainerText: : " + toastContainerText);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        Actions actions = new Actions(driver);
//        actions.contextClick(driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")))
//                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.ARROW_UP)
//                .build().perform();
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
        actions.sendKeys(driver.findElement(By.cssSelector(".ta-results.list-group")), Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
//        driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button[2]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
        Thread.sleep(5000);

        driver.quit();
    }

}
