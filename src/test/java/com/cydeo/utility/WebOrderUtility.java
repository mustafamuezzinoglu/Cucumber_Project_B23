package com.cydeo.utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


// THIS AIN'T NO TEST CLASS SO WE CAN NOT EXTEND TESTBASE
// IT SIMPLY DOES NOT MAKE SENSE
public class WebOrderUtility {

    public static void openWebOrderApp(){
        Driver.getDriver().get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
       // Driver.getDriver().get(ConfigReader.read("weborder_url") );

    }

    public static void verifyOpenWebOrderApp() {

        openWebOrderApp();
        BrowserUtil.waitFor(3);
        assertTrue(Driver.getDriver().getTitle().equals("Web Orders Login"));
        System.out.println("Current web page title is: " + Driver.getDriver().getTitle());

    }

    public static void login(){
        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
       //WebDriver driver = WebDriverFactory.getDriver("chrome");
        //enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        //enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        //click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }
    //Happy Scenario>> credentials already included


    public static void verifyLogin() {
        //login();
        WebElement loginInfo = Driver.getDriver().findElement(By.xpath("//tbody/tr/td[@width='78%']/div[@class='login_info']"));
        assertTrue(loginInfo.isDisplayed());
        BrowserUtil.waitFor(2);
        System.out.println("Login Info : " + loginInfo.getText());

    }


    public static void login( String username, String password){
        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
       // WebDriver driver = WebDriverFactory.getDriver("chrome");
        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        //enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
        //click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

        //WebElement invalidMsg=Driver.getDriver().findElement(By.id("ctl00_MainContent_status"));

    }
    //Negative scenario >> any credential can be entered, BUT only correct one will be accepted


    /**
     * Check for login error message is visible or not , by calling the BrowserUtil method we created
     * @return true if error message displayed , false if not
     */

    public static boolean loginErrorMsgVisible(){

        boolean elementFound =
                BrowserUtil.checkVisibilityOfElement(By.xpath("//span[. ='Invalid Login or Password.']"),2);
        return elementFound ;
    }

    public static void logout(){
        // logout link has id of ctl00_logout
        Driver.getDriver().findElement(By.id("ctl00_logout")).click();
    }

    public static boolean isAtOrderPage(){

        boolean result=false;
        //h2[normalize-space(.)='List of All Orders'] >>> for the element of the header
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),2);
        try{
            wait.until(visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']") ) );
           // wait.until(ExpectedConditions.urlToBe("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Default.aspx"));
            System.out.println("At the right page");
            result=true;
        }catch (TimeoutException e){
            System.out.println("No Such element! You are not at the right page");
        }
       // System.out.println("header.isDisplayed() = " + header.isDisplayed());
        return result;
    }



    public static boolean verifyUserName(String username){
       //div[@class='login_info']
        boolean result=false;
        WebElement welcomeMsg=Driver.getDriver().findElement(By.xpath("//div[@class='login_info']"));
         if(welcomeMsg.getText().contains(username) ){
             result=true;
         }
        //assertTrue(welcomeMsg.getText().contains(username));

        return result;
    }


    public static String getUserNameFromWelcomeMessage(){
        WebElement loginInfoArea=Driver.getDriver().findElement(By.cssSelector("div.login_info")) ;
        String welcomeMsg=loginInfoArea.getText();
        // Welcome, Tester! | Logout
        return welcomeMsg.replace("Welcome, ","").replace("! | Logout","");
        //we are replacing empty "" unnecessary words and getting the name of the welcome message
    }


    public static void selectSideBarTab(String tabName){
        WebElement sideBar1=Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[1]/a"));
        WebElement sideBar2=Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[2]/a"));
        WebElement sideBar3=Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[3]/a"));

        if(tabName.equalsIgnoreCase(sideBar1.getText())){
            BrowserUtil.waitFor(2);
            sideBar1.click();
        }else if(tabName.equalsIgnoreCase(sideBar2.getText())){
            BrowserUtil.waitFor(2);
            sideBar2.click();
        }else if(tabName.equalsIgnoreCase(sideBar3.getText() ) ) {
            BrowserUtil.waitFor(2);
            sideBar3.click();
        }else{
            throw new RuntimeException("There are only 3 options to click, user clicked Invalid tabName: "+tabName);
        }
        BrowserUtil.waitFor(2);
    }

