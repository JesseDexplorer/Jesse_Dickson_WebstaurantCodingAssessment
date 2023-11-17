package code.assement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * ShoppingPage class represents the web page where users can search for products.
 */
public class ShoppingPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public ShoppingPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    /**
     * performs a search for given product on shopping page
     * @param query The search query for the product
     */
    public void searchForProduct(String query){
        //wait for 10 seconds unless the search loads and is clickable
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchval")));
        searchBox.click();
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    /**
     * Retrieves every item description on the page.
     *
     * @return A list containing all item descriptions on the page.
     */
    public List<WebElement> getSearchResult(){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//span[@data-testid='itemDescription']")));
    }

    /**
     * Retrieves the total number of pages in the pagination.
     *
     * @return A list representing the page numbers.
     */
    public List<WebElement> getPageNumbers(){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                ( By.xpath("//a[contains(@aria-label, 'last page')]")));
    }

    /**
     * Navigates to the shopping page and waits until the items on the page are loaded.
     *
     * @param url The address of the shopping page.
     */
    public void navigateToShopPage(String url){
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//span[@data-testid='itemDescription']")));
    }

    /**
     * Clicks the "Add to Cart" button on the shopping page.
     */
    public void clickAddToCartButton(){
        List<WebElement> addButton = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//input[@value='Add to Cart']")));
        addButton.get(addButton.size() - 1).click();
    }


}