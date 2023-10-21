package com.apidemos.android;

import com.trello.utils.PathUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ApiDemosTest {
    private static String PORT = "4723";
    private static String HOST = "127.0.0.1";
    private static AndroidDriver driver;

    private String apiDemosApkPath = PathUtils.buildPath("trello-mobile/src/test/resources/ApiDemos.apk") ;
    @Test
    public void ValidateDashboardTest() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("appium:deviceName", "Pixel_7_Pro_API_30");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("appium:platformName", "android");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:app", apiDemosApkPath);
//        desiredCapabilities.setCapability("appium:appActivity", ".home.HomeActivity");
        desiredCapabilities.setCapability("appium:fullReset", "false");
        desiredCapabilities.setCapability("appium:noReset", "true");
//        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
//        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability( "appium:forceAppLaunch", "true");


        try {
            driver = new AndroidDriver(new URL(String.format("http://%s:%s/", HOST, PORT)), desiredCapabilities);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        WebElement textOption = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Text']"));

        Assert.assertTrue(textOption.isDisplayed());
        System.out.println("Text option is displayed");
        textOption.click();
//        driver.close();
        driver.quit();
    }
}
