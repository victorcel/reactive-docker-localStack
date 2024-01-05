package co.com.bancolombia.exception.model;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponse {

    private String traceId;
    private OffsetDateTime timestamp;
    private HttpStatus status;
    private String message;
    private List<ErrorDetails> errors;
}
