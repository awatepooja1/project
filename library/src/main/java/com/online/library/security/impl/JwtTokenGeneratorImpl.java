
package com.online.library.security.impl;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.library.entity.User;
import com.online.library.security.JwtTokenGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${app.jwttoken.message}")
	private String message;

	@Value("${jwt.expirationDateInMs}")
	private int jwtExpirationInMs;

	@Value("${jwt.refreshExpirationDateInMs}")
	private int refreshExpirationDateInMs;

	@Cacheable(cacheNames = "tokenCache", key = "#t0", condition = "#t0!=null")
	@Override
	public String generateToken(User user) {
		String jwtToken = "";

		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
		String base64 = new String(Base64.getUrlEncoder().encode(bytes));

		jwtToken = Jwts.builder().setSubject(base64).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS256, "secret").compact();
		return jwtToken;
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BadCredentialsException("INVALID_TOKEN");
		}
	}

	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	

}
