package com.api.book.bootrestbook.controller;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //using this annotation you don't need to use  @ResponseBody and also for making RESTAPI we will use this
public class BookController {
    @Autowired  //you need to use @Component above class
    private BookService bookService;
    @GetMapping("/books")//@RequestMapping(value="/books",method= RequestMethod.GET)

    public List<Book> getBooks(){

        return this.bookService.getALLBooks();
//        Jackson: Spring uses the Jackson library for JSON serialization by default.
//        Jackson provides an ObjectMapper class that converts Java objects into JSON format and vice versa.
    }
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){ //@RequestBody is
        // for mappping the Book object from the request we get into this book object
        Book b=this.bookService.addBook(book);
        return b;
    }
    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
        this.bookService.deleteBook(bookId);
    }
    //update book handler
    @PutMapping("/books/{bookId}")
    public Book updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        this.bookService.updateBook(book,bookId);
        return book;
    }
}
//REST API, on the other hand, is a specific type of API that follows the principles of the
// REST (Representational State Transfer) architectural style.
// REST is a set of guidelines for building web services that use HTTP as the communication protocol.

//The key difference between API and REST API is that API is a general term
// that can refer to any type of interface for software components,
// while REST API is a specific type of API that follows the REST architectural style.