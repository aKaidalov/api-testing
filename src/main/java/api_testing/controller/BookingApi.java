package api_testing.controller;

import api_testing.dto.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi {
    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";


    public Response getAllBookings() {
        return given()
                .when()
                .get(API_URL)
                .then()
                .extract().response();
    }

    public Response createBooking(Booking booking) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(booking)
                .log().body()
                .when()
                .post(API_URL)
                .then()
                .log().body()
                .extract().response();
    }
}
