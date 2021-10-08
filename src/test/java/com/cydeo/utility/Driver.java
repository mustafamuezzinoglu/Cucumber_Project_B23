package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Create a Singleton class called Driver
 *
 * 1. create private no arg constructor
 *
 *         2. create private static field with name obj
 *         Data type of variable should be WebDriver
 *
 *         3. create public static method
 *         name : getDriver()
 *         return type :WebDriver
 *         param : none
 *
 *         check if obj is null or not
 *         if yes - create ChromeDriver with all set up
 *         if no  -- return same obj
 */

public class Driver {

     private static WebDriver obj;


    private Driver(){

    }

    /**
     * Return obj with only one WebDriver instance
     * @return
     */

     public static WebDriver getDriver(){
         //read the browser type you want to launch from properties file
         String browserName=ConfigReader.read("browser");

         if(obj==null) {
             switch (browserName.toLowerCase().trim()) {
                 case "chrome":
                     WebDriverManager.chromedriver().setup();
                     obj = new ChromeDriver();
                     break;

                 case "firefox":
                     WebDriverManager.firefoxdriver().setup();
                     obj = new FirefoxDriver();
                     break;
                 case"safari":
                     WebDriverManager.safaridriver().setup();
                     obj=new SafariDriver();
                     break;
                 default:
                     obj = null;
                     //System.out.println("Unknown browser type! " + browserName);
             }

             return obj;


         }else {
            // System.out.println("You have it just use existing one");
             return obj;
         }





     }


    /**
     * Quitting the browser and setting the value of
     * WebDriver instance to null because you can re-use already quitted driver
     */
    public static void closeBrowser(){
         //check if we have WebDriver instance or not
         //basically checking if obj is null or not
         //if not null
             //quit the browser
             //make it null, because once quit it can not be used

         if(obj!=null){
             obj.quit();
             obj = null;
         }
     }
}
