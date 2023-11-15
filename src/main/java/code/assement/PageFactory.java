package code.assement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The PageFactory class manages the WebDriver instance and provides utility methods
 * for logging and handling assertions.
 *
 * <p>This class ensure a single instance of the WebDriver
 * throughout the test execution.</p>
 */
public class PageFactory {
    private static WebDriver driver;


    // Method will initialize a new Chrome browser
    public static WebDriver getDriver(){
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.webstaurantstore.com/");
            driver.manage().window().fullscreen();
        }
        return driver;
    }

    // Method will create a log file for tracking Assertions
    public static void writeToAssertionErrorsFile(String errorMessage, AssertionError e){
        try (FileWriter writer = new FileWriter("assertion_errors.txt", true)) {
            writer.write(getFormattedTimestamp() + "\n" + e + errorMessage); // adds the time stamp to the error message
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }


    // Helper method to get a formatted timestamp
    private static String getFormattedTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return now.format(formatter);
    }

    // Method will close and quit the browser instance
    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