    public static boolean checkAll(){
        boolean result=false;

        WebElement checkAllBtn=Driver.getDriver().findElement(By.xpath("//a[@id='ctl00_MainContent_btnCheckAll']"));
        checkAllBtn.click();
        BrowserUtil.waitFor(4);
        //input[id^='ctl00_MainContent_orderGrid'] >>>Css.selector (StartsWith)
        //input[id$='OrderSelector'] >>>Css.selector (EndsWith)
        List<WebElement> allCheckCheckBoxes =Driver.getDriver().findElements(By.xpath("//tbody/tr/td/input[@type='checkbox']"));

        for(WebElement eachCheckBox : allCheckCheckBoxes){
            if (eachCheckBox.isSelected()){
                result=true;
            }
        }

        return result;


    }

    public static boolean uncheckAll(){
        boolean result=false;

        WebElement uncheckBtn=Driver.getDriver().findElement(By.xpath("//p/a[@id='ctl00_MainContent_btnUncheckAll']"));
        uncheckBtn.click();
        BrowserUtil.waitFor(4);
        List<WebElement> allUncheckCheckBoxes =Driver.getDriver().findElements(By.xpath("//tbody/tr/td/input[@type='checkbox']"));

        for(WebElement eachCheckBox : allUncheckCheckBoxes){
            if (!eachCheckBox.isSelected()){
                result=true;
            }
        }

        return result;

    }

    public static List<String> getAllProducts(){
        //tbody/tr/td[1]
        //tbody/tr/td[@width='78%']//tr/td[1]

        BrowserUtil.waitFor(3);

        WebElement sideBar2=Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[2]/a"));
        sideBar2.click();
        BrowserUtil.waitFor(2);

       List<WebElement> allProductNames=Driver.getDriver().findElements(By.xpath("//tbody/tr/td[@width='78%']//tr/td[1]"));
                                                                                 //table[@class='ProductsTable']//tr/td[1]

       List<String> allProducts=new ArrayList<>();

       for(WebElement eachProductName: allProductNames){
           allProducts.add(eachProductName.getText());
           System.out.println(eachProductName.getText());
       }

       return allProducts;

    }



    public static int getUnitPriceFromForm(String productName){
        int result=0;
        //li/a[text()='Order'] >>Orders
        //li//select[1]>> DropDown
        //value="MyMoney >>1 option
        //FamilyAlbum >> visible text
        //ScreenSaver >> screensaver
        //#ctl00_MainContent_fmwOrder_txtUnitPrice >>Price
        //li/input[@class='btn_dark'] >>Calculate

        WebElement orderBtn=Driver.getDriver().findElement(By.xpath("//li/a[text()='Order']"));
        orderBtn.click();

        BrowserUtil.waitFor(3);

        WebElement dropDownBar=Driver.getDriver().findElement(By.xpath("//li//select[1]"));
        dropDownBar.click();
        //li/select/option >> 3 of them
        WebElement calculateBtn=Driver.getDriver().findElement(By.xpath("//li/input[@class='btn_dark']"));
        WebElement unitPrice=Driver.getDriver().findElement(By.xpath("//li/input[@id='ctl00_MainContent_fmwOrder_txtUnitPrice']"));
        Select selectOption=new Select(dropDownBar);

        if(productName.equalsIgnoreCase("MyMoney")){
            BrowserUtil.waitFor(2);
            selectOption.selectByVisibleText("MyMoney");
            BrowserUtil.waitFor(2);
            calculateBtn.click();
            BrowserUtil.waitFor(2);
            result = Integer.parseInt(unitPrice.getAttribute("value"));
            System.out.println("Price per unit of the MyMoney: " + Integer.parseInt(unitPrice.getAttribute("value")));
        }else if(productName.equalsIgnoreCase("FamilyAlbum")){
            BrowserUtil.waitFor(2);
            selectOption.selectByVisibleText("FamilyAlbum");
            BrowserUtil.waitFor(2);
            calculateBtn.click();
            BrowserUtil.waitFor(2);
           result = Integer.parseInt(unitPrice.getAttribute("value"));
            System.out.println("Price per unit of the FamilyAlbum: " + Integer.parseInt(unitPrice.getAttribute("value")));
        }else if(productName.equalsIgnoreCase("ScreenSaver")){
            BrowserUtil.waitFor(2);
            selectOption.selectByVisibleText("ScreenSaver");
            BrowserUtil.waitFor(2);
            calculateBtn.click();
            BrowserUtil.waitFor(2);
            result = Integer.parseInt(unitPrice.getAttribute("value"));
            System.out.println("Price per unit of the ScreenSaver: " + Integer.parseInt(unitPrice.getAttribute("value")));
        }else {

            throw new RuntimeException("Wrong option selected, no such option is existed");
        }

         return result;

        }


