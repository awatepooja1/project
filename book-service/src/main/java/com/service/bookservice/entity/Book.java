package com.service.bookservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "book")
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column
	String name;
	
	@Column
	String genre;

	

	public Book(String name, String genre) {
		super();
		this.name = name;
		this.genre = genre;
	}

	public Book() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", genre=" + genre + "]";
	}
	

}
