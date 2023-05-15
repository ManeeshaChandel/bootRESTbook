package com.api.book.bootrestbook.controller;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //using this annotation you don't need to use  @ResponseBody and also for making RESTAPI we will use this
public class BookController {
    @Autowired  //you need to use @Component above class
    private BookService bookService;
    @GetMapping("/books")//@RequestMapping(value="/books",method= RequestMethod.GET)
   
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list=bookService.getALLBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            //build function will create new ResponseEntity object
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book =bookService.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.of(Optional.of(book));//still it will not work bcz //give 500 internal server error
        //if bookid is not present it will throw an exception, so we need to add exception as well
    }
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){ //@RequestBody is for mappping the Book object from the request we get into this book object
        Book b=null;
        try{
            b=this.bookService.addBook(book);
            System.out.println(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try{
            this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        try{
            this.bookService.updateBook(book,bookId);
            return ResponseEntity.ok().body(book);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
//        Jackson: Spring uses the Jackson library for JSON serialization by default.
//        Jackson provides an ObjectMapper class that converts Java objects into JSON format and vice versa.

//REST API, on the other hand, is a specific type of API that follows the principles of the
// REST (Representational State Transfer) architectural style.
// REST is a set of guidelines for building web services that use HTTP as the communication protocol.

//The key difference between API and REST API is that API is a general term
// that can refer to any type of interface for software components,
// while REST API is a specific type of API that follows the REST architectural style.