package com.online.library.service;

import com.online.library.entity.User;

public interface UserService {
	
	  public User saveUser(User user);
	  public User getUserByNameAndPassword(String name, String password) throws Exception;


}
