package com.trello.pages;

import com.base.BasePage;
import com.utils.AppiumCommonActions;

public class HomePage extends BasePage {
    private String addButtonID = "com.trello:id/add_fab";


    public boolean isAddButtonDisplayed() {
        return AppiumCommonActions.isElementDisplayed(AppiumCommonActions.findElementByID(addButtonID));
    }

    public void clickAddButton() {
        AppiumCommonActions.findElementByID(addButtonID).click();
    }
}
