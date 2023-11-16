package com.steps.trello;

import com.trello.pages.HomePage;
import com.appium.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
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
    private HomePage homePage;

    public homeTest(HomePage homePage) {
        this.homePage = homePage;
    }

//    @BeforeClass
//    public void setup() {
//        driver = DriverManager.getInstance().getDriver();
//    }
//
//    @AfterClass
//    public void cleanUp() {
//        driver.quit();
//    }



    @Given("I am on trello Home Page")
    public void iAmOnTrelloHomePage() {
        Assert.assertTrue(homePage.isAddButtonDisplayed());
    }

    @Given("I can click on Add button")
    public void iCanClickAddButton() {
        homePage.clickAddButton();
    }
}
