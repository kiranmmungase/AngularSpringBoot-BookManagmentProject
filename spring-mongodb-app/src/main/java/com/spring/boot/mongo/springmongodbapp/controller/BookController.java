package com.spring.boot.mongo.springmongodbapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mongo.springmongodbapp.entity.Book;
import com.spring.boot.mongo.springmongodbapp.exception.ResourceNotFoundException;
import com.spring.boot.mongo.springmongodbapp.repository.BookRepository;


@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/addBook")
	public Book saveBook( @RequestBody Book book) {
		return bookRepository.save(book);
		
	//	return "added book with id:" + book.getId();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/findAllBooks")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBook(@PathVariable String id){
		return bookRepository.findById(id);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable String id) {
		bookRepository.deleteById(id);
		return "Book Deleted with id:"+id;
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> UpdateBook(@PathVariable String id,@RequestBody Book bookDetails){
		Book book = bookRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Exception not present in book with id"+id));
		book.setBookName(bookDetails.getBookName());
		book.setAuthorName(bookDetails.getAuthorName());
		book.setPublisherName(bookDetails.getPublisherName());
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
		
	}
}
