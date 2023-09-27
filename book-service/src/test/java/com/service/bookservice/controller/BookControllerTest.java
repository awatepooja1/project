package com.service.bookservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.bookservice.entity.Book;
import com.service.bookservice.entity.User;
import com.service.bookservice.exceptions.NoBookFoundException;
import com.service.bookservice.repository.BookRepository;
import com.service.bookservice.service.BookService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

	@Mock
	public BookService bookService;
	
	@Mock
	public BookRepository bookRepo;

	@InjectMocks
	public BookController bookController;

	@Mock
	Book book;
	
	ErrorResponse errorResponse = new ErrorResponse("500","Please provide the token");

	@BeforeEach
	void init() {

		this.book = new Book("b", "b");
	}

	// getBook
	@Test
	void test_getBook() {
		// User createdUser = new User("q","q");
		Map<String,String> header = new HashMap<String, String>();
		header.put("authorization", "eeee");
		
		ResponseEntity<?> responseEntity = bookController.getBook(book, header);
		//will return 409(conflict) as the token is invalid
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(409);
	}

	// getBook with valid header
		@Test
		void test_getBook2() {
			 User createdUser = new User();
			 createdUser.setUserName("user");
			 createdUser.setRole("admin");
			 createdUser.setPassword("user");
			String token = generateToken(createdUser);
			Map<String,String> header = new HashMap<String, String>();
			header.put("authorization", "Bearer "+token);
			boolean valid = bookController.validateToken(token);
			assertTrue(valid);
			ResponseEntity<?> responseEntity = bookController.getBook(book, header);
			//will return 409(conflict) as the token is invalid
			assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
		}
		
		// getBook for unauthorized user.
				@Test
				void test_getBook3() {
					 User createdUser = new User();
					 createdUser.setUserName("user");
					 createdUser.setRole("test");
					 createdUser.setPassword("user");
					String token = generateToken(createdUser);
					Map<String,String> header = new HashMap<String, String>();
					header.put("authorization", "Bearer "+token);
					boolean valid = bookController.validateToken(token);
					assertTrue(valid);
					ResponseEntity<?> responseEntity = bookController.getBook(book, header);
					//will return 409(conflict) as the token is invalid
					assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
				}

	
	
	// getBook
		@Test
		void test_getBook_with_empty_header() {
			// User createdUser = new User("q","q");
			Map<String,String> header = new HashMap<String, String>();
			
		
			ResponseEntity<?> responseEntity = bookController.getBook(book, header);
			//will return 500(conflict) as the header is empty
			assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
			assertThat(responseEntity.equals(errorResponse));
		}
		
	// getBookByName
	@Test
	void test_getBookByName1() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookService.getBookByName(book.getName())).thenReturn(books);
		ResponseEntity<?> responseEntity = bookController.getBooksByName(book.getName());

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(302);
	}
	
	// getBookByName throws no book found exception
		@Test
		void test_getBookByName2() throws Exception {
			
			when(bookService.getBookByName("noBook")).thenThrow(NoBookFoundException.class);
			ResponseEntity<?> responseEntity = bookController.getBooksByName("noBook");

			assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
		}

	@Test
	void test_deleteBook1() throws Exception {
		Book newBook = new Book();
		newBook.setName("new");
		newBook.setGenre("new");
		bookService.addBook(newBook);
		List<Book> a = new ArrayList<>();
		a.add(newBook);
		Optional<List<Book>> newBooks = Optional.of(a);
		when(bookRepo.findByName("new")).thenReturn(newBooks);
		ResponseEntity<?> responseEntity = bookController.deleteBook("new");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	//book not deleted as book does not exist and returned ok status with msg book does not exist
	@Test
	void test_deleteBook2() throws Exception {
		ResponseEntity<?> responseEntity = bookController.deleteBook("a");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	//when book exists already 
	@Test
	void test_updateBook1() throws Exception {
		Book book = new Book("a", "a");
		when(bookService.updatebook(book)).thenReturn("Book updated");
		//when(bookRepo.update("a", "a")).thenReturn(true);
		ResponseEntity<?> responseEntity = bookController.updateBook(book);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	//when book does not exist
		@Test
		void test_updateBook2() throws Exception {
			Book book = new Book("a", "a");
			when(bookService.updatebook(book)).thenReturn("Added new entry as book does not exist");
			//when(bookRepo.update("a", "a")).thenReturn(true);
			ResponseEntity<?> responseEntity = bookController.updateBook(book);

			assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		}
		
		
		
		
		public String generateToken(User user) {
			String jwtToken = "";

			ObjectMapper objectMapper = new ObjectMapper();
			String json = null;
			try {
				json = objectMapper.writeValueAsString(user);
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
			byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
			String base64 = new String(Base64.getUrlEncoder().encode(bytes));

			jwtToken = Jwts.builder().setSubject(base64).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 50000))
					.signWith(SignatureAlgorithm.HS256, "secret").compact();
			return jwtToken;
		}
}
