package Pages;

import Utility.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utility.Assertions;
import Utility.DriverFactory;
import Utility.WaitUtils;

import static org.testng.Assert.assertTrue;

public class Homepage {

  WebDriver driver = DriverFactory.getDriver();

    public static String langConfirmation = null;

    private static final By acceptcookies = By.xpath("//button[text()='Cookies accepteren']");
    private static final By Langcontinue = By.xpath("//button[contains(text(),'Continue')]");
    private static final By Headerlogo = By.xpath("//img[@class ='logo-desktop']");
    private static final By Footerlogo = By.xpath("//footer[@class='page-footer']/div/div/div/div/a/img");
    private static final By LabelfindstoreEN = By.xpath("//a[text()='Find a store']");
    private static final By LabelfindstoreNL = By.xpath("//a[text()='Verkooppunten']");
    private static final By languageSelection = By.xpath("//li[@class='top-nav__list-item top-nav__localisation']");
    private static final By langDropdown = By.xpath("//li[@class='top-nav__list-item top-nav__localisation']/form");
    public static final By changeLangToDutch = By.xpath("//ul[@id='lang-list']/li/a[@data-value='nl']");
    public static final By changeLangToEng = By.xpath("//ul[@id='lang-list']/li/a[@data-value='en']");



    public void openApplication()  {
        //Launch URl
        driver.get(ConfigReader.getValue("config.properties","appURL"));

        //Accept cookies
        WebElement eleAcceptCookies = driver.findElement(acceptcookies);
        WaitUtils.waitForElementToBeClickable(driver, eleAcceptCookies);
        eleAcceptCookies.click();

        //Language popup
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement eleLangcontinue = driver.findElement(Langcontinue);
        WaitUtils.waitForElementToBeVisible(driver, eleLangcontinue);
        WaitUtils.waitForElementToBeClickable(driver, eleLangcontinue);
        eleLangcontinue.click();

        String currentLangSelection = driver.findElement(languageSelection).getText();
        currentLangSelection = currentLangSelection.replace("\n", " ").replace("\r", " ").trim();
        System.out.println("language selected - " + currentLangSelection.trim());

        if (currentLangSelection.equalsIgnoreCase("Language en")) {
            System.out.println("Homepage is loaded with Language \"English\"");
            langConfirmation = "ENG";

        } else if (currentLangSelection.equalsIgnoreCase("Language nl")) {
            System.out.println("Homepage is loaded with Language \"Dutch\"");
            langConfirmation = "NL";
        } else {
            System.out.println("Homepage is loaded with different language");
        }
    }

    //validate home page loads correctly
    public void checkHeaderLogo() {
        WebElement eleHeaderlogo = driver.findElement(Headerlogo);
        boolean h_logo = eleHeaderlogo.isDisplayed();
        Assertions.booleanCheck(h_logo,"Header logo is not displayed - "+h_logo);
    }
    public void checkFooterLogo() {
        WebElement eleFooterlogo = driver.findElement(Footerlogo);
        boolean f_logo = eleFooterlogo.isDisplayed();
        Assertions.booleanCheck(f_logo,"Footer logo is not displayed - "+f_logo);
    }

    //validate language switching functionality
    public void switchLanguage() {
        WebElement eleLangDropdown = driver.findElement(langDropdown);
        WaitUtils.waitForElementToBeClickable(driver,eleLangDropdown);
        eleLangDropdown.click();

        if (langConfirmation.equalsIgnoreCase("eng")){
            WebElement eleLangChange = driver.findElement(changeLangToDutch);
            WaitUtils.waitForElementToBeClickable(driver,eleLangChange);
            eleLangChange.click();
            WebElement eleFindAStore = driver.findElement(LabelfindstoreNL);
            WaitUtils.waitForElementToBeVisible(driver,eleFindAStore);
            boolean eleStatus = eleFindAStore.isDisplayed();
            assertTrue(eleStatus,"Language changed to NL -"+eleStatus);
        } else if (langConfirmation.equalsIgnoreCase("nl")){
            WebElement eleLangChange = driver.findElement(changeLangToEng);
            WaitUtils.waitForElementToBeClickable(driver,eleLangChange);
            eleLangChange.click();
            WebElement eleFindAStore = driver.findElement(LabelfindstoreEN);
            WaitUtils.waitForElementToBeVisible(driver,eleFindAStore);
            boolean eleStatus = eleFindAStore.isDisplayed();
            assertTrue(eleStatus,"Language changed to ENG -"+eleStatus);
        }
    }

    public String getSetLang(){
        String currentLang = driver.findElement(languageSelection).getText();
        currentLang = currentLang.replace("\n", " ").replace("\r", " ").trim();
        System.out.println("language selected - " + currentLang.trim());
        currentLang = currentLang.trim();
        return currentLang;
    }

}