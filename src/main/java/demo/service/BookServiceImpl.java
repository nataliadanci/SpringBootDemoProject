package demo.service;

import demo.dto.BookDTO;
import demo.dto.DisplayClientDTO;
import demo.entity.Book;
import demo.entity.Client;
import demo.errorhandling.BookBorrowerNotFoundException;
import demo.errorhandling.BookNotFoundException;
import demo.errorhandling.ClientNotFoundException;
import demo.repository.BookRepository;
import demo.repository.ClientRepository;
import demo.transformers.BookTransformer;
import demo.transformers.DisplayClientTransformer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookTransformer bookTransformer;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DisplayClientTransformer displayClientTransformer;

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {

        Book bookToBeSaved = bookTransformer.fromDTO(bookDTO);
        Book savedBook = bookRepository.save(bookToBeSaved);
        return bookTransformer.fromEntity(savedBook);
    }

    @Override
    public BookDTO deleteBookById(Integer id) throws BookNotFoundException{
        Optional<Book> foundBook = bookRepository.findById(id);
        Book foundBookToDelete = foundBook.orElseThrow(()-> new BookNotFoundException("No book found with this ID."));
        bookRepository.delete(foundBookToDelete);
        return bookTransformer.fromEntity(foundBookToDelete);
    }

    @Override
    public BookDTO getBookById(Integer id) throws BookNotFoundException{
        Optional<Book> foundBookOptional = bookRepository.findById(id);
        Book foundBook = foundBookOptional.orElseThrow(() -> new BookNotFoundException("No book found with this ID"));
        return bookTransformer.fromEntity(foundBook);
    }

    @Override
    public DisplayClientDTO getBookBorrower(Integer bookId) throws BookNotFoundException, BookBorrowerNotFoundException{
        Optional<Book> foundBookOptional = bookRepository.findById(bookId);
        Book foundBook = foundBookOptional.orElseThrow(() -> new BookNotFoundException("No book found with this ID"));

        Client bookBorrower = foundBook.getBookBorrower();
        if(bookBorrower==null){
            throw new BookBorrowerNotFoundException("This book is not yet borrowed");
        }
        return displayClientTransformer.fromEntity(bookBorrower);
    }
}
