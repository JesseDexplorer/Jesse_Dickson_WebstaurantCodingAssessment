package code.assement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShoppingPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public ShoppingPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Method to check for search results
    public void searchForProduct(String query){
        //wait for 10 seconds unless the search loads and is clickable
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchval")));
        searchBox.click();
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    public List<WebElement> getSearchResult(){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//span[@data-testid='itemDescription']")));
    }

}