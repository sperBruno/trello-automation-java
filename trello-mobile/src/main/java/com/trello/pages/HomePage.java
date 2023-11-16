package com.trello.pages;

import com.base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    @AndroidFindBy(id = "com.trello:id/add_fab")
    private WebElement addButton;


    public boolean isAddButtonDisplayed() {
        return this.addButton.isDisplayed();
    }

    public void clickAddButton() {
        this.addButton.click();
    }
}
