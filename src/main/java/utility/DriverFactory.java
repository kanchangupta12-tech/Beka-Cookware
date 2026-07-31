package Utility;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory(){
    }

    public static WebDriver getDriver(){
        if (DRIVER.get()==null){
            DRIVER.set(createDriver());
        }
        return DRIVER.get();
    }

    private static WebDriver createDriver() {
        String browser = ConfigReader.getValue("config.properties","browser");
        int timeout = Integer.parseInt(ConfigReader.getValue("config.properties","pageLoadTimeout"));
        WebDriver driver;
        String headless = ConfigReader.getValue("config.properties","headless");

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless.equalsIgnoreCase("true")){
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                    System.out.println("HEADLESS DRIVER ACTIVE");
                }
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
                return driver;

            case "edge":
                EdgeOptions edgeoptions = new EdgeOptions();
                if (headless.equalsIgnoreCase("true")){
                    edgeoptions.addArguments("--headless=new");
                    edgeoptions.addArguments("--window-size=1920,1080");
                    edgeoptions.addArguments("--no-sandbox");
                    edgeoptions.addArguments("--disable-dev-shm-usage");
                    System.out.println("HEADLESS DRIVER ACTIVE");
                }
                driver = new EdgeDriver(edgeoptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
                return driver;

            default:
                throw new IllegalArgumentException("Unsupported browser - "+browser);
        }

    }

    public static void quitDriver(){
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

}
