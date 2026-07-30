package Pages;

import Utility.ConfigReader;
import Utility.DriverFactory;
import Utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class Search {

    WebDriver driver = DriverFactory.getDriver();
    Homepage hp = new Homepage();

    public static final By search = By.xpath("//button[@class='button button--search-trigger | js-modal-trigger']");
    public static final By searchTxt = By.xpath("//input[@id='search']");
    public static final By searchResultsGrid = By.xpath("//div[@class='search-result']");
    public static final By changeLangToDutch = By.xpath("//ul[@id='lang-list']/li/a[@data-value='nl']");
    public static final By changeLangToEng = By.xpath("//ul[@id='lang-list']/li/a[@data-value='en']");
    private static final By langDropdown = By.xpath("//li[@class='top-nav__list-item top-nav__localisation']/form");

    public void searchProduct(String key){
        String lang = hp.getSetLang();
        String searchProduct;
        if (lang.equalsIgnoreCase("Language en")){
             searchProduct = ConfigReader.getValue("en.properties",key);
        } else if (lang.equalsIgnoreCase("Taal nl")){
            searchProduct = ConfigReader.getValue("nl.properties",key);
        } else {
            searchProduct = "";
            Assert.fail("Language not yet supported");
        }
        WaitUtils.waitForElementToBeClickable(driver, search);
        driver.findElement(search).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // no unique property available, hence used actions
        //WaitUtils.waitForElementToBeClickable(driver, driver.findElement(searchTxt));
        //driver.findElement(searchTxt).sendKeys(searchProduct);
        Actions action = new Actions(driver);
        action.sendKeys(searchProduct).perform();
        action.sendKeys(Keys.ENTER).perform();
        //driver.findElement(searchTxt).sendKeys(Keys.ENTER);
    }

    public void validateResults(){
        WaitUtils.waitForElementToBeVisible(driver, searchResultsGrid);
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

    public void changeLang(String lang){
        WaitUtils.waitForElementToBeClickable(driver,langDropdown);
        driver.findElement(langDropdown).click();

        if (lang.equalsIgnoreCase("en")){
            WaitUtils.waitForElementToBeClickable(driver,changeLangToEng);
            driver.findElement(changeLangToEng).click();
            System.out.println("Language changed to english");
        } else if (lang.equalsIgnoreCase("nl")){
            WaitUtils.waitForElementToBeClickable(driver,changeLangToDutch);
            driver.findElement(changeLangToDutch).click();
            System.out.println("Language changed to NL");
        } else {
            System.out.println("Language not supported yet");
        }
    }

}
