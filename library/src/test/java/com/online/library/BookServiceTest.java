package com.online.library;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

//import java.lang.reflect.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.library.entity.Book;
import com.online.library.entity.User;
import com.online.library.repository.BookRepository;
import com.online.library.repository.UserRepository;
import com.online.library.service.UserService;
import com.online.library.service.impl.BookServiceImpl;
import com.online.library.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	public BookRepository bookRepo;

	@InjectMocks
	public BookServiceImpl bookService;

	Book book;

	@BeforeEach
	void init() {
		this.book = new Book("b", "b");
	}

	@Test
	void testSaveBook() {
		
		Book book1 = new Book("b","b");
		when(bookRepo.save(book)).thenReturn(book);
		Book result = bookService.addBook(book);
		assertEquals(book1.getName(), result.getName());
		assertEquals(book1.getGenre(), result.getGenre());

	}
	
	@Test
	void testGetBookByName() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookRepo.findByName("b")).thenReturn(books);
		List<Book> result = bookService.getBookByName("b");
		assertEquals(result.get(0).getName(), books.get(0).getName());
	}


}
