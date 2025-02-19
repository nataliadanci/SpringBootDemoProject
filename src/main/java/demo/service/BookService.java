package demo.service;

import demo.dto.BookDTO;
import demo.dto.DisplayClientDTO;
import demo.errorhandling.BookBorrowerNotFoundException;
import demo.errorhandling.BookNotFoundException;

public interface BookService {
    BookDTO saveBook(BookDTO bookDTO);

    BookDTO deleteBookById(Integer id) throws BookNotFoundException;

    BookDTO getBookById(Integer id) throws BookNotFoundException;

    DisplayClientDTO getBookBorrower(Integer bookId) throws BookNotFoundException, BookBorrowerNotFoundException;
}
