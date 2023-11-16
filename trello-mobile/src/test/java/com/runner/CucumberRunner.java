package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber Runner.
 */
@CucumberOptions(
        plugin = {"pretty", "json:reports/cucumber.json", "junit:reports/cucumber.xml",
                "html:reports/cucumber-html-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = {"src/test/resources/features"},
        glue = {"com.steps"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
