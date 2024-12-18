package api_testing.auth;

import api_testing.controller.AuthApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthTests {

    @Test
    public void whenPostAuthenticationWithCorrectCredentials_thenReturnHttp200() {
        Response response = AuthApi.login("admin", "password123");
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void whenPostAuthenticationWithIncorrectCredentials_thenReturnBadCredentials() {
        Response response = AuthApi.login("wrongUser", "wrongPassword");
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Bad credentials");
    }

    @Test
    public void whenPostAuthentication_thenReturnToken() {
        String token = AuthApi.getToken();
        assertThat(token).isNotNull();
    }
}