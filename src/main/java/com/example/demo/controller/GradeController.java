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

import com.example.demo.entity.Grade;
import com.example.demo.service.GradeService;

@RestController
@RequestMapping("/api/")
public class GradeController {
	@Autowired
	private GradeService gradeService;
	
	@PostMapping("student/{studentId}/grade/{courseId}")
	public Grade saveGrade(@RequestBody Grade grade,@PathVariable Long studentId,@PathVariable Long courseId) {
		return gradeService.saveTheGrade(grade,studentId,courseId);
	}
//	@GetMapping("/grade")
//	public List<Grade> getAllGrade() {
//		return gradeService.getGrade();
//	}
	

	@GetMapping("/student/{studentId}/course/{CourseId}")
	public List<Grade> getAllGrade(@PathVariable Long studentId,@PathVariable Long CourseId) {
		return gradeService.getAllGrade(studentId,CourseId);
	}
	
	@DeleteMapping("/grade/{id}")
	public Grade deleteOneGrade(@PathVariable Long id) {
		return gradeService.deleteOneGrade(id);
	}
	
	@PutMapping("/grade/{id}")
	public Grade updateOneGrade(@PathVariable Long id,@RequestBody Grade grade) {
		return gradeService.updateOneGrade(id,grade);
	}

}
