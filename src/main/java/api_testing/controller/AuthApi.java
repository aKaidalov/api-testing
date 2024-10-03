package api_testing.controller;

import api_testing.dto.Authentication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {
    public static final String API_URL = BASE_API_URL + "/auth";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    public static String getToken() {
        Authentication authentication = new Authentication();
        authentication.setUsername(USERNAME);
        authentication.setPassword(PASSWORD);

        Response authResponse = given()
                .header("Content-Type", ContentType.JSON.toString())
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
}
