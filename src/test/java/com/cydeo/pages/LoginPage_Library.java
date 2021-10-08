package com.cydeo.pages;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Library {
    @FindBy(id ="inputEmail")
    private WebElement userNameBox;
    @FindBy(id="inputPassword")
    private WebElement passwordBox;
    @FindBy(xpath="//button[.='Sign in']")
    private WebElement loginBtn;

    public LoginPage_Library(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public void navigateTo(){
        Driver.getDriver().get(ConfigReader.read("url.library"));
    }

    public void login(String username, String password){

        this.userNameBox.sendKeys(username);
        BrowserUtil.waitFor(2);
        this.passwordBox.sendKeys(password);
        BrowserUtil.waitFor(2);
        this.loginBtn.click();
    }


}
