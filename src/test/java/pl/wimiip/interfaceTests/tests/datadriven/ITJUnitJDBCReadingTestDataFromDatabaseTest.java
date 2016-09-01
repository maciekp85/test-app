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

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

// Use a parameterized runner
@RunWith(value = Parameterized.class)
public class ITJUnitJDBCReadingTestDataFromDatabaseTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    // Declare instance variables for the parameterized values
    private String weight;
    private String heightMeter;
    private String heightCm;
    private String bmi;
    private String bmiCategory;

    // Define a method that will return test data from the database as a collection of strings. This method internally calls the getTestData() method
    @Parameters
    public static Collection testData() throws Exception {
        return getTestData("src/test/resources/BmiTesting.accdb", "SELECT Weight, HeightMt, HeightCm, Bmi, Category FROM TestData");
    }

    // Add a constructor which maps the instance variables with the test data
    public ITJUnitJDBCReadingTestDataFromDatabaseTest(String weight, String heightMeter, String heightCm, String bmi, String bmiCategory)
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
    public void bmiCalculatorApplication_UseParameterizedVariableFromMicrosoftAccessDatabaseFileAndCompareThemWithVariablesFromApplication_NothingResultsOnlyAsserts() throws Exception {

        // Move to proper view
        commonMethods.moveToExample("datadriven", "4.2 - 4.5");

        // Wait until checkbox will be visibility and then click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value=accessCheckbox]"))).click();

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
     * Method which reads a database query results and returns the data in the collection.
     * @param accdbFile - path to Microsoft Access Database file
     * @return - collection where each element (collection of Strings array) represents record from database table
     * @throws Exception
     */
    public static Collection<String[]> getTestData(String accdbFile, String sqlQuery) throws Exception {

        ArrayList<String[]> records = new ArrayList<String[]>();

        String myDB = "jdbc:ucanaccess://" + accdbFile;
        Connection conn = DriverManager.getConnection(myDB);

        Statement stmt = null;
        ResultSet rs = null;

        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        rs = stmt.executeQuery(sqlQuery);

        ResultSetMetaData rsMetaData = rs.getMetaData();

        int cols = rsMetaData.getColumnCount();

        while(rs.next()) {
            String fields[] = new String[cols];
            int col = 0;

            for(int colIdx=1; colIdx<=cols; colIdx++) {
                fields[col] = rs.getString(colIdx);
                col++;
            }
            records.add(fields);
        }

        rs.close();
        stmt.close();
        conn.close();

        return records;
    }

}
