package co.com.bancolombia.api;

import co.com.bancolombia.model.secretapi.SecretApi;
import co.com.bancolombia.secretsmanager.api.GenericManagerAsync;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenericManagerAsync connector;

    @SneakyThrows
    @Test
    void shouldReturnSecretWhenSecretExists() {
        String textoMock = "test";
        SecretApi mockSecret = SecretApi.builder()
                .clientId(textoMock)
                .clientSecret(textoMock)
                .build();
        when(connector.getSecret(any(), any())).thenReturn(Mono.just(mockSecret));

        webTestClient.get()
                .uri("/api/v1/secret")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.client-id").isEqualTo(textoMock)
                .jsonPath("$.client-secret").isEqualTo(textoMock);
    }

    @SneakyThrows
    @Test
    void shouldReturnServerErrorWhenSecretRetrievalFails() {
        when(connector.getSecret(any(), any())).thenReturn(Mono.error(new RuntimeException()));

        webTestClient.get()
                .uri("/api/v1/secret")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
