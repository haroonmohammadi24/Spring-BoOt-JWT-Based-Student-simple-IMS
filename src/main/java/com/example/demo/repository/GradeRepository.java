package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Grade;


public interface GradeRepository extends JpaRepository<Grade, Long> {
	
	 List<Grade> findByStudentIdAndCourseId(Long studentId,Long CourseId); 

}
