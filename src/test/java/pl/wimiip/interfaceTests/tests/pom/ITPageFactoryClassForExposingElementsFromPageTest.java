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
import pl.wimiip.interfaceTests.pageobjects.BmiCalcPageElements;
import pl.wimiip.interfaceTests.tests.CommonMethods;
import pl.wimiip.interfaceTests.tests.datadriven.ITJUnitDataDrivenTest;

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
     * The test which use the BmiCalcPageElements class for testing the BMI Calculator page
     * @throws Exception
     */
    @Test
    public void bmiCalculatorApplication_CheckUsingPageFactoryClassForExposingElementsFromPage_NothingResultsOnlyAsserts() throws Exception {

        // Create instance of BmiCalcPageElements and pass the driver
        BmiCalcPageElements bmiCalcPageElements = new BmiCalcPageElements(driver);

        // Move to proper view
        commonMethods.moveToExample("pom", "5.2 - 5.3");

        // Wait until checkbox will be visibility and then click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value=exposingElementsCheckbox]"))).click();

        // Activate the BMI Calculator iframe
        driver.switchTo().frame("bmiCal");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Metric - kg/cm"))).click();

        // Get the Weight element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[1]/span/span/input[1]"))).click();
        bmiCalcPageElements.weight.clear();
        bmiCalcPageElements.weight.sendKeys("89");

        // Get the HeightMeter element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span/span/input[1]"))).click();
        bmiCalcPageElements.heightMeter.clear();
        bmiCalcPageElements.heightMeter.sendKeys("1");

        // Get the HeightCm element using BMI Calculator page's elements from BmiCalcPageElements class and then set its value
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span[2]/span/input[1]"))).click();
        bmiCalcPageElements.heightCm.clear();
        bmiCalcPageElements.heightCm.sendKeys("81");

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
