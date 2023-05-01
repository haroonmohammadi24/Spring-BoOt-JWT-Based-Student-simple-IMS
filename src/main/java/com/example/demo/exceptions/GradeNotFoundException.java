package com.example.demo.exceptions;

public class GradeNotFoundException extends RuntimeException{
	
	public GradeNotFoundException(Long id) {
        super("The Grade id '" + id + "' does not exist in our records");
    }
}
