package api_testing;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

class AppTest {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking/";

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get(API_URL)
                .then()
                .statusCode(SC_OK);
    }
}
