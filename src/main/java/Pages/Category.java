package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.DriverFactory;

public class Category {


    WebDriver driver = DriverFactory.getDriver("chrome");
    private static String mainCat = Utility.getValue("mainCat");
    private static String product = Utility.getValue("product");

    private static final By mainCategory = By.xpath("//a[contains(text(),'"+mainCat+"')]");
    private static final By product_name = By.xpath("//img[@alt='"+product+"']");
    private static final By selCategory = By.xpath( "//span[@class='facets__label-text']");
    private static final By totalCount = By.xpath( "//span[@class='facets__label-text']/following-sibling::span[1]");
    private static final By countInGridPage = By.xpath( "//span[@id='ProductCountDesktop']");
    private static final By rejectPopUp = By.xpath(  "//div[@data-testid='modal-form-container']/button[@tabindex='0']");
    private static final By subCatFilter = By.xpath("//a[@class='button--filter']");
    private static final By resetFilter = By.xpath("//a[@class='active-facets__button-remove' and @role='button']");
}
