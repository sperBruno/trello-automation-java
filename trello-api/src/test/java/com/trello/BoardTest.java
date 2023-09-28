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

    @Test
    public void UpdateBoard() {
        // AAA
        // Arrange
        var createBoard = String.format("%s/%s/boards", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion());
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        var queryParams = new HashMap<String, String>();
        String boardName = "AT-08-java1241dd23";
        queryParams.put("name", boardName);
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        var response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).post(createBoard);
        LOGGER.info(response.getBody().asString());
        var boardId = JsonPathHandler.getId(response.getBody().asString());
        // Act

        var boardNameUpdated = "Board Updated";
        var updateBoardEndpoint = String.format(String.format("%s/%s/boards/%s", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion(), boardId));
        queryParams.put("name", boardNameUpdated);
        response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).put(updateBoardEndpoint);
        // Assert
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(JsonPathHandler.getBoardName(response.getBody().asString()), boardName);
        Assert.assertEquals(JsonPathHandler.getBoardName(response.getBody().asString()), boardNameUpdated);
        Assert.assertEquals(JsonPathHandler.getId(response.getBody().asString()), boardId);
    }

    @Test
    public void getBoardTest() {
        // AAA
        // Arrange
        var createBoard = String.format("%s/%s/boards", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion());
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        var queryParams = new HashMap<String, String>();
        String boardName = "AT-08-java1241dd23";
        queryParams.put("name", boardName);
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        var response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).post(createBoard);
//        LOGGER.info(response.getBody().asString());
        var boardId = JsonPathHandler.getId(response.getBody().asString());
        LOGGER.info("board id: " + boardId);
        // Act

        var getBoardEndpoint = String.format(String.format("%s/%s/boards/%s", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion(), boardId));
        queryParams.remove("name");
        response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).get(getBoardEndpoint);
        // Assert
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(JsonPathHandler.getBoardName(response.getBody().asString()), boardName);
        Assert.assertEquals(JsonPathHandler.getId(response.getBody().asString()), boardId);
    }

    @Test
    public void deleteBoardTest() {
        // AAA
        // Arrange
        var createBoard = String.format("%s/%s/boards", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion());
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        var queryParams = new HashMap<String, String>();
        String boardName = "AT-08-java1241dad23delewt1";
        queryParams.put("name", boardName);
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        var response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).post(createBoard);
//        LOGGER.info(response.getBody().asString());
        var boardId = JsonPathHandler.getId(response.getBody().asString());
        LOGGER.info("board id: " + boardId);
        // Act

        var getBoardEndpoint = String.format(String.format("%s/%s/boards/%s", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion(), boardId));
        queryParams.remove("name");
        response = RestAssured.given().log().all().when().headers(headers).queryParams(queryParams).delete(getBoardEndpoint);
        LOGGER.info(response.getBody().asPrettyString());
        // Assert
        var expectedResponse = """
                {\"_value\":null}
                """;
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.body().asString(), expectedResponse);
        Assert.assertTrue(response.body().asString().contains(expectedResponse));
    }
}
