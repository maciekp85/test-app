package pl.wimiip.interfaceTests.tests.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by nishi on 2016-08-18.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
public class ITWorkingWithSeleniumApiTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='2. Working with Selenium API']"))).click();
    }

    @Test
    public void button_CheckingElementText_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("api")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("2.2"))).click();

        // Get the button element
        WebElement button = driver.findElement(By.id("button"));

        // Get the button text
        String buttonText = button.getText();

        // Verify button's text displays "Click on me and my color will change"
        assertEquals("Click on me and my color will change", buttonText);

        // Java String API methods for performing a partial match
        assertTrue(buttonText.contains("color"));
        assertTrue(buttonText.startsWith("Click on"));
        assertTrue(buttonText.endsWith("will change"));

        // Get the paragraph text
        WebElement area = driver.findElement(By.id("area"));

        // Get the paragraph text
        String areaText = area.getText();

        // Verify paragraph's text displays "Div's Text\nSpan's Text"
        assertEquals("Div's Text\nSpan's Text", areaText);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }
}
