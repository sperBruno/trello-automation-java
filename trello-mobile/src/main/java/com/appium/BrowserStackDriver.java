package com.appium;

import com.trello.utils.PathUtils;
import com.trello.utils.PropertiesInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackDriver implements IAppiumDriver{
    private static final Logger LOGGER = LogManager.getLogger(BrowserStackDriver.class.getSimpleName());
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private static final String apiDemosAPK = "bs://c416ce089246b1a89b80b17baba1eba92ce74528";
    @Override
    public AppiumDriver initDriver() {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
        capabilities.setCapability("platformVersion", "12.0");
//        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("app", apiDemosAPK);

//        capabilities.setCapability("appium:fullReset", "false");
//        capabilities.setCapability("appium:noReset", "true");
//        capabilities.setCapability("appium:forceAppLaunch", "true");
        String browserStackUserName = PropertiesInfo.getInstance().getBrowserStackUserName();
        capabilities.setCapability("browserstack.user", browserStackUserName);
        String browserStackUserKey = PropertiesInfo.getInstance().getBrowserStackUserKey();
        capabilities.setCapability("browserstack.key", browserStackUserKey);
        capabilities.setCapability("Project", "AT-08-final");
        capabilities.setCapability("build", "AT-08-1");
        capabilities.setCapability("name", "AT-08 - ApiDemos");

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("appiumVersion", "2.0.0");
        browserstackOptions.put("userName", browserStackUserName);
        browserstackOptions.put("accessKey", browserStackUserKey);
        browserstackOptions.put("projectName", "AT-08-final");
        browserstackOptions.put("buildName", "AT-08 - ApiDemos");
        capabilities.setCapability("bstack:options", browserstackOptions);
        try {
            return new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", browserStackUserName, browserStackUserKey)),
                    capabilities);
        } catch (MalformedURLException e) {
            LOGGER.warn(e);
        }
        return null;
    }
}
