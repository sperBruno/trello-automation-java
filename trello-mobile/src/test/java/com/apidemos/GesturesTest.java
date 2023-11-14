package com.apidemos;

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

    private static final String apiDemosAPK = PathUtils.buildPath("trello-mobile/src/test/resources/apps/ApiDemos.apk");
    private static AndroidDriver driver;
    private static String PORT = "4723";
    private static String HOST = "127.0.0.1";
    private static WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UIAutomator2");
//        capabilities.setCapability("appium:deviceName", "pixel_xl");
        capabilities.setCapability("appium:udid", "emulator-5554");

        capabilities.setCapability("appium:app", apiDemosAPK);
        capabilities.setCapability("appium:fullReset", "false");
        capabilities.setCapability("appium:noReset", "true");
        capabilities.setCapability("appium:forceAppLaunch", "true");
        try {
            driver = new AndroidDriver(new URL(String.format("http://%s:%s", HOST, PORT)),
                    capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
        }
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
