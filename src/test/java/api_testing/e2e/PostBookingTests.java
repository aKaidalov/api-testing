package api_testing.e2e;

import api_testing.controller.BookingApi;
import api_testing.dto.Booking;
import api_testing.generator.BookingGenerator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class PostBookingTests {

    private BookingApi bookingApi;

    @BeforeEach
    public void initializeBookingApi() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenPostBookingWithWrongAcceptHeader_thenReturnStatus418() {
        Booking generatedBooking = BookingGenerator.getFullPayload();

        Response response = bookingApi.createBooking(generatedBooking, ContentType.HTML);

        assertThat(response.getStatusCode()).isEqualTo(418);
    }
}
