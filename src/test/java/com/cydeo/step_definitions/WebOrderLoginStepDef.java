package com.cydeo.step_definitions;

import com.cydeo.pages.WLoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


public class WebOrderLoginStepDef {

    WLoginPage loginPage;

    @Given("we are at web order login page")
    public void we_are_at_web_order_login_page() {
       loginPage=new WLoginPage();
        loginPage.goTo();
        //loginPage.errorMsg.isDisplayed();

    }

    @When("we provide valid credentials")
    public void we_provide_valid_credentials() {
      loginPage.login("Tester","test");
        BrowserUtil.waitFor(2);
      loginPage.loginButton.click();


    }

    @Then("we should see all order page")
    public void we_should_see_all_order_page() {
     //cucumber-junit dependency already come with junit4
      //so we will be just using assertions methods coming from junit4
      //it's extremely

        //import static org.junit.Assert.*;
        //assertEquals(4,4);
        assertEquals("Web Orders", Driver.getDriver().getTitle());

    }


    @When("we provide invalid credentials")
    public void weProvideInvalidCredentials() {

        loginPage.login("bla","bla");
    }

    @Then("we should still be at login page")
    public void weShouldStillBeAtLoginPage() {
        assertEquals("Web Orders Login",Driver.getDriver().getTitle() );

    }

    @And("login error message should be present")
    public void loginErrorMessageShouldBePresent() {

        assertTrue(loginPage.loginErrorMsg() );
    }

    @When("user provides username {string} and password {string}")
    public void userProvidesUsernameAndPassword(String username, String password) {
     //username ="whatever provided from scenario step"
        // password = "whatever provided from scenario step"
        loginPage.login(username,password);
    }
}
