package com.steps.trello;

import com.trello.pages.BoardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BoardSteps {
    private BoardPage boardPage;
    public BoardSteps(BoardPage boardPage) {
        this.boardPage = boardPage;
    }
    @Then("I should see {string} card")
    public void iShouldSeeCardInList(String cardName) {
        Assert.assertTrue(this.boardPage.isCardDisplayed(cardName));
    }
}
