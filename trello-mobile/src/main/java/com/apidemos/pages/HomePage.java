package com.apidemos.pages;

import com.base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(accessibility = "App")
    private WebElement app;

    @AndroidFindBy(accessibility = "Views")
    private WebElement viewsOption;

    public boolean isAppDisplayed() {
        return this.app.isDisplayed();
    }

    public void clickAppOption() {
        this.app.click();
    }

    public void clickViewsOption() {
        this.viewsOption.click();
    }
}
