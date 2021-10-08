package com.cydeo.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EatStepDef {

    @Given("Alice is hungry")
    public void alice_is_hungry() {

        System.out.println("This is @Given code Alice_is_hungry");
    }


    @When("she eats {int} cucumbers")
    public void she_eats_cucumbers(Integer quantity) {
        System.out.println("This is @When code she_eats_cucumbers "+quantity);

    }


    @Then("she will be full")
    public void she_will_be_full() {
        System.out.println("This is @Then code she_will_be_full");

    }

    @Given("Ivan is hungry")
    public void ivan_is_hungry() {
        System.out.println("This is @Given code Ivan_is_hungry");
    }

    @When("He eats {int} cucumbers")
    public void he_eats_cucumbers(Integer quantity) {
        System.out.println("This is @When code he_eats_cucumbers "+quantity);

    }

    @Then("he faints")
    public void he_faints() {
        System.out.println("This is @Then code he_faints");

    }

}
