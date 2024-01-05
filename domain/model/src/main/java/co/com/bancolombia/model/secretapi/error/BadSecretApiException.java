package co.com.bancolombia.model.secretapi.error;

public class BadSecretApiException extends RuntimeException{

    private final String message;
    private static final String MESSAGE = "invalid secret: %s";

    public BadSecretApiException(String message) {
        super(String.format(MESSAGE,message));
        this.message = message;
    }
}
