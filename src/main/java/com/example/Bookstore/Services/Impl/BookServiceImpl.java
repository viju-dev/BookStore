package com.example.Bookstore.Services.Impl;

import com.example.Bookstore.Entities.Book;
import com.example.Bookstore.Repositories.BookRepository;
import com.example.Bookstore.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepo;


    @Override
    public String add_book(Book book) throws Exception {
        bookRepo.save(book); // saved book in inventory (database)
        return "Book has been added Successfully";
    }

    @Override
    public Book get_book(int id) throws Exception{
        Book book = bookRepo.findById(id).get();

        if (book == null){ // if book null throwing Null pointer exception with message
            throw new  NullPointerException("Book with given id is not found...!");
        }
        //returning book
        return book;
    }

    @Override
    public List<Book> get_books_by_author(String author) throws Exception{
        List<Book> bookList = bookRepo.findAllByAuthor(author);
        if (bookList.isEmpty()){ // if list is empty throwing Null pointer exception with message
            throw new  NullPointerException("Books of given author are not found...!");
        }
        //returning list of books
        return bookList;
    }

    @Override
    public List<Book> get_books_by_name(String name) throws Exception{
        List<Book> bookList = bookRepo.findAllByName(name);
        if (bookList.isEmpty()){ // if list is empty throwing Null pointer exception with message
            throw new  NullPointerException("Book with given name are not found...!");
        }
        //returning list of books
        return bookList;
    }

    @Override
    public List<Book> get_all_books() throws Exception{
        List<Book> bookList = bookRepo.findAll();
        if (bookList.isEmpty()){ // if list is empty throwing Null pointer exception with message
            throw new  NullPointerException("Books inventory is empty...!");
        }
        //returning list ok books
        return bookList;
    }

    @Override
    public String update_book(Book book) throws Exception{

        Book book1 = bookRepo.findByName(book.getName());
        if (book1 != null) {//if book that we get from inventory (repo) is not null then updating it with updated book data and saving in inventory
            book1 = Book.builder()
                    .id(book1.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .build();
        bookRepo.save(book1);
        }
        else { // else if book is null throwing null pointer exception with message
            throw new NullPointerException("Book with given id does not exist in inventory...!");
        }

        return "Book has been updated Successfully...";
    }

    @Override
    public String remove_book(int id) throws Exception {

        if (bookRepo.findById(id).get() != null){ // if book exist in inventory we are simply deleting it by id
            bookRepo.deleteById(id);
        }
        else { // if book we are trying to delete not exist then throwing run -time exception with message
            throw new RuntimeException("Book with given id does not exist in inventory");
        }

        return "Book has been deleted from inventory";
    }
}