    public static int  getDiscountFromForm(String productName,int quantity) {

        selectSideBarTab("Order");
        WebElement multiDropdownElm = Driver.getDriver().findElement(By.tagName("select"));//li/select/options
        Select multiSelectObj = new Select(multiDropdownElm);

        WebElement quantities = Driver.getDriver().findElement((By.xpath("//li/input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")));
        WebElement calculateBtn = Driver.getDriver().findElement(By.xpath("//li/input[@class='btn_dark']"));
        // WebElement calculateBtn = Driver.getDriver().findElement(By.xpath("//input[@value = 'Calculate']"));
        WebElement discountBox = Driver.getDriver().findElement((By.xpath(" //li/input[@id='ctl00_MainContent_fmwOrder_txtDiscount']")));
        //li/input[@id='ctl00_MainContent_fmwOrder_txtDiscount']

        int result = 0;
        if (productName.equalsIgnoreCase("MyMoney")) {
            multiSelectObj.selectByVisibleText("MyMoney");
            BrowserUtil.waitFor(3);
            if (quantity >= 10) {
                BrowserUtil.waitFor(3);
                Actions actions = new Actions(Driver.getDriver());
                actions.click(quantities).pause(2000).sendKeys(Keys.BACK_SPACE).perform();
                BrowserUtil.waitFor(2);
                quantities.sendKeys("" + quantity);
                BrowserUtil.waitFor(3);
                quantities.click();
                BrowserUtil.waitFor(3);
                calculateBtn.click();
                BrowserUtil.waitFor(3);
                //String valueOfDiscount = discountBox.getAttribute("value");
                System.out.println(result = Integer.parseInt(discountBox.getAttribute("value")));
                BrowserUtil.waitFor(3);
                System.out.println("Discount of MyMoney %" + result);

            }
        } else if (productName.equalsIgnoreCase("FamilyAlbum")) {
            multiSelectObj.selectByVisibleText("FamilyAlbum");
            quantities.click();
            quantities.sendKeys("" + quantity);
            if (quantity >= 10) {
                quantities.click();
                calculateBtn.click();
                result = Integer.parseInt(discountBox.getAttribute("value"));
                System.out.println("Discount of FamilyAlbum %" + result);
            } else {
                throw new RuntimeException("Discount apply only if quantity is 10+");
            }


        } else if (productName.equalsIgnoreCase("ScreenSaver")) {
            multiSelectObj.selectByVisibleText("ScreenSaver");
            quantities.click();
            quantities.sendKeys("" + quantity);
            if (quantity >= 10) {
                quantities.click();
                calculateBtn.click();
                result = Integer.parseInt(discountBox.getAttribute("value"));
                System.out.println("Discount of ScreenSaver %" + result);
            } else {
                throw new RuntimeException("Discount apply only if quantity is 10+");
            }

        } else {
            throw new RuntimeException("Wrong option selected, no such option is existed");
        }



        BrowserUtil.waitFor(3);
        return result;
    }



