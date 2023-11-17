package com.hooks;

import com.appium.DriverManager;
import com.utils.AppiumCommonActions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MobileHooks {

    private static final Logger LOGGER = LogManager.getLogger(MobileHooks.class.getSimpleName());


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
// Take a screenshot...
            final byte[] screenshot =
                    ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
// embed it in the report.
            scenario.attach(screenshot, "image/png", "screenshot-1");

            AppiumCommonActions.runJSEScript("browserstack_executor: {\"action\": \"setSessionStatus\",\"arguments\": {\"status\": \"failed\", \"reason\": \"TEST FAILED!\"}}");
            LOGGER.info(scenario.getName() + " has failed");
        } else {
            // set test status as pass
            AppiumCommonActions.runJSEScript("browserstack_executor: {\"action\": \"setSessionStatus\",\"arguments\": {\"status\": \"passed\", \"reason\": \"TEST PASSED!\"}}");
            LOGGER.info(scenario.getName() + " has passed");
        }
        // cast to JavascriptExecutor

        DriverManager.getInstance().getDriver().quit();
    }
}
