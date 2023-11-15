package com.trello;

import com.appium.DriverManager;
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
    private static AndroidDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
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
