package uk.nhs.adaptors.scr;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.OK;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import uk.nhs.adaptors.scr.clients.SpineClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpineMockServiceTest {
    private static final String SETUP_ENDPOINT = "/setup";
    private static final String HEALTHCHECK_ENDPOINT = "/healthcheck";
    private static final int PORT = 8081;

    @Autowired
    private SpineClient spineClient;

    @Test
    public void getHealthcheckShouldReturnOkStatus() {
        given()
            .port(PORT)
            .when()
            .get(HEALTHCHECK_ENDPOINT)
            .then()
            .statusCode(OK.value()).extract();
    }

    @Test
    public void sampleEndpointShouldReturnMockedData() {
        String sampleJson = "{\"sample\":\"ok\"}";

        given()
            .port(PORT)
            .contentType(APPLICATION_JSON_VALUE)
            .body(sampleJson)
            .when()
            .post(SETUP_ENDPOINT)
            .then()
            .statusCode(ACCEPTED.value()).extract();

        String dataFromSpine = spineClient.getSampleEndpoint();

        assertThat(dataFromSpine).isEqualTo(sampleJson);
    }
}
