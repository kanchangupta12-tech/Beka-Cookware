package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactoryOrg {

    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", CommonFunctions.getValue("headless")));


        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
//                if(headless){
//                    options.addArguments("--headless=new");
//                    options.addArguments("--no-sandbox");
//                    options.addArguments("--disable-dev-shm-usage");
//                    options.addArguments("--window-size=1920,1080");
//                    System.out.println("HEADLESS DRIVER ACTIVE");
//                    System.out.println(options.asMap());
//                }
                tDriver.set(new ChromeDriver(options));
                break;
            case "Edge":
                tDriver.set(new EdgeDriver());
                break;

            default:
                System.out.println("No browser found");

        }

        return getDriver();
    }

        public static WebDriver getDriver() {
            return tDriver.get();
        }

        public static void quitDriver() {
            tDriver.get().quit();
            tDriver.remove(); // important — prevents memory leaks / stale references
        }
}
