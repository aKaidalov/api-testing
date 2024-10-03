package api_testing.controller;

import api_testing.dto.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi extends BaseApi {
    public static final String API_URL =  BASE_API_URL + "/booking";

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

    public Response getBooking(Integer bookingid) {
        return given()
                .when()
                .get(API_URL + "/" + bookingid)
                .then()
                .log()
                .body()
                .extract().response();
    }

    public Response deleteBooking(int bookingId) {
        String token = AuthApi.getToken();

        System.out.println("---");
        System.out.println("Token is " + token);
        System.out.println("---");
        return given()
                .header("Cookie", "token=" + token)
                .log().uri().and().log().headers()
                .when()
                .delete(API_URL + "/" + bookingId)
                .then()
                .extract().response();
    }
}
