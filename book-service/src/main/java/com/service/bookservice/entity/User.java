package com.service.bookservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {

@Id
public String userName;
public String password;
public String role;





public User(String userName, String password, String role) {
	super();
	this.userName = userName;
	this.password = password;
	this.role = role;
}

public User() {
	
}

public String getUserName() {
    return userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}


public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

@Override
public String toString() {
	return "User [userName=" + userName + ", password=" + password + ", role=" + role + "]";
}

}