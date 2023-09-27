package com.service.bookservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.bookservice.entity.Book;
import com.service.bookservice.exceptions.NoBookFoundException;

@Service
public interface BookService {
	
	public List<Book> getBookByName(String name) throws NoBookFoundException;
	
	public String addBook(Book book);
	
	public String updatebook(Book book) throws Exception;

}
