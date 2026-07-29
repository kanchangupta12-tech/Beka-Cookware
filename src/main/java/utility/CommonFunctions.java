package Utility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.util.Properties;

public class CommonFunctions {



    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public static JavascriptExecutor getJS() {
        return (JavascriptExecutor) getDriver();
    }

    public static void jsExecutorForScroll(WebElement element){

        getJS().executeScript("arguments[0].scrollIntoView();",element);
    }

    public static void jsExecutorForScrollUsingAxis(int x ,int y){

        getJS().executeScript("window.scrollBy(arguments[0], arguments[1])",x,y);
    }

    public static boolean ElePresent(WebElement ele){
        try{
            return ele.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static Actions getActions() {
        return new Actions(getDriver());
    }

    public  static void action(WebElement element){
        getActions().moveToElement(element).perform();
    }

    public  static void actionOnClick(WebElement element){
        getActions().click(element).perform();
    }

    public  static void actionOnSendKeys(WebElement element,String text){
        getActions().sendKeys(element,text).perform();
    }

    public  static void actionOnKeys(){
        getActions().sendKeys(Keys.ENTER).perform();
    }

    public static void selectOption(WebElement element,String index){
        Select select = new Select(element);
        select.selectByIndex(Integer.parseInt(index));
    }

    public static void moveToElement(WebElement element){
        getActions().moveToElement(element).perform();
    }

}
