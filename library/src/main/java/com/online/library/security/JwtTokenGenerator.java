package com.online.library.security;

import java.util.Map;

import com.online.library.entity.User;

public interface JwtTokenGenerator {

	Map<String, String> generateToken(User user);
	
	public boolean validateToken(String authToken);
	
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject);
	
	String doGenerateToken(Map<String, Object> claims, String subject);


}