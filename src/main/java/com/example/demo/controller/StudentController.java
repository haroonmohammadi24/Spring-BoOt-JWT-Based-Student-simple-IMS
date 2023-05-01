package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveTheStudent(student);
	}
	@GetMapping("/student")
	public List<Student> getAllStudent() {
		return studentService.getStudent();
	}
	

	@GetMapping("/student/{id}")
	public Student getOneStudent(@PathVariable Long id) {
		return studentService.getOneStudent(id);
	}
	
	@DeleteMapping("/student/{id}")
	public Student deleteOneStudent(@PathVariable Long id) {
		return studentService.deleteOneStudent(id);
	}
	
	@PutMapping("/student/{id}")
	public Student updateOneStudent(@PathVariable Long id,@RequestBody Student student) {
		return studentService.updateOneStudent(id,student);
	}
}
