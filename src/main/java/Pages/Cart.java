package Pages;

import Utility.CommonFunctions;
import Utility.ConfigReader;
import Utility.DriverFactory;
import Utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Cart {

    WebDriver driver = DriverFactory.getDriver();
    Homepage hp = new Homepage();
    public static final By cart = By.xpath("//li[@class='aside-nav__list-item']/a[@data-modal-id='cart-modal']");
    public static final By emptyCartMsg = By.xpath("//div[@id='cart-container']/p");
    public static final By continueShoppingbtn = By.xpath("//button[@class='button js-modal-close']");
    public static final By homepageValidation = By.id("main");
    public static final By addToCartbtn = By.xpath("//button[@class='button button--add-to-cart js-add-to-cart js-add-to-cart-main ']");
    public static final By cartCounter = By.xpath("//input[@class='input quantity-select__input']");
    public static final By pricing = By.xpath("//span[@class='ajaxcart__price']");
    public static final By cartIncrement = By.xpath("//button[@class='quantity-select__button'][@data-increment='1']");

    public void clickCartOption(){
        WaitUtils.waitForElementToBeClickable(driver, cart);
        CommonFunctions.moveToElement(driver.findElement(cart));
        driver.findElement(cart).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkCartMessage(){
        WaitUtils.waitForElementToBeClickable(driver,emptyCartMsg);
        String cartMsg = driver.findElement(emptyCartMsg).getText();
        String lang = hp.getSetLang();
        if (lang.equalsIgnoreCase("Language en")){
            String expCartMsg = ConfigReader.getValue("en.properties","cartMsg");
            Assert.assertTrue(cartMsg.contains(expCartMsg), "empty card message is not as expected - "+cartMsg);
        } else if (lang.equalsIgnoreCase("Taal nl")){
            String expCartMsg = ConfigReader.getValue("nl.properties","cartMsg");
            Assert.assertTrue(cartMsg.contains(expCartMsg), "empty card message is not as expected - "+cartMsg);
        } else {
            Assert.fail("Language not yet supported");
        }

    }

    public void continueShopping(){
        driver.findElement(continueShoppingbtn).click();
    }

    public void validateHomePageIsDisplayed(){
        WaitUtils.waitForElementToBeVisible(driver, homepageValidation);
        Assert.assertTrue(driver.findElement(homepageValidation).isDisplayed(), "Homepage not displayed");
    }

    public void addToCart(){
        WaitUtils.waitForElementToBeClickable(driver, addToCartbtn);
        CommonFunctions.jsExecutorForScroll(driver.findElement(addToCartbtn));
        CommonFunctions.moveToElement(driver.findElement(addToCartbtn));
        driver.findElement(addToCartbtn).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateCartCounter(String expCnt){
        WaitUtils.waitForElementToBeClickable(driver, cartCounter);
        String cnt = driver.findElement(cartCounter).getAttribute("value");
        Assert.assertEquals(cnt, expCnt, "cart counter is not as expected");
    }

    public void validatePriceFormat(){
        WaitUtils.waitForElementToBeClickable(driver, pricing);
        String price = driver.findElement(pricing).getText();
        boolean isValidFormat = price.matches("^€\\d+,\\d{2}$");
        Assert.assertTrue(isValidFormat, "\"Price format is invalid! Expected €XX,XX but got: " + price);
    }

    public void updateCartCounter(String cnt){
        WaitUtils.waitForElementToBeClickable(driver, cartCounter);
        driver.findElement(cartCounter).clear();
        driver.findElement(cartCounter).sendKeys(cnt);
        driver.findElement(pricing).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void incrementCart(){
        WaitUtils.waitForElementToBeClickable(driver, cartIncrement);
        driver.findElement(cartIncrement).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateRecalculatePrice(){
        WaitUtils.waitForElementToBeClickable(driver, pricing);
        String price = driver.findElement(pricing).getText();
        price = price.replaceAll("[€\\s]", "");
        price = price.replace(".", "").replace(",", ".");
        incrementCart();
        WaitUtils.waitForElementToBeClickable(driver, driver.findElement(pricing));
        String newPrice = driver.findElement(pricing).getText();
        newPrice = newPrice.replaceAll("[€\\s]", "");
        newPrice = newPrice.replace(".", "").replace(",", ".");
        Double intPrice = Double.parseDouble(price);
        Double intNewPrice = Double.parseDouble(newPrice);
        Assert.assertTrue(intPrice < intNewPrice,"Pricing is not recalculated correctly: "+price+" , "+newPrice);

    }

    public Double fetchPrice(){
        WaitUtils.waitForElementToBeClickable(driver, pricing);
        String price = driver.findElement(pricing).getText();
        price = price.replaceAll("[€\\s]", "");
        price = price.replace(".", "").replace(",", ".");
        return Double.parseDouble(price);
    }

    public void checkEmptyCartMessage(String lang){
        WaitUtils.waitForElementToBeClickable(driver, emptyCartMsg);
        String cartMsg = driver.findElement(emptyCartMsg).getText();
        if (lang.equalsIgnoreCase("en")){
            String expCartMsg = ConfigReader.getValue("en.properties","cartMsg");
            Assert.assertTrue(cartMsg.contains(expCartMsg), "empty card message is not as expected - "+cartMsg);
        } else if (lang.equalsIgnoreCase("nl")){
            String expCartMsg = ConfigReader.getValue("nl.properties","cartMsg");
            Assert.assertTrue(cartMsg.contains(expCartMsg), "empty card message is not as expected - "+cartMsg);
        } else {
            Assert.fail("Language not yet supported");
        }

    }
}
