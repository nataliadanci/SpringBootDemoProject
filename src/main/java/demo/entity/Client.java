package demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="client_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id //this annotation marks the class attribute "id" as an identity attribute
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "name") // an optional annotation when the name of the attribute is
                          // the same as the name of the column
    private String name;

    @Column(name = "address") //this is a mandatory annotation because the name of the attribute is
                             // not the same as the name of the column
    private String clientAddress;

    @Column(name = "age")
    private int age;

    @Column(name = "username")
    private String username;

    @Deprecated
    @Column(name = "password")
    private String password;


}
