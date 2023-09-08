package com.online.library.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.online.library.entity.User;
import com.online.library.repository.UserRepository;
import com.online.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	

	/*
	 * public UserServiceImpl(UserRepository userRepository) {
	 * 
	 * this.userRepository = userRepository; }
	 */

	public UserServiceImpl() {
	}

	@Cacheable(cacheNames="userCache", key="#id")
	@Override
	public User saveUser(User user) {
		if (user.userName == null || user.password == null) {
			throw new IllegalArgumentException("Invalid id and password");
		}
		 return userRepository.save(user);
	}

	@Cacheable(cacheNames = "userSearchCache"/* , key="#u1" */)
	@Override
	public User getUserByNameAndPassword(String name, String password) throws IllegalArgumentException {
		User user = userRepository.findByUserNameAndPassword(name, password);
		if (user == null) {
			throw new IllegalArgumentException("Invalid id and password");
		}
		return user;
	}
}
