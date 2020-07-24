package uk.nhs.adaptors.scr.utils.spineMockSetup;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.nhs.adaptors.scr.utils.EndpointMockData;
import uk.nhs.adaptors.scr.utils.spineMockSetup.interfaces.SpineMockSetupForHttpMethod;
import uk.nhs.adaptors.scr.utils.spineMockSetup.interfaces.SpineMockSetupForResponseContent;
import uk.nhs.adaptors.scr.utils.spineMockSetup.interfaces.SpineMockSetupSetupEndpoint;
import uk.nhs.adaptors.scr.utils.spineMockSetup.interfaces.SpineMockSetupWithHttpStatusCode;

@Component
public class SpineMockSetup {
    private static final String SETUP_ENDPOINT = "/setup";
    private static final int PORT = 8081;

    private static class Builder implements SpineMockSetupWithHttpStatusCode, SpineMockSetupForHttpMethod,
        SpineMockSetupForResponseContent, SpineMockSetupSetupEndpoint {
        private String url;
        private String httpMethod;
        private Integer httpStatusCode;
        private String responseContent;

        public void setupEndpoint() {
            EndpointMockData endpointMockData = new EndpointMockData();
            endpointMockData.setUrl(url);
            endpointMockData.setHttpMethod(httpMethod);
            endpointMockData.setHttpStatusCode(httpStatusCode);
            endpointMockData.setResponseContent(responseContent);

            try {
                given()
                    .port(PORT)
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(new ObjectMapper().writeValueAsString(endpointMockData))
                    .when()
                    .post(SETUP_ENDPOINT)
                    .then()
                    .statusCode(OK.value())
                    .extract();
            }
            catch (JsonProcessingException e) {
                // skip
            }
        }

        @Override
        public SpineMockSetupWithHttpStatusCode forHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        @Override
        public SpineMockSetupForResponseContent withHttpStatusCode(Integer httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        @Override
        public SpineMockSetupSetupEndpoint withResponseContent(String responseContent) {
            this.responseContent = responseContent;
            return this;
        }
    }

    public SpineMockSetupForHttpMethod forUrl(String url) {
        Builder builder = new Builder();
        builder.url = url;
        return builder;
    }
}
