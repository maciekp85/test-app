package pl.wimiip.interfaceTests.magentocommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by nishi on 2016-09-09.
 */
public class Search {

    private WebElement search;

    @FindBy(xpath = "//form[@id='search_mini_form']/div[2]/button")
    private WebElement searchButton;

    public Search() {
        PageFactory.initElements(Browser.driver(), this);
    }

    public SearchResults searchInStore(String query) {
        search.sendKeys(query);
        searchButton.click();
        return new SearchResults(query);
    }
}
