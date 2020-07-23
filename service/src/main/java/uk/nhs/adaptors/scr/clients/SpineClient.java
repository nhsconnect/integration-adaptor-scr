package uk.nhs.adaptors.scr.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import uk.nhs.adaptors.scr.config.SpineConfiguration;

@Component
public class SpineClient {
    private final WebClient webClient;

    @Autowired
    public SpineClient(WebClient.Builder webClientBuilder, SpineConfiguration spineConfiguration) {
        this.webClient = webClientBuilder
            .baseUrl(spineConfiguration.getUrl())
            .build();
    }

    public String getHealthcheckMessage() {
        return this.webClient
            .get()
            .uri("/healthcheck")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}
