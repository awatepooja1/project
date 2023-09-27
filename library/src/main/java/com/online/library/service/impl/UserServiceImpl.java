package com.online.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.online.library.entity.User;
import com.online.library.repository.UserRepository;
import com.online.library.security.impl.SaltBasedPasswordEncryption;
import com.online.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public SaltBasedPasswordEncryption saltpass;

	/*
	 * public UserServiceImpl(UserRepository userRepository) {
	 * 
	 * this.userRepository = userRepository; }
	 */

	public UserServiceImpl() {
	}

	// method to save user with encrypted password
	@Cacheable(cacheNames = "userCache", key = "#p0", condition = "#p0!=null")
	@Override
	public User saveUser(User user) {
		if (user.userName == null || user.password == null) {
			throw new IllegalArgumentException("Invalid id and password");
		}
		// salt value
		String saltValue = saltpass.getSaltvalue(30);
		// encrypted password
		String encryptedPassword = saltpass.generateSecurePassword(user.getPassword(), saltValue);
		user.setPassword(encryptedPassword);
		// System.out.println(encryptedPassword);
		return userRepository.save(user);
	}

	// method to get user details by passing name and password
	@Cacheable(cacheNames = "userSearchCache", key = "#z0", condition = "#z0!=null")
	@Override
	public User getUserByNameAndPassword(String name, String password) throws IllegalArgumentException {
		User user = userRepository.findByUserNameAndPassword(name, password);
		if (user == null) {
			throw new IllegalArgumentException("Invalid id and password");
		}
		return user;
	}
}
