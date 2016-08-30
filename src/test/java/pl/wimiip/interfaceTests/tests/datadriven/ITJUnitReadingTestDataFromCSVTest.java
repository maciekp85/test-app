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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

// Use a parameterized runner
@RunWith(value = Parameterized.class)
public class ITJUnitReadingTestDataFromCSVTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    // Declare instance variables for the parameterized values
    private String weight;
    private String heightMeter;
    private String heightCm;
    private String bmi;
    private String bmiCategory;

    // Define a method that will return test data from the CSV file as a collection. This method internally calls the getTestData() method
    @Parameters
    public static Collection testData() throws IOException {
        return getTestData(".\\src\\test\\resources\\Data.csv");
    }

    // Add a constructor which maps the instance variables with the test data
    public ITJUnitReadingTestDataFromCSVTest(String weight, String heightMeter, String heightCm, String bmi, String bmiCategory)
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
        commonMethods.moveToExample("datadriven", "4.3");

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

        ITJUnitDataDrivenTest.verifyVariables(bmi, bmiCategory);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }


    /**
     * Method which reads a CSV file line-by-line, then splits lines into an array of strings using comma delimeter.
     * This array is then added to Collection, either of which is returned to the testData() method.
     * @param fileName - path to CSV file
     * @return - collection where each element (array of Strings) represents record from CSV file
     * @throws IOException
     */
    public static Collection<String[]> getTestData(String fileName) throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while ((record=file.readLine())!=null) {
            String fields[] = record.split(",");
            records.add(fields);
        }
        file.close();
        return records;
    }

}
