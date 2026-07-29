package Pages;

import Utility.ConfigReader;
import Utility.DriverFactory;
import Utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

public class Search {

    WebDriver driver = DriverFactory.getDriver();
    Homepage hp = new Homepage();

    public static final By search = By.xpath("//button[@class='button button--search-trigger | js-modal-trigger']");
    public static final By searchTxt = By.id("search");
    public static final By searchResultsGrid = By.xpath("//div[@class='search-result']");

    public void searchProduct(String key){
        String lang = hp.getSetLang();
        String searchProduct;
        if (lang.equalsIgnoreCase("Language en")){
             searchProduct = ConfigReader.getValue("en.properties",key);
        } else if (lang.equalsIgnoreCase("Taal nl")){
            searchProduct = ConfigReader.getValue("nl.properties",key);
        } else {
            Assert.fail("Language not yet supported");
            searchProduct = "";
        }
        WaitUtils.waitForElementToBeClickable(driver, driver.findElement(search));
        driver.findElement(search).click();
        WaitUtils.waitForElementToBeClickable(driver, driver.findElement(searchTxt));
        driver.findElement(searchTxt).sendKeys(searchProduct);
        driver.findElement(searchTxt).sendKeys(Keys.ENTER);
    }

    public void validateResults(){
        WaitUtils.waitForElementToBeVisible(driver, driver.findElement(searchResultsGrid));
        List<WebElement> ele = driver.findElements(searchResultsGrid);
        int totalCnt = ele.size();
        if (totalCnt > 0) {
            System.out.println("Valid results are generated for valid search - " + totalCnt);
        } else {
            System.out.println("Valid results are not generated for valid search - "+ totalCnt);
        }
    }

    public void validateNoResults(){
        String title = driver.getTitle();
        if(title.contains("0 results")) {
            System.out.println("Expected result displayed for invalid search");
        } else if (title.contains("0 resultaten")) {
            System.out.println("Expected result displayed for invalid search");
        } else {
           Assert.fail("Expected results not displayed or language not supported yet");

        }
    }

}
