//package com.epam.training_portal.context;
//
//import io.restassured.response.Response;
//
//public class TestContextAPI {
//
//    private Object requestPayload;
//    private Response response;
//    private String token;
//    private String id;
//
//    public Object getRequestPayload() {
//        return requestPayload;
//    }
//
//    public void setRequestPayload(Object requestPayload) {
//        this.requestPayload = requestPayload;
//    }
//
//    public Response getResponse() {
//        return response;
//    }
//
//    public void setResponse(Response response) {
//        this.response = response;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//}
package com.epam.training_portal.context;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TestContextAPI {

    private Map<String, Object> requestPayload = new HashMap<>(); // Type-safe payload
    private Response response;
    private String token;
    private String id;

    public Map<String, Object> getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(Map<String, Object> requestPayload) {
        this.requestPayload = requestPayload;
    }

    public void addToPayload(String key, Object value) {
        this.requestPayload.put(key, value);
    }

    public Object getFromPayload(String key) {
        return this.requestPayload.get(key);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}