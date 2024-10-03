package api_testing.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi {
    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";


    public Response getAllBookings() {
        return given()
                .when()
                .get(API_URL)
                .then()
                .extract().response();
    }
}
