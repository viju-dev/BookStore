package com.example.Bookstore.Controllers;

import com.example.Bookstore.Entities.Book;
import com.example.Bookstore.Services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @PostMapping("/addBook")
    public ResponseEntity add_book(@Validated @RequestBody Book book) {
        System.out.println(book.toString());

        //checking if json fields valid or not
        if (book.getName() == null ||book.getName().isEmpty() ){//if book is null then returning response with message and http code
            return new ResponseEntity<>("Book Name can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        } else if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return new ResponseEntity<>("Book Author can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        } else if (book.getPrice() == null || book.getPrice().isNaN()) {
            return new ResponseEntity<>("Book Price can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        }

        //checking if book price is negative
        if(book.getPrice() < 0){
            return new ResponseEntity<>("Book price can't be negative...",HttpStatus.NOT_ACCEPTABLE);
        }

        String res;
        // handling if any exception occur
        try {
            res = bookService.add_book(book);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning responseEntity with string response
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/getBookById")
    public ResponseEntity get_book(@RequestParam("id") int id) {
        Book book;
        //handling null pointer and other runtime exception if occur with error message and http-status code
        try {
           book = bookService.get_book(id);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book,HttpStatus.FOUND);
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity get_books_by_author(@RequestParam("author") String author) {
        List<Book> bookList;
        //handling null pointer and other runtime exception if occur with error message and http-status code
        try {
            bookList = bookService.get_books_by_author(author);
        } catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning list of books
        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }

    @GetMapping("/getBooksByName")
    public ResponseEntity get_books_by_name(String name) {
        List<Book> bookList;
        //handling null pointer and other runtime exception if occur with error message and http-status code
        try {
            bookList = bookService.get_books_by_name(name);
        } catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning list of books
        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity get_all_books() {
        List<Book> bookList;
        //handling null pointer and other runtime exception if occur with error message and http-status code
        try {
            bookList = bookService.get_all_books();
        } catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning list of books
        return new ResponseEntity<>(bookList,HttpStatus.FOUND);
    }

    @PutMapping("/updateBook") // we can also create separate apis for editing price,author and description
    public ResponseEntity update_book(@RequestBody Book book) {

        //checking if json fields valid or not
        if (book.getName() == null ||book.getName().isEmpty() ){//if book is null then returning response with message and http code
            return new ResponseEntity<>("Book Name can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        } else if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return new ResponseEntity<>("Book Author can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        } else if (book.getPrice() == null || book.getPrice().isNaN()) {
            return new ResponseEntity<>("Book Price can't be empty or null...",HttpStatus.NOT_ACCEPTABLE);
        }

        //checking if book price is negative
        if(book.getPrice() < 0){
            return new ResponseEntity<>("Book price can't be negative...",HttpStatus.NOT_ACCEPTABLE);
        }

        String res;
        try {
            res = bookService.update_book(book);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); //NOT_MODIFIED // not found
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning string response
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteBookById")
    public ResponseEntity remove_book(@RequestParam("id") int id) {
        String res;
        //handling runtime exception and other exception if occur
        try{
            res = bookService.remove_book(id);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //returning string response
        return new ResponseEntity<>(res,HttpStatus.GONE);
    }
}
