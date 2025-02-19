package demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id //this annotation marks the class attribute "id" as an identity attribute
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "author_id", updatable = false)
    private Set<Book> books;
}
