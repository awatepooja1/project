package com.online.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.online.library.entity.User;
import com.online.library.repository.UserRepository;
import com.online.library.security.impl.SaltBasedPasswordEncryption;
import com.online.library.service.UserService;
import com.online.library.service.impl.UserServiceImpl;

@Configuration
public class UserConfiguration {
	
	@Bean
	@Primary
	public UserService getUserService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public SaltBasedPasswordEncryption getSaltBasedPasswordEncryption() {
		return new SaltBasedPasswordEncryption();
	}
	
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}

}
