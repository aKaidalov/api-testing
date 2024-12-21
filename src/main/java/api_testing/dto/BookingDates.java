package api_testing.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDates {
    private Date checkin;
    private Date checkout;
}
