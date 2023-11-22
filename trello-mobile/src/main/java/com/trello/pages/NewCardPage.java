package com.trello.pages;

import com.base.BasePage;
import com.utils.AppiumCommonActions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class NewCardPage extends BasePage {


    @AndroidFindBy(id = "com.trello:id/board_selection")
    private WebElement selectBoard;
    @AndroidFindBy(id = "com.trello:id/list_selection")
    private WebElement selectList;
    @AndroidFindBy(id = "com.trello:id/card_name_input")
    private WebElement cardName;

    @AndroidFindBy(id = "com.trello:id/confirm")
    private WebElement saveBtn;

    String boardNameLocator = "//android.widget.TextView[@resource-id=\"com.trello:id/board_name\" and @text=\"%s\"]";
    String ListOption = "//android.widget.TextView[@resource-id=\"com.trello:id/list_name\" and @text=\"%s\"]";

    public void setBoard(String boardName) {
        selectBoard.click();
        WebElement boardOption = AppiumCommonActions.findElementByXpath(String.format(boardNameLocator, boardName));
        boardOption.click();
    }

    public void setList(String listName) {
        selectList.click();
        WebElement listOption = AppiumCommonActions.findElementByXpath(String.format(ListOption, listName));
        listOption.click();
    }

    public void setCardName(String cardName) {
        this.cardName.clear();
        this.cardName.sendKeys(cardName);
    }

    public void clickSaveButton() {
        this.saveBtn.click();
    }
}
