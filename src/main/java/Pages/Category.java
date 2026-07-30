package Pages;

import Utility.CommonFunctions;
import Utility.ConfigReader;
import Utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utility.DriverFactory;
import org.testng.Assert;

public class Category {


    WebDriver driver = DriverFactory.getDriver();

    private static String mainCat = ConfigReader.getValue("en.properties","mainCat");
    private static String Cat = ConfigReader.getValue("en.properties","cat");
    private static String product = ConfigReader.getValue("en.properties","product");
    private static String mainCatNL = ConfigReader.getValue("nl.properties","mainCat");
    private static String CatNL = ConfigReader.getValue("nl.properties","cat");
    private static String productNL = ConfigReader.getValue("nl.properties","product");
    private static final By mainCategory = By.xpath("//a[contains(text(),'"+mainCat+"')]");

    private static final By product_name = By.xpath("//ul[@class='facets__list']/li/div/label/span[@class='facets__label-text'][contains(text(),'"+product+"')]");
    private static final By cat_name = By.xpath("//li[@class='facets__item']/div/label/span[contains(text(),'"+Cat+"')]");
    private static final By mainCategoryNL = By.xpath("//a[contains(text(),'"+mainCatNL+"')]");
    private static final By product_nameNL = By.xpath("//ul[@class='facets__list']/li/div/label/span[@class='facets__label-text'][contains(text(),'"+productNL+"')]");
    private static final By cat_nameNL = By.xpath("//li[@class='facets__item']/div/label/span[contains(text(),'"+CatNL+"')]");

    private static final By selCategory = By.xpath( "//span[@class='facets__label-text']");
    private static final By totalCount = By.xpath( "//span[@class='facets__label-text']/following-sibling::span[1]");
    private static final By countInGridPage = By.xpath( "//span[@id='ProductCountDesktop']");
    private static final By rejectPopUp = By.xpath(  "//div[@data-testid='modal-form-container']/button[@tabindex='0']");
    private static final By subCatFilter = By.xpath("//a[@class='button--filter']");
    private static final By resetFilter = By.xpath("//a[@class='active-facets__button-remove' and @role='button']");

    private static final By subCat = By.id("Facet-2-template--24904267661688__main");
    private static final By subCatSel = By.xpath("//label[@for='Filter-Subcategory-4']/span[@class='facets__label-text']");
    private static final By subCatLabel = By.xpath("//details[@id='Details-2-template--24904267661688__main']");

    public void chooseMainCat(String lang){
        if(lang.equalsIgnoreCase("en")) {
            WaitUtils.waitForElementToBeClickable(driver, mainCategory);
            driver.findElement(mainCategory).click();
        }else if (lang.equalsIgnoreCase("nl")) {
            WaitUtils.waitForElementToBeClickable(driver, mainCategoryNL);
            driver.findElement(mainCategoryNL).click();
        } else {
            System.out.println("Cannot find the category or language is not correctly set");
        }
    }

    public void chooseCategory(String lang){
        String cnt = validateProductGridCount();
        if(lang.equalsIgnoreCase("en")) {
            WaitUtils.waitForElementToBeClickable(driver, cat_name);
            CommonFunctions.moveToElement(driver.findElement(cat_name));
            driver.findElement(cat_name).click();
        }else if (lang.equalsIgnoreCase("nl")) {
            WaitUtils.waitForElementToBeClickable(driver, cat_nameNL);
            CommonFunctions.moveToElement(driver.findElement(cat_nameNL));
            driver.findElement(cat_nameNL).click();
        } else {
            System.out.println("Cannot find the category or language is not correctly set");
        }
        String newCnt = validateProductGridCount();
        Assert.assertTrue(Integer.parseInt(cnt) >= Integer.parseInt(newCnt),"Category Filter is not behaving as expected");
    }

    public void chooseProduct(String lang){
        String cnt = validateProductGridCount();
        WaitUtils.waitForElementToBeVisible(driver, subCatLabel);
        CommonFunctions.jsExecutorForScroll(driver.findElement(subCatLabel));
        CommonFunctions.moveToElement(driver.findElement(subCatLabel));

        if (lang.equalsIgnoreCase("en")) {
            CommonFunctions.jsExecutorForScroll(driver.findElement(subCat));
            WaitUtils.waitForElementToBeClickable(driver, subCatSel);
            driver.findElement(subCatSel).click();
        }else if (lang.equalsIgnoreCase("nl")) {
            CommonFunctions.jsExecutorForScroll(driver.findElement(subCat));
            WaitUtils.waitForElementToBeClickable(driver, subCatSel);
            driver.findElement(subCatSel).click();
        } else {
            System.out.println("Cannot find the category or language is not correctly set");
        }
        String newCnt = validateProductGridCount();
          Assert.assertTrue(Integer.parseInt(cnt) >= Integer.parseInt(newCnt), "Sub Category Filter is not behaving as expected");

   }

    public String validateProductGridCount(){

        WaitUtils.waitForElementToBeClickable(driver, countInGridPage);
        String total = driver.findElement(countInGridPage).getText().trim();
        String[] arrTotal = total.split(" ");
        //return arrTotal[0];
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\d+").matcher(total);
        return m.find() ? m.group() : "0";

    }
}
