package pl.wimiip.interfaceTests.tests.datadriven;

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
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

// Use a parameterized runner
@RunWith(value = Parameterized.class)
public class ITJUnitDataDrivenTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    // Declare instance variables for the parameterized values
    private String weight;
    private String heightMeter;
    private String heightCm;
    private String bmi;
    private String bmiCategory;

    // Define a method that will return the collection of parameters by using the @Parameters annotation
    @Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][] {
                        {"45", "1", "60", "17.5", "Underweight"},
                        {"70","1", "68", "24.9", "Normal"},
                        {"89", "1", "81", "27.3", "Overweight"},
                        {"100","1", "78", "31.6", "Obese"}
                    }
                );
    }

    // Add a constructor which will be used by the test runner to pass the parameters to the SimpleDDT class instance
    public ITJUnitDataDrivenTest(String weight, String heightMeter, String heightCm, String bmi, String bmiCategory)
    {
        this.weight = weight;
        this.heightMeter = heightMeter;
        this.heightCm = heightCm;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
    }

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='4. Data-driven Testing']"))).click();
    }


    // Add the test case method testBMICalculator() that uses parameterized variables
    @Test
    public void bmiCalculatorApplication_UseParameterizedVariableFromCollectionAndCompareThemWithVariablesFromApplication_NothingResultsOnlyAsserts() throws Exception {

        // Move to proper view
        commonMethods.moveToExample("datadriven", "4.2 - 4.5");

        // Wait until checkbox will be visibility and then click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value=dataDrivenCheckbox]"))).click();

        // Activate the BMI Calculator iframe
        driver.switchTo().frame("bmiCal");

        // Get the Weight element and set the value using parameterized weight variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Metric - kg/cm"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[1]/span/span/input[1]"))).click();

        WebElement weightField = driver.findElement(By.xpath("//*[@id='nTWeightKg']"));
        weightField.clear();
        weightField.sendKeys(weight);

        // Get the Height element (meter field) and set the value using parameterized height variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span/span/input[1]"))).click();

        WebElement heightFieldMeter = driver.findElement(By.xpath("//*[@id='nHM']"));
        heightFieldMeter.clear();
        heightFieldMeter.sendKeys(heightMeter);

        // Get the Height element (centimeter field) and set the value using parameterized height variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tabStrip-2']/div[2]/span[2]/span/input[1]"))).click();

        WebElement heightFieldCentimeter = driver.findElement(By.xpath("//*[@id='nHCm']"));
        heightFieldCentimeter.clear();
        heightFieldCentimeter.sendKeys(heightCm);

        // Click on Calculate Button
        WebElement calculateButton = driver.findElement(By.id("calcButtonKg"));
        calculateButton.click();

        Thread.sleep(100);

        verifyVariables(bmi, bmiCategory);

    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }


    /**
     * Method which gets BMI element and BMI Category elements and verify their values using parameterized variables
     * @param bmi - value of BMI element from collection
     * @param bmiCategory - value of BMI category from collection
     */
    public static void verifyVariables(String bmi, String bmiCategory) {

        try {
            // Get the BMI element and verify its value using parameterized bmi variable
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bmiDisplayKg']/div[2]/div[2]")));
            String bmiLabel = driver.findElement(By.xpath("//*[@id='bmiDisplayKg']/div[2]/div[2]")).getText().substring(4);
            assertEquals(bmi, bmiLabel);

            // Get the BMI Category element and verify its value using parameterized bmiCategory variable
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bmiDisplayKg']/div[1]/div[@class='fit" + bmiCategory + "']")));
            String bmiCategoryLabel = driver.findElement(By.xpath("//*[@id='bmiDisplayKg']/div[1]/div[@class='fit" + bmiCategory + "']")).getText();
            assertEquals(bmiCategory.toLowerCase(), bmiCategoryLabel);

        } catch (Exception ec) {
            System.out.println("Exception threw!");
        }
    }
}
