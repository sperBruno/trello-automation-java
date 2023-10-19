package com.trello.api.steps;

import com.trello.ApiRequestHandler;
import com.trello.api.Context;
import com.trello.client.RequestManager;
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

    public BoardStepdefs(Context context) {
        this.context = context;
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
        request.setQueryParam("name", boardName);
        request.setEndpoint("/boards/");

        //Act
        response = RequestManager.post(request);
        context.setProperty("createBoardResponse", response.getBody().asPrettyString());
        context.setResponse(response);
        boardID = response.getBody().path("id");
        LOGGER.info(String.format("boardID: %s", boardID));
        context.setProperty("boardId", boardID);
    }


}
