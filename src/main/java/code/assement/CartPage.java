package code.assement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Navigates to the cart web page using the provided URL.
     *
     * <p>This method uses the WebDriver to navigate to the specified cart URL.</p>
     *
     * @param cartUrl The URL of the cart web page.
     */
    public void navigateToCartPage(String cartUrl){
        driver.get(cartUrl);
    }

    /**
     * Clicks on the button that initiates the process of emptying the cart.
     *
     * <p>This method uses an explicit wait to locate and click the button that starts the emptying cart process.</p>
     */
    public void clickEmptyCartButton(){
        WebElement emptyCartButton = wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//button[contains(text(),'Empty Cart')]")));
        emptyCartButton.click();
    }

    /**
     * Clicks on the confirmation popup to proceed with emptying the cart.
     *
     * <p>This method locates and clicks the confirmation button in the popup to empty the cart.</p>
     */
    public void confirmEmptyCartDecision(){
        driver.findElement(By.cssSelector("footer button.btn")).click();

    }

    /**
     * Checks if the cart is empty and returns the corresponding text.
     *
     * <p>This method uses an explicit wait to check for the presence of the element indicating an empty cart.
     * It returns the text displayed for an empty cart.</p>
     *
     * @return The text indicating that the cart is empty.
     */
    public String verifyCartIsEmpty(){
        WebElement emptyCartText = wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//p[@class='header-1']")));
        return emptyCartText.getText();
    }



}
