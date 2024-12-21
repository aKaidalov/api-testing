package api_testing.controller;

import api_testing.dto.Authentication;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthApi extends BaseApi {
    public static final String API_URL = BASE_API_URL + "/auth";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    public static String getToken() {
        Authentication authentication = new Authentication();
        authentication.setUsername(USERNAME);
        authentication.setPassword(PASSWORD);

        Response authResponse = given()
                .header("Content-Type", JSON.toString())
                .body(authentication)
                .log()
                .headers()
                .and()
                .log()
                .body()
                .when()
                .post(API_URL)
                .then()
                .log()
                .body()
                .and()
                .extract().response();

        return authResponse.getBody().jsonPath().getString("token");
    }

    public static Response login(String username, String password) {
        Authentication authentication = new Authentication(username, password);

        return given().contentType(JSON)
                .accept(JSON.toString())
                .body(authentication)
                .when()
                .post(API_URL).then()
                .log()
                .body()
                .and()
                .extract().response();
    }
}
