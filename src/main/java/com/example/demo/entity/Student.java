package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="student")
public class Student {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name = "birth_day")
	private LocalDate birthDay;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<Grade> grades;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Course> course;
}
