package pl.wimiip.interfaceTests.magentocommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by nishi on 2016-09-09.
 */
public class Browser {

    private static String system = System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
    private static WebDriver driver = new ChromeDriver();

    public static WebDriver driver() {
        return driver;
    }

    public static void open(String url) {
        driver.get(url);
    }

    public static void close() {
        driver.close();
    }

}
