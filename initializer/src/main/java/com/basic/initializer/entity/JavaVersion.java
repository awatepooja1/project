package com.basic.initializer.entity;

public enum JavaVersion {

	Java8(8), Java11(11), Java17(17), Java20(20);

	private int version;

	JavaVersion(int version) {
		this.version = version;
	}

}
