package pl.wimiip.interfaceTests.tests.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.pageobjects.BmiCalcPageOperations;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import static org.junit.Assert.*;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

public class ITPageFactoryClassForExposingOperationOnPageTest extends ITConfigurationForChromeBrowser {

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
     * The test which use the BmiCalcPageElements class for testing the BMI Calculator page
     * @throws Exception
     */
    @Test
    public void bmiCalculatorApplication_CheckUsingPageFactoryClassForExposingOperationsOnPage_NothingResultsOnlyAsserts() throws Exception {

        // Create instance of BmiCalcPageElements and pass the driver
        BmiCalcPageOperations bmiCalcPageOperations = new BmiCalcPageOperations(driver);

        // Move to proper view
        commonMethods.moveToExample("pom", "5.2 - 5.3");

        // Wait until checkbox will be visibility and then click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value=exposingElementsCheckbox]"))).click();

        // Activate the BMI Calculator iframe
        driver.switchTo().frame("bmiCal");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Metric - kg/cm"))).click();

        // Calculate the Bmi for passed values (weight, heightMt, heightCm)
        bmiCalcPageOperations.calculateBmi("89", "1", "81");

        // Click on Calculate Button
        WebElement calculateButton = driver.findElement(By.id("calcButtonKg"));
        calculateButton.click();

        Thread.sleep(100);

        // Verify Bmi & Bmi Category values
        assertEquals("27.3", bmiCalcPageOperations.getBmi());
        assertEquals("overweight", bmiCalcPageOperations.getBmiCategory("Overweight"));
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

}
