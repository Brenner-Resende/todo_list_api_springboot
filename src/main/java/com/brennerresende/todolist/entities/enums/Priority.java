package com.brennerresende.todolist.entities.enums;

public enum Priority {
	HIGH(1),
	MEDIUM(2),
	LOW(3);
	
	private int code;
	
	private Priority(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Priority valueOf(int code) {
		for (Priority value : Priority.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Priority code");
	}
}
