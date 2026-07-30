package Pages;

import Utility.CommonFunctions;
import Utility.ConfigReader;
import Utility.DriverFactory;
import Utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static Pages.Homepage.langConfirmation;

public class Product {

    WebDriver driver = DriverFactory.getDriver();
    private static String prdName = ConfigReader.getValue("en.properties","productName");
    private static String prdTitleInDutch = ConfigReader.getValue("nl.properties","productName");
    private static final By product = By.xpath("//a[@class='product-item__url'][@aria-label='"+ prdName +"']");
    private static final By productNL = By.xpath("//a[@class='product-item__url'][@aria-label='"+ prdTitleInDutch +"']");
    private static final By productImage = By.xpath("//div[@data-index='0']/div/picture/img");
    private static final By productTitle = By.xpath("//h1[@class='product__title']");
    private static final By productPrice = By.xpath("//span[@class='js-product-price']");
    private static final By productDesc = By.xpath("//div[@class='product__center']");
    private static final By productDescInDetail = By.xpath("//div[@class='rte accordion__content accordion__content--visible']");
    private static final By productTitleInD = By.xpath("//a[@aria-label='"+ prdTitleInDutch +"']");
    private static final By productPopUp = By.xpath("//div[@data-testid='modal-form-container']/button");
    private static final By imageSlide = By.xpath("//button[@class='swiper-next swiper-button js-slider-product--next']");
    private static final By scrollBestSeller = By.id("shopify-section-template--24904268349816__products_preview_Xde8gC");

    Homepage hp = new Homepage();

    public void moveToProduct() {
        String lang = hp.getSetLang();
        if (lang.equalsIgnoreCase("Language en")){
            WaitUtils.waitForElementToBeVisible(driver,scrollBestSeller);
            CommonFunctions.jsExecutorForScroll(driver.findElement(scrollBestSeller));
            CommonFunctions.moveToElement(driver.findElement(scrollBestSeller));
            WaitUtils.waitForElementToBeClickable(driver, product);
            driver.findElement(product).click();
        } else if (lang.equalsIgnoreCase("Taal nl")){
            WaitUtils.waitForElementToBeVisible(driver,scrollBestSeller);
            CommonFunctions.jsExecutorForScroll(driver.findElement(scrollBestSeller));
            CommonFunctions.moveToElement(driver.findElement(scrollBestSeller));
            WaitUtils.waitForElementToBeClickable(driver, productNL);
            driver.findElement(productNL).click();
        } else {
            Assert.fail("Language not yet supported");
        }


    }

    public boolean productImage() {
        return CommonFunctions.ElePresent(driver.findElement(productImage));
    }

    public void checkProductTitle(String expectedTitle) {
        String title = driver.findElement(productTitle).getText();
        Assert.assertTrue(title.equalsIgnoreCase(expectedTitle),"Product tile is not displayed as expected - " + title);
    }

    public String checkPrice() {
        String Price = driver.findElement(productPrice).getText();
        return Price.trim();
    }

    public void checkDescription(String expectedDesc) {
        //CommonFunctions.jsExecutorForScroll(driver.findElement(productDesc));
        WaitUtils.waitForElementToBeClickable(driver, productDesc);
        //driver.findElement(productDesc).click();
        CommonFunctions.jsExecutorForScroll(driver.findElement(productDesc));
        String detailedDesc = driver.findElement(productDesc).getText();
        detailedDesc = detailedDesc.trim();
        Assert.assertTrue(detailedDesc.contains(expectedDesc),"Product description is not displayed as expected - " + expectedDesc);
    }


    public String checkPriceInNLlang(){
        String Price = driver.findElement(productPrice).getText();
        return Price.trim();
    }

    public void switchGalleryImage(){
        WaitUtils.waitForElementToBeClickable(driver, imageSlide);
        driver.findElement(imageSlide).click();
        Assert.assertTrue(productImage(),"Image gallery switch is not working as expected");
    }
}
