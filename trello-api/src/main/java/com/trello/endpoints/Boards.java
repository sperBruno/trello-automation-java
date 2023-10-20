package com.trello.endpoints;

import com.trello.ApiRequestHandler;
import com.trello.client.RequestManager;
import com.trello.utils.PropertiesInfo;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Boards {
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private ApiRequestHandler request;
    public Boards() {
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        request = new ApiRequestHandler();
        request.setHeaders(headers);
        request.setQueryParam(queryParams);
    }

    public Response createBoard(String boardName) {
        request.setQueryParam("name", boardName);
        request.setEndpoint("/boards/");
        return  RequestManager.post(request);
    }

    public Response deleteBoard(String boardId) {
        request.setEndpoint(String.format("/boards/%s", boardId));
        return RequestManager.delete(request);
    }

    public Response getBoard(String boardID) {
        request.getQueryParams().remove("name");
        request.setEndpoint("/boards/".concat(boardID));
        return RequestManager.get(request);
    }

    public Response updateBoard(String boardId) {
        request.setEndpoint(String.format("/boards/%s", boardId));
        return RequestManager.put(request);
    }

    public void setQueryParam(String key, String value) {
        request.setQueryParam(key, value);
    }
}
