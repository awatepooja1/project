package com.basic.initializer.model;

public enum JavaVersion {

	Java8(8), Java11(11), Java17(17), Java20(20);

	private int version;

	JavaVersion(int version) {
		this.version = version;
	}

}
