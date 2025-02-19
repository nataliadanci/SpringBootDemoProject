package demo.transformers;

import demo.controller.AuthorController;
import demo.dto.AuthorDTO;
import demo.dto.BookDTO;
import demo.entity.Author;
import demo.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class AuthorTransformer implements Transformer<Author, AuthorDTO> {
    @Override
    public AuthorDTO fromEntity(Author author) {

        if(author==null){
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setNationality(author.getNationality());

        return authorDTO;
    }

    @Override
    public Author fromDTO(AuthorDTO authorDTO) {

        if(authorDTO==null){
            return null;
        }

        Author author = new Author();

        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setNationality(authorDTO.getNationality());

        return author;
    }
}
