package api_testing.smoke;

import api_testing.controller.BookingApi;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeTests {
    private static BookingApi bookingApi;
    private static final Booking booking = BookingGenerator.getFullPayload();

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
        Response createBookingResponse = bookingApi.createBooking(booking);

        assertThat(createBookingResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void whenGetBookingByIdIsCalled_thenReturnHttp200() {
        BookingResponse createdBooking = bookingApi
                .createBooking(booking)
                .as(BookingResponse.class);

        Response getBookingByIdResponse = bookingApi.getBooking(createdBooking.getBookingid());

        assertThat(getBookingByIdResponse.getStatusCode()).isEqualTo(200);
    }
}
