package demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id //this annotation marks the class attribute "id" as an identity attribute
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "stock")
    private int stock;
}
