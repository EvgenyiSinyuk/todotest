package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseApi {

    private final String BASE_URI = System.getProperty("base_uri");
    private final String USER = System.getProperty("user");
    private final String PASS = System.getProperty("password");

    public Response get(String path) {
        return getRequestSpecification()
                .get(path);
    }

    public Response get(String path, Map<String, Integer> params) {
        return getRequestSpecification()
                .params(params)
                .get(path);
    }

    public Response post(String path, String body) {
        return getRequestSpecification()
                .body(body)
                .post(path);
    }

    public Response put(String path, String body) {
        return getRequestSpecification()
                .body(body)
                .put(path);
    }

    public Response delete(String path) {
        return getRequestSpecification()
                .delete(path);
    }

    private RequestSpecification getRequestSpecification() {
        RequestSpecification rs = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .port(8080)
                .auth().basic(USER, PASS);
        if (BASE_URI.contains("localhost")) {
            return rs.port(Integer.parseInt(BASE_URI.replace("localhost:", "")));
        } else {
            return rs.baseUri(BASE_URI);
        }
    }

    public Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
}