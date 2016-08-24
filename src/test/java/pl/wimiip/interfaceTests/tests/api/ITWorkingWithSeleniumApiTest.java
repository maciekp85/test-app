package pl.wimiip.interfaceTests.tests.api;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void button_PerformingDoubleClickOnElementAndCheckedWhetherTextIsDisplayed_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.6");

        // Get button element
        WebElement button = driver.findElement(By.id("button"));

        // Get paragraph element
        WebElement paragraph = driver.findElement(By.id("helloMessage"));

        // Verify text within paragraph is not visible
        assertTrue(paragraph.getText().isEmpty());

        Actions builder = new Actions(driver);
        builder.doubleClick(button).build().perform();

        // Verify whether text is visible and proper
        assertFalse(paragraph.getText().isEmpty());
        assertEquals("Hello World", paragraph.getText());
    }

    @Test
    public void twoDivElements_PerformingDragAndDropOperations_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.7");

        // Get source element
        WebElement source = driver.findElement(By.id("draggable"));

        // Get target element
        WebElement target = driver.findElement(By.id("droppable"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).build().perform();

        assertEquals("Dropped!", target.getText());
    }

    @Test
    public void pageTitleAndInputs_JavaScriptCalls_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.8");

        // Casting the WebDriver instance to a JavascriptExecutor interface
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Get page title and check whether is correct
        String title = (String) js.executeScript("return document.title");
        assertEquals("Test App - Start Test App Template", title);

        // Get count of input elements on page and check whether is proper
        long inputs = (Long) js.executeScript("var inputs = document.getElementsByTagName('input'); return inputs.length; ");
        assertEquals(2, inputs);
    }

    @Test
    public void page_CapturingScreenshotWithSeleniumWebDriver_SavesFileToAppropriateDirectory() {

        // Move to proper view
        moveToExample("api", "2.9");

        // The TakesScreenshot interface provides the getScreenshotAs() method to capture a screenshot of the page displayed in the driver instance.
        try {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Using the copyFile() method of the FileUtils class from org.apache.commons.io.FileUtils class to save the file object returned by the getScreenshotAs() method.
            FileUtils.copyFile(srcFile, new File("c:\\screenshots\\page_view_2.9.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void page_CapturingScreenshotWithRemoteWebDriverOrGrid_SavesFileToAppropriateDirectory() {

        // Move to proper view
        moveToExample("api", "2.10");

        // While running tests with RemoteWebDriver or Grid it is not possible to take screenshots, as the TakesScreenshot interface is not implemented in RemoteWebDriver.
        // However, we can use the Augmenter class which adds the TakesScreenshot interface to the remote driver instance
        // (in this case I do not use RemoteWebDriver instance, it is only example to show how to fix this problem!)
        driver = new Augmenter().augment(driver);

        try {
            // The TakesScreenshot interface provides the getScreenshotAs() method to capture a screenshot of the page displayed in the driver instance.
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            // Using the copyFile() method of the FileUtils class from org.apache.commons.io.FileUtils class to save the file object returned by the getScreenshotAs() method.
            FileUtils.copyFile(srcFile, new File("c:\\screenshots\\page_view.2.10.png"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void browserWindow_MaximizingAndSetPosition_NothingResultsOnlyAsserts() {

        // Set the position of the current window and then get it. This is relative to the upper left corner of the screen.
        driver.manage().window().setPosition(new Point(200, 200));
        Point position = driver.manage().window().getPosition();
        assertEquals(new Point(200, 200), position);

        // Maximizes the current window
        driver.manage().window().maximize();
    }

    @Test
    public void dropdown_BasicChecksAndCallVariousMethodsToSelectOptions_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.11");

        // Get the Dropdown as a Select using its name attribute
        Select dropdown = new Select(driver.findElement(By.name("dropdown")));

        // Verify Dropdown does not support multiple selection
        assertFalse(dropdown.isMultiple());
        // Verify Dropdown has four options for selection
        assertEquals(4, dropdown.getOptions().size());

        // We will verify Dropdown has expected values as listed in a array
        List<String> exp_options = Arrays.asList(new String[]{"1", "2", "3", "4"});
        List<String> act_options = new ArrayList<String>();

        // Retrieve the option values from Dropdown using getOptions() method
        for (WebElement option: dropdown.getOptions())
                act_options.add(option.getText());

        // Verify expected options array and actual options array match
        assertArrayEquals(exp_options.toArray(), act_options.toArray());

        // With Select class we can select an option in Dropdown using Visible Text
        dropdown.selectByVisibleText("2");
        assertEquals("2", dropdown.getFirstSelectedOption().getText());

        // or we can select an option in Dropdown using value attribute
        dropdown.selectByValue("three");
        assertEquals("3", dropdown.getFirstSelectedOption().getText());

        // or we can select an option in Dropdown using index
        dropdown.selectByIndex(0);
        assertEquals("1", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void multipleSelectList_BasicChecksAndCallVariousMethodsToSelectMultipleOptions_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.11");

        // Get the List as a Select using its name attribute
        Select colorList = new Select(driver.findElement(By.name("colorList")));

        // Verify List support multiple selection
        assertTrue(colorList.isMultiple());

        // Verify List has eight options for selection
        assertEquals(8, colorList.getOptions().size());

        // Select multiple options in the list using visible text
        colorList.selectByVisibleText("Red");
        colorList.selectByVisibleText("Green");
        colorList.selectByVisibleText("Yellow");

        // We will verify list has multiple options selected as listed in a array
        List<String> exp_sel_options= Arrays.asList(new String[]{"Red", "Green", "Yellow"});
        List<String> act_sel_options = new ArrayList<String>();

        for (WebElement option: colorList.getAllSelectedOptions())
                act_sel_options.add(option.getText());

        // Verify expected array for selected options match with actual options selected
        assertArrayEquals(exp_sel_options.toArray(), act_sel_options.toArray());

        // Verify there 3 options selected in the list
        assertEquals(3, colorList.getAllSelectedOptions().size());

        // Deselect an option using visible text (green color)
        colorList.deselectByVisibleText("Green");
        // Verify selected options count
        assertEquals(2, colorList.getAllSelectedOptions().size());

        // Deselect and option using value attribute of the option (red color)
        colorList.deselectByValue("rd");
        // Verify selected options count
        assertEquals(1, colorList.getAllSelectedOptions().size());

        // Deselect an option using index of the option (yellow color)
        colorList.deselectByIndex(5);
        // Verify selected options count
        assertEquals(0, colorList.getAllSelectedOptions().size());
    }

    @Test
    public void radioButtonAndGroup_CheckSelectOperations_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.12");

        // Get the radio button as WebElement using its value attribute
        WebElement petrol = driver.findElement(By.xpath("//input[@value='Petrol']"));

        // Check if it is alreaady selected? otherwise select the Radiobutton by calling click() method
        if(!petrol.isSelected())
            petrol.click();

        // Verify Radiobutton is selected
        assertTrue(petrol.isSelected());

        // We can also get all the Radiobuttons from a Radio Group in a list using findElements() method along with Radio Group identifier
        List<WebElement> fuel_type = driver.findElements(By.name("type"));

        for (WebElement type: fuel_type) {
            // Search for Diesel Radiobutton in the Radio Group and select it
            if(type.getAttribute("value").contains("Diesel"))
            {
                if(!type.isSelected())
                    type.click();

                assertTrue(type.isSelected());
                break;
            }
        }

    }

    @Test
    public void checkBox_CheckSelectAndDeselectOperations_NothingResultsOnlyAsserts() {

        // Move to proper view
        moveToExample("api", "2.13");

        // Get the Checkbox as WebElement using its value attribute
        WebElement robertCheckbox = driver.findElement(By.xpath("//input[@value='robert']"));

        // Check if its already selected? otherwise select the Checkbox by calling click() method
        if(!robertCheckbox.isSelected())
            robertCheckbox.click();

        // Verify Checkbox is Selected
        assertTrue(robertCheckbox.isSelected());

        // Check Checkbox id selected? If yes, deselect it
        if(robertCheckbox.isSelected())
            robertCheckbox.click();

        // Verify Checkbox is Deselected
        assertFalse(robertCheckbox.isSelected());
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
