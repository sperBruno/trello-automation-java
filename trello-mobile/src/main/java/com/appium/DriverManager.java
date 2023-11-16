package com.appium;

import com.trello.utils.PropertiesInfo;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {

    private AndroidDriver driver;
    private static DriverManager instance;
    private static WebDriverWait wait;

    private DriverManager() {
        initAndroidDriver();
    }

    private void initAndroidDriver() {
        driver = (AndroidDriver) AppiumFactoryDriver.getDriver(PropertiesInfo.getInstance().getPlatformName()).initDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
}
