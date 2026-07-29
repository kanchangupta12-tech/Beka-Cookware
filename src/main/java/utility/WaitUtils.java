package Utility;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    static int timeout = Integer.parseInt(ConfigReader.getValue("config.properties","pageLoadTimeout"));

    public static void waitForElementToBeVisible(WebDriver driver, WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

}
