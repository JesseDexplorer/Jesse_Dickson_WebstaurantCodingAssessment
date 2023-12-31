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

/**
 * Test class for the main functionality of the web application.
 * This class contains test cases related to searching for products,
 * navigating through pages, adding items to the cart, and emptying the cart.
 * It uses the ShoppingPage and CartPage classes to perform various test scenarios.
 */

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
        cart = new CartPage(driver);

    }


    @Test
    public void testCaseOne(){
        // go to search bar and search for given query
        shopPage.searchForProduct(searchQuery);

        // get the total number of pages in the pagination
        List<WebElement> pageNumbers = shopPage.getPageNumbers();

        String pageNumberSize = pageNumbers.get(pageNumbers.size() - 1).getText();
        int totalPageNumber = Integer.parseInt(pageNumberSize); // convert the total number of pages to integer for loop

        // will jump to page 1 and then go to each page till the last page
        for (int currentPageNumber = 1; currentPageNumber <= totalPageNumber; currentPageNumber++){
        String newPageNumber = String.valueOf(currentPageNumber);
        shopPage.navigateToShopPage(shopUrl + newPageNumber); // as the page number changes it will modify the url

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


        // empty the cart
        shopPage.clickAddToCartButton(); // selects the last item on the list
        cart.navigateToCartPage(cartUrl);
        cart.clickEmptyCartButton();
        cart.confirmEmptyCartDecision(); // handles the popup confirmation to empty the cart


        // verify that the cart was emptied
        try{
            Assert.assertTrue(cart.verifyCartIsEmpty().contains("Your cart is empty."));
        }catch (AssertionError e){
            String errorMessage = """

                     Cart was not emptied

                    """;
            PageFactory.writeToAssertionErrorsFile(errorMessage, e); // writes to document assert error
        }
    }

    @After
    public void tearDown(){
        PageFactory.quitDriver();

    }
}
