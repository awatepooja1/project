package com.online.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.online.library.entity.Book;

@Service
public interface BookService {
	
	public List<Book> getBookByName(String name);
	
	public Book addBook(Book book);

}
