package com.steps.apidemos;

import com.apidemos.pages.HomePage;
import com.appium.DriverManager;
import com.google.common.collect.ImmutableMap;
import com.trello.utils.PathUtils;
import com.utils.AppiumCommonActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GesturesTest {
     HomePage homePage;

    public GesturesTest(HomePage homePage) {
        this.homePage = homePage;
    }


    @Given("I scroll and pinch Gesture")
    public void scrollAndPinchGestureTest() {
        Assert.assertTrue(homePage.isAppDisplayed());
        homePage.clickViewsOption();

        AppiumCommonActions.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) DriverManager.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Grid"))).getId(),
                "direction", "down",
                "percent", 0.75
        ));


        AppiumCommonActions.executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) AppiumCommonActions.findElementByAccessibilityID("Grid")).getId(),
                "percent", 1
        ));
        System.out.println("complete gesture");
        DriverManager.getInstance().getDriver().navigate().back();
    }

    @When("click gesture")
    public void clickGestureTest() {
        WebElement appMenuItem = AppiumCommonActions.findElementByAccessibilityID("App");
       AppiumCommonActions.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId()
        ));

        DriverManager.getInstance().getDriver().navigate().back();
        AppiumCommonActions.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", 640, "y", 530
        ));
        DriverManager.getInstance().getDriver().navigate().back();
    }

    @When("I long press")
    public void longPressTest() {
        WebElement appMenuItem = AppiumCommonActions.findElementByAccessibilityID("App");
        //Long PRess
        AppiumCommonActions.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId(), "duration", 1000
        ));
        DriverManager.getInstance().getDriver().navigate().back();
    }

    @When("I double click")
    public void doubleClickTest() {
        WebElement appMenuItem = AppiumCommonActions.findElementByAccessibilityID("App");
        AppiumCommonActions.executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId()
        ));
        DriverManager.getInstance().getDriver().navigate().back();
        DriverManager.getInstance().getDriver().navigate().back();
    }

    @When("I wait test")
    public void waitsTest() throws InterruptedException {
        homePage.clickAppOption();
        WebElement alarmElement = AppiumCommonActions.findElementByAccessibilityID("Alarm");

        Assert.assertTrue(alarmElement.isDisplayed(), "Alarm is not displayed");
        System.out.println("Alarm Text found" + alarmElement.getText());

        Assert.assertEquals(alarmElement.getText(), "Alarm");
        Thread.sleep(5000);
        System.out.println("Pasamos el sleep");


        DriverManager.getInstance().getWait().until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Alarm")));

        alarmElement.click();
        Boolean isTextPresent = DriverManager.getInstance().getWait().until(ExpectedConditions.textToBePresentInElement(
                AppiumCommonActions.findElementByAccessibilityID("Alarm Service"),
                "Alarm Service"));
        System.out.println("Alarm Service is displayed: " + String.valueOf(isTextPresent));
        DriverManager.getInstance().getDriver().navigate().back();
        DriverManager.getInstance().getDriver().navigate().back();
    }
}
