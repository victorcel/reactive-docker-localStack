package co.com.bancolombia.api;

import co.com.bancolombia.api.mapper.ResponseMapper;
import co.com.bancolombia.model.secretapi.SecretApi;
import co.com.bancolombia.model.secretapi.error.BadSecretApiException;
import co.com.bancolombia.secretsmanager.api.GenericManagerAsync;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    @Value("${aws.secretName}")
    private String secretName;

    private final GenericManagerAsync connector;

    @SneakyThrows
    public Mono<ServerResponse> getSecret(ServerRequest serverRequest) {

        return connector.getSecret(secretName, SecretApi.class)
                .flatMap(ResponseMapper::toSecretApiDto)
                .flatMap(ServerResponse.ok()::bodyValue)
                .onErrorResume(err -> Mono.error(
                                new BadSecretApiException(err.getMessage()
                                )
                        )
                );

    }
}