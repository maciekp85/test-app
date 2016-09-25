package pl.wimiip.interfaceTests.config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nishi on 2016-06-19.
 */
public class ITConfigurationForChromeBrowser {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        driver.close();
    }

}
