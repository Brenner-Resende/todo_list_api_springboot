package com.brennerresende.todolist.services.exceptions;

public class InvalidDataInput extends IllegalArgumentException{
	private static final long serialVersionUID = 1L;
	
	public InvalidDataInput(String msg) {
		super(msg);
	}
}
