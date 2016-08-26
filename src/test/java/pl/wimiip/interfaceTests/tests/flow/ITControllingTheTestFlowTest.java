package pl.wimiip.interfaceTests.tests.flow;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by nishi on 2016-08-24.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
public class ITControllingTheTestFlowTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='3. Controlling the Test Flow']"))).click();
    }

    @Test
    public void headerText_CheckIfTextIsCorrect_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.1");

        // Wait until header WebElement will be visibility on the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("label")));

        // Get the header WebElement and put its text in String variable
        WebElement header = driver.findElement(By.tagName("label"));
        String textHeader = header.getText();

        // Check if header text is correct
        assertEquals("Hello Selenium Test Flow!", textHeader);

    }

    @Test
    public void button_CheckImplicitWaitToFindElementAndVerifyItsText_MessageAboutNotFindingElement() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.2");

        // Set the Implicit Wait time Out to 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {

            // Get link for Page 4 and click on it
            WebElement page4button = driver.findElement(By.linkText("Page 4"));
            page4button.click();

            // Get an element with id page4 and verify it's text
            WebElement message = driver.findElement(By.id("page45"));
            assertTrue(message.getText().contains("Abcdefgh"));

        } catch (NoSuchElementException e) {
            System.out.println("Test with implicit wait set on 10 second. Element not found");;
        }
    }

    @Test
    public void pageTitle_CheckExplicitWaitTitleContainsText_NothingResultsOnlyAsserts() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        commonMethods.logInAndMoveToSeleniumPage(driver, wait);

        // Move to proper view

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='3. Controlling the Test Flow']"))).click();

            // Check whether url contains passed value
            assertTrue(wait.until(ExpectedConditions.urlContains("flow")));

            // Wait until located element will be visibility and then click on it.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("3.3"))).click();

        // Click on Google Home Page link
        wait.until(ExpectedConditions.elementToBeClickable(By.id("google"))).click();

        // Enter a term to search and submit
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("selenium");
        // Locate Search button WebElement and click on it
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Szukaj']"));
        searchButton.click();

        // Create Wait using WebDriverWait. This will wait for 10 seconds for timeout before title is updated with search term
        // If title is updated in specified time limit test will move to the next step instead of waiting for 10 seconds
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("selenium"));

        // Verify Title
        assertTrue(driver.getTitle().toLowerCase().startsWith("selenium"));

        // Quits this driver, closing every associated window.
        driver.quit();
    }

    @Test
    public void button_CheckCustomExpectedConditionToFindElementAndVerifyItsText_MessageAboutNotFindingElement() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.4");

        try {
            // Get link for Page 4 and click on it
            WebElement page4button = driver.findElement(By.linkText("Page 4"));
            page4button.click();

            // Create wait using WebDriverWait. This will wait for 5 seconds for timeout before page4 element is found.
            // Element is found in specified time limit test will move to the text step insted of waiting for 10 seconds.
            // Expected condition is expecting a WebElement to be returned after findElement finds the element with specified locator.
            WebElement message = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver d) {
                    return d.findElement(By.id("page45"));
                }});

            assertTrue(message.getText().contains("abcdefgh"));

        } catch (NoSuchElementException e) {
            System.out.println("Test with custom-expected conditions. Element not found!!");
        }
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

}
