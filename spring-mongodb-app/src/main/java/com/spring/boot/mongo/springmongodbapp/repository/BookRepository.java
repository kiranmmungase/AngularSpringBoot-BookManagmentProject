package com.spring.boot.mongo.springmongodbapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.mongo.springmongodbapp.entity.Book;

public interface BookRepository extends MongoRepository<Book,String> {

}
