import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestCase1 {
        public WebDriver driver;
        public String baseUrl = "https://www.saucedemo.com/";
        public WebDriverWait waitDriver;

        @BeforeTest
        public void setUp(){
            System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
            driver = new ChromeDriver();
            driver.get(baseUrl);
            waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("Browser opened");
        }

        @Test(priority = 2)
        public void logIn(){
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            waitDriver.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
            System.out.println("Logged in");
        }

        @Test(priority =  3)
        public void verifyHeader(){
            String actualHeader = driver.findElement(By.className("title")).getText();
            Assert.assertEquals(actualHeader, "PRODUCTS");
            System.out.println("Header is ok");
        }

        @Test(priority = 4)
        public void verifyShoppingCart(){
            driver.findElement(By.id("shopping_cart_container")).isDisplayed();
            System.out.println("Shopping cart visible");
        }

        @Test(priority = 5)
        public void verifyBurgerMenu(){
            WebElement bm_menu = driver.findElement(By.className("bm-burger-button"));
            bm_menu.isDisplayed();
            Assert.assertEquals(bm_menu.getLocation().getX(), 15);
            Assert.assertEquals(bm_menu.getLocation().getY(), 20);
            System.out.println("Burger menu in good position");
        }

        @Test(priority = 6)
        public void verifyTwitter(){
            String url = "https://twitter.com/saucelabs";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
            System.out.println("Twitter link ok");
        }

        @Test(priority = 7)
        public void verifyFacebook(){
            String url = "https://www.facebook.com/saucelabs";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
            System.out.println("Facebook link ok");
        }

        @Test(priority = 8)
        public void verifyLinkedin(){
            String url = "https://www.linkedin.com/company/sauce-labs/";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
            System.out.println("Linkedin link ok");
        }

        @Test(priority = 9)
        public void Logout(){
            driver.findElement(By.className("bm-burger-button")).click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
            driver.findElement(By.id("logout_sidebar_link")).click();
            waitDriver.until(ExpectedConditions.urlToBe(baseUrl));
            System.out.println("Logged out");
        }

        @AfterTest
        public void terminateBrowser(){
            driver.close();
            System.out.println("Browser terminated");
        }
}
