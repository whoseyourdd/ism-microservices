package com.whoseyourdd.ism.classroom.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Grade
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gradeId;

	@Column(name = "grade_level", unique = true)
	private String gradeLevel;

	@OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)	
	private List<Classroom> classrooms;
}
