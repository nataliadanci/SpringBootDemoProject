package demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="credit_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

    @Id //this annotation marks the class attribute "id" as an identity attribute
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cvv")
    private int cvvCode;

    @Column(name = "type")
    private String cardType;

    @OneToOne(mappedBy = "creditCard")
    private Client creditCardOwner;

}