    public static int calculateTotal(String productName,int quantity){
        int result=0;
        selectSideBarTab("Order");
        WebElement multiDropdownElm = Driver.getDriver().findElement(By.tagName("select"));//li/select/options
        multiDropdownElm.click();
        Select multiSelectObj = new Select(multiDropdownElm);
        BrowserUtil.waitFor(2);
        multiSelectObj.selectByVisibleText(productName);
        BrowserUtil.waitFor(2);

        WebElement quantities = Driver.getDriver().findElement((By.xpath("//li/input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")));
        Actions actions=new Actions(Driver.getDriver());
        actions.click(quantities).pause(2000).sendKeys(Keys.BACK_SPACE).perform();
        BrowserUtil.waitFor(2);
        quantities.sendKeys(""+quantity);
        WebElement calculateBtn = Driver.getDriver().findElement(By.xpath("//li/input[@class='btn_dark']"));
        calculateBtn.click();

        BrowserUtil.waitFor(3);
        WebElement totalPrice=Driver.getDriver().findElement(By.xpath("//li/input[@id='ctl00_MainContent_fmwOrder_txtTotal']"));
        System.out.print("Total price of item = $");
        System.out.println(result = Integer.parseInt(totalPrice.getAttribute("value")));

        return result;

    }


    public static int getExpectedDiscount(String productName, int quantity){
        /**
         *  Create a method `getExpectedDiscount`
         *      *     1.  accept `String productName` , `int quantity`
         *      *     2.  return `discount` amount according to `productName`,`quantity`
         *      *     3.  It should
         *      *         - if `quantity` is less than 10
         *      *           - return `0`
         *      *         - else according to all products table
         *      *           - return `correct discount number` : 8 , 15, 10
         *      *         - This is all java no selenium code.
         */
        int result=0;
        selectSideBarTab("Order");
        WebElement multiDropdownElm = Driver.getDriver().findElement(By.tagName("select"));//li/select/options
        multiDropdownElm.click();
        Select multiSelectObj = new Select(multiDropdownElm);
        BrowserUtil.waitFor(2);
        multiSelectObj.selectByVisibleText(productName);
        BrowserUtil.waitFor(2);


        WebElement quantities = Driver.getDriver().findElement((By.xpath("//li/input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")));
        Actions actions=new Actions(Driver.getDriver());
        actions.click(quantities).pause(2000).sendKeys(Keys.BACK_SPACE).perform();
        BrowserUtil.waitFor(2);
        quantities.sendKeys(""+quantity);

        WebElement discountBox = Driver.getDriver().findElement((By.xpath(" //li/input[@id='ctl00_MainContent_fmwOrder_txtDiscount']")));
        //li/input[@id='ctl00_MainContent_fmwOrder_txtDiscount']
        discountBox.click();
        BrowserUtil.waitFor(2);

        if(quantity>=10){
            if(productName.equalsIgnoreCase("MyMoney")){
                System.out.print("Discount of MyMoney = % ");
                System.out.println(result = Integer.parseInt(discountBox.getAttribute("value")));
                assertTrue(result==8);
            }else if(productName.equalsIgnoreCase("FamilyAlbum")){
                System.out.print("Discount of FamilyAlbum = % ");
                System.out.println(result = Integer.parseInt(discountBox.getAttribute("value")));
                assertTrue(result==15);
            }else if(productName.equalsIgnoreCase("ScreenSaver")){
                System.out.print("Discount of ScreenSaver = % ");
                System.out.println(result = Integer.parseInt(discountBox.getAttribute("value")));
                assertTrue(result==10);
            }else {
                throw new RuntimeException("Product name option is not exist. Given product name is "+productName);
            }

        }else {
            result=0;
        }

        return result;
    }

