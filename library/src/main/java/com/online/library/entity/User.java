package com.online.library.entity;

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


public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
}

public User() {
	// TODO Auto-generated constructor stub
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
}