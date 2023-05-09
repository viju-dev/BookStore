# BookStore Application

This is a simple bookstore application that allows users to perform CRUD (Create, Read, Update, Delete) operations on a collection of books. It is built using the Spring Boot framework and uses a MySQL database to store book data. The application includes a RESTful API, which can be consumed by a frontend application to perform the above operations.

## Prerequisites :

    JDK 11 or later
    MySQL database
    Maven

## Getting Started :

To run the application, follow these steps:

    Clone this repository: git clone https://github.com/viju-dev/bookstore.git
    Navigate to the project directory: cd bookstore
    Open the application.properties file located in src/main/resources and update the MySQL database connection settings with your own database settings.
    Run the application: mvn spring-boot:run
    The application will now be running on http://localhost:8080.

## Endpoints :

The following endpoints are available in the API:

<br>


    GET /book/getAllBooks
- Retrieves a list of all books in the inventory.

<br>


    GET /book/getBookById?id=
- Retrieves the book with the specified ID.

<br>


     GET /book/getBooksByName?name=
- Retrieves a list of all books with the specified name.

<br>


     GET /book/getBooksByAuthor?author=
- Retrieves a list of all books written by the specified author.

<br>


     POST /book/addBook
- Adds a new book to the inventory.

<br>


     PUT /book/updateBook
- Updates an existing book in the inventory.

<br>


     DELETE /book/deleteBookById?id=
- Deletes the book with the specified ID from the inventory.

<br>

## Testing :

The application includes JUnit 5 test cases for the BookService class. To run the tests, execute the following command:

bash

    mvn test
or

    run the file "BookServiceImplTest" which is located at "BookStore/src/test/java/com/example/Bookstore/Services/Impl"

## Built With :

- Spring Boot
- MySQL
- Maven
- JUnit 5
- Mockito
