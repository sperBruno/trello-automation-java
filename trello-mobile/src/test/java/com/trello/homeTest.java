package com.trello;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class homeTest {
    private static String PORT = "4723";
    private static String HOST = "127.0.0.1";
    private static AndroidDriver driver;

    @BeforeClass
    public void setup() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("appium:deviceName", "");
//        desiredCapabilities.setCapability("appium:udid", "");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("appium:platformName", "android");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:appPackage", "com.trello");
        desiredCapabilities.setCapability("appium:appActivity", ".home.HomeActivity");
        desiredCapabilities.setCapability("appium:fullReset", "false");
        desiredCapabilities.setCapability("appium:noReset", "true");
//        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
//        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appium:forceAppLaunch", "true");


        try {
            driver = new AndroidDriver(new URL(String.format("http://%s:%s/", HOST, PORT)), desiredCapabilities);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void homeScreenIsDisplayed() {
        WebElement element = driver.findElement(AppiumBy.id("com.trello:id/add_fab"));

        Assert.assertTrue(element.isDisplayed());
        System.out.println("se muestra add board");
        element.click();
    }
}
