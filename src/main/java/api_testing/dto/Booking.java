package api_testing.dto;

import lombok.Data;

@Data
public class Booking {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additonalneeds;
}
