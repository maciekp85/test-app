package pl.wimiip.interfaceTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;

/**
 * Created by nishi on 2016-06-20.
 */
public class CommonMethods extends ITConfigurationForChromeBrowser {

    public void logIn() {
        driver.get("http://localhost:8080/#/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login"))).click();
        fillInForm();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button"))).click();
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
