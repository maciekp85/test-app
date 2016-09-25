package pl.wimiip.interfaceTests.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;

/**
 * Created by nishi on 2016-05-02.
 */
public class BasicTestChrome extends ITConfigurationForChromeBrowser {

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost:8080/#/");
    }
}
