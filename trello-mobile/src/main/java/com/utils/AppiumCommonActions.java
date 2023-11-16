package com.utils;

import com.appium.DriverManager;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AppiumCommonActions {

    private AppiumCommonActions() {}

    public static boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static WebElement findElementByAccessibilityID(String accessibilityID) {
        return DriverManager.getInstance().getDriver().findElement(AppiumBy.accessibilityId(accessibilityID));
    }

    public static void executeScript(String script, ImmutableMap immutableMap) {
        DriverManager.getInstance().getDriver().executeScript(script, immutableMap);
    }

    public static WebElement findElementByID(String elementID) {
        return DriverManager.getInstance().getDriver().findElement(AppiumBy.id(elementID));
    }
}
