package com.cydeo.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtil {
    /**
     * A method to pause the thread certain seconds
     * @param seconds
     */
    public static void waitFor( int seconds){
        try {
            Thread.sleep(seconds *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     *   WebDriverWait wait =new WebDriverWait(Driver.getDriver(),2);
     *         //check of visibility pf the errorMsg in 2 seconds
     *         try {
     *             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='blablactl00_MainContent_status']")));
     *         }catch (TimeoutException e){
     *             //System.out.println("Error message is "+e.getMessage());
     *             e.printStackTrace();
     *         }
     */

    /**
     * This method will check for visibility of element within the time given
     * @param locator By.id or By.xpath or By.whatever
     * @param second time to wait
     * @return true if the element is found within the time and visible , false if not
     */
    public static boolean checkVisibilityOfElement(By locator, int second){
       boolean result=false;
        WebDriverWait wait =new WebDriverWait(Driver.getDriver(),second);
             //check of visibility pf the errorMsg in 2 seconds
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            result=true;
        }catch (TimeoutException e){
            //System.out.println("Error message is "+e.getMessage());
                // e.printStackTrace();
            System.out.println("WE DID NOT SEE THE ERROR MESSAGE ELEMENT");
        }

        return result;

    }



}
