package demo.controller;

import demo.dto.AuthorDTO;
import demo.dto.BookDTO;
import demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;

@RestController //This means that this class is a controller (also a bean)
@RequestMapping(path = "/demo/authors/") //This means URLs with /demo
@CrossOrigin(origins = "", allowedHeaders = "") //Allows any requests from browser
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping(path = "/add")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO){
        try{
            AuthorDTO savedAuthorDTO = authorService.saveAuthor(authorDTO);
            return ResponseEntity.created(new URI("/authors/" + savedAuthorDTO.getId())).body(savedAuthorDTO);
        } catch (URISyntaxException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody AuthorDTO deleteAuthorById(@PathVariable("id") Integer id){
        try{
            return authorService.deleteAuthorById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }

    }

    @GetMapping(path = "/{id}")
    public @ResponseBody AuthorDTO getAuthorById(@PathVariable("id") Integer id){
        try {
            return authorService.getAuthorById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(path = "/publishBook/{author_id}/{book_id}")
    public @ResponseBody AuthorDTO publishBook(@PathVariable("author_id") Integer authorId, @PathVariable("book_id") Integer bookId){
        try {
            return authorService.publishBook(authorId, bookId);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}

//TODO: ADD BOOKS IN AUTHOR DTO
//TODO: FINISH AND TEST PUBLISH BOOK
//TODO: ENDPOINT TO DELETE BOOK PUBLISHED
//TODO: GET BOOKS BY AUTHOR_ID


