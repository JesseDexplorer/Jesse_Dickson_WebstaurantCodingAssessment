import code.assement.PageFactory;
import code.assement.ShoppingPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MainTest {

    protected String searchQuery = "stainless work table";
    private ShoppingPage shopPage;

    @Before
    public void setUp(){
        WebDriver driver = PageFactory.getDriver();
        shopPage = new ShoppingPage(driver);

    }


    @Test
    public void testCaseOne(){
        shopPage.searchForProduct(searchQuery);
    }

    @After
    public void tearDown(){
        PageFactory.quitDriver();

    }
}
