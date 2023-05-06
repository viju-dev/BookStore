package com.example.Bookstore.Services;

import com.example.Bookstore.Entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public String add_book(Book book) throws Exception;

    public Book get_book(int id) throws Exception;
    
    public List<Book> get_books_by_author(String author) throws Exception;
    public List<Book> get_books_by_name(String name) throws Exception;

    public List<Book> get_all_books() throws Exception;

    public String update_book(Book book) throws Exception;

    public String remove_book(int id) throws Exception;

}
