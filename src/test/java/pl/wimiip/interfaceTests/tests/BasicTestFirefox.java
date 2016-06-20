package pl.wimiip.interfaceTests.tests;

import org.junit.Test;
import pl.wimiip.interfaceTests.config.ITConfigurationForFirefoxBrowser;

/**
 * Created by nishi on 2016-05-08.
 */
public class BasicTestFirefox extends ITConfigurationForFirefoxBrowser {

    @Test
    public void testFirefoxBrowser() throws InterruptedException {
        driver.get("http://localhost:8080/#/");
    }
}
