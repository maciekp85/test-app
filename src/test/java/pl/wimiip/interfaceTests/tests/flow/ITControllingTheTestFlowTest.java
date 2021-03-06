package pl.wimiip.interfaceTests.tests.flow;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.config.ITConfigurationForFirefoxBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.util.List;
import java.util.Set;
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

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
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

        wait = new WebDriverWait(driver, 5);

        // Quits this driver, closing every associated window.
        driver.quit();
    }

    @Test
    public void button_CheckCustomExpectedConditionToFindElementAndVerifyItsText_NothingResultsOnlyAsserts() {

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
                    return d.findElement(By.id("page4"));
                }});

            assertEquals("Page 4", message.getText());

        } catch (NoSuchElementException e) {
            System.out.println("Test with custom-expected conditions. Element not found!!");
        }
    }

    @Test
    public void checkbox_TestIsElementPresent_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.5");

        // Check if element with locator criteria exists on Page
        if(isElementPresent(By.id("cat"))) {
            // Get the checkbox and select it
            WebElement cat = driver.findElement(By.id("cat"));
            if(!cat.isSelected())
                cat.click();

        } else {
            System.out.println("Cat checbox doesn't exist!");
        }
    }

    @Test
    public void checkbox_CheckTwoElementStatesBeforeSelectIt_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.6");

        // Get the checkbox as WebElement using it's id attribut
        WebElement lamp = driver.findElement(By.id("lamp"));

        // Check if its enabled before selecting it
        if(lamp.isEnabled()) {
            // Check if it is already selected? otherwise select the Checkbox
            if(!lamp.isSelected())
                lamp.click();
        } else {
            System.out.println("Lamp checkbox is disabled");
        }
    }

    @Test
    public void popUpWindow_HandlePopupWindowAndSwitchBetweenWindows_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.7");

        // Save the WindowHandle of Parent Browser Window
        String parentWindow = driver.getWindowHandle();

        // Clicking Home pop-up window button will open Home Page in a new Popup Browser
        WebElement homeButton = driver.findElement(By.id("homeButton"));
        homeButton.click();

        try {
            // Switch to the Home pop-up Browser Window
            driver.switchTo().window("HomeWindow");
        } catch (NoSuchWindowException e) {
            System.out.println("NoSuchWindowException threw!");
        }

        // Verify the driver context is in Home Page pop-up Browser window
        assertTrue(driver.getCurrentUrl().contains("home"));

        // Close the Home pop-up Browser Window
        driver.close();

        // Move back to the Parent Browser Window
        driver.switchTo().window(parentWindow);

        // Verify the driver context is in Parent Browser Window
        assertTrue(driver.getCurrentUrl().contains("popupwindowbyname"));
    }

    @Test
    public void popUpWindow_HandleAllPopupWindowAndSwitchBetweenWindowsBasedOnTheirTitles_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.8");

        // Save the WindowHandle of Parent Browser Window
        String parentWindow = driver.getWindowHandle();

        // Clicking Google pop-up window button will open Google Home Page in a new Popup Browser Window
        WebElement googleButton = driver.findElement(By.id("googleButton"));
        googleButton.click();

        // Get Handles of all the open Popup Windows
        // Iterate through the set and check if title of each window matches with expected Window Title
        Set<String> allWindows = driver.getWindowHandles();
        if(!allWindows.isEmpty()) {
            for(String windowId: allWindows) {
                try {
                    if(driver.switchTo().window(windowId).getTitle().contains("Google")) {
                        // Close the Google Page Popup Window
                        driver.close();
                        break;
                    }
                } catch (NoSuchWindowException ex) {
                    System.out.println("NoSuchWindowException threw!");
                }
            }
        }

        // Move back to the Parent Browser Window
        driver.switchTo().window(parentWindow);

        // Verify the driver context is in Parent Browser Window
        assertTrue(driver.getTitle().contains("Test App"));
    }

    @Test
    public void popUpWindow_HandleAllPopupWindowAndSwitchBetweenWindowsBasedOnTheirContents_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.9");

        // Save the WindowHandle of Parent Browser
        String currentWindowId = driver.getWindowHandle();

        // Clicking Selenium Button will open Selenium Page in a new Popup Browser Window
        WebElement seleniumButton = driver.findElement(By.id("seleniumButton"));
        seleniumButton.click();

        // There is no name or title provided for Selenium Page Popup
        // We will iterate through all the open Windows and check the contents to find out if it's Selenium Window
        Set<String> allWindows = driver.getWindowHandles();

        if(!allWindows.isEmpty()) {
            for (String windowId: allWindows) {
                driver.switchTo().window(windowId);

                if(driver.getPageSource().contains("Table of contents:")) {
                    try {

                        // Close the Selenium Page PopupWindow
                        driver.close();
                        break;

                    } catch (NoSuchWindowException ex) {
                        System.out.println("NoSuchWindowException threw!");
                    }
                }
            }
        }

        // Move back to the Parent Browser Window
        driver.switchTo().window(currentWindowId);

        // Verify the driver context is in Parent Browser Window
        assertTrue(driver.getPageSource().contains("Identifying and handling a pop-up window by its content"));
    }

    @Test
    public void alertWindow_SwitchingToElementWindowAndVerifyTextDisplayedOnElement_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.10");

        // Clicking button will show a simple Alert with OK button
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        try {

            // Wait until alert will be present
            wait.until(ExpectedConditions.alertIsPresent());

//            // Get the Alert
            Alert alert = driver.switchTo().alert();

            // Get the Text displayed on Alert using getText() method of Alert class
            String textOnAlert = alert.getText();

            // Click OK button, by calling accept() method of Alert Class
            alert.accept();

            // Verify Alert displayed correct message to user
            assertEquals("Hello! I am an alert box!", textOnAlert);

        } catch (NoAlertPresentException ex) {
            System.out.println("NoAlertPresentException threw!");
        }
    }

    @Test
    public void confirmBoxAlert_TestConfirmAccept_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.11");

        // Clicking button will show a Confirmation Alert with OK and Cancel Button
        WebElement confirmBoxAlertButton = driver.findElement(By.id("confirmBoxAlertButton"));
        confirmBoxAlertButton.click();

        clickedOKButton(true);
    }

    @Test
    public void confirmBoxAlert_TestConfirmDismiss_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.11");

        // Clicking button will show a Confirmation Alert with OK and Cancel Button
        WebElement confirmBoxAlertButton = driver.findElement(By.id("confirmBoxAlertButton"));
        confirmBoxAlertButton.click();

        clickedOKButton(false);

    }

    @Test
    public void promptBoxAlert_HandlePromptBoxAlertEnterInputValueAndCheckIfTheSameValueIsDisplayedOnThePage_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.12");

        // Clicking button will show a Prompt Alert asking user to enter value/text with OK and Cancel Button
        WebElement promptBoxAlertButton = driver.findElement(By.id("promptBoxAlertButton"));
        promptBoxAlertButton.click();

        try {

            // Get the Alert
            Alert alert = driver.switchTo().alert();

            // Enter some value on Prompt by calling sendKeys() method of Alert class
            alert.sendKeys("John Rambo");

            // Click OK button, by calling accept() method of Alert class
            alert.accept();

            // Verify Page displays message with value entered in Prompt
            WebElement message = driver.findElement(By.id("message"));
            assertEquals("Hello John Rambo! How are you today?", message.getText());

        } catch (NoAlertPresentException ex) {
            System.out.println("NoAlertPresentException threw!");
        }
    }

    @Test
    public void iFrames_TestIFrameByIdOrName_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.13");

        // Move to first example
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("First example"))).click();

        // Activate the iframe on left side using it's id attribute
        driver.switchTo().frame("left");

        // Get an element from the iframe on left side and verify it's contents
        WebElement leftMessage = driver.findElement(By.tagName("p"));
        assertEquals("The language for building web pages", leftMessage.getText());

        // Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();

        // Activate the iframe on right side using it's name attribute
        driver.switchTo().frame("right");

        // Get an element from the iframe on left side and verify it's message
        WebElement rightMessage = driver.findElement(By.tagName("p"));
        assertEquals("Strona główna", rightMessage.getText());

        // Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();
    }

    @Test
    public void iFrames_TestIFrameByIndex_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.13");

        // Move to first example
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("First example"))).click();

        // Activate the iframe in middle using it's index. Index starts at 0.
        driver.switchTo().frame(1);

        // Get an element from the iframe in the middle and verify it's contents
        WebElement middleMessage = driver.findElement(By.tagName("p"));
        assertEquals("What is your favourite sport?", middleMessage.getText());

        // Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();
    }

    @Test
    public void iFrames_TestIFrameByItsContent_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.13");

        // Move to first example
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("First example"))).click();

        // Get all iframes on the Page, created with <iframe> tag
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        // In this example iframe in the middle is activated by checking the contents
        // Activate iframe and check if it has the desired content. It found perform the operations,
        // if not, then switch back to the Page and continue checking next frame
        for(WebElement iframe: iframes) {

            // switchTo().frame() also accepts frame elements apart from id, name or index
            driver.switchTo().frame(iframe);

            if(driver.getPageSource().contains("MACIEK'S TEST PAGE")) {
                assertTrue(true, "Middle Frame Found");
                break;
            } else {
                driver.switchTo().defaultContent();
            }
        }

        // Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();
    }

    @Test
    public void iFramesAndWindows_TestTwitterIFrameByItsWebElementAndSwitchBetweenOpenWindows_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("flow", "3.13");

        // Move to second example
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Second example"))).click();

        // Get the iframe element
        WebElement twitterFrame = driver.findElement(By.tagName("iframe"));

        // Activate the iframe
        driver.switchTo().frame(twitterFrame);

        // Get and Click the follow button from iframe
        WebElement button = driver.findElement(By.id("follow-button"));
        button.click();

        // Store the the handle of current driver window
        String currentWindow = driver.getWindowHandle();

        // The Twitter Popup does not have name or title. Script will get handles of all open windows and
        // desired window will be activated by checking it's Title
        Set<String> allWindows = driver.getWindowHandles();
        if(!allWindows.isEmpty()) {
            for(String windowId: allWindows) {
                driver.switchTo().window(windowId);
                if(driver.getTitle().equals("Unmesh Gundecha (@upgundecha) na Twitterze")) {
                    assertTrue(true, "Twitter Login Popup Window Found");
                    driver.close();
                    break;
                }
            }
        }

        // Switch back to original driver window
        driver.switchTo().window(currentWindow);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }

    /**
     * OTHER METHODS
     */


    /**
     * Method for checking if an element is present on a page (3.5 example).
     * @param by locator using an instance of By claas
     * @return true if the element is found and no exception is thrown, false if NoSuchElementException is thrown
     */
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method which clicks on appropriate button and verify if text is correct (3.11 example).
     * @param choice - (booleand value) what button you should click (selectable: "yes: for Accept or "no" for Cancel)
     */
    private void clickedOKButton(boolean choice) {
        try {
            // Get the Alert
            Alert alert = driver.switchTo().alert();

            if(choice) {
                // Click OK button, by calling accept() method of Alert class
                alert.accept();

                // Verify Page displays correct message on Accept
                WebElement message = driver.findElement(By.id("message"));
                assertEquals("You pressed OK!", message.getText());
            } else {
                // Click Cancel button, by calling dismiss() method of Alert class
                alert.dismiss();

                // Verify Page displays correct message on Accept
                WebElement message = driver.findElement(By.id("message"));
                assertEquals("You pressed Cancel!", message.getText());
            }

        } catch (NoAlertPresentException ex) {
            System.out.println("NoAlertPresentException threw!");
        }
    }

}
