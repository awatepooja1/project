package com.online.library;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//import java.lang.reflect.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.library.entity.User;
import com.online.library.repository.UserRepository;
import com.online.library.service.UserService;
import com.online.library.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	public UserRepository userRepo;

	@InjectMocks
	public UserServiceImpl userService;

	User user;

	@BeforeEach
	void init() {
		this.user = new User("q", "q");
	}

	@Test
	void testSaveUser() {
		// User user = new User("q","q");
		User createdUser = new User("q", "q");
		when(userRepo.save(user)).thenReturn(user);
		User result = userService.saveUser(user);
		assertEquals(createdUser.getUserName(), result.getUserName());
		assertEquals(createdUser.getPassword(), result.getPassword());

	}

	@Test
	void testUserService_null() {

		assertThrows(IllegalArgumentException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				User user1 = new User(null, null);
				 userService.saveUser(user1);
				 userService.getUserByNameAndPassword(null, null);
			}
		});
	}

	@Test
	void testGetUserByNameAndPassword() throws Exception {
		when(userRepo.findByUserNameAndPassword(user.userName, user.password)).thenReturn(user);
		User result = userService.getUserByNameAndPassword("q", "q");
		assertEquals(result.getUserName(), user.getUserName());
	}

}
