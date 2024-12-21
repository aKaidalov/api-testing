package api_testing.dto;

import lombok.Data;

@Data
public class BookingResponse {
    private Integer bookingid;
    private Booking booking;
}
