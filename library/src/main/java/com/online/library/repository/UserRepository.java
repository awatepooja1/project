package com.online.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.library.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	public User findByUserNameAndPassword(String userName, String password);

	public Optional<User> findByUserName(String username);


}
