package com.aurionpro.mappings.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="courses")
@AllArgsConstructor    //parameterized constructor
@NoArgsConstructor     //default constructor
@Data
public class Course {

    @Id
    @Column(name="courseId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Column(name="courseName")
    private String courseName;

    @Column(name="duration")
    private String duration;

    @Column(name="fees")
    private String fees;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="instructorId") 
    private Instructor instructor;
}