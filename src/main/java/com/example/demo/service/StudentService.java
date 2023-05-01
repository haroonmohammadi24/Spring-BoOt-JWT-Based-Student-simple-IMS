package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	public Student saveTheStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

	public Student getOneStudent(Long id) {
		// TODO Auto-generated method stub
		return studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException(id));
	}

	public Student deleteOneStudent(Long id) {
		Student student=studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		studentRepo.delete(student);
		return student;
	}

	public Student updateOneStudent(Long id, Student updateStudent) {
		Student student=studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		student.setName(updateStudent.getName());
		student.setBirthDay(updateStudent.getBirthDay());
		studentRepo.save(student);
		return student;
	} 

}
