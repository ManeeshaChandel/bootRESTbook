package com.api.book.bootrestbook.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="books") //our database table will be created and name as "Books"  //optional annotation
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="book_Id") //changing the column id name    //optional annotation
    private int id;
    private String title;
    @OneToOne(cascade = CascadeType.ALL) //we are using cascade bcz we need to make object of Author before object of Book
                                           //through this we will get author_id which is required in Book table as a forein key(bcz of onetoone mapping)
    @JsonManagedReference
    private Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
