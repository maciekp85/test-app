package pl.wimiip.interfaceTests.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nishi on 2016-08-24.
 */
public interface Common {

    void logInAndMoveToSeleniumPage(WebDriver driver, WebDriverWait wait);

    void moveToExample(String url, String numberExample);
}
