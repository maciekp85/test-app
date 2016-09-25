package pl.wimiip.interfaceTests.magentocommerce.test;

import org.junit.Test;
import static org.junit.Assert.*;

import pl.wimiip.interfaceTests.magentocommerce.pages.*;

/**
 * Created by nishi on 2016-09-09.
 */
public class SearchTest {

    @Test
    public void testProductSearch() {

        // Create an instance of Home Page
        HomePage homePage = new HomePage();

        // Navigate to the Home Page
        homePage.get();

        // Search for 'Analog Watch', the searchInStore method will return SearchResults class
        SearchResults searchResults = homePage.Search().searchInStore("Analog Watch");

        // Verify there are 9 products available with this search
        assertEquals(9, searchResults.getProducts().size());
        for (String item : searchResults.getProducts()) {
            assertTrue(item.contains("Watch"));
        }

        // Close the Search result page
        searchResults.close();
    }
}
