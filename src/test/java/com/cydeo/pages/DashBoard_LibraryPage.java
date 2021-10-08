package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoard_LibraryPage {

    @FindBy(id="user_count")
    private WebElement userCountElement;
    @FindBy (id="book_count")
    private WebElement bookCountElement;
    @FindBy (id="borrowed_books")
    private WebElement borrowBookCountElement;


    public DashBoard_LibraryPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public String getUserCountText(){
       return userCountElement.getText();
    }


    public String getBookCountText(){
       return bookCountElement.getText();
    }


    public String getBorrowedCountText(){

        return borrowBookCountElement.getText();
    }


}
