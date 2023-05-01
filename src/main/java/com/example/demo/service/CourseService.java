package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.CourseNotFoundException;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseService {
	
	private CourseRepository courseRepo;
	
	private StudentRepository studentRepo;
	
	public Course saveTheCourse(Course course) {
		
		return courseRepo.save(course);
	}

	public List<Course> getAllCourse() {
	
		return courseRepo.findAll();
	}

	public Course deleteOneCourse(Long id) {
        Course course=courseRepo.findById(id).orElseThrow(()->new CourseNotFoundException(id));
		courseRepo.delete(course);
        return course;
	}

	public Course updateOneCourse(Long id, Course updateCourse) {
		Course course=courseRepo.findById(id).orElseThrow(()->new CourseNotFoundException(id));
		course.setCode(updateCourse.getCode());
		course.setDescription(updateCourse.getDescription());
		course.setSubject(updateCourse.getSubject());
		
		courseRepo.save(course);
		
		return course;
	}
	
	public Course addStudentToCourse(Long studentId,Long courseId) {
		
		Course course=courseRepo.findById(courseId).orElseThrow(()->new CourseNotFoundException(courseId));
		Student student=studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException(studentId));
		
		course.getStudents().add(student);
		courseRepo.save(course);
		
		return course;
	}
}
