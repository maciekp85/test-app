package pl.wimiip.interfaceTests.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by nishi on 2016-05-08.
 */
public class BasicTestFirefox {

    private FirefoxDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFirefoxBrowser() throws InterruptedException {
        driver.get("http://localhost:8080/#/");
        Thread.sleep(10000);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
