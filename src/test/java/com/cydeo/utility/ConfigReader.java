package com.cydeo.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A Utility class that load the key value pair inside config.properties file
 * into Properties object and provide single method called
 * read to get the value out of the properties file we created.
 */
public class ConfigReader {
    //declare properties object as class level so it can be accessible in static method

    private static Properties properties =new Properties();

    //we want to only load the file once,
    static {

        try {
        FileInputStream in =new FileInputStream("config.properties");
        properties.load(in);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String key){

        return properties.getProperty(key);
    }
}
