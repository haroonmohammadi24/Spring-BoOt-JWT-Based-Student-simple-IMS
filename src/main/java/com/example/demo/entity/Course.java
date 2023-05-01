package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="course")
public class Course {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "subject",nullable = false)
	private String subject;
	
	@Column(name = "code",nullable = false)
	private String code;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	private List<Grade> grades;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	    name = "course_student",
	    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
	)
	private Set<Student> students;
}
