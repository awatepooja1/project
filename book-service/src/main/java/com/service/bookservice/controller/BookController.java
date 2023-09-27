package com.service.bookservice.controller;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.bookservice.entity.Book;
import com.service.bookservice.entity.Message;
import com.service.bookservice.entity.Payload;
import com.service.bookservice.entity.User;
import com.service.bookservice.exceptions.NoBookFoundException;
import com.service.bookservice.repository.BookRepository;
import com.service.bookservice.service.BookService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@RestController
@RestControllerAdvice
@RequestMapping("/book-service")
public class BookController {
	
	@Autowired
	public BookService bookService;
	
	
	public BookRepository bookRepo;
	
	ErrorResponse errorResponse = new ErrorResponse();
	
	@PostMapping("/search/books/{name}")
	public ResponseEntity<?> getBooksByName(@PathVariable String name){
		try {
		List<Book> books = bookService.getBookByName(name);
		return new ResponseEntity<>(books, HttpStatus.FOUND);
		} catch (NoBookFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	   } catch (Exception e){
		   errorResponse.setStatusCode("500");
		   errorResponse.setMessage(e.getMessage()); 
	     return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	   }
		
	}
	
	@ExceptionHandler(value = { ServletException.class })
	@PutMapping("/add/book")
	  public ResponseEntity<?> getBook(@RequestBody Book book, @RequestHeader Map<String,String> header){
	  try{
		 ObjectMapper objectMapper = new ObjectMapper();
	     if(header.isEmpty()) {
	    	 errorResponse.setStatusCode("500");
			 errorResponse.setMessage("Please provide the token"); 
			  return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		 String bearerToken = header.get("authorization");
		 String token = bearerToken.substring(7);
		 validateToken(token);
		 String[] parts = token.split("\\.");
		 String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
		 Payload p = objectMapper.readValue(payload, Payload.class);
			
		 String userDetails = p.getSub();
		 byte[] bytes = Base64.getUrlDecoder().decode(userDetails);
		 String json = new String(bytes, StandardCharsets.UTF_8);
		 User user = objectMapper.readValue(json, User.class);
		 
		 if(user.getRole().equals("admin")) {
			 String msg = bookService.addBook(book);
			 return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		 }else {
			 Message m = new Message("Unauthorized", "You are not authorized for this service");
			 return new ResponseEntity<>(m, HttpStatus.UNAUTHORIZED);
		 }
		
	   }catch (Exception e){
		     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		   }
	  
	  }
	
	@DeleteMapping("/delete/book")
	  public ResponseEntity<?> deleteBook(@RequestBody String name){
	  try{
		  Optional<List<Book>> books = bookRepo.findByName(name);
		  if(books.isEmpty()) {
			  return new ResponseEntity<>("Book does not exist.", HttpStatus.OK);
		  }
		  for(Book book : books.get()) {
		  bookRepo.delete(book);
		  }
	     return new ResponseEntity<>("Book entries deleted", HttpStatus.OK);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
	  }
	
	@PutMapping("/update/book")
	  public ResponseEntity<?> updateBook(@RequestBody Book book){
	  try{
		 String updateMessage =  bookService.updatebook(book);
		 
	     return new ResponseEntity<>(updateMessage, HttpStatus.OK);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
	  }
	
		

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey("secret").parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BadCredentialsException("INVALID_TOKEN");
		}
	}

}
