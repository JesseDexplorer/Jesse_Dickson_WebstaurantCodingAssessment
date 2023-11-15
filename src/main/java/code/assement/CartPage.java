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

    public void getCartPage(String cartUrl){
        driver.get(cartUrl);
    }

    public void clickEmptyCartButton(){
        WebElement emptyCartButton = wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//button[contains(text(),'Empty Cart')]")));
        emptyCartButton.click();
    }

    public void confirmEmptyCartDecision(){
        driver.findElement(By.cssSelector("footer button.btn")).click();

    }

    public String verifyCartIsEmpty(){
        WebElement emptyCartText = wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//p[@class='header-1']")));
        return emptyCartText.getText();
    }



}
