package api_testing.smoke;

import api_testing.controller.BookingApi;
import api_testing.dto.Booking;
import api_testing.dto.BookingDates;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class SmokeTests {
    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    private static BookingApi bookingApi;

    @BeforeAll
    public static void instantiateBookingApi() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenGetBookingIdsIsCalled_thenReturnHttp200() {
        Response getBookingResponse = bookingApi.getAllBookings();

        assertThat(getBookingResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void givenBookingRequest_whenCreateBookingIsCalled_thenReturnHttp200() {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(new Date());
        bookingDates.setCheckout(new Date());
        Booking booking = new Booking();
        booking.setFirstname("Mihkel");
        booking.setLastname("Nikhil");
        booking.setDepositpaid(true);
        booking.setTotalprice(500);
        booking.setBookingdates(bookingDates);

        Response createBookingResponse = bookingApi.createBooking(booking);

        assertThat(createBookingResponse.getStatusCode()).isEqualTo(200);

    }

    @Test
    public void whenGetBookingByIdIsCalled_thenReturnHttp200() {
        String payload = "{\n" +
                "\"firstname\": " + "\"German\",\n" +
                "\"lastname\": " + "\"Mumma\",\n" +
                "\"totalprice\": " + "100\n," +
                "\"depositpaid\": " + "true\n," +
                "\"bookingdates\": {\n" +
                "\"checkin\": " + "\"2023-03-23\",\n" +
                "\"checkout\": " + "\"2023-03-25\"\n" +
                "}\n" +
                "}";

        int bookingId = given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .extract()
                .path("bookingid");

        given()
                .when()
                .get(API_URL + "/" + bookingId)
                .then()
                .statusCode(200);
    }
}
