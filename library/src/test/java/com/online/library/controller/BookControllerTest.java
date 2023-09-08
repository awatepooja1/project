package com.online.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.online.library.entity.Book;
import com.online.library.entity.User;
import com.online.library.security.JwtTokenGenerator;
import com.online.library.service.BookService;
import com.online.library.service.UserService;



@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
	public BookService bookService;

	@InjectMocks
	public BookController bookController;
	
	

	Book book;

	@BeforeEach
	void init() {

		this.book = new Book("b", "b");
	}

	//getBook
	@Test
	void test_getBook() {
		// User createdUser = new User("q","q");
		when(bookService.addBook(book)).thenReturn(book);
		ResponseEntity<?> responseEntity = bookController.getBook(book);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}
	
	//getBookByName
	@Test
	void test_getBookByName() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookService.getBookByName(book.getName())).thenReturn(books);
		ResponseEntity<?> responseEntity = bookController.getBooksByName(book.getName());

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(302);
	}

}
