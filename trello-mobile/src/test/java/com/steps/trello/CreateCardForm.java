package com.steps.trello;

import com.trello.pages.NewCardPage;

import io.cucumber.java.en.Given;


public class CreateCardForm {
    private NewCardPage newCardPage;

    public CreateCardForm(NewCardPage newCardPage) {
        this.newCardPage = newCardPage;
    }

    @Given("I create a {string} card in {string} list in {string} board")
    public void iCreateANewCar(String cardName, String listName, String boardName) {
        this.newCardPage.setBoard(boardName);
        this.newCardPage.setList(listName);
        this.newCardPage.setCardName(cardName);
        this.newCardPage.clickSaveButton();
    }
}
