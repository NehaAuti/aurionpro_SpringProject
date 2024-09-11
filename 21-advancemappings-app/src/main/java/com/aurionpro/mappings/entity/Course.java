package com.aurionpro.mappings.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="courses")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="courseId")
	private int courseId;
	@Column(name="courseName")
	private String courseName;
	@Column(name="duration")
	private int duration;
	@Column(name="fees")
	private double fees;	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="instructorId")
	private Instructor instructor;

    @ManyToMany
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "courseId"),
        inverseJoinColumns = @JoinColumn(name = "rollnumber")
    )
    @JsonBackReference
    private List<Student> students;

}
