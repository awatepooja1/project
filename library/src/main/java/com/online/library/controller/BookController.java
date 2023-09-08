package com.online.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.library.entity.Book;
import com.online.library.entity.User;
import com.online.library.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	public BookService bookService;
	
	@PostMapping("/search/books")
	public ResponseEntity<?> getBooksByName(@PathVariable String name){
		try {
		List<Book> books = bookService.getBookByName(name);
		return new ResponseEntity<>(books, HttpStatus.FOUND);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
		
	}
	
	@PostMapping("/add/book")
	  public ResponseEntity<?> getBook(@RequestBody Book book){
	  try{
		  bookService.addBook(book);
	     return new ResponseEntity<>(book, HttpStatus.CREATED);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
	  }

}
