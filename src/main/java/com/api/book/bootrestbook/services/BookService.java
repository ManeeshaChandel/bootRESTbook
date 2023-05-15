package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
    //we are making it static bcz static function only takes static variables
    private static List<Book> list=new ArrayList<>();
    //this static block will execute when we create object of the class
   static{
        list.add(new Book(12,"JAVA KAHE KI","Hari Naam"));
        list.add(new Book(13,"Oggy and Cockroaches","CN"));
        list.add(new Book(14,"Rolly Polly","NICK"));
   }
   public List<Book> getALLBooks(){
        return list;
   }
   //get single book by id
    public Book getBookById(int id){
       Book book=null;
       book=list.stream().filter(e->e.getId()==id).findFirst().get();
       return book;
    }
    public Book addBook(Book b){
       list.add(b);
       return b;
    }

    public void deleteBook(int bid) {
       list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
        //creating a list in which book object having id=bid is not present
    }
    //update the book
    public void updateBook(Book book, int bookId) {
       list=list.stream().map(b->{
           if(b.getId()==bookId){
               b.setTitle(book.getTitle());
               b.setAuthor(book.getAuthor());
           }
           return b;
       }).collect(Collectors.toList());
    }
}
