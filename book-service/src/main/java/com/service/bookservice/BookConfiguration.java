package com.service.bookservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.service.bookservice.repository.BookRepository;
import com.service.bookservice.service.BookService;
import com.service.bookservice.service.impl.BookServiceImpl;

@Configuration
public class BookConfiguration {
	
	@Bean
	public BookService getBookService() {
		return new BookServiceImpl();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> 
        request.antMatchers("/book-service/**").permitAll()
                        .anyRequest().authenticated())
                .csrf().disable();
        return http.build();
    }

}
