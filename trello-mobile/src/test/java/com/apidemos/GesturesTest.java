package com.apidemos;

import com.appium.DriverManager;
import com.google.common.collect.ImmutableMap;
import com.trello.utils.PathUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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

    private static AndroidDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public void setUp() {
       driver = DriverManager.getInstance().getDriver();
       wait = DriverManager.getInstance().getWait();
    }


    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void scrollAndPinchGestureTest() {
        WebElement appMenuItem = driver.findElement(AppiumBy.accessibilityId("Views"));
        appMenuItem.click();

        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) driver.findElement(AppiumBy.accessibilityId("Grid"))).getId(),
                "direction", "down",
                "percent", 0.75
        ));

        driver.executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) driver.findElement(AppiumBy.accessibilityId("Grid"))).getId(),
                "percent", 1
        ));
        System.out.println("complete gesture");
        driver.navigate().back();
    }

    @Test
    public void clickGestureTest() {
        WebElement appMenuItem = driver.findElement(AppiumBy.accessibilityId("App"));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId()
        ));

        driver.navigate().back();
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", 640, "y", 530
        ));
        driver.navigate().back();
    }

    @Test
    public void longPressTest() {
        WebElement appMenuItem = driver.findElement(AppiumBy.accessibilityId("App"));
        //Long PRess
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId(), "duration", 1000
        ));
        driver.navigate().back();
    }

    @Test
    public void doubleClickTest() {
        WebElement appMenuItem = driver.findElement(AppiumBy.accessibilityId("App"));
        driver.executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appMenuItem).getId()
        ));
        driver.navigate().back();
        driver.navigate().back();
    }

    @Test
    public void waitsTest() throws InterruptedException {
        WebElement appMenuItem = driver.findElement(AppiumBy.accessibilityId("App"));
        appMenuItem.click();
        WebElement alarmElement = driver.findElement(AppiumBy.accessibilityId("Alarm"));

        Assert.assertTrue(alarmElement.isDisplayed(), "Alarm is not displayed");
        System.out.println("Alarm Text found" + alarmElement.getText());

        Assert.assertEquals(alarmElement.getText(), "Alarm");
        Thread.sleep(5000);
        System.out.println("Pasamos el sleep");


        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Alarm")));

        alarmElement.click();
        Boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(AppiumBy.accessibilityId("Alarm Service")),
                "Alarm Service"));
        System.out.println("Alarm Service is displayed: " + String.valueOf(isTextPresent));
    }
}
