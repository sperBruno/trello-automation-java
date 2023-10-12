package com.trello;

//curl --location --request POST 'https://api.trello.com/1/boards/?name=AT-08-class-3f1c13d1-f4cd-4377-95ed-f4f7a12320d5&key=&token=' \




import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BoardTest {
    @Test
    public void testCreateBoard() {
        //Arrange
        String endpoint =  "https://api.trello.com/1/boards/";
        String apiKey = "";
        String apiToken = "";
        String boardName = "bruno test board";
//        String endpoint = baseUrl + "?name="+ boardName + "&key=" + apiKey + "&token=" +apiToken;


        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boardName);

        //Act
        var response = RestAssured.given().log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                        .post(endpoint);
        //Assert
        Assert.assertEquals(response.statusCode(), 200);
    }
}