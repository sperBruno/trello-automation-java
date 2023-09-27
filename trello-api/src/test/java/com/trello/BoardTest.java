package com.trello;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.trello.utils.PropertiesInfo;

import java.util.HashMap;

public class BoardTest {
    private static final Logger LOGGER = LogManager.getLogger(BoardTest.class.getSimpleName());
    @Test
    public void CreateBoard() {
        var endpoint = String.format("%s/%s/boards", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion());
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        var queryParams = new HashMap<String, String>();
        String boardName = "AT-08-java1213";
        queryParams.put("name", boardName);
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        var response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).post(endpoint);
        LOGGER.info(response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 200);

        String actualBoardName = JsonPathHandler.getBoardName(response.getBody().asPrettyString());
        LOGGER.info(actualBoardName);
        Assert.assertEquals(boardName, actualBoardName, String.format("Actual board name: %s, does not match with %s ", actualBoardName, boardName));
    }
}
