package demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
//this is a data transfer object
public class DisplayClientDTO implements Serializable {
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

    @JsonProperty("credit_card")
    private DisplayCreditCardDTO displayCreditCardDTO;

    @JsonProperty("bookBorrowed")
    private BookDTO bookBorrowed;

    //we are not including password in this DTO because we will use this DTO to get and display a client
    // or a list of clients and the password is confidential
  /*@JsonProperty("password")
    private String password;*/
}
