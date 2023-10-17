package com.trello;

import com.trello.utils.JsonPath;
import com.trello.utils.PropertiesInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BoardTest {
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private String apiKey;
    private String apiToken;
    private Map<String, String> headers;
    private Map<String, String> queryParams;

    private String boardID;
    private RequestHandler request;
    @BeforeClass
    public void setUp() {
        request = new RequestHandler();

        apiKey = PropertiesInfo.getInstance().getApiKey();
        apiToken = PropertiesInfo.getInstance().getApiToken();

        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);

        request.setBaseUrl(String.format("%s/%s", PropertiesInfo.getInstance().getBaseApi(),
                PropertiesInfo.getInstance().getApiVersion()));

        request.setHeaders(headers);
        request.setQueryParam(queryParams);

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(request.getBaseUrl()).build();
    }

//    @Test
//    public void testCreateBoard() {
//        //Arrange
//        String boardName = "bruno test board 1 to test body";
//
//        queryParams.put("name", boardName);
//
//        //Act
//        var response = RestAssured.given()
//                .spec(requestSpec).log().all().when()
//                .headers(headers)
//                .queryParams(queryParams)
//                .post("/boards/")
//                .then()
//                .spec(responseSpec).extract().response();
//        //Assert
//        System.out.println(response.getBody().asPrettyString());
//    }

    @Test(priority = 1)
    public void testCreateBoardReqSpec() {
        //Arrange

        String boardName = "bruno test board 1-2";

//        queryParams.put("name", boardName);
        request.setQueryParam("name", boardName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(request.getHeaders())
                .queryParams(request.getQueryParams())
                .post("/boards/");

        System.out.println(response.getBody().asPrettyString());
        //Assert
        Assert.assertEquals(response.statusCode(), 200);

        boardID = response.getBody().path("id");
        System.out.println(String.format("boardID: %s", boardID));

        String name = JsonPath.getResult(response.getBody().asPrettyString(), "$.name");
        System.out.println(String.format("New board name: %s", name));
        Assert.assertEquals(name, boardName);
    }

    @Test(priority = 2)
    public void UpdateBoard() {
        //AAA
        //Arrange
        String boardName = "bruno test board 1-2 Updated";

        queryParams.put("name", boardName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .put(String.format("/boards/%s", boardID));
        System.out.println(response.getBody().asPrettyString());
        //Asserts
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void getBoardTest() {

        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .get(String.format("/boards/%s", boardID)).then()
                .spec(responseSpec).extract().response();

//        String name = response.path("name");
        String name = JsonPath.getResult(response.getBody().asPrettyString(), "$.name");

        Assert.assertEquals(name, "bruno test board 1-2 Updated");
    }

    @Test(priority = 4)
    public void deleteBoardTest() {
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .delete(String.format("/boards/%s", boardID)).then()
                .spec(responseSpec).extract().response();
        System.out.println("Board Deleted");
        System.out.println(response.getBody().asPrettyString());
    }
}