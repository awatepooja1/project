package com.online.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.online.library.entity.Book;
import com.online.library.repository.BookRepository;
import com.online.library.service.BookService;

public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRepo;

	@Override
	public List<Book> getBookByName(String name) {

		return bookRepo.findByName(name);

	}

	@Override
	public Book addBook(Book book) {
		return bookRepo.save(book);

	}

}
