package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * We need a utility class with method
 * to get WebDriver object with all the settings needed
 * by passing browserName
 *
 * WebDriverFactory.get("chrome")==>> WebDriver object with Chrome Driver
 * WebDriverFactory.get("firefox") ==>> WebDriver object with FireFox Driver
 */
public class WebDriverFactory {

    public static WebDriver getDriver(String browserName){
        //it should be static as we don't need to create object from it

        WebDriver driver;

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
             driver =new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            driver=new SafariDriver();
        }else {//any other driver
            driver=null;
        }
       /*
        switch(browserName.toLowerCase().trim()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver =new ChromeDriver();
               break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver =new FirefoxDriver();
               break;
            default:
                driver=null;
                System.out.println("Unknown browser type! "+browserName);
        }

        */

        driver.manage().window().maximize();

        return driver;
    }
}
