package utility;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {



    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement ele){
        getWait(driver).until(ExpectedConditions.visibilityOf(ele));
    }


    public static void waitForElementToBeClickable(WebDriver driver, WebElement ele){
        getWait(driver).until(ExpectedConditions.elementToBeClickable(ele));

    }

    public static void fluentWait(WebDriver driver, WebElement ele){
        FluentWait<WebDriver> flWait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class);
        flWait.until(dri -> ele);
    }

}
