package co.com.bancolombia.exception.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = ErrorDetailsSerializer.class)
@AllArgsConstructor
@Getter
public enum ErrorDetails {

    API_CURRENCY_NOT_SUPPORTED(125, "currency not supported", "http://cedesistemas.edu.co/124");

    private final Integer errorCode;
    private final String errorMessage;
    private final String referenceUrl;
}