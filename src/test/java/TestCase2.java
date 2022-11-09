import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase2 {
    public WebDriver driver;
    public String baseUrl = "https://www.saucedemo.com/";
    public WebDriverWait waitDriver;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void logIn(){
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
    }

    @Test(priority = 2)
    public void checkItem(){
        driver.findElement(By.id("item_4_title_link")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory-item.html?id=4"));
        driver.findElement(By.className("inventory_details_name large_size")).isDisplayed();
        driver.findElement(By.className("inventory_details_desc large_size")).isDisplayed();
        driver.findElement(By.className("inventory_item_price")).isDisplayed();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

    }

    @Test(priority = 3)
    public void goBackToProducts(){
        driver.findElement(By.id("back-to-products")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
    }

    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
}
