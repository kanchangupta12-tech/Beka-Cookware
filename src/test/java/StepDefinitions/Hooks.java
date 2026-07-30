package StepDefinitions;

import Utility.Assertions;
import Utility.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup(){
        Assertions.init();
    }

    @After
    public void tearDown(){
        try{
            Assertions.assertAll();
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
