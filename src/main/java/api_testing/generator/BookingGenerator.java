package api_testing.generator;

import api_testing.dto.Booking;
import api_testing.dto.BookingDates;
import com.github.javafaker.Faker;

import static java.util.concurrent.TimeUnit.DAYS;

public class BookingGenerator {

    public static Booking getFullPayload() {
        Faker faker = new Faker();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(faker.date().future(1, DAYS));
        bookingDates.setCheckout(faker.date().future(15, DAYS));

        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setDepositpaid(true);
        booking.setTotalprice(faker.number().numberBetween(100, 1000));
        booking.setAdditonalneeds(faker.harryPotter().spell());
        booking.setBookingdates(bookingDates);

        return booking;
    }
}
