package com.cydeo.pages;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.Driver;
import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WLoginPage {

    @FindBy(id ="ctl00_MainContent_username" )
    public WebElement usernameField ;

    @FindBy(name = "ctl00$MainContent$password")
    public WebElement passwordField ;

    @FindBy(css = "#ctl00_MainContent_login_button")
    public WebElement loginButton ;

    @FindBy(id= "ctl00_MainContent_status")
    public WebElement errorMsg ;

    public WLoginPage(){
        PageFactory.initElements(Driver.getDriver() , this);
        //this calling instances of current class
        //this() calling constructor

        //this: to call instance variables & instance methods inside current class
        //this(): used for calling the constructor
    }


    public void goTo(){
        Driver.getDriver().navigate().to(ConfigReader.read("weborder_url") );
    }


    public void login(String username, String password){
        //or you can access by using usernameField
       this.usernameField.sendKeys(username);
       this.passwordField.sendKeys(password);
       this.loginButton.click();

    }

    public boolean loginErrorMsg(){

        return this.errorMsg.isDisplayed();
    }


}
