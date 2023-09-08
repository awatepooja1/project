package com.online.library.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.library.entity.User;
import com.online.library.security.JwtTokenGenerator;
import com.online.library.service.UserService;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
@RequestMapping("/api")
public class UserController {


private UserService userService;
private JwtTokenGenerator jwtGenerator;

  @Autowired
  public UserController(UserService userService, JwtTokenGenerator jwtGenerator){
    this.userService=userService;
    this.jwtGenerator=jwtGenerator;
  }

  @PostMapping("/register")
  public ResponseEntity<?> postUser(@RequestBody User user){
  try{
     userService.saveUser(user);
     return new ResponseEntity<>(user, HttpStatus.CREATED);
   } catch (Exception e){
     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
   }
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody User user) {
    try {
      if(user.getUserName() == null || user.getPassword() == null) {
      throw new Exception("UserName or Password is Empty");
    }
    User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
    if(userData == null){
       throw new Exception("UserName or Password is Invalid");
    }
       return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
    } catch (Exception e) {
       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }
  
  
  @GetMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtGenerator.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(token);
	}
  
  public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}