import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class SeleniumDemo {
        public WebDriver driver;
        @BeforeAll
        public static void setUp(){
            System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        }
        @BeforeEach
        public void setUp2(){
            System.out.println("blabla");
            driver = new ChromeDriver();
        }
        @Test
        public void openChrome(){
            driver.get("https://www.saucedemo.com/");
        }


        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
