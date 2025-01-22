package demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
//this is  dt transfer object
public class CreateClientDTO implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String clientAddress;
    @JsonProperty("age")
    private int age;
    @JsonProperty("username")
    private String username;

    //we keep password in this DTO because we use it to create a client and at creation we need to set a password
    @JsonProperty("password")
    private String password;

    @JsonProperty("credit_card")
    private CreditCardDTO creditCardDTO;
}
