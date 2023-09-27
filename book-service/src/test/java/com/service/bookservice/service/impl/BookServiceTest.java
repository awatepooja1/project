package com.service.bookservice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;

//import java.lang.reflect.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.service.bookservice.entity.Book;
import com.service.bookservice.exceptions.NoBookFoundException;
import com.service.bookservice.repository.BookRepository;



@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	public BookRepository bookRepo;

	@InjectMocks
	public BookServiceImpl bookService;

	Book book;
	
	NoBookFoundException noBookFoundException;

	
	 
	@BeforeEach
	void init() {
		this.book = new Book("b", "b");
	}

	@Test
	void testSaveBook() {
		
		Book book1 = new Book("b","b");
		when(bookRepo.save(book)).thenReturn(book);
		String result = bookService.addBook(book);
		assertEquals("Saved Successfully",result);
		

	}
	
	@Test
	void testGetBookByName_whenBookIsNotAvailable() throws Exception {
		
		 Assertions.assertThrows(NoBookFoundException.class,()-> bookService.getBookByName("b"));
	}


}
