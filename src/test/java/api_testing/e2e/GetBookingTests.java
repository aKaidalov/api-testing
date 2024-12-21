package api_testing.e2e;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class GetBookingTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void whenGetBookingIdsIsCalled_thenShouldHaveMoreThanOne() {
        given()
                .when()
                .get(API_URL)
                .then()
                .body("$", hasSize(greaterThan(1)));
    }

    @Test
    public void givenSpecificBookingId_whenGetBookingByIdIsCalled_thenShouldContainAdditionalNeeds() {
        String payload = "{\n" +
                "\"firstname\": " + "\"Harry\",\n" +
                "\"lastname\": " + "\"Potter\",\n" +
                "\"totalprice\": " + "100\n," +
                "\"depositpaid\": " + "true\n," +
                "\"bookingdates\": {\n" +
                "\"checkin\": " + "\"2023-03-23\",\n" +
                "\"checkout\": " + "\"2023-03-25\"\n" +
                "},\n" +
                "\"additionalneeds\":" + "\"I have an owl\"" +
                "}";

        int bookingId = given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        given()
                .when()
                .get(API_URL + "/" + bookingId)
                .then()
                .statusCode(200)
                .body("additionalneeds", equalTo("I have an owl"))
                .body("lastname", equalTo("Potter"));
    }
}
