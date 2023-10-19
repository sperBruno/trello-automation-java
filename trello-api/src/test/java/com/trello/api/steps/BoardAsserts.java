package com.trello.api.steps;

import com.trello.api.Context;
import com.trello.utils.JsonPath;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;

import java.io.InputStream;

public class BoardAsserts {
    private static final Logger LOGGER = LogManager.getLogger(BoardAsserts.class.getSimpleName());
    private Context context;

    public BoardAsserts(Context context) {
        this.context = context;
    }

    @Then("I should see field {string} with value {string}")
    public void iShouldSeeFieldWithValue(String field, String value) {
        String actualResult = JsonPath.getResult(context.getProperty("createBoardResponse"), String.format("$.%s", field));
        LOGGER.info(String.format("New board name: %s", actualResult));
        Assert.assertEquals(actualResult, value, String.format("board name does not match with expected value: %s", value));
    }


    @Then("I validate createBoard response schema")
    public void iValidateCreateBoardResponseSchema() {
        InputStream createBoardJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("schemas/createBoardSchema.json");
        context.getResponse()
                .then()
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(createBoardJsonSchema))
                .extract().response();
        ;
    }
}
