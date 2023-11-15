import code.assement.PageFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MainTest {
    @Before
    public void setUp(){
        WebDriver driver = PageFactory.getDriver();

    }


    @Test
    public void testCaseOne(){
        System.out.println("set up");
    }

    @After
    public void tearDown(){
        PageFactory.quitDriver();

    }
}
