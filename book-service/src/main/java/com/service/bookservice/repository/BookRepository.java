package com.service.bookservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.service.bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(" FROM book b WHERE b.name LIKE %:name%")
	Optional<List<Book>> findByName(@Param("name") String name);
	
	@Modifying
	@Query("update book b set b.genre = genre where b.name = name")
	boolean update(@Param("name") String name, @Param("genre") String genre);

}
