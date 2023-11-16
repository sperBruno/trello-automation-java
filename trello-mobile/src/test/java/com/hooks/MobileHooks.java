package com.hooks;

import com.appium.DriverManager;
import io.cucumber.java.After;

public class MobileHooks {

    @After()
    public void afterHook( ) {
        DriverManager.getInstance().getDriver().quit();
    }
}
