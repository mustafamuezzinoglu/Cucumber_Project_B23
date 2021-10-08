package com.cydeo.step_definitions;

import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before("@ui")
    public void setUpDriver(){
        System.out.println("This is from @Before inside Hook class");
      // set up implicit wait
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

   @After("@ui")
    public void tearDown(){
       System.out.println("This is from @After inside Hook class");
        Driver.closeBrowser();

    }


}
