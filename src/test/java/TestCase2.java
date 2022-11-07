import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase2 {
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



    /*@AfterTest
    public void terminateBrowser(){
        driver.close();
    }*/
}
