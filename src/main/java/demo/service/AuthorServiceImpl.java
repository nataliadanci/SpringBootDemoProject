package demo.service;

import demo.dto.AuthorDTO;
import demo.dto.BookDTO;
import demo.entity.Author;
import demo.entity.Book;
import demo.errorhandling.AuthorNotFoundException;
import demo.errorhandling.BookNotFoundException;
import demo.repository.AuthorRepository;
import demo.repository.BookRepository;
import demo.transformers.AuthorTransformer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorTransformer authorTransformer;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {

        Author authorToBeSaved = authorTransformer.fromDTO(authorDTO);
        Author savedAuthor = authorRepository.save(authorToBeSaved);
        return authorTransformer.fromEntity(savedAuthor);
    }

    @Override
    public AuthorDTO deleteAuthorById(Integer id) throws AuthorNotFoundException {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        Author foundAuthorToDelete = foundAuthor.orElseThrow(()-> new AuthorNotFoundException("No author found with this ID."));
        authorRepository.delete(foundAuthorToDelete);
        return authorTransformer.fromEntity(foundAuthorToDelete);
    }

    @Override
    public AuthorDTO getAuthorById(Integer id) throws AuthorNotFoundException{
        Optional<Author> foundAuthorOptional = authorRepository.findById(id);
        Author foundAuthor = foundAuthorOptional.orElseThrow(()-> new AuthorNotFoundException("No author found with this ID."));
        return authorTransformer.fromEntity(foundAuthor);
    }

    @Override
    public AuthorDTO publishBook(Integer authorId, Integer bookId) throws AuthorNotFoundException, BookNotFoundException{
        Optional<Author> foundAuthorOptional = authorRepository.findById(authorId);
        Author foundAuthor = foundAuthorOptional.orElseThrow(()-> new AuthorNotFoundException("No author found with this ID."));

        Optional<Book> foundBookOptional = bookRepository.findById(bookId);
        Book foundBook = foundBookOptional.orElseThrow(() -> new BookNotFoundException("No book found with this ID"));

        //TODO: COMPLETE THIS METHOD

        return null;
    }
}


