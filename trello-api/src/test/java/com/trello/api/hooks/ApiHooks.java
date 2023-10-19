package com.trello.api.hooks;

import com.trello.ApiRequestHandler;
import com.trello.api.Context;
import com.trello.client.RequestManager;
import com.trello.utils.PropertiesInfo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ApiHooks {
    private static final Logger LOGGER = LogManager.getLogger(ApiHooks.class.getSimpleName());
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private ApiRequestHandler request;

    private ResponseSpecification responseSpec;
    private Context context;

    public ApiHooks(Context context) {
        this.context = context;
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        request = new ApiRequestHandler();
        request.setHeaders(headers);
        request.setQueryParam(queryParams);
    }

    @Before()
    public void beforeAllHook() {
        System.out.println("This is the before all hook.");
    }

    @After("@deleteBoard")
    public void deleteBoardHook() {
        String boardId = context.getProperty("boardId");
        LOGGER.info(String.format("BoardId %s from hook ", boardId));
        request.setEndpoint(String.format("/boards/%s", boardId));
        var response = RequestManager.delete(request)
                .then()
                .spec(responseSpec).extract().response();
        LOGGER.info("Board deleted by hook: ".concat(response.getBody().asPrettyString()));
    }
}
