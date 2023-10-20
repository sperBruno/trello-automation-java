package com.trello.api.steps;

import com.trello.ApiRequestHandler;
import com.trello.api.Context;
import com.trello.client.RequestManager;
import com.trello.endpoints.Boards;
import com.trello.utils.PropertiesInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BoardStepdefs {
    private static final Logger LOGGER = LogManager.getLogger(BoardStepdefs.class.getSimpleName());
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private ApiRequestHandler request;
    private Response response;
    private String boardID;
    private Context context;
    private Boards boards;

    public BoardStepdefs(Context context, Boards boards) {
        this.context = context;
        this.boards = boards;
    }

    @Given("I set apiRequestHandler with proper credential")
    public void iSetApiRequestHandlerWithProperCredential() {
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        request = new ApiRequestHandler();
        request.setHeaders(headers);
        request.setQueryParam(queryParams);
    }

    @When("I create a board with name {string}")
    public void iCreateABoardWithName(String boardName) {
        response = this.boards.createBoard(boardName);
        context.setProperty("createBoardResponse", response.getBody().asPrettyString());
        context.setResponse(response);
        boardID = response.getBody().path("id");
        LOGGER.info(String.format("boardID: %s", boardID));
        context.setProperty("boardId", boardID);
    }


    @When("I get a board with {string}")
    public void iGetABoardWith(String boardID) {
        this.boardID = boardID.contains("boardId") ? context.getProperty("boardId") : boardID;
        response = this.boards.getBoard(this.boardID);
        context.setResponse(response);
    }

    @When("I update board name with {string}")
    public void iUpdateBoardNameWith(String newBoardName) {
        this.boards.setQueryParam("name", newBoardName);
        var response = this.boards.updateBoard(context.getProperty("boardId"));
        context.setResponse(response);
    }

    @When("I delete a board with {string}")
    public void iDeleteABoardWith(String boardId) {
        this.boardID = boardId.contains("boardId") ? context.getProperty("boardId") : boardId;
        var response = this.boards.deleteBoard(this.boardID);
        context.setResponse(response);
    }
}
