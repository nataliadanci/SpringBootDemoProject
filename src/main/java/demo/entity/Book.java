package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id //this annotation marks the class attribute "id" as an identity attribute
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @OneToOne(mappedBy = "bookBorrowed")
    @JsonIgnore
    private Client bookBorrower;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author bookAuthor;

}
