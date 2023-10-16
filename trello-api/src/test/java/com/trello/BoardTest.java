package com.trello;

//curl --location --request POST 'https://api.trello.com/1/boards/?name=AT-08-class-3f1c13d1-f4cd-4377-95ed-f4f7a12320d5&key=&token=' \




import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BoardTest {
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://api.trello.com/1").build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void testCreateBoard() {
        //Arrange
//        String endpoint =  "https://api.trello.com/1/boards/";
        String apiKey = "84b4afa69fcbbb3e12ece30db732262a";
//        String apiKey = "";
        String apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";
        String boardName = "bruno test board 1 to test body";
//        String endpoint = baseUrl + "?name="+ boardName + "&key=" + apiKey + "&token=" +apiToken;


        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boardName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec).log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                        .post("/boards/")
                .then()
                .spec(responseSpec).extract().response();
        //Assert
//        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void testCreateBoardReqSpec() {
        //Arrange

//        String endpoint =  "https://api.trello.com/1/boards/";
        String apiKey = "84b4afa69fcbbb3e12ece30db732262a";
        String apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";
        String boardName = "bruno test board 1-2";
//        String endpoint = baseUrl + "?name="+ boardName + "&key=" + apiKey + "&token=" +apiToken;


        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
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
    }

    @Test
    public void UpdateBoard() {
        //AAA
        //Arrange
        String apiKey = "84b4afa69fcbbb3e12ece30db732262a";
        String apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";
        String boardName = "bruno test board 1-2 Updated";


        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boardName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .put("/boards/652dc67e5cc4f63a15172cea");
        System.out.println(response.getBody().asPrettyString());
        //Asserts
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getBoardTest() {
        String apiKey = "84b4afa69fcbbb3e12ece30db732262a";
//        String apiKey = "";
        String apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";

        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);

        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .get("/boards/652dc9d80d310150df15b7cf").then()
                .spec(responseSpec).extract().response();

        String name = response.path("name");

        Assert.assertEquals(name, "bruno test board 1 to test body" );
    }

    @Test
    public void deleteBoardTest() {
        String apiKey = "84b4afa69fcbbb3e12ece30db732262a";
        String apiToken = "ATTA0c333e3feb33cfb68bf5cc2528a45d1bc620f47a2568c5e084412a46fa536bd10CF076E7";

        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);

        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .delete("/boards/652dc9d80d310150df15b7cf").then()
                .spec(responseSpec).extract().response();
        System.out.println("Board Deleted");
        System.out.println(response.getBody().asPrettyString());
    }
}