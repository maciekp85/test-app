package pl.wimiip.interfaceTests.tests.datadriven;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by nishi on 2016-08-29.
 * Based partly on examples from the "Selenium Testing Tools Cookbook" book by Unmesh Gundecha
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
public class ITDataDrivenTestingTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='4. Data-driven Testing']"))).click();
    }

    @Test
    public void headerText_CheckIfTextIsCorrect_NothingResultsOnlyAsserts() {

        // Move to proper view
        commonMethods.moveToExample("datadriven", "4.1");

        // Wait until header WebElement will be visibility on the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("label")));

        // Get the header WebElement and put its text in String variable
        WebElement header = driver.findElement(By.tagName("label"));
        String textHeader = header.getText();

        // Check if header text is correct
        assertEquals("Hello Data-driven testing!", textHeader);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }


}
