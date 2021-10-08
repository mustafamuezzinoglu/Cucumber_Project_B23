package com.cydeo.runner;

//this class has only one purpose
//instructing how and what feature we want to run
//where are the step definitions

//do we want to just generate missing step definitions
//dryRun=true will run the test without failing for missing steps
//so you can copy all the missing steps if there is any

//do we want to get json, html report
//do we want to filter the test run according to certain tags

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
                   glue = "com/cydeo/step_definitions",
        plugin = { "html:target/cucumber.html"  } ,
                   dryRun = false
        //dry-run checks if we have any missing step definition steps
                   //,tags= "@wip"
                     )
//dryRun=true >>checks before run the class each steps are correct , do we have them or not
//dryRun=false >> when you put false its gonna just run normally
public class TestRunner {

}
