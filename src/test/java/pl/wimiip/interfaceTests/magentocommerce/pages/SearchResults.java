package pl.wimiip.interfaceTests.magentocommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by nishi on 2016-09-09.
 */
public class SearchResults extends LoadableComponent<SearchResults> {

    private String query;

    public SearchResults(String query) {
        PageFactory.initElements(Browser.driver(), this);
    }

    @Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals("Search results for: '" + this.query + "' | Magento 2 Demo"));
  }

    @Override
    public void load() {

    }

    public List<String> getProducts() {
        List<String> products = new ArrayList<String>();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> productList = Browser.driver().findElements(By.cssSelector("ol > li"));
        for (WebElement item: productList) {
            products.add(item.findElement(By.cssSelector("strong > a")).getText());
        }

        return products;
    }

    public void close() {
        Browser.close();
    }

    public Search Search() {
        Search search = new Search();
        return search;
    }

}
