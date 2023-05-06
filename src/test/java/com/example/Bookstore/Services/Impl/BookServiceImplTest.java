package com.example.Bookstore.Services.Impl;
import org.junit.jupiter.api.Test;
//
import com.example.Bookstore.Entities.Book;
import com.example.Bookstore.Repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(1)
                .name("Book Name")
                .author("Author Name")
                .description("Book Description")
                .price(10.0)
                .build();
    }

    @Test
    @DisplayName("Add Book Test")
    void add_book_test() throws Exception {
        when(bookRepository.save(book)).thenReturn(book);

        String result = bookService.add_book(book);

        Assertions.assertEquals("Book has been added Successfully", result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    @DisplayName("Get Book Test")
    void get_book_test() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Book result = bookService.get_book(1);

        Assertions.assertEquals(book, result);
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Get Books By Author Test")
    void get_books_by_author_test() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAllByAuthor("Author Name")).thenReturn(bookList);

        List<Book> result = bookService.get_books_by_author("Author Name");

        Assertions.assertEquals(bookList, result);
        verify(bookRepository, times(1)).findAllByAuthor("Author Name");
    }

    @Test
    @DisplayName("Get Books By Name Test")
    void get_books_by_name_test() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAllByName("Book Name")).thenReturn(bookList);

        List<Book> result = bookService.get_books_by_name("Book Name");

        Assertions.assertEquals(bookList, result);
        verify(bookRepository, times(1)).findAllByName("Book Name");
    }

    @Test
    @DisplayName("Get All Books Test")
    void get_all_books_test() throws Exception {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.get_all_books();

        Assertions.assertEquals(bookList, result);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Update Book Test")
    void update_book_test() throws Exception {

        when(bookRepository.findByName("Updated Book Name")).thenReturn(book);

        Book updatedBook = Book.builder()
                .id(1)
                .name("Updated Book Name")
                .author("Updated Author Name")
                .description("Updated Book Description")
                .price(20.0)
                .build();

        String result = bookService.update_book(updatedBook);
        System.out.println(result.toString());
        Assertions.assertEquals("Book has been updated Successfully...", result);
        verify(bookRepository, times(1)).findByName("Updated Book Name");//"Updated Book Name"//book.getName()
        verify(bookRepository, times(1)).save(updatedBook);
    }


    @Test
    @DisplayName("Remove Book Test")
    void remove_book_test() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        String result = bookService.remove_book(1);

        Assertions.assertEquals("Book has been deleted from inventory", result);
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).deleteById(1);
    }

}