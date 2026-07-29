package Utility;

import org.testng.asserts.SoftAssert;

public class Assertions {

    static SoftAssert ast = new SoftAssert();

    public static void comparisonCheck(String actual,String expected, String message){
        ast.assertEquals(actual,expected,message);
    }

    public static void booleanCheck(Boolean actual,String message){
        ast.assertTrue(actual,message);
    }


    public static void assertAll(){
        ast.assertAll();
    }

}

