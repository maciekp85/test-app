package pl.wimiip.interfaceTests.tests.flow;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        commonMethods.logInAndMoveToSeleniumPage();
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
    public void messageText_CheckImplicitWaitToGetMessage_MessageAboutNotFindingElement() {

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

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

}
