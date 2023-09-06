package com.basic.initializer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PackagingType {

	Jar("jar"), War("war");

	//@JsonValue
	private String type;

	PackagingType(String type) {
		this.type = type;
	}

}
