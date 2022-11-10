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
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        System.out.println("Logged in");
    }

    @Test(priority = 2)
    public void checkItem(){
        String title = "Sauce Labs Backpack";
        String description = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String price = "$29.99";

        driver.findElement(By.id("item_4_title_link")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory-item.html?id=4"));

        String actualTitle = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        String actualDesc = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]")).getText();
        String actualPrice = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]")).getText();
        Assert.assertEquals(title,actualTitle);
        Assert.assertEquals(description,actualDesc);
        Assert.assertEquals(price, actualPrice);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        waitDriver.until(ExpectedConditions.textToBe(By.className("shopping_cart_badge"), String.valueOf(1)));

        System.out.println("Title, description and price visible. Item added to cart from its own page successfully");
    }

    @Test(priority = 3)
    public void goBackToProducts(){
        driver.findElement(By.id("back-to-products")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        System.out.println("Went back to products page successfully.");
    }

    @Test(priority = 4)
    public void checkItemFromHome(){
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        waitDriver.until(ExpectedConditions.textToBe(By.className("shopping_cart_badge"), String.valueOf(2)));
        System.out.println("Item added to cart from products page successfully.");
    }

    @Test(priority = 5)
    public void openShoppingCart(){
        driver.findElement(By.className("shopping_cart_link")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html"));
        System.out.println("Opened shopping cart page.");
    }

    @Test(priority = 6)
    public void checkOut(){
        driver.findElement(By.id("checkout")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
        System.out.println("Opened checkout page.");
    }

    @Test(priority = 7)
    public void enterCheckoutInfo(){
        driver.findElement(By.id("first-name")).sendKeys("Milica");
        driver.findElement(By.id("last-name")).sendKeys("Makevic");
        driver.findElement(By.id("postal-code")).sendKeys("11000");
        driver.findElement(By.id("continue")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-two.html"));
        driver.findElement(By.id("finish")).click();
        waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-complete.html"));
        System.out.println("Entered first and last name and zip code. Finished checkout successfully.");
    }

    @Test(priority = 8)
    public void verifyMessage(){
        String actualHeader = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(actualHeader, "THANK YOU FOR YOUR ORDER");
        System.out.println("Thank you message ok.");
    }

    @Test(priority = 9)
    public void Logout(){
        driver.findElement(By.className("bm-burger-button")).click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();
        waitDriver.until(ExpectedConditions.urlToBe(baseUrl));
        System.out.println("Logged out.");
    }

    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
}
