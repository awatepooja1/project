package com.service.bookservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.bookservice.entity.Book;
import com.service.bookservice.exceptions.NoBookFoundException;
import com.service.bookservice.repository.BookRepository;
import com.service.bookservice.service.BookService;
import java.util.Collections;

public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRepo;

	private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public List<Book> getBookByName(String name) throws NoBookFoundException {

		Optional<List<Book>> books = bookRepo.findByName(name);
		if (books.isEmpty()) {
			logger.debug("method getBookByName from bookServiceImpl returned empty list.");
			throw new NoBookFoundException();
		}
		if (books.isPresent()) {
			return books.get();
		}
		return Collections.emptyList();

	}

	@Override
	public String addBook(Book book) {
		logger.debug("adding book");
		Optional<List<Book>> books = bookRepo.findByName(book.getName());
		if (books.isEmpty()) {
			bookRepo.save(book);
			return "Saved Successfully";
		}
		return "Book already exists";
	}

	@Transactional
	@Override
	public String updatebook(Book book) throws Exception {

		try {
			Optional<List<Book>> books = bookRepo.findByName(book.getName());
			if (books.isPresent()) {
				for (Book perBook : books.get()) {
					bookRepo.update(perBook.getName(), perBook.getGenre());
				}
				return "Book updated";
			}else {
				addBook(book);
				return "Added new entry as book does not exist";
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return null;

	}

}
