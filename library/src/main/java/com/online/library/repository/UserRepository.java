package com.online.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.library.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	public User findByUserNameAndPassword(String userName, String password);


}
