package pl.wimiip.interfaceTests.tests.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.util.List;

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

        // Move to proper view
        moveToExample("api", "2.2");

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

    @Test
    public void paragraph_CheckingElementAttributeValue_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.3");

        // Get the paragraph element
        WebElement message = driver.findElement(By.id("message"));

        // Checking whether align attribute is correct
        assertEquals("justify", message.getAttribute("align"));
    }

    @Test
    public void htmlElement_CheckingElementStyle_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.4");

        // Get the button element
        WebElement button = driver.findElement(By.id("button"));

        // Get css value for button element
        String width = button.getCssValue("width");

        // Check whether css value is correct
        assertEquals("150px", width);
    }

    @Test
    public void multipleSelectList_HoldingCtrlKeyAndThenSelectingSeveralOptions_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.5");

        // Get options from multiple select list
        List<WebElement> selectList = driver.findElements(By.tagName("option"));

        // Select second and fourth option from select list using Ctrl key
        // Option index start at 0
        Actions builder = new Actions(driver);

        builder.click(selectList.get(1))
                .keyDown(Keys.CONTROL)
                .click(selectList.get(3))
                .keyUp(Keys.CONTROL)
                .build().perform();

        // Verify selected select list shows two options selected
        List<WebElement> options = driver.findElements(By.cssSelector("option:checked"));
        assertEquals(2, options.size());
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

    /**
     * OTHER METHODS
     */

    /**
     * Method who moves you to proper view based on chapter and example string values
     * @param url name within url address
     * @param numberExample number within title of example
     */
    private void moveToExample(String url, String numberExample) {

        // Check whether url contains passed value
        assertTrue(wait.until(ExpectedConditions.urlContains(url)));

        // Wait until located element will be visibility and then click on it.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(numberExample))).click();
    }
}
