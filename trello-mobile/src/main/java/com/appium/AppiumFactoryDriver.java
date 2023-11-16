package com.appium;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AppiumFactoryDriver {

    private static Map<Mobile, Supplier<IAppiumDriver>> driverMap = new HashMap<>();

    static {
        driverMap.put(Mobile.Android, AAndroidDriver::new);
        driverMap.put(Mobile.Android_BrowserStack, BrowserStackDriver::new);
    }

    public static IAppiumDriver getDriver(String platformName) {

        return driverMap.getOrDefault(Mobile.valueOf(platformName), AAndroidDriver::new).get();
    }
}
