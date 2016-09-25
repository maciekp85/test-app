package pl.wimiip.interfaceTests.magentocommerce.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.junit.Assert.*;


/**
 * Created by nishi on 2016-09-09.
 */
public class HomePage extends LoadableComponent<HomePage> {

    static String url = "http://magento2demo.firebearstudio.com/";
    private static String title = "Home Page | Magento 2 Demo";

    public HomePage() {
        PageFactory.initElements(Browser.driver(), this);
        Browser.driver().manage().window().maximize();
    }

    @Override
    public void load() {
        Browser.open(url);
    }

    @Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals(title));
    }

    public void close() {
        Browser.close();
    }

    public Search Search() {
        Search search = new Search();
        return search;
    }

}
