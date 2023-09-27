package com.service.bookservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
	
	public Message(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}
	String type;
	String msg;
	
}
