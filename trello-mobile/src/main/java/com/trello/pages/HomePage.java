package com.trello.pages;

import com.base.BasePage;
import com.utils.AppiumCommonActions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    @AndroidFindBy(id = "com.trello:id/add_fab")
    private WebElement addButton;


    @AndroidFindBy(id = "com.trello:id/add_card_text")
    private WebElement addCard;

    String boardLocator = "//android.widget.TextView[@resource-id=\"boardName\" and @text=\"%s\"]";

    public boolean isAddButtonDisplayed() {
        return this.addButton.isDisplayed();
    }

    public void clickAddButton() {
        this.addButton.click();
    }
    public void clickAddCardOptionButton() {
        this.addCard.click();
    }

    public void openBoard(String boardName) {
        AppiumCommonActions.findElementByXpath(String.format(boardLocator, boardName)).click();
    }
}
