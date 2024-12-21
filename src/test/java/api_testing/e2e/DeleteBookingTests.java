package api_testing.e2e;

import api_testing.controller.BookingApi;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingTests {

    private BookingApi bookingApi;

    @BeforeEach
    public void initializeBookingApi() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenDeleteBooking_thenShouldReturnStatus201() {
        Booking booking = BookingGenerator.getFullPayload();

        BookingResponse bookingResponse = bookingApi.createBooking(booking).as(BookingResponse.class);
        int bookingId = bookingResponse.getBookingid();
        Response deleteBookingResponse = bookingApi.deleteBooking(bookingId);

        assertThat(deleteBookingResponse.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void givenDeletedBookingId_whenGetBookingById_thenShouldReturnStatus404() {
        Booking booking = BookingGenerator.getFullPayload();
        BookingResponse createdBookingResponse = bookingApi.createBooking(booking).as(BookingResponse.class);
        int bookingId = createdBookingResponse.getBookingid();

        Response deleteBookingResponse = bookingApi.deleteBooking(bookingId);
        assertThat(deleteBookingResponse.getStatusCode()).isEqualTo(201);

        Response getBookingResponse = bookingApi.getBooking(bookingId);

        assertThat(getBookingResponse.getStatusCode()).isEqualTo(404);
    }
}
