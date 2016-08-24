package pl.wimiip.interfaceTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by nishi on 2016-06-20.
 */
public class CommonMethods extends ITConfigurationForChromeBrowser implements Common {

    @Override
    public void logInAndMoveToSeleniumPage() {
        driver.get("http://localhost:8080/#/");
        try {
            if(driver.findElement(By.linkText("Login")).isDisplayed()) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login"))).click();
                fillInForm();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium"))).click();
            }
        } catch (NoSuchElementException exc) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium"))).click();
        }
    }

    /**
     * Method who moves you to proper view based on chapter and example string values
     * @param url name within url address
     * @param numberExample number within title of example
     */
    @Override
    public void moveToExample(String url, String numberExample) {

        // Check whether url contains passed value
        assertTrue(wait.until(ExpectedConditions.urlContains(url)));

        // Wait until located element will be visibility and then click on it.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(numberExample))).click();
    }

    private void fillInForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login"))).click();
        driver.findElement(By.name("login")).sendKeys("maciek");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("maciek@test.pl");
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
        wait.until(ExpectedConditions.urlContains("home"));
    }

}
