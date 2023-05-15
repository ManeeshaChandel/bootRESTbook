package com.api.book.bootrestbook.dao;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer>{  //here we wre defining that our data is of Book type and it's id is of Integer type
    //WE WILL get all the CRUD functions by using interface and extending CrudRepository
    public Book findById(int id);
}
