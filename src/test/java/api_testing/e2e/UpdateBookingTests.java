package api_testing.e2e;

import api_testing.controller.AuthApi;
import api_testing.controller.BookingApi;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class UpdateBookingTests {

    private BookingApi bookingApi;

    @BeforeEach
    public void initializeBookingApi() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenPutBooking_thenReturnStatus200() {
        Booking newBooking = BookingGenerator.getFullPayload();
        BookingResponse createdBookingResponse = bookingApi.createBooking(newBooking).as(BookingResponse.class);
        int bookingId = createdBookingResponse.getBookingid();

        String token = AuthApi.getToken();

        Booking updatedBooking = BookingGenerator.getFullPayload();
        Response putResponse = bookingApi.updateBooking(bookingId, updatedBooking, token);

        assertThat(putResponse.getStatusCode()).isEqualTo(200);
    }
}
