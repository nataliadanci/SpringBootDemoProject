package demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class DisplayCreditCardDTO implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("number")
    private String cardNumber;

    @JsonProperty("expiration_date")
    private String expirationDate;

    /*@JsonProperty("cvv") //when we retrieve the credit card info we don't want the cvv to be displayed
    private int cvvCode;*/

    @JsonProperty("type")
    private String cardType;

}
