package com.trello;


import com.trello.utils.JsonPath;

public final class JsonPathHandler {

    /**
     * Default private constructor.
     */
    private JsonPathHandler() {
    }

    public static String getName(String json, String value) {
        return JsonPath.getResultList(json, String.format("$[?(@.id == '%s')].name", value)).get(0).toString();
    }

    public static String getBoardName(String json) {
        return JsonPath.getResult(json, "$.name");
    }

    public static String getId(String json) {
        return JsonPath.getResult(json, "$.id");
    }
}
