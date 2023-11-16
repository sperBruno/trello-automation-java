package com.base;

import com.appium.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static AndroidDriver driver;
    protected static WebDriverWait wait;
    public BasePage() {
        driver = DriverManager.getInstance().getDriver();
        wait = DriverManager.getInstance().getWait();
    }
}
