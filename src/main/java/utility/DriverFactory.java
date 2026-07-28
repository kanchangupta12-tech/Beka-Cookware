package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

import static utility.DriverFactoryOrg.tDriver;

public class DriverFactory {

    static WebDriver driver = null;

    public static WebDriver getDriver(String browser) {


        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
                return driver;

            case "edge":
                EdgeOptions edgeoptions = new EdgeOptions();
                driver = new EdgeDriver(edgeoptions);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
                return driver;

            default:
                System.out.println("No browser found");
                return driver;

        }
    }

}
