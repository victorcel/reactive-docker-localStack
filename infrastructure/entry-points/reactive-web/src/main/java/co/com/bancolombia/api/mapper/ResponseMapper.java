package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.SecretApiDto;
import co.com.bancolombia.model.secretapi.SecretApi;
import reactor.core.publisher.Mono;


public class ResponseMapper {

    public static Mono<SecretApiDto> toSecretApiDto(SecretApi secretApi){
        return Mono.just(SecretApiDto.builder()
                .clientId(secretApi.getClientId())
                .clientSecret(secretApi.getClientSecret())
                .build());
    }
}
