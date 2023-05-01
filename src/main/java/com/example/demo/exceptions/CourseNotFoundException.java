package com.example.demo.exceptions;

public class CourseNotFoundException extends RuntimeException{
	
	public CourseNotFoundException(Long id) {
		super("The Student id "+id+" does not exist in our records");
	}
}
