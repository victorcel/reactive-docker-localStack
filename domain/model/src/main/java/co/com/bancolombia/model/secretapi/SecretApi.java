package co.com.bancolombia.model.secretapi;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class SecretApi {

    private String clientId;
    private String clientSecret;
}
