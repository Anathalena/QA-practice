import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase1 {
        public WebDriver driver;
        public String baseUrl = "https://www.saucedemo.com/";

        @BeforeTest
        public void setUp(){
            System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
            driver = new ChromeDriver();
            driver.get(baseUrl);
        }

        @Test
        public void logIn(){
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }

        @Test
        public void verifyHeader(){
            String actualHeader = driver.findElement(By.className("title")).getText();
            Assert.assertEquals(actualHeader, "PRODUCTS");
        }

        @Test
        public void verifyShoppingCart(){
            driver.findElement(By.id("shopping_cart_container")).isDisplayed();
        }

        @Test
        public void verifyBurgerMenu(){
            driver.findElement(By.className("bm-burger-button")).isDisplayed();
        }

        @Test
        public void verifyTwitter(){
            String url = "https://twitter.com/saucelabs";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
        }

        @Test
        public void verifyFacebook(){
            String url = "https://www.facebook.com/saucelabs";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
        }

        @Test
        public void verifyLinkedin(){
            String url = "https://www.linkedin.com/company/sauce-labs/";
            driver.findElement(By.xpath("//a[@href=\"" + url + "\"]")).isEnabled();
        }

        @Test
        public void verifyLogout(){
            driver.findElement(By.id("logout_sidebar_link")).isEnabled();
        }

        @AfterTest
        public void terminateBrowser(){
            driver.close();
        }
}
