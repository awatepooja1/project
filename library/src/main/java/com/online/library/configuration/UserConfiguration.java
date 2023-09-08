package com.online.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.online.library.entity.User;
import com.online.library.repository.UserRepository;
import com.online.library.service.UserService;
import com.online.library.service.impl.UserServiceImpl;

@Configuration
public class UserConfiguration {
	
	@Bean
	@Primary
	public UserService getUserService() {
		return new UserServiceImpl();
	}
	
	
	
	
	 
	

}
