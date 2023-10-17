package com.trello;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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

    @BeforeClass
    public void setUp() {
        apiKey = "84b4afa69fcbbb3e12ece30db732262a";
        apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";
        requestSpec = new RequestSpecBuilder().setBaseUri("https://api.trello.com/1").build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
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

        queryParams.put("name", boardName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .post("/boards/");

        System.out.println(response.getBody().asPrettyString());
        //Assert
        Assert.assertEquals(response.statusCode(), 200);

        boardID = response.getBody().path("id");
        System.out.println(String.format("boardID: %s", boardID));
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

        String name = response.path("name");

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