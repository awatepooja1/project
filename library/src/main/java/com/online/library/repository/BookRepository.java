package com.online.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("FROM books b WHERE b.name LIKE %:name%")
	List<Book> findByName(@Param("name") String name);

}
