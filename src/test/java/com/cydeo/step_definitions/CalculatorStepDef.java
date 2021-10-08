package com.cydeo.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepDef {

    @Given("calculator app is open")
    public void calculator_app_is_open() {

        System.out.println("This is @Given calculator app is open");
    }

    @When("I add {int} with {int}")
    public void i_add_with(Integer int1, Integer int2) {
        System.out.println("When I add "+int1+" with "+int2);
    }

    @Then("I should get result {int} displayed")
    public void i_should_get_result_displayed(Integer result) {
        System.out.println("@Then result should be "+result);

    }
}
