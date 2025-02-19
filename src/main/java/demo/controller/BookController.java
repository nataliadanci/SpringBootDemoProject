package demo.controller;

import demo.dto.BookDTO;
import demo.dto.DisplayClientDTO;
import demo.errorhandling.BookBorrowerNotFoundException;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;

@RestController //This means that this class is a controller (also a bean)
@RequestMapping(path = "/demo/books/") //This means URLs with /demo
@CrossOrigin(origins = "", allowedHeaders = "") //Allows any requests from browser
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/add")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        try{
            BookDTO savedBookDTO = bookService.saveBook(bookDTO);
            return ResponseEntity.created(new URI("/books/" + savedBookDTO.getId())).body(savedBookDTO);
        } catch (URISyntaxException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody BookDTO deleteBookById(@PathVariable("id") Integer id){
        try{
            return bookService.deleteBookById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }

    }

    @GetMapping(path = "/{id}")
    public @ResponseBody BookDTO getBookById(@PathVariable("id") Integer id){
        try {
            return bookService.getBookById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = "/borrower/{book_id}")
    public @ResponseBody DisplayClientDTO getBookBorrower(@PathVariable("book_id") Integer bookId){
        try{
            return bookService.getBookBorrower(bookId);
        } catch (BookBorrowerNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, exception.getMessage(), exception);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
