package com.trello.api.hooks;

import com.trello.api.Context;
import com.trello.endpoints.Boards;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHooks {
    private static final Logger LOGGER = LogManager.getLogger(ApiHooks.class.getSimpleName());

    private ResponseSpecification responseSpec;
    private Context context;
    private Boards boards;

    public ApiHooks(Context context, Boards boards) {
        this.context = context;
        this.boards = boards;
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Before()
    public void beforeAllHook() {
        System.out.println("This is the before all hook.");
    }

    @Before("@createBoard")
    public void createBoardHook() {
        var boardName = "AT-08 board from hook";
        Response response = boards.createBoard(boardName);
        context.setResponse(response);
        String boardID = response.getBody().path("id");
        context.setProperty("boardId", boardID);
        LOGGER.info(String.format("boardID: %s", boardID));
    }

    @After("@deleteBoard")
    public void deleteBoardHook() {
        String boardId = context.getProperty("boardId");
        LOGGER.info(String.format("BoardId %s from hook ", boardId));

        var response = this.boards.deleteBoard(boardId);
        response.then()
                .spec(responseSpec).extract().response();
        LOGGER.info("Board deleted by hook: ".concat(response.getBody().asPrettyString()));
    }
}
