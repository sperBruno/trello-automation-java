package com.trello;

import com.trello.client.RequestManager;
import com.trello.utils.JsonPath;
import com.trello.utils.PropertiesInfo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * We can comment or delete this class since it already automated using cucumber.
 */

/**
public class BoardTest {

    private ResponseSpecification responseSpec;
    private String apiKey;
    private String apiToken;
    private Map<String, String> headers;
    private Map<String, String> queryParams;

    private String boardID;
    private ApiRequestHandler request;

    @BeforeClass
    public void setUp() {
        request = new ApiRequestHandler();

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

        request.setHeaders(headers);
        request.setQueryParam(queryParams);
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

//    @Test(priority = 1)
//    public void testCreateBoardReqSpec() {
//        //Arrange
//
//        String boardName = "bruno test board 1-2";
//
////        queryParams.put("name", boardName);
//        request.setQueryParam("name", boardName);
//
//        //Act
//        var response = RestAssured.given()
//                .spec(requestSpec)
//                .log().all().when()
//                .headers(request.getHeaders())
//                .queryParams(request.getQueryParams())
//                .post("/boards/");
//
//        System.out.println(response.getBody().asPrettyString());
//        //Assert
//        Assert.assertEquals(response.statusCode(), 200);
//
//        boardID = response.getBody().path("id");
//        System.out.println(String.format("boardID: %s", boardID));
//
//        String name = JsonPath.getResult(response.getBody().asPrettyString(), "$.name");
//        System.out.println(String.format("New board name: %s", name));
//        Assert.assertEquals(name, boardName);
//    }

    @Test(priority = 1)
    public void testCreateBoardSchemaValidation() {
        //Arrange
        InputStream createBoardJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("schemas/createBoardSchema.json");

        String boardName = "bruno test board 1-2";

        request.setQueryParam("name", boardName);
        request.setEndpoint("/boards/");

        //Act
        var response = RequestManager.post(request);
        response
                .then()
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(createBoardJsonSchema))
                .extract().response();
        ;

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
        request.setQueryParam("name", boardName);
        request.setEndpoint(String.format("/boards/%s", boardID));
        //Act
        var response = RequestManager.put(request);
        System.out.println(response.getBody().asPrettyString());
        //Asserts
        Assert.assertEquals(response.statusCode(), 200);
        String name = JsonPath.getResult(response.getBody().asPrettyString(), "$.name");
        Assert.assertEquals(name, "bruno test board 1-2 Updated");
    }

    @Test(priority = 2)
    public void getBoardTest() {
        //Given
        InputStream getBoardJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("schemas/getBoardSchema.json");
        queryParams.remove("name");
        request.setEndpoint(String.format("/boards/%s", boardID));

        //When
        var response = RequestManager.get(request);
        response.then()
                .spec(responseSpec)
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(getBoardJsonSchema))
                .extract().response();
        System.out.println(response.getBody().asPrettyString());

        //Then
        String name = JsonPath.getResult(response.getBody().asPrettyString(), "$.name");
        Assert.assertEquals(name, "bruno test board 1-2 Updated");
    }

    @Test(priority = 6)
    public void deleteBoardTest() {
        request.setEndpoint(String.format("/boards/%s", boardID));
        var response = RequestManager.delete(request)
                .then()
                .spec(responseSpec).extract().response();
        System.out.println("Board Deleted");
        System.out.println(response.getBody().asPrettyString());

//        """
//                {\"value\": null}
//                """;
    }
}
 **/