    public static void enterAddressInfo(){

        /**
         * Create a void method `enterAddressInfo`
         *      *     1.  accept no param (optionally you can parameterize it but we will use `javafaker` library to randomize it later)
         *      *     2.  It should fill up the `Address Information` section of order form
         *      *
         */

        //selectSideBarTab("Order");

        WebElement fullName = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtName"));
        WebElement addressInput=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2"));
        WebElement cityInput=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3"));
        WebElement stateInput=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4"));
        WebElement zipCodeInput=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));

        BrowserUtil.waitFor(2);

        Faker faker =new Faker();
        String name   = faker.name().fullName();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zip = faker.number().digits(5);
       // WebDriverWait wait =new WebDriverWait(Driver.getDriver(),10);

        fullName.sendKeys(name,Keys.TAB);
        BrowserUtil.waitFor(2);
        System.out.println(fullName.getAttribute("value"));

        addressInput.sendKeys(address,Keys.TAB);
        BrowserUtil.waitFor(2);
        System.out.println(addressInput.getAttribute("value"));

        cityInput.sendKeys(city,Keys.TAB);
        BrowserUtil.waitFor(2);
        System.out.println(cityInput.getAttribute("value"));

        stateInput.sendKeys(state,Keys.TAB);
        BrowserUtil.waitFor(2);
        System.out.println(stateInput.getAttribute("value"));

        zipCodeInput.sendKeys(zip,Keys.TAB);
        BrowserUtil.waitFor(2);
        System.out.println(zipCodeInput.getAttribute("value"));


    }


    public static void enterPaymentInfo(){
        /**
         * 15.  Create a void method `enterPaymentInfo`
         *        1.  accept no param (optionally you can parameterize it but we will use `javafaker` library to randomize it later)
         *        2.  It should fill up the `Payment Information` section of order form
         */

      // selectSideBarTab("Order");
       WebElement visaCheckBox=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
       WebElement masterCardCheckBox=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
       WebElement americanExpressBox=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2"));
       WebElement cardNumberInput=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
       WebElement cardExpirationDate=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1"));

       Faker faker =new Faker();
       String cardNums=faker.number().digits(9);
       String expireDate="02/22";
       int eachOption=1;

       List<WebElement> allRadioBoxes=Driver.getDriver().findElements(By.xpath("//input[@type='radio']"));

       for(WebElement eachRadioBox: allRadioBoxes){

           if(eachRadioBox.getText().equals("Visa")){
               eachRadioBox.click();
               BrowserUtil.waitFor(3);
               cardNumberInput.sendKeys(""+cardNums,Keys.TAB);
               BrowserUtil.waitFor(3);
               System.out.println("Option "+eachOption++ +" = "+cardNumberInput.getAttribute("value"));
               cardExpirationDate.sendKeys(expireDate,Keys.TAB);
               System.out.println(cardExpirationDate.getAttribute("value"));
           }else {
               BrowserUtil.waitFor(3);
               eachRadioBox.click();
               Actions actions=new Actions(Driver.getDriver());
               actions.click(cardNumberInput).pause(3000).keyDown(Keys.COMMAND)
                       .sendKeys("A").keyUp(Keys.COMMAND).sendKeys(Keys.BACK_SPACE).perform();
               BrowserUtil.waitFor(3);
               cardNumberInput.sendKeys(""+cardNums,Keys.TAB);
               BrowserUtil.waitFor(3);
               System.out.println("Option "+eachOption++ +" = "+cardNumberInput.getAttribute("value"));
               cardExpirationDate.sendKeys(expireDate,Keys.TAB);
               System.out.println(cardExpirationDate.getAttribute("value"));

           }

       }


    }


    public static boolean  submitAndVerify() {
        boolean result = false;
        /**
         * 16. Create a void method `submitAndVerify`
         *        1. accept no param return boolean
         *        2. click on `process` button
         *        3. check if `New order has been successfully added.` message present
         *        4. return `true` if it is , false if it is not.
         *        5. Use explicit wait to avoid long wait time if not present.
         *
         */
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        WebElement processBtn=Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton"));
        BrowserUtil.waitFor(2);
        processBtn.click();

        WebElement successMsg=Driver.getDriver().findElement(By.xpath("//strong[normalize-space(.)='New order has been successfully added.']"));
        wait.until(visibilityOfElementLocated(By.xpath("//strong[normalize-space(.)='New order has been successfully added.']")) );
        String message = successMsg.getText();
        if (message.equals("New order has been successfully added.")) {
            System.out.println(message);
            result = true;
        } else {
            throw new RuntimeException("Verify message is not found! ");
        }
        return result;
    }


}


