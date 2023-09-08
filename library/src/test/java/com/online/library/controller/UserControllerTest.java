package com.online.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.online.library.entity.User;
import com.online.library.security.JwtTokenGenerator;
import com.online.library.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	public UserService userService;

	@InjectMocks
	public UserController userController;
	
	@Mock
	JwtTokenGenerator jwt;

	User user;

	@BeforeEach
	void init() {

		this.user = new User("q", "q");
	}

	@Test
	void test_register_user() {
		// User createdUser = new User("q","q");
		when(userService.saveUser(user)).thenReturn(user);
		ResponseEntity<?> responseEntity = userController.postUser(user);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}
	
	@Test
	void test_login_user() throws Exception {
		// User createdUser = new User("q","q");
		when(userService.getUserByNameAndPassword(user.userName, user.password)).thenReturn(user);
		ResponseEntity<?> responseEntity = userController.loginUser(user);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

}
