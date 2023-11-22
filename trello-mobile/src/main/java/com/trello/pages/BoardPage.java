package com.trello.pages;

import com.base.BasePage;
import com.utils.AppiumCommonActions;
import org.openqa.selenium.WebElement;

public class BoardPage extends BasePage {
    public boolean isCardDisplayed(String cardName) {
        WebElement element = AppiumCommonActions.findElementByXpath(String.format("//android.widget.TextView[@resource-id=\"com.trello:id/cardText\" and @text=\"%s\"]",  cardName));
        return element.isDisplayed();
    }
}
