package com.steps.hooks;

import com.appium.DriverManager;
import com.appium.Mobile;
import com.trello.utils.PropertiesInfo;
import com.utils.AppiumCommonActions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobileHooks {

    private static final Logger LOGGER = LogManager.getLogger(MobileHooks.class.getSimpleName());


    @After
    public void tearDown(Scenario scenario) {
        LOGGER.info("After hook for scenario: " + scenario.getName());
        String browserStackStatusMessage = "";
        if (scenario.isFailed()) {
            browserStackStatusMessage = "browserstack_executor: {\"action\": \"setSessionStatus\",\"arguments\": {\"status\": \"failed\", \"reason\": \"TEST FAILED!\"}}";
            LOGGER.info(scenario.getName() + " has failed");
        } else {
            browserStackStatusMessage = "browserstack_executor: {\"action\": \"setSessionStatus\",\"arguments\": {\"status\": \"passed\", \"reason\": \"TEST PASSED!\"}}";
            LOGGER.info(scenario.getName() + " has passed");
        }

        if (PropertiesInfo.getInstance().getEnv().equalsIgnoreCase(Mobile.Android_BrowserStack.toString())) {
            AppiumCommonActions.runJSEScript(browserStackStatusMessage);
        }
        // cast to JavascriptExecutor

        DriverManager.getInstance().getDriver().quit();
    }
}
