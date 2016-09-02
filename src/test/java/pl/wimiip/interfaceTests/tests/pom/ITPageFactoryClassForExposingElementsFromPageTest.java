package pl.wimiip.interfaceTests.tests.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.pageobjects.BmiCalcPage;
import pl.wimiip.interfaceTests.tests.CommonMethods;
import pl.wimiip.interfaceTests.tests.datadriven.ITJUnitDataDrivenTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

public class ITPageFactoryClassForExposingElementsFromPageTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='5. Using the Page Object Model']"))).click();
    }

    /**
     * The test which use the BmiCalcPage class for testing the BMI Calculator page
     * @throws Exception
     */
    @Test
    public void bmiCalculatorApplication_CheckUsingPageFactoryClassForExposingElementsFromPage_NothingResultsOnlyAsserts() throws Exception {

        // Create instance of BmiCalcPage and pass the driver
        BmiCalcPage bmiCalcPage = new BmiCalcPage(driver);

        // Move to proper view
        commonMethods.moveToExample("pom", "5.2 - 5.3");

        // Wait until checkbox will be visibility and then click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value=exposingElementsCheckbox]"))).click();

        // Activate the BMI Calculator iframe
        driver.switchTo().frame("bmiCal");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Metric - kg/cm"))).click();

        // Get the Weight element using BMI Calculator page's elements from BmiCalcPage class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[1]/span/span/input[1]"))).click();
        bmiCalcPage.weight.clear();
        bmiCalcPage.weight.sendKeys("89");

        // Get the HeightMeter element using BMI Calculator page's elements from BmiCalcPage class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span/span/input[1]"))).click();
        bmiCalcPage.heightMeter.clear();
        bmiCalcPage.heightMeter.sendKeys("1");

        // Get the HeightCm element using BMI Calculator page's elements from BmiCalcPage class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span[2]/span/input[1]"))).click();
        bmiCalcPage.heightCm.clear();
        bmiCalcPage.heightCm.sendKeys("81");

        // Click on Calculate Button
        WebElement calculateButton = driver.findElement(By.id("calcButtonKg"));
        calculateButton.click();

        Thread.sleep(100);

        // Verify Bmi & Bmi Category values
        ITJUnitDataDrivenTest.verifyVariables("27.3", "Overweight");
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

}
