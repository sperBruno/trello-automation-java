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

public class AAndroidDriver implements IAppiumDriver {
    private static final Logger LOGGER = LogManager.getLogger(AAndroidDriver.class.getSimpleName());
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private static final String apiDemosAPK = PathUtils.buildPath("trello-mobile/src/test/resources/apps/ApiDemos.apk");
    private static String PORT = "4723";
    private static String HOST = "127.0.0.1";

    @Override
    public AppiumDriver initDriver() {

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UIAutomator2");
//        capabilities.setCapability("appium:deviceName", "pixel_xl");
        capabilities.setCapability("appium:udid", "emulator-5554");
        if (PropertiesInfo.getInstance().getApiDemosFlag()) {
            capabilities.setCapability("appium:app", apiDemosAPK);
        } else {
            capabilities.setCapability("appium:appPackage", "com.trello");
            capabilities.setCapability("appium:appActivity", ".home.HomeActivity");
        }
        capabilities.setCapability("appium:fullReset", "false");
        capabilities.setCapability("appium:noReset", "true");
        capabilities.setCapability("appium:forceAppLaunch", "true");
        try {
            return new AndroidDriver(new URL(String.format("http://%s:%s", HOST, PORT)),
                    capabilities);
        } catch (MalformedURLException e) {
            LOGGER.warn(e);
        }
        return null;
    }
}
