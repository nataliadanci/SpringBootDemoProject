package demo.service;

import demo.dto.AuthorDTO;
import demo.errorhandling.AuthorNotFoundException;
import demo.errorhandling.BookNotFoundException;

public interface AuthorService {
    AuthorDTO saveAuthor(AuthorDTO authorDTO);

    AuthorDTO deleteAuthorById(Integer id) throws AuthorNotFoundException;

    AuthorDTO getAuthorById(Integer id) throws AuthorNotFoundException;

    AuthorDTO publishBook(Integer authorId, Integer bookId) throws AuthorNotFoundException, BookNotFoundException;
}
