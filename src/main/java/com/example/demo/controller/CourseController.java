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

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

@RestController
@RequestMapping(value = "/api/")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@PostMapping("course")
	public Course saveCourse(@RequestBody Course course) {
		return courseService.saveTheCourse(course);
	}
//	@GetMapping("/course")
//	public List<Course> getAllCourse() {
//		return courseService.getCourse();
//	}
	

	@GetMapping("course")
	public List<Course> getAllCourse() {
		return courseService.getAllCourse();
	}
	
	@DeleteMapping("/course/{id}")
	public Course deleteOneCourse(@PathVariable Long id) {
		return courseService.deleteOneCourse(id);
	}
	
	@PutMapping("/course/{id}")
	public Course updateOneCourse(@PathVariable Long id,@RequestBody Course course) {
		return courseService.updateOneCourse(id,course);
	}
	
	@PostMapping("course/{courseId}/student/{studentId}")
	public Course saveCourse(@PathVariable Long courseId,@PathVariable Long studentId) {
		return courseService.addStudentToCourse(studentId,courseId);
	}
	
	
}
