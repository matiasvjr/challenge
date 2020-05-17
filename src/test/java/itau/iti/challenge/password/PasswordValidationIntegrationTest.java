package itau.iti.challenge.password;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordValidationIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndTrue_WhenMetTheCriteria() {
        final String password = "AbTp9!fok";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("true", responseEntity.getBody(), "Response should be true!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus400_WhenNull() {
        final String password = null;
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(400, responseEntity.getStatusCodeValue(), "Got wrong http status!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenEmpty() {
        final String password = "";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenShorterThan9Characters() {
        final String password = "AbTp9!fo";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenThereIsNoDigit() {
        final String password = "AbTpX!fok";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenThereIsNoCapitalLetter() {
        final String password = "abtp9!fok";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenThereIsNoLowerCase() {
        final String password = "ABTP9!FOK";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenThereIsNoSpecialCharacter() {
        final String password = "AbTp9Xfok";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    @Test
    void test_PasswordValidation_ShouldBeStatus200AndFalse_WhenThereIsRepeatingCharacters() {
        final String password = "AbTp9!fokqq";
        final ResponseEntity<String> responseEntity = request(password);

        assertEquals(200, responseEntity.getStatusCodeValue(), "Got wrong http status!");
        assertEquals("false", responseEntity.getBody(), "Response should be false!");
    }

    private ResponseEntity<String> request(final String password) {
        final TestRestTemplate restTemplate = new TestRestTemplate();
        final String url = buildUrl(password);

        return restTemplate.exchange(url, HttpMethod.POST, null, String.class);

    }

    private String buildUrl(final String password) {
        final String baseUrl = buildBaseUrl();

        if (password != null) {
            return UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("password", password)
                    .toUriString();
        }

        return baseUrl;
    }

    private String buildBaseUrl() {
        return "http://localhost:" + port + "/password/validate";
    }
}
