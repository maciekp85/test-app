package pl.wimiip.interfaceTests.tests.locating;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by nishi on 2016-06-19.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
@WebAppConfiguration
public class ITLocatingElementsTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage();
    }

    @Test
    public void inputFieldsOfLoginForm_LocatingElementById_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.2"))).click();
        fillInForm("Maciek", "test");
        clearForm();
    }

    @Test
    public void previousAndNextButtons_LocatingElementByName_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.3"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("previous")));
        WebElement previousButton = driver.findElement(By.name("previous"));
        WebElement nextButton = driver.findElement(By.name("next"));
        assertNotNull(previousButton);
        assertNotNull(nextButton);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.name("text"), "Previous"));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.name("text"), "Next"));

        previousButton.click();
        assertTrue(driver.findElement(By.name("text")).isDisplayed());

        String text = driver.findElement(By.name("text")).getText();
        assertEquals("Previous", text);

        nextButton.click();
        text = driver.findElement(By.name("text")).getText();
        assertEquals("Next", text);
    }

    @Test
    public void htmlHeadingElements_LocatingElementByClassName_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.4"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("h1")));
        WebElement h1 = driver.findElement(By.className("h1"));
        assertEquals("Bootstrap heading (36px)", h1.getText());
        assertEquals("h1", h1.getAttribute("class"));
        assertEquals("h1", h1.getTagName());
        assertTrue(h1.isDisplayed());
        String h4 = driver.findElement(By.className("h4")).getText();
        assertNotSame(h1, h4);
        assertNotEquals("Bootstrap heading (18px)", h1.getText());
        assertEquals("Bootstrap heading (18px)", h4);
    }

    @Test
    public void htmlUlElement_LocatingElementsByLinkText_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.5"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Onet")));
        WebElement li1 = driver.findElement(By.linkText("Onet"));
        assertEquals("http://onet.pl/", li1.getAttribute("href"));
        WebElement li3 = driver.findElement(By.linkText("Wp"));
        assertEquals("http://wp.pl/", li3.getAttribute("href"));
    }

    @Test
    public void dropdownMenu_LocatingElementsUsingFindElementsMethod_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.6"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pages"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Onet")));
        List<WebElement> links = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));
        assertEquals(3, links.size());

        ArrayList list = new ArrayList();
        list.add("Onet");
        list.add("Interia");
        list.add("Wp");

        for(int i=0; i < links.size(); i++) {
            assertEquals(list.get(i), links.get(i).getText());
        }
    }

    @Test
    public void htmlUIElement_LocatingElementsByPartialText_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.7"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Onet")));
        WebElement li3 = driver.findElement(By.partialLinkText("Wirtualna"));
        assertEquals("http://wp.pl/", li3.getAttribute("href"));
    }

    @Test
    public void thAndTrTagsInTable_LocatingElementsByTagName_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.8"))).click();
        WebElement table = driver.findElement(By.id("myTable"));
        List<WebElement> columns = table.findElements(By.tagName("th"));
        assertEquals(3, columns.size());
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        assertEquals(3, rows.size());
    }

    @Test
    public void htmlElements_LocatingElementsUsingCssSelectors_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.9"))).click();

        // Finding elements with
        // ABSOLUTE PATH
        WebElement userName = driver.findElement(By.cssSelector("html body div div div div div div div div div div div div form div div input"));
        // ... by describing the direct parent to child relationships with > separator
        userName = driver.findElement(By.cssSelector("html > body > div > div > div > div > div > div > div > div > div > div > div > div > form > div > div > input"));

        // Finding elements with
        // RELATIVE PATH
        userName = driver.findElement(By.cssSelector("input"));

        // Finding elements
        // USING THE CLASS SELECTOR
        WebElement loginButton = driver.findElement(By.cssSelector("input.form-control"));

        // ... there is also shortcut but this will return all the elements with class as form-control and the test may not return the correct element.
        loginButton = driver.findElement(By.cssSelector(".form-control"));

        // Finding elements
        // USING ID SELECTOR
        userName = driver.findElement(By.cssSelector("input#login"));

        // ... there is also shortcut but this will return all the elements with id as login and the test may not return the correct element.
        userName = driver.findElement(By.cssSelector("#login"));

        // Finding elements
        // USING ATTRIBUTES SELECTOR
        userName = driver.findElement(By.cssSelector("input[name=username]"));

        // ... locating of the <img> element by using alt attribute
        WebElement picture = driver.findElement(By.cssSelector("img[alt='picture with alt']"));

        // MULTIPLE ATTRIBUTES
        WebElement valueButton = driver.findElement(By.cssSelector("input[type='button'][value='ButtonValue']"));

        // Finding elements
        // USING ATTRIBUTES NAME SELECTOR

        // to lookup all the <img> elements which have alt attribute
        List<WebElement> imagesWithAlt = driver.findElements(By.cssSelector("img[alt]"));
        assertEquals(1, imagesWithAlt.size());

        // A Boolean not() pseudo-class can also be used to locate elements not matching the specified criteria.
        List<WebElement> imagesWithoutAlt = driver.findElements(By.cssSelector("img:not([alt])"));
        assertEquals(1, imagesWithoutAlt.size());

        // Performing partial match on attribute values
        // first button id = "firstButtonPrimary"
        // second button id = "secondButtonValue"
        // third button id = "thirdButtonRocketPower"

        //  ^=
        WebElement firstButton = driver.findElement(By.cssSelector("button[id^='first']"));
        assertEquals("firstButtonPrimary", firstButton.getAttribute("id"));
        WebElement secondButton = driver.findElement(By.cssSelector("input[id^='second']"));
        assertEquals("secondButtonValue", secondButton.getAttribute("id"));

        // $=
        firstButton = driver.findElement(By.cssSelector("button[id$='Primary']"));
        assertEquals("firstButtonPrimary", firstButton.getAttribute("id"));
        secondButton = driver.findElement(By.cssSelector("input[id$='Value']"));
        assertEquals("secondButtonValue", secondButton.getAttribute("id"));

        // *=
        WebElement thirdButton = driver.findElement(By.cssSelector("input[id*=Rocket]"));
        assertEquals("thirdButtonRocketPower", thirdButton.getAttribute("id"));
    }

    @Test
    public void htmlElements_LocatingElementsUsingXPathSelectors_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.10"))).click();

        // Finding elements with
        // ABSOLUTE PATH
        WebElement userName = driver.findElement(By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/form/div/div/input"));

        // Finding elements with
        // RELATIVE PATH
        userName = driver.findElement(By.xpath("//input"));

        // Finding elements
        // USING INDEX
        WebElement password = driver.findElement(By.xpath("//form/div[2]//input"));

        // Finding elements using attributes values with XPath
        userName = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement picture = driver.findElement(By.xpath("//img[@alt='picture 1 with alt']"));

        WebElement previousButton = driver.findElement(By.xpath("//input[@alt='Previous']"));
        WebElement nextButton = driver.findElement(By.xpath("//input[@type='submit'][@value='Next']"));

        // Operator and
        nextButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Next']"));
        // Operator or
        nextButton = driver.findElement(By.xpath("//input[@value='Previous' or @value='Next']"));

        // Finding elements using attributes with XPath
        List<WebElement> pictures = driver.findElements(By.xpath("//img[@alt]"));
        assertEquals(2, pictures.size());

        // Performing partial match on attribute values

        // starts-with()
        WebElement maciek = driver.findElement(By.xpath("//label[starts-with(@id, 'maciek_123')]"));
        assertEquals("Maciek", maciek.getText());

        // ends-with()
        try {
            WebElement krzysiek = driver.findElement(By.xpath("//label[ends-with(@id, 'krzysiek')]"));
            assertEquals("Krzysiek", krzysiek.getText());
        } catch (InvalidSelectorException exc) {
            // The ends-with function is part of xpath 2.0 but browsers (for example chrome) generally only support 1.0
            // This is only explanation which I have found in the net.
        }

        // contains()
        WebElement robert = driver.findElement(By.xpath("//label[contains(@id, 'robert')]"));
        assertEquals("Robert", robert.getText());

        // Matching any attribute using a value
        WebElement username = driver.findElement(By.xpath("//input[@*='username']"));
        assertEquals("username", username.getAttribute("id"));

        // Locating elements wth XPath axis

        // ANCESTOR
        // Selects all ancestors (parent, grandparent, and so on) of the current node
        // This will get the table element
        WebElement table = driver.findElement(By.xpath("//td[text()='Product 1']/ancestor::table"));
        assertEquals("table", table.getTagName());

        // DESCENDANT
        // Selects all descendants (children, grandchildren, and so on) of the current node.
        // This will get the input element from the third column pf the second row from the table.
        WebElement qty12 = driver.findElement(By.xpath("//table/descendant::td/input"));
        assertEquals("12", qty12.getAttribute("value"));

        // FOLLOWING
        // Selects everything in the document after the closing tag of the current node.
        // This will get the second row from the table
        WebElement secondRow = driver.findElement(By.xpath("//td[text()='Product 1']/following::tr"));
        assertEquals("Product 2 $150", secondRow.getText());

        // FOLLOWING-SIBLING
        // Selects all siblings after the current node
        // This will get the second column from the second row immediately after the column that has Product 1
        // as the text value
        WebElement secondRowSecondColumn = driver.findElement(By.xpath("//td[text()='Product 1']/following-sibling::td"));
        assertEquals("$100", secondRowSecondColumn.getText());

        // PRECEDING
        // Select all nodes that appear before the current node in the document, except ancestors, attribute nodes,
        // and namespaces nodes.
        // This will get the header row.
        WebElement headerRow = driver.findElement(By.xpath("//td[text()='$150']/preceding::tr"));
        assertEquals("Product Price Qty", headerRow.getText());

        // PRECEDING-SIBLING
        // Selects all siblings before the current node.
        WebElement firstColumnOfThirdRow = driver.findElement(By.xpath("//td[text()='$150']/preceding-sibling::td"));
        assertEquals("Product 2", firstColumnOfThirdRow.getText());
    }

    @Test
    public void tableHtmlElement_LocatingElementsUsingText_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.11"))).click();

        WebElement cell = driver.findElement(By.xpath("//td[contains(text(), 'Item 1')]"));
        assertEquals("Item 1", cell.getText());

        cell = driver.findElement(By.xpath("//td[.='Item 2']"));
        assertEquals("Item 2", cell.getText());

    }


    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }


    /**
     * Method which fills in a form
     * @param login
     * @param password
     */
    private void fillInForm(String login, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        assertNotNull(driver.findElement(By.id("clickMeButton")));
        driver.findElement(By.id("clickMeButton")).click();
    }

    /**
     * Method clearing fields form. It does not accept any arguments.
     */
    private void clearForm() {
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).isDisplayed()
            && wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).getText() != null) {
            driver.findElement(By.id("login")).clear();
            assertEquals("", driver.findElement(By.id("login")).getText());
        }
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).getText() != null)
            driver.findElement(By.id("password")).clear();
            assertEquals("", driver.findElement(By.id("login")).getText());
        }
}
