package com.apidemos.pages;

import com.base.BasePage;
import com.utils.AppiumCommonActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

//    @AndroidFindBy(accessibility = "App")
//    private WebElement app;
    private String appAccessibilityId = "App";
    private String viewsAccessibilityId = "Views";

    public boolean isAppDisplayed() {
//        return this.app.isDisplayed();
        return AppiumCommonActions.isElementDisplayed(
                AppiumCommonActions.findElementByAccessibilityID(appAccessibilityId));
    }

    public void clickAppOption() {
//        app.click();
        AppiumCommonActions.findElementByAccessibilityID(appAccessibilityId).click();
    }

    public void clickViewsOption() {
        AppiumCommonActions.findElementByAccessibilityID(viewsAccessibilityId).click();
    }
}
