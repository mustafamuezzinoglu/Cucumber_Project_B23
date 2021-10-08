package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WOrder_Page {
   @FindBy(xpath = "//h2[normalize-space(.)-'Order']")
    public WebElement header;

   @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productDropdown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityBox;

    @FindBy(id = "id=ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement pricePerUnit;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
    public WebElement discountBox;

    @FindBy(id="ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement totalBox;

    @FindBy(css = "input[type='submit'][value='Calculate']")
    public WebElement calculateBtn;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameField;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName2")
    public WebElement streetField;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName3")
    public WebElement cityField;

    @FindBy(id="ctl00_MainContent_fmwOrder_txtName4")
    public WebElement stateField;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName5")
    public WebElement zipField;

    @FindBy(id="ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement cardRadioBox;

    @FindBy(id="ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber ;

    @FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement cardDate ;

    @FindBy(id="ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton ;

    @FindBy(xpath =" //strong[normalize-space(.)='New order has been successfully added.']")
    public WebElement successMessage ;

    public WOrder_Page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }




}
