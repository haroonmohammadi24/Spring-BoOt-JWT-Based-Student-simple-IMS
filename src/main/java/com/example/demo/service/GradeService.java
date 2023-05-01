package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.CourseNotFoundException;
import com.example.demo.exceptions.GradeNotFoundException;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class GradeService {

	private GradeRepository gradeRepo;
	private StudentRepository studentRepo;
	private CourseRepository courseRepo;

	
	
	public Grade saveTheGrade(Grade grade,Long studentId,Long courseID) {
		Student student=studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException(studentId));
		Course course=courseRepo.findById(courseID).orElseThrow(()->new CourseNotFoundException(courseID));
		grade.setStudent(student);
		grade.setCourse(course);
		return gradeRepo.save(grade);
	}
//
//	public List<Grade> getGrade() {
//	
//		return gradeRepo.findAll();
//	}

	public List<Grade> getAllGrade(Long studentId,Long CourseId) {
	
		return gradeRepo.findByStudentIdAndCourseId(studentId,CourseId);
	}

	public Grade deleteOneGrade(Long id) {
		Grade grade=gradeRepo.findById(id).orElseThrow(()->new GradeNotFoundException(id));
		
         return grade;
	}

	public Grade updateOneGrade(Long id, Grade updateGrade) {
		Grade grade=gradeRepo.findById(id).orElseThrow(()->new GradeNotFoundException(id));
		grade.setScore(updateGrade.getScore());
		gradeRepo.save(grade);
		return grade;
	}
	
	
	

}
