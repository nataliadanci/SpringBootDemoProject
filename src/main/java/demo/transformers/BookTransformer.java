package demo.transformers;

import demo.dto.BookDTO;
import demo.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookTransformer implements Transformer<Book, BookDTO> {


    @Override
    public BookDTO fromEntity(Book book) {

        if(book==null){
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setBookBorrower(book.getBookBorrower());

        return bookDTO;
    }

    @Override
    public Book fromDTO(BookDTO bookDTO) {

        if(bookDTO==null){
            return null;
        }

        Book book = new Book();

        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setBookBorrower(bookDTO.getBookBorrower());

        return book;
    }
}
