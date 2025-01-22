package demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CreditCardDTO implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("number")
    private String cardNumber;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("cvv")
    private int cvvCode;

    @JsonProperty("type")
    private String cardType;

}
