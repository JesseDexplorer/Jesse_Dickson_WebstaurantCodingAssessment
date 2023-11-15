import code.assement.CartPage;
import code.assement.PageFactory;
import code.assement.ShoppingPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainTest {

    private WebDriver driver;
    protected String searchQuery = "stainless work table";
    private ShoppingPage shopPage;
    private CartPage cart;

    protected String shopUrl = "https://www.webstaurantstore.com/search/stainless-work-table.html?page=";
    protected String cartUrl = "https://www.webstaurantstore.com/viewcart.cfm";
    protected String keyword = "Table";

    @Before
    public void setUp(){
        driver = PageFactory.getDriver();
        shopPage = new ShoppingPage(driver);

    }


    @Test
    public void testCaseOne(){
        shopPage.searchForProduct(searchQuery);

    // get the total number of pages in the pagination
    List<WebElement> pageNumbers = shopPage.getPageNumbers();
    String pageNumberSize = pageNumbers.get(pageNumbers.size() - 1).getText();
    int totalPageNumber = Integer.parseInt(pageNumberSize); // convert the total number of pages to integer for loop

    // will jump to page 1 and then go to each page till the last page
        for (int currentPageNumber = 1; currentPageNumber <= totalPageNumber; currentPageNumber++){
        String newPageNumber = String.valueOf(currentPageNumber);
        shopPage.getShopPage(shopUrl + newPageNumber); // as the page number changes it will modify the url

        // verify that each item on the page has the given keyword
        List<WebElement> currentPageResult = shopPage.getSearchResult();
        for (WebElement item : currentPageResult) {
            try {
                Assert.assertTrue(item.getText().contains(keyword));
            } catch (AssertionError e) {
                // add assertion error to the error message
                String errorMessage = "\n" + "This item:" + "\n" +
                        item.getText() + "\n does not have keyword " + keyword + "\n" +
                        "page: " + driver.getTitle() + "\n\n";
                PageFactory.writeToAssertionErrorsFile(errorMessage, e); // creates and writes to document assert error
            }
        }

        }

        shopPage.clickAddToCartButton();
    }

    @After
    public void tearDown(){
        PageFactory.quitDriver();

    }
}
