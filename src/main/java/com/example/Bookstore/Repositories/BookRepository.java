package com.example.Bookstore.Repositories;

import com.example.Bookstore.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByName(String name);

    Book findByName(String name);
}
