package Utility;

import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    public static String getValue(String fileName, String key) {
        Properties prop = new Properties();
        FileReader f = null;
        try {
            f =new FileReader(fileName);
            prop.load(f);
        }catch(Exception e){
            System.out.println("No File found");
        }

        return prop.getProperty(key);
    }

}
